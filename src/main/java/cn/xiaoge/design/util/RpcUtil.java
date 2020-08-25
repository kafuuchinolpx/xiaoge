package cn.xiaoge.design.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResponseErrorHandler;

import javax.net.ssl.*;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;

@Slf4j
public class RpcUtil {
    private RpcUtil(){
    }
    public static void main(String[] args) throws Exception {
        //webservice 1
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ser:mobileCode", "15685608148");
        jsonObject.put("ser:userID", "");
        System.out.println(webserviceInvoke("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx", jsonObject, "getMobileCodeInfo", "http://WebXml.com.cn/"));
        jsonObject.clear();

        //webservice 2
        jsonObject.put("param", "{}");
        System.out.println(webserviceInvoke("http://220.197.219.92:5555/services/RestUserService", jsonObject, "login", "http://service.rest2.cms.jeecms.com/"));
        jsonObject.clear();

        //post
        jsonObject.put("page","1");
        jsonObject.put("size","1");
        System.out.println(httpInvoke("http://gzzw.gzegn.gov.cn:83/unifiedquery/group/findAll", jsonObject, null, HttpMethod.POST, false, JSONObject.class));
        jsonObject.clear();

        //get
        jsonObject.put("value", "123");
        System.out.println(httpInvoke("https://gzzw.gzegn.gov.cn:84/LcItemConfig/LcUser/getUserInfo?value={value}", jsonObject, null, HttpMethod.GET, false, JSONObject.class));
    }

    /**
     * 调用http请求
     * @param url    路径
     * @param data   参数数据
     * @param header 请求头数据
     * @param method 方法
     * @param isRaw  是否是原生
     * @return 返回的对象
     */
    public static <T> T httpInvoke(
            String url,
            JSONObject data,
            JSONObject header,
            HttpMethod method,
            boolean isRaw,
            Class<T> c) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10000);
        // 设置超时
        requestFactory.setReadTimeout(10000);
        RestTemplate restTemplate;
        if (url.startsWith("https://")) {
            restTemplate = new RestTemplate(new HttpsClientRequestFactory());
        }else {
            restTemplate= new RestTemplate(requestFactory);
        }
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            //调用出现错误时捕获异常
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                // 返回false表示不管response的status是多少都返回没有错(就不进入handleError)
                // 这里可以自己定义那些status code你认为是可以抛Error
                return response.getRawStatusCode() == 200 ? false : true;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                // 这里面可以实现你自己遇到了Error进行合理的处理
                log.info("resultInfo:{}", response.toString());
                log.info("statusCode:{}", response.getStatusCode().name());
                log.info("statusCodeValue:{}", response.getRawStatusCode());
            }
        });
        HttpHeaders headers = new HttpHeaders();
        //设置请求头
        if (header != null) {
            header.forEach((k,v)->{
                headers.set(k, v.toString());
            });
        }
        final HttpEntity requestEntity;
        if (isRaw) {
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            requestEntity = new HttpEntity<>(data.toJSONString(), headers);
        } else {
            //设置参数
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            if (data != null) {
                data.forEach((k,v)->{
                    params.add(k, v.toString());
                });
            }
            requestEntity = new HttpEntity<>(params, headers);
        }
        ResponseEntity<T> exchange = restTemplate.exchange(url, method, requestEntity, c, data == null ? new JSONObject() : data);
        return exchange.getBody();
    }

    /**
     * WebService调用
     *
     * @param url             路径
     * @param data            数据
     * @param method          方法
     * @param targetNamespace 在wsdl中的targetNamespace
     * @throws Exception
     * @return响应对象
     */
    public static JSONObject webserviceInvoke(
            String url,
            JSONObject data,
            String method,
            String targetNamespace) throws Exception {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10000);
        // 设置超时
        requestFactory.setReadTimeout(10000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            //调用出现错误时捕获异常
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                // 返回false表示不管response的status是多少都返回没有错(就不进入handleError)
                return response.getRawStatusCode() == 200 ? false : true;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                // 这里面可以实现你自己遇到了Error进行合理的处理
                log.info("resultInfo:{}", response.toString());
                log.info("statusCode:{}", response.getStatusCode().name());
                log.info("statusCodeValue:{}", response.getRawStatusCode());
            }
        });
        //拼接方法和参数
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<ser:" + method + ">");
        data.forEach((k, v) -> stringBuffer.append("<" + k + ">" + v + "</" + k + ">"));
        stringBuffer.append("</ser:" + method + ">");
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"targetNamespace\"><soapenv:Header/><soapenv:Body>data</soapenv:Body></soapenv:Envelope>";
        String allXml = xml.replaceAll("targetNamespace", targetNamespace).replaceAll("data", stringBuffer.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/xml;charset=UTF-8"));
        HttpEntity<String> requestEntity = new HttpEntity<>(allXml, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return XmlJsonUtil.xml2Json(exchange.getBody(), null);
    }

}

/**
 * 用于https需要加密时
 */
class HttpsClientRequestFactory extends SimpleClientHttpRequestFactory {
    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
        try {
            if (!(connection instanceof HttpsURLConnection)) {
                throw new RuntimeException("An instance of HttpsURLConnection is expected");
            }

            HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;

            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            httpsConnection.setSSLSocketFactory(new MyCustomSslSocketFactory(sslContext.getSocketFactory()));

            httpsConnection.setHostnameVerifier((s, sslSession) -> true);

            super.prepareConnection(httpsConnection, httpMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static class MyCustomSslSocketFactory extends SSLSocketFactory {

        private final SSLSocketFactory delegate;

        public MyCustomSslSocketFactory(SSLSocketFactory delegate) {
            this.delegate = delegate;
        }

        @Override
        public String[] getDefaultCipherSuites() {
            return delegate.getDefaultCipherSuites();
        }

        @Override
        public String[] getSupportedCipherSuites() {
            return delegate.getSupportedCipherSuites();
        }

        @Override
        public Socket createSocket(final Socket socket, final String host, final int port, final boolean autoClose) throws IOException {
            final Socket underlyingSocket = delegate.createSocket(socket, host, port, autoClose);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(final String host, final int port) throws IOException {
            final Socket underlyingSocket = delegate.createSocket(host, port);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(final String host, final int port, final InetAddress localAddress, final int localPort) throws
                IOException {
            final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(final InetAddress host, final int port) throws IOException {
            final Socket underlyingSocket = delegate.createSocket(host, port);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(final InetAddress host, final int port, final InetAddress localAddress, final int localPort) throws
                IOException {
            final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
            return overrideProtocol(underlyingSocket);
        }

        private Socket overrideProtocol(final Socket socket) {
            if (!(socket instanceof SSLSocket)) {
                throw new RuntimeException("An instance of SSLSocket is expected");
            }
            ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1"});
            return socket;
        }
    }
}

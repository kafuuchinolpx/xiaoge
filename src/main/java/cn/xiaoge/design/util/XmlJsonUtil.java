package cn.xiaoge.design.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;
import java.util.*;

public class XmlJsonUtil {
    private XmlJsonUtil(){
    }
    /**
    * json转xml
    * @param map
    * @return
    */
    public static String json2xml(JSONObject map) {
        return iterateJson(map, new StringBuffer());
    }


    /**
    * 递归xml节点
    * @param map json对象
    * @param sb 递归字符串
    * @return xml字符串
    */
    private static String iterateJson(Map map, StringBuffer sb) {
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value){
                value = "";
            }
            if (value instanceof JSONArray) {
                JSONArray list = (JSONArray) map.get(key);
                if (list.get(0) instanceof JSONObject) {
                    for (int i = 0; i < list.size(); i++) {
                        sb.append("<" + key + " type=\"" + value.getClass().getSimpleName() + "\">");
                        if (list.get(i) instanceof JSONObject) {
                            Map hm = (Map) list.get(i);
                            iterateJson(hm, sb);
                        }
                        sb.append("</" + key + ">");
                    }
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        sb.append("<" + key + " type=\"" + value.getClass().getSimpleName() + "\">" + list.get(i) + "</" + key + ">");
                    }
                }
            } else if (value instanceof JSONObject) {
                sb.append("<" + key + " type=\"" + value.getClass().getSimpleName() + "\">");
                iterateJson((Map) value, sb);
                sb.append("</" + key + ">");
            } else {
                sb.append("<" + key + " type=\"" + value.getClass().getSimpleName() + "\">" + value + "</" + key + ">");
            }
        }
        return sb.toString();
    }
    /**
    * xml转json
    * @param xml xml内容
    * @return json对象
    * @throws Exception
    */
    public static JSONObject xml2Json(String xml) throws Exception {
        return xml2Json(xml,null);
    }


    /**
    * xml转json
    * @param xml
    * @param list 为xml中为数组的节点名 防止当节点的数量为1时转换为json对象而非json数组 可以为空
    * @return json对象
    * @throws Exception
    */
    public static JSONObject xml2Json(String xml, List<String> list) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Document document;
        try {
            document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            iterateNodes(root, jsonObject, list);
            return jsonObject;
        } catch (Exception e) {
            document = DocumentHelper.parseText("<xml>" + xml + "</xml>");
            Element root = document.getRootElement();
            iterateNodes(root, jsonObject, list);
            return jsonObject.getJSONObject("xml");
        }
    }

    /**
    * 递归xml节点
    * @param node 节点
    * @param json json对象
    * @param list 必须以数组方式保存的键
    */
    private static void iterateNodes(Element node, JSONObject json, List<String> list) {
        // 获取当前元素的名称
        String nodeName = node.getName();
        String type = node.attributeValue("type");
        // 判断已遍历的JSON中是否已经有了该元素的名称
        if (json.containsKey(nodeName)) {
            // 该元素在同级下有多个
            Object object = json.get(nodeName);
            JSONArray array;
            if (object instanceof JSONArray) {
                array = (JSONArray) object;
            } else {
                array = new JSONArray();
                array.add(object);
            }
            // 获取该元素下所有子元素
            List<Element> listElement = node.elements();
            if (listElement.isEmpty()) {
                // 该元素无子元素，获取元素的值
                String nodeValue = node.getTextTrim();
                array.add(nodeValue);
                json.put(nodeName, array);
                return;
            }
            // 有子元素
            JSONObject newJson = new JSONObject();
            // 遍历所有子元素
            for (Element e : listElement) {
                // 递归
                iterateNodes(e, newJson, list);
            }
            array.add(newJson);
            json.put(nodeName, array);
            return;
        }
        // 该元素同级下第一次遍历
        // 获取该元素下所有子元素
        List<Element> listElement = node.elements();
        if (listElement.isEmpty()) {
            // 该元素无子元素，获取元素的值
            String nodeValue = node.getTextTrim();
            if (StringUtils.isEmpty(type)){
                json.put(nodeName, nodeValue);
                return;
            }
            if ("Boolean".equals(type)) {
                json.put(nodeName, TypeUtils.castToBoolean(nodeValue));
            } else if("Integer".equals(type)){
                json.put(nodeName, TypeUtils.castToInt(nodeValue));
            }else if("Double".equals(type)){
                json.put(nodeName, TypeUtils.castToDouble(nodeValue));
            }else if("Byte".equals(type)){
                json.put(nodeName, TypeUtils.castToByte(nodeValue));
            }else if("JSONObject".equals(type)){
                json.put(nodeName, new JSONObject());
            }else {
                json.put(nodeName, nodeValue);
            }
            return;
        }
        // 有子节点，新建一个JSONObject来存储该节点下子节点的值
        JSONObject object = new JSONObject();

        // 遍历所有一级子节点
        for (Element e : listElement) {
            // 递归
            iterateNodes(e, object, list);
        }
        if (list!=null && list.contains(nodeName)) {
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(object);
            json.put(nodeName, jsonArray);
        } else {
            json.put(nodeName, object);
        }
        return;
    }

    public static void main(String[] args) throws Exception {
        String xmlStr = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "   <soap:Body>\n" +
                "      <getMobileCodeInfoResponse xmlns=\"http://WebXml.com.cn/\">\n" +
                "         <getMobileCodeInfoResult>15685608148：贵州 贵阳 贵州联通GSM卡</getMobileCodeInfoResult>\n" +
                "      </getMobileCodeInfoResponse>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";
        List<String> strings = Arrays.asList("datarow", "sourceid");
        JSONObject jsonObject = xml2Json(xmlStr, strings);
        System.out.println(jsonObject);
        String s = json2xml(jsonObject);
        System.out.println(s);
        System.out.println(xml2Json(s,strings));

    }
}
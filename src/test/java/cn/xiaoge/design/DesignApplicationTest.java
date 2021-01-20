package cn.xiaoge.design;

import cn.xiaoge.design.entity.QiqiTest;
import cn.xiaoge.design.entity.QiqiTesttidid;
import cn.xiaoge.design.service.QiqiTesttididService;
import cn.xiaoge.design.service.impl.AlcoholTemplateServiceImpl;
import cn.xiaoge.design.testClass.HttpRequest;
import cn.xiaoge.design.util.JacksonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DesignApplicationTest {
    @Autowired
    private AlcoholTemplateServiceImpl alcoholTemplateService;

    @Autowired
    QiqiTesttididService qiqiTesttididService;

    @Test
    public void contextLoads() {

        /**
         * 发送 GET 请求
         */
//        String s = HttpRequest.sendGet("https://api.qiseyu.cn/api//Editor/GetTemplateInfo", "id=1917348&tdsourcetag=s_pcqq_aiomsg&Cardid=&dd=1610760189000");
        /**
         * 转换为JSON数组并且提取其中的属性
         * data 提取获取到参数中的data属性
         * thumbnail 提取data中的thumbnail属性
         */
//        JSONObject jsonObject = JSON.parseObject(s);
//        JSONArray array = jsonObject.getJSONArray("data");
        /**
         *使用循环存入数据库
         */
//        for (Object o : array) {
//            String string = o.toString();
//            QiqiTest qiqiTest = JacksonUtil.readValue(string, QiqiTest.class);
//            System.out.println(qiqiTest);
////            qiqiTesttididService.add(qiqiTesttidid);
//        }


        /**
         * 发送 POST 请求
         */
        for (; ; ) {
            String s = HttpRequest.sendPost("https://api.qiseyu.cn/api/Member/TokenLogin", null);
            System.out.println(s);
        }

    }

}

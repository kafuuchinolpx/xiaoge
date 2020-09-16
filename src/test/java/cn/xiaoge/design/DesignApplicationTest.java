package cn.xiaoge.design;

import cn.xiaoge.design.service.impl.AlcoholTemplateServiceImpl;
import com.alibaba.fastjson.JSON;
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

    @Test
    public void contextLoads() {

    }

}

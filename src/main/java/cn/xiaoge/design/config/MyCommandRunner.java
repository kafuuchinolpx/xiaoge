package cn.xiaoge.design.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import cn.xiaoge.design.util.RedisUtil;
import cn.xiaoge.design.repository.SystemUserRepository;
import cn.xiaoge.design.entity.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.util.DigestUtils;

/**
* 项目启动完成后自动执行
*/
@Component
public class MyCommandRunner implements CommandLineRunner {
    @Autowired
    private SystemUserRepository systemUserRepository;


    @Override
    public void run(String... args) {
        System.out.println("启动完成，正在清除Redis缓存。。。");
        RedisUtil.deleteAll();


//检查管理员是否存在
        List<SystemUser> systemUsers = systemUserRepository.findByName("admin");
        if (systemUsers != null && systemUsers.size() == 0) {
            SystemUser systemUser=new SystemUser();
            systemUser.setName("admin");
            systemUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
            systemUserRepository.save(systemUser);
        }
    }
}

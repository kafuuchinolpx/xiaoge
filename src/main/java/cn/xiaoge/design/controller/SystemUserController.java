package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.SystemUser;
import cn.xiaoge.design.service.SystemUserService;
import cn.xiaoge.design.entity.vo.ReturnBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;


@Slf4j
@RestController
@CrossOrigin
@Api(tags = {"09.系统用户接口"})
@RequestMapping("systemUser")
@Validated
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @PostMapping("add")
    @ApiOperation(value = "系统用户添加")
    public ReturnBean add(
            @Size(max = 50) String password,
            @Size(max = 11) String name) throws Exception {
        SystemUser systemUser = new SystemUser();
        systemUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        systemUser.setName(name);
        systemUserService.add(systemUser);
        log.info("添加系统用户:{}", systemUser);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "系统用户修改不为空的属性")
    @PostMapping("update")
    public ReturnBean update(
            Integer id,
            String ids,
            @Size(max = 50)  String password,
            @Size(max = 11)  String name) throws Exception {
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                SystemUser systemUser = new SystemUser();
                systemUser.setId(Integer.parseInt(s));
                systemUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
                systemUser.setName(name);
                systemUserService.updateNotNull(systemUser);
                log.info("修改系统用户:{}", systemUser);
            }
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }
        if (id!=null) {
            SystemUser systemUser = new SystemUser();
            systemUser.setId(id);
            systemUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            systemUser.setName(name);
            systemUserService.updateNotNull(systemUser);
            log.info("修改系统用户:{}", systemUser);
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR,"id和ids必须传一个");

    }


    @PostMapping("delete")
    @ApiOperation(value = "系统用户根据id删除")
    public ReturnBean delete(Integer id,String ids) {
        //单个删除
        if (id!=null){
            systemUserService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)){
            String[] split = ids.split(",");
            for (String s : split) {
                systemUserService.deleteById(Integer.parseInt(s));
            }
        }
        log.info("删除系统用户:id:{},ids:{}", id, ids);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "系统用户分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(
            @RequestParam(defaultValue = "1") Integer page,
            String searchKey,
            String order,
            @Max(value=10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, systemUserService.findAll(page,searchKey,order,size));
    }




    @ApiOperation(value = "系统用户根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, systemUserService.findById(id));
    }

}

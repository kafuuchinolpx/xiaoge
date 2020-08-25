package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.AppUser;
import cn.xiaoge.design.service.AppUserService;
import cn.xiaoge.design.entity.vo.ReturnBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
@RestController
@CrossOrigin
@Api(tags = {"07.应用用户接口"})
@RequestMapping("appUser")
@Validated
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("add")
    @ApiOperation(value = "应用用户添加")
    public ReturnBean add(
            @Size(max = 11) String name,
            @Size(max = 11) String password,
            @Size(max = 11) String telephone,
            @Size(max = 11) String wxCode,
             String address) throws Exception {
        AppUser appUser = new AppUser();
        appUser.setName(name);
        appUser.setPassword(password);
        appUser.setTelephone(telephone);
        appUser.setWxCode(wxCode);
        appUser.setAddress(address);
        appUserService.add(appUser);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "应用用户修改不为空的属性")
    @PostMapping("update")
    public ReturnBean update(
            Integer id,
            String ids,
            @Size(max = 11)  String name,
            @Size(max = 11)  String password,
            @Size(max = 11)  String telephone,
            @Size(max = 11)  String wxCode,
              String address) throws Exception {
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                AppUser appUser = new AppUser();
                appUser.setId(Integer.parseInt(s));
                appUser.setName(name);
                appUser.setPassword(password);
                appUser.setTelephone(telephone);
                appUser.setWxCode(wxCode);
                appUser.setAddress(address);
                appUserService.updateNotNull(appUser);
            }
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }
        if (id!=null) {
            AppUser appUser = new AppUser();
            appUser.setId(id);
            appUser.setName(name);
            appUser.setPassword(password);
            appUser.setTelephone(telephone);
            appUser.setWxCode(wxCode);
            appUser.setAddress(address);
            appUserService.updateNotNull(appUser);
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR,"id和ids必须传一个");

    }


    @PostMapping("delete")
    @ApiOperation(value = "应用用户根据id删除")
    public ReturnBean delete(Integer id,String ids) {
        //单个删除
        if (id!=null){
            appUserService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)){
            String[] split = ids.split(",");
            for (String s : split) {
                appUserService.deleteById(Integer.parseInt(s));
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "应用用户分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(
            @RequestParam(defaultValue = "1") Integer page,
            String order,
            @Max(value=10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, appUserService.findAll(page,order,size));
    }




    @ApiOperation(value = "应用用户根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, appUserService.findById(id));
    }



}

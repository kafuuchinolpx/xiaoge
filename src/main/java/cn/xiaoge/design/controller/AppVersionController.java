package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.AppVersion;
import cn.xiaoge.design.service.AppVersionService;
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
@Api(tags = {"08.应用版本接口"})
@RequestMapping("appVersion")
@Validated
public class AppVersionController {

    @Autowired
    private AppVersionService appVersionService;

    @PostMapping("add")
    @ApiOperation(value = "应用版本添加")
    public ReturnBean add(
            @Size(max = 11) String versionName) throws Exception {
        AppVersion appVersion = new AppVersion();
        appVersion.setVersionName(versionName);
        appVersionService.add(appVersion);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "应用版本修改不为空的属性")
    @PostMapping("update")
    public ReturnBean update(
            Integer id,
            String ids,
            @Size(max = 11)  String versionName) throws Exception {
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                AppVersion appVersion = new AppVersion();
                appVersion.setId(Integer.parseInt(s));
                appVersion.setVersionName(versionName);
                appVersionService.updateNotNull(appVersion);
            }
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }
        if (id!=null) {
            AppVersion appVersion = new AppVersion();
            appVersion.setId(id);
            appVersion.setVersionName(versionName);
            appVersionService.updateNotNull(appVersion);
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR,"id和ids必须传一个");

    }


    @PostMapping("delete")
    @ApiOperation(value = "应用版本根据id删除")
    public ReturnBean delete(Integer id,String ids) {
        //单个删除
        if (id!=null){
            appVersionService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)){
            String[] split = ids.split(",");
            for (String s : split) {
                appVersionService.deleteById(Integer.parseInt(s));
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "应用版本分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(
            @RequestParam(defaultValue = "1") Integer page,
            String searchKey,
            String order,
            @Max(value=10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, appVersionService.findAll(page,searchKey,order,size));
    }




    @ApiOperation(value = "应用版本根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, appVersionService.findById(id));
    }



}

package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.Purpose;
import cn.xiaoge.design.service.PurposeService;
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
@Api(tags = {"04.用途接口"})
@RequestMapping("purpose")
@Validated
public class PurposeController {

    @Autowired
    private PurposeService purposeService;

    @PostMapping("add")
    @ApiOperation(value = "用途添加")
    public ReturnBean add(
            @Size(max = 11) String name,
             String remark) throws Exception {
        Purpose purpose = new Purpose();
        purpose.setName(name);
        purpose.setRemark(remark);
        purposeService.add(purpose);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "用途修改不为空的属性")
    @PostMapping("update")
    public ReturnBean update(
            Integer id,
            String ids,
            @Size(max = 11)  String name,
              String remark) throws Exception {
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                Purpose purpose = new Purpose();
                purpose.setId(Integer.parseInt(s));
                purpose.setName(name);
                purpose.setRemark(remark);
                purposeService.updateNotNull(purpose);
            }
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }
        if (id!=null) {
            Purpose purpose = new Purpose();
            purpose.setId(id);
            purpose.setName(name);
            purpose.setRemark(remark);
            purposeService.updateNotNull(purpose);
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR,"id和ids必须传一个");

    }


    @PostMapping("delete")
    @ApiOperation(value = "用途根据id删除")
    public ReturnBean delete(Integer id,String ids) {
        //单个删除
        if (id!=null){
            purposeService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)){
            String[] split = ids.split(",");
            for (String s : split) {
                purposeService.deleteById(Integer.parseInt(s));
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "用途分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(
            @RequestParam(defaultValue = "1") Integer page,
            String searchKey,
            String order,
            @Max(value=10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, purposeService.findAll(page,searchKey,order,size));
    }




    @ApiOperation(value = "用途根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, purposeService.findById(id));
    }



}

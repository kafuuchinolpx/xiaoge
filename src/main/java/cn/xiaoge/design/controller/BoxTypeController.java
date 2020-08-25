package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.BoxType;
import cn.xiaoge.design.service.BoxTypeService;
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
@Api(tags = {"02.盒型接口"})
@RequestMapping("boxType")
@Validated
public class BoxTypeController {

    @Autowired
    private BoxTypeService boxTypeService;

    @PostMapping("add")
    @ApiOperation(value = "盒型添加")
    public ReturnBean add(
            @RequestParam @Size(max = 11) String name,
             String remark) throws Exception {
        BoxType boxType = new BoxType();
        boxType.setName(name);
        boxType.setRemark(remark);
        boxTypeService.add(boxType);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "盒型修改不为空的属性")
    @PostMapping("update")
    public ReturnBean update(
            Integer id,
            String ids,
            @Size(max = 11)  String name,
              String remark) throws Exception {
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                BoxType boxType = new BoxType();
                boxType.setId(Integer.parseInt(s));
                boxType.setName(name);
                boxType.setRemark(remark);
                boxTypeService.updateNotNull(boxType);
            }
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }
        if (id!=null) {
            BoxType boxType = new BoxType();
            boxType.setId(id);
            boxType.setName(name);
            boxType.setRemark(remark);
            boxTypeService.updateNotNull(boxType);
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR,"id和ids必须传一个");

    }


    @PostMapping("delete")
    @ApiOperation(value = "盒型根据id删除")
    public ReturnBean delete(Integer id,String ids) {
        //单个删除
        if (id!=null){
            boxTypeService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)){
            String[] split = ids.split(",");
            for (String s : split) {
                boxTypeService.deleteById(Integer.parseInt(s));
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "盒型分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(
            @RequestParam(defaultValue = "1") Integer page,
            String searchKey,
            String order,
            @Max(value=10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, boxTypeService.findAll(page,searchKey,order,size));
    }




    @ApiOperation(value = "盒型根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, boxTypeService.findById(id));
    }



}

package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.Material;
import cn.xiaoge.design.service.MaterialService;
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
@Api(tags = {"03.材料接口"})
@RequestMapping("material")
@Validated
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping("add")
    @ApiOperation(value = "材料添加")
    public ReturnBean add(
            @Size(max = 11) String name,
             String remark) throws Exception {
        Material material = new Material();
        material.setName(name);
        material.setRemark(remark);
        materialService.add(material);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "材料修改不为空的属性")
    @PostMapping("update")
    public ReturnBean update(
            Integer id,
            String ids,
            @Size(max = 11)  String name,
              String remark) throws Exception {
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                Material material = new Material();
                material.setId(Integer.parseInt(s));
                material.setName(name);
                material.setRemark(remark);
                materialService.updateNotNull(material);
            }
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }
        if (id!=null) {
            Material material = new Material();
            material.setId(id);
            material.setName(name);
            material.setRemark(remark);
            materialService.updateNotNull(material);
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR,"id和ids必须传一个");

    }


    @PostMapping("delete")
    @ApiOperation(value = "材料根据id删除")
    public ReturnBean delete(Integer id,String ids) {
        //单个删除
        if (id!=null){
            materialService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)){
            String[] split = ids.split(",");
            for (String s : split) {
                materialService.deleteById(Integer.parseInt(s));
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "材料分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(
            @RequestParam(defaultValue = "1") Integer page,
            String searchKey,
            String order,
            @Max(value=10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, materialService.findAll(page,searchKey,order,size));
    }




    @ApiOperation(value = "材料根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, materialService.findById(id));
    }



}

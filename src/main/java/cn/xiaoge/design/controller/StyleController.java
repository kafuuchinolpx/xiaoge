package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.Style;
import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.service.StyleService;
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
@Api(tags = {"11.风格接口"})
@RequestMapping("style")
@Validated
public class StyleController {

    @Autowired
    private StyleService styleService;

    @PostMapping("add")
    @ApiOperation(value = "风格添加")
    public ReturnBean add(@Size(max = 11) String name, String remark) throws Exception {
        Style style = new Style();
        style.setName(name);
        style.setRemark(remark);
        styleService.add(style);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "风格修改不为空的属性")
    @PostMapping("update")
    public ReturnBean update(Integer id, String ids, @Size(max = 11) String name, String remark) throws Exception {
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                Style style = new Style();
                style.setId(Integer.parseInt(s));
                style.setName(name);
                style.setRemark(remark);
                styleService.updateNotNull(style);
            }
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }
        if (id != null) {
            Style style = new Style();
            style.setId(id);
            style.setName(name);
            style.setRemark(remark);
            styleService.updateNotNull(style);
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR, "id和ids必须传一个");

    }


    @PostMapping("delete")
    @ApiOperation(value = "风格根据id删除")
    public ReturnBean delete(Integer id, String ids) {
        //单个删除
        if (id != null) {
            styleService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                styleService.deleteById(Integer.parseInt(s));
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "风格分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(@RequestParam(defaultValue = "1") Integer page, String searchKey, String order,
                              @Max(value = 10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, styleService.findAll(page, searchKey, order, size));
    }


    @ApiOperation(value = "风格根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, styleService.findById(id));
    }


}

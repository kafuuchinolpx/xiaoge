package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.AlcoholTemplateGroup;
import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.service.AlcoholTemplateGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@Api(tags = {"13.分组接口"})
@RequestMapping("alcoholTemplateGroup")
@Validated
public class AlcoholTemplateGroupController {
    @Autowired
    private AlcoholTemplateGroupService alcoholTemplateGroupService;

    @PostMapping("add")
    @ApiOperation(value = "分组添加")
    public ReturnBean add(AlcoholTemplateGroup alcoholTemplateGroup) {
        if (alcoholTemplateGroup.getId() == 0) {
            alcoholTemplateGroup.setId(null);
        }
        alcoholTemplateGroupService.add(alcoholTemplateGroup);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @PostMapping("deleteById")
    @ApiOperation(value = "根据id删除分组")
    public ReturnBean deleteById(Integer id) {
        alcoholTemplateGroupService.deleteById(id);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @PostMapping("update")
    @ApiOperation(value = "修改分组")
    public ReturnBean update(AlcoholTemplateGroup alcoholTemplateGroup) {
        alcoholTemplateGroupService.updateNotNull(alcoholTemplateGroup);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @PostMapping("findAll")
    @ApiOperation(value = "查询所有分组")
    public ReturnBean findAll() {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateGroupService.findAll());
    }
}

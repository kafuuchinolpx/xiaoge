package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.WineBoxGroup;
import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.service.WineBoxGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@Api(tags = {"15.分组接口"})
@RequestMapping("wineBoxGroup")
@Validated
public class WineBoxGroupController {
    @Autowired
    private WineBoxGroupService aineBoxGroupService;

    @PostMapping("add")
    @ApiOperation(value = "分组添加")
    public ReturnBean add(WineBoxGroup aineBoxGroup) {
        if (aineBoxGroup.getId() == 0) {
            aineBoxGroup.setId(null);
        }
        aineBoxGroupService.add(aineBoxGroup);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @PostMapping("deleteById")
    @ApiOperation(value = "根据id删除分组")
    public ReturnBean deleteById(Integer id) {
        aineBoxGroupService.deleteById(id);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @PostMapping("update")
    @ApiOperation(value = "修改分组")
    public ReturnBean update(WineBoxGroup aineBoxGroup) {
        aineBoxGroupService.updateNotNull(aineBoxGroup);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @PostMapping("findAll")
    @ApiOperation(value = "查询所有分组")
    public ReturnBean findAll() {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, aineBoxGroupService.findAll());
    }
}

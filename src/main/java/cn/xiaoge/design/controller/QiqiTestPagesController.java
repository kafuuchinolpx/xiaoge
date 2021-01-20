package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.service.QiqiTestPagesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api(tags = {"17.编辑分组后台接口"})
@RequestMapping("qiqiTestPages")
@Validated
public class QiqiTestPagesController {

    @Autowired
    private QiqiTestPagesService qiqiTestPagesService;

    @PostMapping("findAll")
    @ApiOperation(value = "查询所有")
    public ReturnBean findAll() {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, qiqiTestPagesService.findAll());
    }
}

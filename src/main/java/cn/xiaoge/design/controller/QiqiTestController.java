package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.service.QiqiTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api(tags = {"16.编辑模板后台主接口"})
@RequestMapping("localhostqiqiTest")
@Validated
public class QiqiTestController {

    @Autowired
    private QiqiTestService qiqiTestService;

    @GetMapping("findAll")
    @ApiOperation(value = "查询所有")
    public ReturnBean findAll() {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, qiqiTestService.findAll());
    }

    @GetMapping("findById")
    @ApiOperation(value = "id查询")
    public ReturnBean findById(Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, qiqiTestService.findById(id));
    }
}

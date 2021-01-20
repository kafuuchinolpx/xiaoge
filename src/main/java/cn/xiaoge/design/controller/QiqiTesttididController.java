package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.QiqiTesttidid;
import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.service.QiqiTesttididService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api(tags = {"18.编辑模板模板接口"})
@RequestMapping("localhostqiqiTesttidid")
@Validated
public class QiqiTesttididController {

    @Autowired
    private QiqiTesttididService qiqiTesttididService;

    @GetMapping("findAll")
    @ApiOperation(value = "查询所有")
    public ReturnBean findAll() {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, qiqiTesttididService.findAll());
    }

    @PostMapping("add")
    @ApiOperation("添加")
    public ReturnBean add(QiqiTesttidid qiqiTesttidid) {
        System.out.println(qiqiTesttidid);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }
}

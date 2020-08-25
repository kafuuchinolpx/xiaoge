package cn.xiaoge.design.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 19070
 */
@RestController
@CrossOrigin
@Api(tags = {"10.水模板接口"})
@RequestMapping("water")
@Validated
public class WaterController {


}

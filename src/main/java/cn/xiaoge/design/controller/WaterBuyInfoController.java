package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.WaterBuyInfo;
import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.service.WaterBuyInfoService;
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
@Api(tags = {"13.水订单接口"})
@RequestMapping("waterBuyInfo")
@Validated
public class WaterBuyInfoController {

    @Autowired
    private WaterBuyInfoService owaterBuyInfoService;

    @PostMapping("add")
    @ApiOperation(value = "订单添加")
    public ReturnBean add(Integer userId, @Size(max = 11) String payStatus, String info, @Size(max = 11) String file) throws Exception {
        WaterBuyInfo owaterBuyInfo = new WaterBuyInfo();
        owaterBuyInfo.setUserId(userId);
        owaterBuyInfo.setPayStatus(payStatus);
        owaterBuyInfo.setInfo(info);
        owaterBuyInfo.setFile(file);
        owaterBuyInfoService.add(owaterBuyInfo);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "订单修改不为空的属性")
    @PostMapping("update")
    public ReturnBean update(
            Integer id,
            String ids,
            Integer userId,
            @Size(max = 11) String payStatus,
            String info,
            @Size(max = 11) String file
            ) throws Exception {
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                WaterBuyInfo owaterBuyInfo = new WaterBuyInfo();
                owaterBuyInfo.setId(Integer.parseInt(s));
                owaterBuyInfo.setUserId(userId);
                owaterBuyInfo.setPayStatus(payStatus);
                owaterBuyInfo.setInfo(info);
                owaterBuyInfo.setFile(file);
                owaterBuyInfoService.updateNotNull(owaterBuyInfo);
            }
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR, "id和ids必须传一个");

    }


    @PostMapping("delete")
    @ApiOperation(value = "订单根据id删除")
    public ReturnBean delete(Integer id, String ids) {
        //单个删除
        if (id != null) {
            owaterBuyInfoService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                owaterBuyInfoService.deleteById(Integer.parseInt(s));
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "订单分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(
            @RequestParam(defaultValue = "1") Integer page,
            String owaterBuyInfo,
            @Max(value = 10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, owaterBuyInfoService.findAll(page, owaterBuyInfo, size));
    }


    @ApiOperation(value = "订单根据id查询单个")
    @PostMapping("findByIdWithAppUser")
    public ReturnBean findByIdWithAppUser(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, owaterBuyInfoService.findByIdWithAppUser(id));
    }


    @ApiOperation(value = "订单根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, owaterBuyInfoService.findById(id));
    }


}

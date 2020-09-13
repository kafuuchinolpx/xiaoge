package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.QuotationInformation;
import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.service.QuotationInformationService;
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
@Api(tags = {"12.价格接口"})
@RequestMapping("quotationInformation")
@Validated
public class QuotationInformationController {

    @Autowired
    private QuotationInformationService quotationInformationService;

    @PostMapping("add")
    @ApiOperation(value = "报价添加")
    public ReturnBean add(String name, Double price) {
        QuotationInformation quotationInformation = new QuotationInformation();
        quotationInformation.setName(name);
        quotationInformation.setPrice(price);
        quotationInformationService.add(quotationInformation);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "报价修改不为空的属性")
    @PostMapping("update")
    public ReturnBean update(Integer id, String ids,
                             @Size(max = 11) String name, Double price) {
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                QuotationInformation quotationInformation = new QuotationInformation();
                quotationInformation.setId(Integer.parseInt(s));
                quotationInformation.setName(name);
                quotationInformation.setPrice(price);
                quotationInformationService.updateNotNull(quotationInformation);
            }
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }
        if (id != null) {
            QuotationInformation quotationInformation = new QuotationInformation();
            quotationInformation.setId(id);
            quotationInformation.setName(name);
            quotationInformation.setPrice(price);
            quotationInformationService.updateNotNull(quotationInformation);
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR, "id和ids必须传一个");

    }


    @PostMapping("delete")
    @ApiOperation(value = "报价根据id删除")
    public ReturnBean delete(Integer id, String ids) {
        //单个删除
        if (id != null) {
            quotationInformationService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                quotationInformationService.deleteById(Integer.parseInt(s));
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "报价分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(
            @RequestParam(defaultValue = "1") Integer page, String searchKey, String order,
            @Max(value = 10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, quotationInformationService.findAll(page, searchKey, order, size));
    }


    @ApiOperation(value = "报价根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, quotationInformationService.findById(id));
    }


}

package cn.xiaoge.design.controller;
import cn.xiaoge.design.entity.AlcoholTemplate;
import cn.xiaoge.design.service.AlcoholTemplateService;
import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.File;
import java.util.Date;

@RestController
@CrossOrigin
@Api(tags = {"05.酒模板接口"})
@RequestMapping("alcoholTemplate")
@Validated
public class AlcoholTemplateController {

    @Autowired
    private AlcoholTemplateService alcoholTemplateService;

    @Value("${filePath}")
    private String filePath;

    @PostMapping("add")
    @ApiOperation(value = "酒模板添加")
    public ReturnBean add(@Size(max = 50) String name, Integer length, MultipartFile file, Integer groupId, Integer purposeId, Integer styleId,
                          Integer materialId, Integer boxTypeId) throws Exception {
        AlcoholTemplate alcoholTemplate = new AlcoholTemplate();
        alcoholTemplate.setName(name);
        alcoholTemplate.setLength(length);

        if (file != null) {
            String uuid8 = UUIDUtil.getuuid8();
            String fileName = file.getOriginalFilename();
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : null;
            File file1 = new File(filePath + uuid8 + type);
            file.transferTo(file1);
            alcoholTemplate.setImage(uuid8 + type);
        }
        String header1style = "{\"margin\": \"auto\"width\":\"100px\",\"height\":\"280px\",\"transform\":\"rotateX(0deg) rotateY(0deg)\",\"writingMode\":\"tb\",\"fontFamily\":\"李旭科书法\",\"top\":\"410px\",\"left\":\"31px\",\"position\":\"absolute\",\"color\":\"#e9d826\",\"fontSize\":\"25px\"}";
        String header2style = "{\"margin\": \"auto\"width\":\"100px\",\"height\":\"320px\",\"transform\":\"rotateX(0deg) rotateY(0deg)\",\"writingMode\":\"tb\",\"fontFamily\":\"李旭科书法\",\"top\":\"225px\",\"left\":\"250px\",\"position\":\"absolute\",\"color\":\"#dfd826\",\"fontSize\":\"60px\"}";
        alcoholTemplate.setGroupId(groupId);
        alcoholTemplate.setPurposeId(purposeId);
        alcoholTemplate.setStyleId(styleId);
        alcoholTemplate.setMaterialId(materialId);
        alcoholTemplate.setBoxTypeId(boxTypeId);
        alcoholTemplate.setHeader1Style(header1style);
        alcoholTemplate.setHeader2Style(header2style);
        alcoholTemplate.setDeleteState(1);
        alcoholTemplateService.add(alcoholTemplate);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "酒模板修改不为空的属性")
    @PostMapping("update")
    public ReturnBean update(Integer id, @Size(max = 50) String name, Integer length, MultipartFile file,
                             Integer groupId, Integer purposeId, Integer styleId, Integer materialId, Integer boxTypeId) throws Exception {

        AlcoholTemplate alcoholTemplate = new AlcoholTemplate();
        alcoholTemplate.setId(id);
        alcoholTemplate.setName(name);
        alcoholTemplate.setLength(length);
        if (file != null) {
            String uuid8 = UUIDUtil.getuuid8();
            String fileName = file.getOriginalFilename();
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : null;
            File file1 = new File(filePath + uuid8 + type);
            file.transferTo(file1);
            alcoholTemplate.setImage(uuid8 + type);
        }
        alcoholTemplate.setGroupId(groupId);
        alcoholTemplate.setPurposeId(purposeId);
        alcoholTemplate.setStyleId(styleId);
        alcoholTemplate.setMaterialId(materialId);
        alcoholTemplate.setBoxTypeId(boxTypeId);
        alcoholTemplateService.updateNotNull(alcoholTemplate);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "酒模板修改不为空的属性")
    @PostMapping("updateStyle")
    public ReturnBean updateStyle(Integer id, String headerText, String header1Style, String header2Style

    ) throws Exception {

        AlcoholTemplate alcoholTemplate = new AlcoholTemplate();

        alcoholTemplate.setId(id);
        if (!StringUtils.isEmpty(header1Style)) {
            alcoholTemplate.setHeader1Style(header1Style);
        }
        if (!StringUtils.isEmpty(header2Style)) {
            alcoholTemplate.setHeader2Style(header2Style);
        }
        alcoholTemplate.setRemark(headerText);
        alcoholTemplateService.updateNotNull(alcoholTemplate);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }


    @PostMapping("delete")
    @ApiOperation(value = "酒模板根据id删除")
    public ReturnBean delete(Integer id, String ids) {
        //单个删除
        if (id != null) {
            AlcoholTemplate byId = alcoholTemplateService.findById(id);
            byId.setDeleteState(0);
            byId.setDeleteTime(new Date());
            alcoholTemplateService.add(byId);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                AlcoholTemplate byId = alcoholTemplateService.findById(Integer.parseInt(s));
                byId.setDeleteState(0);
                byId.setDeleteTime(new Date());
                alcoholTemplateService.add(byId);
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }


    @PostMapping("recommend")
    @ApiOperation(value = "添加或取消推荐")
    public ReturnBean recommend(Integer id) {
        System.out.println(id);
        AlcoholTemplate byId = alcoholTemplateService.findById(id);
        if (byId.getRecommend() == 0) {
            byId.setRecommend(1);
            alcoholTemplateService.add(byId);
        } else if (byId.getRecommend() == 1) {
            byId.setRecommend(0);
            alcoholTemplateService.add(byId);
        } else {
            return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR);
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }


    @PostMapping("recycleReduction")
    @ApiOperation(value = "回收站根据id还原")
    public ReturnBean recycleReduction(Integer id, String ids) {
        //单个删除
        if (id != null) {
            AlcoholTemplate byId = alcoholTemplateService.findById(id);
            byId.setDeleteState(1);
            byId.setDeleteTime(new Date());
            alcoholTemplateService.add(byId);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                AlcoholTemplate byId = alcoholTemplateService.findById(Integer.parseInt(s));
                byId.setDeleteState(1);
                byId.setDeleteTime(new Date());
                alcoholTemplateService.add(byId);
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @PostMapping("recycleDelete")
    @ApiOperation(value = "酒模板回收站根据id删除")
    public ReturnBean recycleDelete(Integer id, String ids) {
        //单个删除
        if (id != null) {
            alcoholTemplateService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                alcoholTemplateService.deleteById(Integer.parseInt(s));
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @PostMapping("recycleBin")
    @ApiOperation(value = "后台验证回收站密码")
    public ReturnBean recycleBin(String account) {
        if ("xgbzkj123123".equals(account)) {
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        } else {
            return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR);
        }
    }

    @ApiOperation(value = "回收站查询")
    @PostMapping("findAllByDelete")
    public ReturnBean findAllByDelete() {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateService.findAll());
    }


    @ApiOperation(value = "酒模板分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(@RequestParam(defaultValue = "1") Integer page, String order, @Max(value = 10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateService.findAll(page, order, size));
    }

    @ApiOperation(value = "酒模板根据id查询单个")
    @PostMapping("findByIdWithPurpose")
    public ReturnBean findByIdWithPurpose(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateService.findByIdWithPurpose(id));
    }

    @ApiOperation(value = "酒模板根据id查询单个")
    @PostMapping("findByIdWithMaterial")
    public ReturnBean findByIdWithMaterial(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateService.findByIdWithMaterial(id));
    }

    @ApiOperation(value = "酒模板根据id查询单个")
    @PostMapping("findByIdWithBoxType")
    public ReturnBean findByIdWithBoxType(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateService.findByIdWithBoxType(id));
    }


    @ApiOperation(value = "酒模板根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateService.findById(id));
    }


}

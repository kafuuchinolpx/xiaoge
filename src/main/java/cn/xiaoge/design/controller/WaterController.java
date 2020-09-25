package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.Water;
import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.service.WaterService;
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
import java.util.List;
import java.util.Optional;

/**
 * @author 19070
 */
@RestController
@CrossOrigin
@Api(tags = {"10.水模板接口"})
@RequestMapping("water")
@Validated
public class WaterController {
    @Autowired
    WaterService waterService;

    @Value("${filePath}")
    private String filePath;

    @PostMapping("add")
    @ApiOperation(value = "水模板添加")
    public ReturnBean add(Integer id, @Size(max = 50) String name, Integer length, MultipartFile file, Integer purposeId) throws Exception {
        Water water = new Water();
        if (null != id && !"".equals(id)) {
            water.setId(id);
        }
        water.setName(name);
        water.setLength(length);
        if (file != null) {
            String uuid8 = UUIDUtil.getUUID8();
            String fileName = file.getOriginalFilename();
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : null;
            File file1 = new File(filePath + uuid8 + type);
            file.transferTo(file1);
            water.setImage(uuid8 + type);
        }
        water.setPurposeId(purposeId);
        waterService.insert(water);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @PostMapping("delete")
    @ApiOperation(value = "水模板根据id删除")
    public ReturnBean delete(Integer id, String ids) {
        //单个删除
        if (id != null) {
            waterService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                waterService.deleteById(Integer.parseInt(s));
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "水模板修改不为空的属性")
    @PostMapping("updateStyle")
    public ReturnBean updateStyle(Integer id, String header2Text, String headerStyle, String bodyStyle) throws Exception {
        Water water = new Water();
        water.setId(id);
        if (!StringUtils.isEmpty(headerStyle)) {
            water.setHeaderStyle(headerStyle);
        }
        if (!StringUtils.isEmpty(bodyStyle)) {
            water.setBodyStyle(bodyStyle);
        }
        if (!StringUtils.isEmpty(header2Text)) {
            water.setRemark(header2Text);
        }
        waterService.updateNotNull(water);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }


    @ApiOperation(value = "水模板分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(@RequestParam(defaultValue = "1") Integer page, String order,
                              @Max(value = 10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, waterService.findAll(page, order, size));
    }

    @ApiOperation(value = "水模板根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, waterService.findById(id));
    }
}

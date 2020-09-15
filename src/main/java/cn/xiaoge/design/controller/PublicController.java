package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.*;
import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.service.*;
import cn.xiaoge.design.util.UUIDUtil;
import cn.xiaoge.design.util.VerifyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.File;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = {"01.公共接口"})
public class PublicController {

    @Autowired
    private SystemUserService systemUserService;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public ReturnBean login(
            @RequestParam String loginName,
            @RequestParam String verify,
            @RequestParam String loginPassword, HttpSession session) {
        if (session.getAttribute("verifyCode") != null && session.getAttribute("verifyCode").equals(verify.toLowerCase())) {
            SystemUser user = systemUserService.login(loginName, loginPassword);
            if (user != null) {
                session.setAttribute("loginUser", user);
                return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
            }
            return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR, "用户名密码错误");
        }
        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR, "验证码错误");
    }

    @Autowired
    private AppUserService userService;

    @ApiOperation(value = "App登录")
    @PostMapping("app/login")
    public ReturnBean appLogin(@RequestParam String telephone, @RequestParam String password) {
        AppUser user = userService.findByTelephoneAndPassword(telephone, password);
        if (user != null) {
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, user);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR, "验证码错误");
    }

    @ApiOperation(value = "获取登录用户信息")
    @PostMapping("userInfo")
    public ReturnBean userInfo(HttpSession session) {
        Object loginUser = session.getAttribute("loginUser");
        return loginUser == null ? ReturnBean.of(ReturnBean.AnswerCode.NOT_LOGIN) : ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, loginUser);
    }

    @ApiOperation(value = "退出")
    @PostMapping("logout")
    public ReturnBean logout(HttpSession session) {
        session.invalidate();
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, "退出成功");
    }

    @ApiOperation(value = "获取验证码")
    @GetMapping("verify")
    public void verify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        VerifyUtil.getRandcode(request, response);
    }

    @Autowired
    BoxTypeService boxTypeService;

    @ApiOperation(value = "app盒型分页查询全部")
    @PostMapping("app/boxType/findAll")
    public ReturnBean boxTypeFindAll(
            @RequestParam(defaultValue = "1") Integer page,
            String searchKey,
            String order,
            @Max(value = 10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, boxTypeService.findAll(page, searchKey, order, size));
    }

    @Autowired
    private MaterialService materialService;

    @ApiOperation(value = "材料分页查询全部")
    @PostMapping("app/material/findAll")
    public ReturnBean materialFindAll(
            @RequestParam(defaultValue = "1") Integer page,
            String searchKey,
            String order,
            @Max(value = 10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, materialService.findAll(page, searchKey, order, size));
    }

    @Autowired
    private PurposeService purposeService;

    @ApiOperation(value = "用途分页查询全部")
    @PostMapping("app/purpose/findAll")
    public ReturnBean purposeFindAll(
            @RequestParam(defaultValue = "1") Integer page,
            String searchKey,
            String order,
            @Max(value = 10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, purposeService.findAll(page, searchKey, order, size));
    }

    @Autowired
    private StyleService styleService;

    @ApiOperation(value = "用途分页查询全部")
    @PostMapping("app/style/findAll")
    public ReturnBean styleFindAll(@RequestParam(defaultValue = "1") Integer page, String searchKey, String order,
                                   @Max(value = 10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, styleService.findAll(page, searchKey, order, size));
    }

    @Autowired
    private AlcoholTemplateService alcoholTemplateService;

    @ApiOperation(value = "app根据boxtypeId")
    @PostMapping("app/alcoholTemplate/findAll")
    public ReturnBean findByIdWithBoxType(@RequestParam(defaultValue = "1") Integer boxTypeId, @RequestParam(defaultValue = "1") Integer materialId,
                                          @RequestParam(defaultValue = "1") Integer styleId, String alcoholName) {
        if (materialId == 0 && styleId == 0) {
            List<AlcoholTemplate> list = alcoholTemplateService.findByBoxTypeIdAndLengthGreaterThan(boxTypeId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else {
            List<AlcoholTemplate> list = alcoholTemplateService.findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(boxTypeId, materialId, styleId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        }
    }

    @ApiOperation(value = "app根据waterPurpose")
    @PostMapping("app/water/findAll")
    public ReturnBean findByIdWithPurpose(@RequestParam(defaultValue = "1") Integer purposeId, String waterName) {
        List<Water> list = waterService.findByPurposeIdAndLengthGreaterThan(purposeId, waterName.length());
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
    }

    @ApiOperation(value = "酒模板根据id查询单个")
    @PostMapping("app/alcoholTemplate/findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateService.findById(id));
    }

    @Autowired
    AppUserService appUserService;

    @ApiOperation(value = "app用户注册")
    @PostMapping("user/zhuChe")
    public ReturnBean zhuChe(AppUser appUser) {
        appUserService.add(appUser);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @Autowired
    private OrderService orderService;
    @Value("${filePath}")
    private String filePath;

    @PostMapping("app/order/add")
    @ApiOperation(value = "订单添加")
    public ReturnBean add(Integer userId, @RequestParam(defaultValue = "'未支付'") String payStatus, String boxId, String boxName,
                          String info, String wineParameters, MultipartFile file1, MultipartFile file2) throws Exception {
        Order order = new Order();
        order.setUserId(userId);
        order.setPayStatus(payStatus);
        order.setInfo(info + "boxId:" + boxId + "name:" + boxName);
        order.setWineParameters(wineParameters);
        if (file1 != null && !file1.isEmpty()) {
            String uuid8 = UUIDUtil.getUUID8();
            String fileName = file1.getOriginalFilename();
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : null;
            File file = new File(filePath + uuid8 + type);
            file1.transferTo(file);
            order.setFile1(uuid8 + type);
        }
        if (file2 != null && !file2.isEmpty()) {
            String uuid8 = UUIDUtil.getUUID8();
            String fileName = file2.getOriginalFilename();
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : null;
            File file = new File(filePath + uuid8 + type);
            file2.transferTo(file);
            order.setFile2(uuid8 + type);
        }
        orderService.add(order);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @Autowired
    WaterService waterService;

    @ApiOperation("水订制分割查询")
    @PostMapping({"app/water/fengGeChaXun"})
    public ReturnBean fengGeChaXun() {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, waterService.findAll());
    }

}

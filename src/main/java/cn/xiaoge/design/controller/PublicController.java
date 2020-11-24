package cn.xiaoge.design.controller;

import cn.xiaoge.design.entity.*;
import cn.xiaoge.design.entity.vo.ReturnBean;
import cn.xiaoge.design.service.*;
import cn.xiaoge.design.util.UUIDUtil;
import cn.xiaoge.design.util.VerifyUtil;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Max;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = {"01.公共接口"})
public class PublicController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private WineBoxService wineBoxService;

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
                                          @RequestParam(defaultValue = "1") Integer styleId, @RequestParam(defaultValue = "1") Integer purposeId, String alcoholName) {
        if (materialId == 0 && styleId == 0 && purposeId == 0) {//全不选择
            List<AlcoholTemplate> list = alcoholTemplateService.findByBoxTypeIdAndLengthGreaterThan(boxTypeId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId == 0 && styleId > 0 && purposeId > 0) {//不选择材料其余选择
            List<AlcoholTemplate> list = alcoholTemplateService.findByBoxTypeIdAndStyleIdAndPurposeIdAndLengthGreaterThan(boxTypeId, styleId, purposeId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId > 0 && styleId == 0 && purposeId > 0) {//不选择用途其余选择
            List<AlcoholTemplate> list = alcoholTemplateService.findByBoxTypeIdAndMaterialIdAndPurposeIdAndLengthGreaterThan(boxTypeId, materialId, purposeId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId > 0 && styleId > 0 && purposeId == 0) {//不选择风格其余选择
            List<AlcoholTemplate> list = alcoholTemplateService.findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(boxTypeId, materialId, styleId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId == 0 && styleId == 0 && purposeId > 0) {//只选择风格
            List<AlcoholTemplate> list = alcoholTemplateService.findByBoxTypeIdAndStyleIdAndLengthGreaterThan(boxTypeId, styleId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId > 0 && styleId == 0 && purposeId == 0) {//只选择材料
            List<AlcoholTemplate> list = alcoholTemplateService.findByBoxTypeIdAndMaterialIdAndLengthGreaterThan(boxTypeId, materialId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId == 0 && styleId > 0 && purposeId == 0) {//只选择用途
            List<AlcoholTemplate> list = alcoholTemplateService.findByBoxTypeIdAndPurposeIdAndLengthGreaterThan(boxTypeId, purposeId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId > 0 && styleId > 0 && purposeId > 0) {//全部选择
            List<AlcoholTemplate> list = alcoholTemplateService.findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(boxTypeId, materialId, styleId, purposeId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "app根据boxtypeId")
    @PostMapping("app/wineBox/findAll")
    public ReturnBean findAll(@RequestParam(defaultValue = "1") Integer boxTypeId, @RequestParam(defaultValue = "1") Integer materialId,
                              @RequestParam(defaultValue = "1") Integer styleId, @RequestParam(defaultValue = "1") Integer purposeId, String alcoholName) {
        if (materialId == 0 && styleId == 0 && purposeId == 0) {//全不选择
            List<WineBox> list = wineBoxService.findByBoxTypeIdAndLengthGreaterThan(boxTypeId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId == 0 && styleId > 0 && purposeId > 0) {//不选择材料其余选择
            List<WineBox> list = wineBoxService.findByBoxTypeIdAndStyleIdAndPurposeIdAndLengthGreaterThan(boxTypeId, styleId, purposeId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId > 0 && styleId == 0 && purposeId > 0) {//不选择用途其余选择
            List<WineBox> list = wineBoxService.findByBoxTypeIdAndMaterialIdAndPurposeIdAndLengthGreaterThan(boxTypeId, materialId, purposeId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId > 0 && styleId > 0 && purposeId == 0) {//不选择风格其余选择
            List<WineBox> list = wineBoxService.findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(boxTypeId, materialId, styleId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId == 0 && styleId == 0 && purposeId > 0) {//只选择风格
            List<WineBox> list = wineBoxService.findByBoxTypeIdAndStyleIdAndLengthGreaterThan(boxTypeId, styleId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId > 0 && styleId == 0 && purposeId == 0) {//只选择材料
            List<WineBox> list = wineBoxService.findByBoxTypeIdAndMaterialIdAndLengthGreaterThan(boxTypeId, materialId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId == 0 && styleId > 0 && purposeId == 0) {//只选择用途
            List<WineBox> list = wineBoxService.findByBoxTypeIdAndPurposeIdAndLengthGreaterThan(boxTypeId, purposeId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        } else if (materialId > 0 && styleId > 0 && purposeId > 0) {//全部选择
            List<WineBox> list = wineBoxService.findByBoxTypeIdAndMaterialIdAndStyleIdAndLengthGreaterThan(boxTypeId, materialId, styleId, purposeId, alcoholName.length());
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }


    @ApiOperation(value = "app根据waterPurpose")
    @PostMapping("app/water/findAll")
    public ReturnBean findByIdWithPurpose(@RequestParam(defaultValue = "1") Integer purposeId, String waterName) {
        List<Water> list = waterService.findByPurposeIdAndLengthGreaterThan(purposeId, waterName.length());
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
    }

    @ApiOperation(value = "酒模板根据id查询单个并且查出子类")
    @PostMapping("app/alcoholTemplate/findByIdAndSon")
    public ReturnBean findByIdAndSon(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateService.findByIdAndSon(id));
    }

    @ApiOperation(value = "用户添加收藏")
    @PostMapping("app/alcoholTemplate/addCollection")
    public ReturnBean addCollection(@RequestParam Integer userId, @RequestParam String collection) {

        AppUser appUser = userService.findById(userId);
        appUser.setCollection(collection);
        userService.add(appUser);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "酒模板根据id查询单个")
    @PostMapping("app/alcoholTemplate/findByIdInfo")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateService.findById(id));
    }


    @ApiOperation(value = "酒模板根据id查询单个")
    @PostMapping("app/alcoholTemplate/findById")
    public ReturnBean alcoholTemplateFindById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateService.findById(id));
    }

    @ApiOperation(value = "酒盒模板根据id查询单个")
    @PostMapping("app/wineBox/findById")
    public ReturnBean wineBoxFindById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, wineBoxService.findById(id));
    }

    @Autowired
    QuotationInformationService quotationInformationService;

    @ApiOperation(value = "报价查询全部")
    @PostMapping("app/quotationInformation/findAll")
    public ReturnBean quotationInformationFindAll() {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, quotationInformationService.findAllApp());
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
            String uuid8 = UUIDUtil.getuuid8();
            String fileName = file1.getOriginalFilename();
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : null;
            File file = new File(filePath + uuid8 + type);
            file1.transferTo(file);
            order.setFile1(uuid8 + type);
        }
        if (file2 != null && !file2.isEmpty()) {
            String uuid8 = UUIDUtil.getuuid8();
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
    WaterBuyInfoService waterBuyInfoService;

    @PostMapping("app/waterBuyInfo/add")
    @ApiOperation(value = "水订单添加")
    public ReturnBean addWaterBuyInfo(Integer userId, @RequestParam(defaultValue = "'未支付'") String payStatus, String info,
                                      String selectWaterIndex, String waterName, MultipartFile file, String remark) throws Exception {
        WaterBuyInfo waterBuyInfo = new WaterBuyInfo();
        waterBuyInfo.setUserId(userId);
        waterBuyInfo.setPayStatus(payStatus);
        waterBuyInfo.setInfo(info + "waterId:" + selectWaterIndex + "name:" + waterName);
        waterBuyInfo.setRemark(remark);
        if (file != null && !file.isEmpty()) {
            String uuid8 = UUIDUtil.getuuid8();
            String fileName = file.getOriginalFilename();
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : null;
            File file1 = new File(filePath + uuid8 + type);
            file.transferTo(file1);
            waterBuyInfo.setFile(uuid8 + type);
        }
        waterBuyInfoService.add(waterBuyInfo);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @Autowired
    WaterService waterService;

    @ApiOperation("水订制分割查询")
    @PostMapping({"app/water/fengGeChaXun"})
    public ReturnBean fengGeChaXun() {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, waterService.findAll());
    }

    @ApiOperation("手机app父子查询")
    @PostMapping("app/AlcoholTemplateController/findallByApp")
    public ReturnBean findAllByApp() {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, alcoholTemplateService.findAllApp());
    }

    @ApiOperation(value = "app查询所有")
    @PostMapping("app/alcoholTemplate/getAll")
    public ReturnBean getAll() {
        List<AlcoholTemplate> list = alcoholTemplateService.findAllNotBox();
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
    }

    @ApiOperation(value = "app查询所有")
    @PostMapping("app/alcoholTemplate/getAllByTuijian")
    public ReturnBean getAllByRecommend() {
        List<AlcoholTemplate> list = alcoholTemplateService.getAllByRecommend();
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
    }

    @ApiOperation(value = "用户酒包装收藏查询")
    @PostMapping("app/alcoholTemplate/getUserAlcoholTemplateCollection")
    public ReturnBean getUserAlcoholTemplateCollection(Integer userId, String collection) {

        //可以在中括号内加上任何想要替换的字符
        String regEx = "[\\[\\]]";
        String aa = "";//这里是将特殊字符换为aa字符串,""代表直接去掉
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(collection);//这里把想要替换的字符串传进来
        String newString = m.replaceAll(aa).trim(); //将替换后的字符串存在变量newString中

        if (null != newString) {
            List list = new ArrayList();
            if (!StringUtils.isEmpty(newString)) {
                String[] split = newString.split(",");
                for (String s : split) {
                    AlcoholTemplate byId1 = alcoholTemplateService.findById(Integer.parseInt(s));
                    list.add(byId1);
                }
            }
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @Autowired
    UserAddressService userAddressService;

    @ApiOperation(value = "用户地址存储")
    @PostMapping("app/alcoholTemplate/saveUserAddress")
    public ReturnBean getUserAddress(UserAddress userAddress) {
        List<UserAddress> byUserId = userAddressService.findByUserId(userAddress.getUserId());
        if (null == byUserId) {
            userAddress.setDefaultType(1);
        }
        if (null == userAddress.getDefaultType()) {
            userAddress.setDefaultType(0);
        }
        userAddressService.add(userAddress);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "用户地址查询")
    @PostMapping("app/alcoholTemplate/getUserAddress")
    public ReturnBean getUserAddress(Integer userId) {
        List<UserAddress> list = userAddressService.findByUserId(userId);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
    }

    @ApiOperation(value = "用户地址更改")
    @PostMapping("app/alcoholTemplate/userAddressChange")
    public ReturnBean userAddressChange(Integer userId, Integer defaultAddress) {
        List<UserAddress> byUserId = userAddressService.findByUserId(userId);
        for (UserAddress userAddress : byUserId) {
            if (1 == userAddress.getDefaultType()) {
                userAddress.setDefaultType(0);
            }
        }
        for (UserAddress userAddress : byUserId) {
            if (defaultAddress.equals(userAddress.getId())) {
                userAddress.setDefaultType(1);
            }
        }
        byUserId.forEach(o -> userAddressService.add(o));
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "app根据材料查询所有")
    @PostMapping("app/alcoholTemplate/findAllByMaterialId")
    public ReturnBean findAllByMaterialId(Integer materialId) {
        List<AlcoholTemplate> list = alcoholTemplateService.findAllNotBoxAndMaterialId(materialId);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, list);
    }

    @Autowired
    AdvertisementService advertisementService;

    @ApiOperation(value = "广告用户保存")
    @PostMapping("advertisement/userSaveData")
    public ReturnBean advertisementUserSaveData(Advertisement advertisement) {
        advertisementService.add(advertisement);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }


}

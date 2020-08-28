package cn.xiaoge.design.controller;
import cn.xiaoge.design.entity.Order;
import cn.xiaoge.design.service.OrderService;
import cn.xiaoge.design.entity.vo.ReturnBean;
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
@Api(tags = {"06.订单接口"})
@RequestMapping("order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("add")
    @ApiOperation(value = "订单添加")
    public ReturnBean add(
            Integer userId,
            @Size(max = 11) String payStatus,
             String info,
            @Size(max = 11) String file1,
            @Size(max = 11) String file2) throws Exception {
        Order order = new Order();
        order.setUserId(userId);
        order.setPayStatus(payStatus);
        order.setInfo(info);
        order.setFile1(file1);
        order.setFile2(file2);
        orderService.add(order);
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "订单修改不为空的属性")
    @PostMapping("update")
    public ReturnBean update(
            Integer id,
            String ids,
            Integer userId,
            @Size(max = 11)  String payStatus,
              String info,
            @Size(max = 11)  String file1,
            @Size(max = 11)  String file2) throws Exception {
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                Order order = new Order();
                order.setId(Integer.parseInt(s));
                order.setUserId(userId);
                order.setPayStatus(payStatus);
                order.setInfo(info);
                order.setFile1(file1);
                order.setFile2(file2);
                orderService.updateNotNull(order);
            }
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }
        if (id!=null) {
            Order order = new Order();
            order.setId(id);
            order.setUserId(userId);
            order.setPayStatus(payStatus);
            order.setInfo(info);
            order.setFile1(file1);
            order.setFile2(file2);
            orderService.updateNotNull(order);
            return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
        }

        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR,"id和ids必须传一个");

    }


    @PostMapping("delete")
    @ApiOperation(value = "订单根据id删除")
    public ReturnBean delete(Integer id,String ids) {
        //单个删除
        if (id!=null){
            orderService.deleteById(id);
        }
        //批量删除
        if (!StringUtils.isEmpty(ids)){
            String[] split = ids.split(",");
            for (String s : split) {
                orderService.deleteById(Integer.parseInt(s));
            }
        }
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS);
    }

    @ApiOperation(value = "订单分页查询全部")
    @PostMapping("findAll")
    public ReturnBean findAll(
            @RequestParam(defaultValue = "1") Integer page,
            String order,
            @Max(value=10000) @RequestParam(defaultValue = "15") Integer size) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, orderService.findAll(page,order,size));
    }



    @ApiOperation(value = "订单根据id查询单个")
    @PostMapping("findByIdWithAppUser")
    public ReturnBean findByIdWithAppUser(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, orderService.findByIdWithAppUser(id));
    }


    @ApiOperation(value = "订单根据id查询单个")
    @PostMapping("findById")
    public ReturnBean findById(@RequestParam Integer id) {
        return ReturnBean.of(ReturnBean.AnswerCode.SUCCESS, orderService.findById(id));
    }



}

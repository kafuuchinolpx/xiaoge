package cn.xiaoge.design.controller;

import cn.xiaoge.design.service.PayService;
import cn.xiaoge.design.entity.vo.AlipayBean;
import com.alipay.api.AlipayApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 订单接口
 *
 * @author Louis
 * @date Dec 12, 2018
 */
@RestController
@RequestMapping("order")
public class AlipayController {

    @Autowired
    private PayService payService;

    /**
     * 阿里支付
     *
     * @param subject
     * @param body
     * @return
     * @throws AlipayApiException
     */
    @PostMapping(value = "alipay")
    public String alipay(String outTradeNo, String subject, String totalAmount, String body) throws AlipayApiException {
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(body);
        return payService.aliPay(alipayBean);
    }
}
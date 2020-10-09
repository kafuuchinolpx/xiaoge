package cn.xiaoge.design.entity.vo;

import lombok.Data;

/**
 * 支付实体对象
 * 根据支付宝接口协议，其中的属性名，必须使用下划线，不能修改
 * @author Louis
 * @date Dec 12, 2018
 */
@Data
public class AlipayBean {
    
    /**
     * 商户订单号，需要保证不重复，必填
     * 
     */
    private String outTradeNo;
    /**
     * 订单名称，必填
     */
    private String subject;
    /**
     * 付款金额，必填
     * 根据支付宝接口协议，必须使用下划线
     */
    private String totalAmount;
    /**
     * 商品描述，可空
     */
    private String body;
    /**
     * 超时时间参数
     */
    private String timeoutExpress = "30m";
    /**
     * 产品编号
     */
    private String productCode = "FAST_INSTANT_TRADE_PAY";

}
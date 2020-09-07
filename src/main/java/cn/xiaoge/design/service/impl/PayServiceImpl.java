package cn.xiaoge.design.service.impl;

import cn.xiaoge.design.component.Alipay;
import cn.xiaoge.design.entity.vo.AlipayBean;
import cn.xiaoge.design.service.PayService;
import com.alipay.api.AlipayApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private Alipay alipay;
    
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return alipay.pay(alipayBean);
    }

}
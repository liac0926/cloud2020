package com.cn.springcloud.service.Impl;

import com.cn.cloud.entites.Payment;
import com.cn.springcloud.dao.PaymentDao;
import com.cn.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lac
 * @date 2020/4/5-9:26
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id){
      return paymentDao.getPaymentById(id);
    }
}

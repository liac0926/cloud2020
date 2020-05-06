package com.cn.springcloud.service;


import com.cn.cloud.entites.Payment;

/**
 * @author lac
 * @date 2020/4/5-9:25
 */
public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(Long id);
}

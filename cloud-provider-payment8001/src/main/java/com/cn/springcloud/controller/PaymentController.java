package com.cn.springcloud.controller;

/**
 * @author lac
 * @date 2020/4/5-9:32
 */



import com.cn.cloud.entites.CommonResult;
import com.cn.cloud.entites.Payment;
import com.cn.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 服务发现，获取服务信息
     * @param payment
     * @return
     */
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value="/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("********插入结果："+result+"测试一下热部署");
        if(result>0){
            return new CommonResult(200,"插入数据成功",result);
        }else{
            return new CommonResult(444,"插入数据失败",null);

        }
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id ){
        Payment payment=paymentService.getPaymentById(id);
        if(payment!=null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录",null);

        }
    }

    /**
     * 服务发现
     * @return
     */
    @GetMapping(value="payment/discovery")
    public Object discovery(){
        List<String> services=discoveryClient.getServices();
        for (String element:services){
            log.info("*****element:"+element);
        }
        // 一个微服务下的全部实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.debug(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + instance.getUri());
        }
        return this.discoveryClient;
    }

@GetMapping(value="/payment/feign/timeout")
    public String paymentTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}

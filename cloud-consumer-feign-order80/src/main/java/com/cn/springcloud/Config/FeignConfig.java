package com.cn.springcloud.Config;

import org.springframework.context.annotation.Bean;
import feign.Logger;
import org.springframework.context.annotation.Configuration;

/**
 * @author lac
 * @date 2020/4/26-15:40
 */
@Configuration
public class FeignConfig {
    /**
     * feignClient配置日志级别
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        // 请求和响应的头信息,请求和响应的正文及元数据
        return Logger.Level.FULL;
    }
}

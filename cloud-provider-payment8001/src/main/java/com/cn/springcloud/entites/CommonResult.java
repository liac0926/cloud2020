package com.cn.springcloud.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lac
 * @date 2020/4/5-8:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public  CommonResult(Integer code,String message){
        this(code,message,null);
    }
}

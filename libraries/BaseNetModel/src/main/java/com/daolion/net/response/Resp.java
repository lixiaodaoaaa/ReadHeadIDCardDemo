package com.daolion.net.response;


/**
 * 基础的response，包含公共字段
 *
 * @author miaoxiongfei@foxmail.com
 * @date 2016-06-29 15:16
 */
public class Resp<T> extends ResponseModel {

    private T data;
    public T getData() {
        return data;
    }


}

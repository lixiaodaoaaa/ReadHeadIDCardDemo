package com.daolion.net.request;

import java.io.File;

import okhttp3.RequestBody;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/22
       Time     :  16:06
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class RequestUtils {
    
    
    public static RequestBody getFormRequestBody(String formValue){
        return new RequestBodyBuilder().setType(RequestBodyBuilder.TYPE_MULTIPART_FORMDATA).build(formValue);
    }
    
    public static RequestBody getFormRequestBodyByBytes(byte[] bytes){
        return new RequestBodyBuilder().setType(RequestBodyBuilder.TYPE_MULTIPART_FORMDATA).build(bytes);
    }
    
    public static RequestBody getFormRequestBodyByFile(File file){
        return new RequestBodyBuilder().setType(RequestBodyBuilder.TYPE_MULTIPART_FORMDATA).build(file);
    }
    
    public static RequestBody getJsonRequestBody(Object obj){
        return new RequestBodyBuilder().setType(RequestBodyBuilder.TYPE_JSON).build(obj);
    }
    
    public static RequestBody getJsonRequestBody(String jsonStr){
        return new RequestBodyBuilder().setType(RequestBodyBuilder.TYPE_JSON).build(jsonStr);
    }
    
    
}

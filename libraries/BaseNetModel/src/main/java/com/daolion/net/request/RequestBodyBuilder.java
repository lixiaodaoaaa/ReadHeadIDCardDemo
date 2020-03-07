package com.daolion.net.request;

import com.daolion.net.utils.JsonUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/22
       Time     :  14:15
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class RequestBodyBuilder {
    
    private RequestBody requestBody;
    
    public static final int TYPE_MULTIPART_FORMDATA = 2;//""multipart/form-data
    public static final int TYPE_JSON = 1;//"application/json"
    
    private int currentType = TYPE_MULTIPART_FORMDATA;
    
    
    public RequestBody build(String strBody){
        requestBody = RequestBody.create(getMediaTypeByType(), strBody);
        return requestBody;
    }
    
    
    public RequestBody build(File file){
        requestBody = RequestBody.create(getMediaTypeByType(), file);
        return requestBody;
    }
    
    public RequestBody build(Object object){
        requestBody = RequestBody.create(getMediaTypeByType(), JsonUtils.toJson(object));
        return requestBody;
    }
    
    public RequestBody build(byte[] bytes){
        requestBody = RequestBody.create(getMediaTypeByType(), bytes);
        return requestBody;
    }
    
    public RequestBodyBuilder setType(int type){
        if (type < TYPE_JSON || type > TYPE_MULTIPART_FORMDATA) {
            throw new IllegalArgumentException(
                    "request type not allowed low than typeJson and " + "high than type_multipart_form_data");
        }
        this.currentType = type;
        return this;
    }
    
    
    private MediaType getMediaTypeByType(){
        switch (currentType) {
            case TYPE_MULTIPART_FORMDATA:
                return MediaType.parse("multipart/form-data");
            case TYPE_JSON:
                return MediaType.parse("application/json");
            default:
                break;
        }
        return MediaType.parse("multipart/form-data");
    }
}

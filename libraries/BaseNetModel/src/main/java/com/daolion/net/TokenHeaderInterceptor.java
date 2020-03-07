package com.daolion.net;

import com.brc.idauth.api.response.ResponseCode;
import com.brc.idauth.api.utils.AuthorUtils;
import com.daolion.net.utils.StringUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/9/4
       Time     :  17:45
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class TokenHeaderInterceptor implements Interceptor {
    
    private static int CODE_NEED_REFRESH_TOKEN = 500;
    
    @Override
    public Response intercept(Chain chain) throws IOException{
        String token ="";
        if (StringUtils.isEmpty(token)) {
            Request originalRequest = chain.request();
            return chain.proceed(originalRequest);
        }else {
            Request originalRequest = chain.request();
            Request hasTokenRequest;
            if (hasRefreshTokenUrl(originalRequest.url().toString())) {
                hasTokenRequest = originalRequest.newBuilder().header("Authorization", AuthorUtils.getAuthorInfo()).header("Accept", "application/json").build();
            }else {
                hasTokenRequest = originalRequest.newBuilder().header("access_token", token).header("Accept", "application/json").build();
            }
            Response response = chain.proceed(hasTokenRequest);//执行此次请求
            if (response.code() == CODE_NEED_REFRESH_TOKEN||response.code()== ResponseCode.TOKEN_EXPIRED) {
//                EventBus.getDefault().post(new NeedRefreshTokenEvent(true));
            }
            return response;
        }
    }
    
    public boolean hasRefreshTokenUrl(String url){
        return url.contains("SERVER/auth/oauth/token");
    }
    
    
}


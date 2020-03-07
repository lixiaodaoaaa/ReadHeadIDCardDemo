package com.brc.idauth.api.utils;


import com.daolion.net.utils.Base64Utils;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019/1/10
       Time     :  14:00
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class AuthorUtils {
    
    public static String getAuthorInfo(){
        String clientUserName = "clientTest";
        String cliendPassword = "123456";
        String grant_type = "password";
        return String.format("Basic %s", Base64Utils.base64(clientUserName, cliendPassword));
    }
}

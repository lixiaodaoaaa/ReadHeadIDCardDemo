package com.brc.idauth.api.response;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/9/4
       Time     :  23:50
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class LogoutResponse {
    private String msg;
    private int code;
    
    public LogoutResponse(String msg, int code){
        this.msg = msg;
        this.code = code;
    }
    
    public String getMsg(){
        return msg;
    }
    
    public void setMsg(String msg){
        this.msg = msg;
    }
    
    public int getCode(){
        return code;
    }
    
    public void setCode(int code){
        this.code = code;
    }
    
    @Override
    public String toString(){
        return "LogoutResponse{" + "msg='" + msg + '\'' + ", code=" + code + '}';
    }
}

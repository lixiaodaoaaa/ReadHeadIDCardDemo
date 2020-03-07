package com.digops.ui.mine;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/22
       Time     :  16:11
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class RegistIdBean {
    private String loginMaching;
    
    public RegistIdBean(String loginMaching){
        this.loginMaching = loginMaching;
    }
    
    public String getLoginMaching(){
        return loginMaching;
    }
    
    public void setLoginMaching(String loginMaching){
        this.loginMaching = loginMaching;
    }
    
    @Override
    public String toString(){
        return "RegistIdBean{" + "loginMaching='" + loginMaching + '\'' + '}';
    }
}
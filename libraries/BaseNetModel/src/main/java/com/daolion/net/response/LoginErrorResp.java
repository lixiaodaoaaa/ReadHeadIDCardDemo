package com.brc.idauth.api.response;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/9/7
       Time     :  10:52
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class LoginErrorResp {
    
    private String error;
    
    private String error_description;
    
    public LoginErrorResp(String error, String error_description){
        this.error = error;
        this.error_description = error_description;
    }
    
    public String getError()
    {
        return error;
    }
    
    public void setError (String error)
    {
        this.error = error;
    }
    
    public String getError_description ()
    {
        return error_description;
    }
    
    public void setError_description (String error_description)
    {
        this.error_description = error_description;
    }
    
    @Override
    public String toString()
    {
        return "ClassPojo [error = "+error+", error_description = "+error_description+"]";
    }
}

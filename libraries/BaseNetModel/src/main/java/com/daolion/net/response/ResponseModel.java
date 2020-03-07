package com.daolion.net.response;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/14
       Time     :  09:43
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public abstract class ResponseModel {

    /**
     * {
     * "data": {},
     * "message": "success",
     * "status": 200
     * }
     */

    public int status;
    public String message;
}

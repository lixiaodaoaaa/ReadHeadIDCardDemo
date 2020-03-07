package com.brc.idauth.base;

import com.brc.idauth.net.ApiManager;
import com.daolion.base.control.FrameModel;
import com.daolion.net.APIService;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-05
       Time     :  16:18
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public interface BaseModel extends FrameModel {


    APIService apiService = ApiManager.getInstance().getApiService();

}

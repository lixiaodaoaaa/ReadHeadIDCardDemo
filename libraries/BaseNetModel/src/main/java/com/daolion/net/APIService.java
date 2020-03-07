package com.daolion.net;

import com.daolion.net.response.PictureInfo;
import com.daolion.net.response.Resp;
import com.daolion.net.response.SquareBean;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/13
       Time     :  20:49
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public interface APIService {
    @GET("oss/api/app/version/list")
    Observable<RspUpdate> checkUpdate(@Header("appKey") String key,
                                      @Header("timeStamp") long timeStamp, @Header("nonce") String nonce,
                                      @Header("version") String version, @Header("sign") String sign, @Query(
            "appPackageName") String name);

    @POST("devicecenter/api/open/device/register")
    Observable<JsonObject> registDevice(@Body RequestBody requestBody);


    @Multipart
    @POST("oss/api/oss/upload")
    Observable<Resp<PictureInfo>> uploadPicture(@Part("file") RequestBody pictureBody);

    @POST("adaptor/api/cloud/0/device/event")
    Observable<Resp<String>> uploadEvent(@Body RequestBody requestBody);

    @GET("riskcontrolcenter/api/open/case/relation/list")
    Observable<SquareBean> searchSquare(@Query("caseName") String caseNmae,@Query("page") String page,@Query("pageSize") String pageSize);

    @POST()
    Observable<JSONObject> requestLiscense();

    @POST()
    Observable<JSONObject> uploadLicence(@Body JSONObject body);
}

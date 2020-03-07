package com.brc.idauth.net;


import com.brc.idauth.BuildConfig;
import com.daolion.base.utils.NetWorkUtil;
import com.daolion.net.APIService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
       Author   :  lixiaodaoaaa
       Date     :  2018/8/13
       Time     :  20:39
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class ApiManager {

    public String BASE_URL = BuildConfig.URL_SERVER;


    private final static String APPKEY = "appKey";
    private final static String APPSECRET = "appSecret";

    private static ApiManager apiManager;
    private APIService apiService;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    private String appKey = "4029a4bd62320ce7";
    private String appSecret = "0o69d4gc14omiqrbo1n1o1z35kzuvrkz";


    private ApiManager() {

        // Log信息
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(chain -> {
                        Request request = chain.request()
                                .newBuilder()
                                .removeHeader("Pragma")
                                //添加统一header配置
                                .addHeader("Content-Type", "application/json")
                                .addHeader(APPKEY, appKey)
                                .addHeader(APPSECRET, appSecret)
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Connection", "keep-alive")
                                .addHeader("Accept", "*/*")
                                .build();
                        return chain.proceed(request);
                    })
                    .addNetworkInterceptor(new HttpCacheInterceptor())
                    .addNetworkInterceptor(loggingInterceptor)
                    .build();
        }


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
            apiService = retrofit.create(APIService.class);
        }

    }


    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }


    public APIService getApiService() {
        return apiService;
    }


    class HttpCacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);

            if (NetWorkUtil.isNetConnected()) {
                //有网的时候使用接口声明的@Headers里的cache配置
                String cacheControl = request.cacheControl().toString();
                return originalResponse
                        .newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        //添加统一header配置
                        .addHeader("Content-Type", "application/json; charset=UTF-8")
                        .addHeader(APPKEY, appKey)
                        .addHeader(APPSECRET, appSecret)
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .build();
            } else {
                return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=2419200").removeHeader("Pragma").build();
            }
        }
    }

}

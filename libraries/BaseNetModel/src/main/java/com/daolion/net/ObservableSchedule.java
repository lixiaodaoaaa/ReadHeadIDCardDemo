package com.brc.idauth.api;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/15
       Time     :  11:22
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class ObservableSchedule{
    
    public static <T> Observable<T> schedule(Observable<T> observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    
    
}

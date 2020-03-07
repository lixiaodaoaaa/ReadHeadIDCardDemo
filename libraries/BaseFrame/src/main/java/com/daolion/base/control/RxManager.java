package com.daolion.base.control;

import rx.Subscription;

/**
 * 用于管理Rxjava相关代码的生命周期处理
 *
 * @author miaoxiongfei@foxmail.com
 * @date 2016-06-29 16:06
 */
public class RxManager{
    
    private rx.subscriptions.CompositeSubscription mCompositeSubscription;
    
    public RxManager(){
        mCompositeSubscription=new rx.subscriptions.CompositeSubscription();// 管理订阅者者
    }
    
    
    public void add(Subscription m){
        mCompositeSubscription.add(m);
    }
    
    public void clear(){
        mCompositeSubscription.unsubscribe();// 取消订阅
    }
    
}

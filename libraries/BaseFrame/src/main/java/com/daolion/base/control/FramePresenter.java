package com.daolion.base.control;

import android.content.Context;

import rx.Observable;

/**
 */
public abstract class FramePresenter<V extends FrameView, M extends FrameModel> {
    public Context context = FrameApplication.getInstance().getApplicationContext();
    public M mModel;
    public V mView;

    
    protected FramePresenter(V mView, M mModel){
        this.mModel = mModel;
        this.mView = mView;
        mView.setPresenter(this);
    }
    
    public RxManager rxManager = new RxManager();
    
    
    protected void onStart(){
    }
    
    
    public void onDestroy(){
        rxManager.clear();
    }

    protected <T> Observable<T> schedule(Observable<T> observable){
        return ObservableSchedule.schedule(observable);
    }

}


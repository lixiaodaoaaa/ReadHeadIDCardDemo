package com.brc.idauth.ui.splash;

import com.brc.idauth.base.BaseModel;
import com.daolion.base.control.FramePresenter;
import com.daolion.base.control.FrameView;
import com.google.gson.JsonObject;

import rx.Observable;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-18
       Time     :  21:20
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public interface SplashContract {

    interface Model extends BaseModel {


        Observable<JsonObject> registerDevice();

    }

    interface View extends FrameView<Presenter> {

        void registerDeviceResult(boolean success);

    }


    abstract class Presenter extends FramePresenter<View,Model> {
        protected Presenter(View mView, Model mModel) {
            super(mView, mModel);
        }

        abstract void registDevice();
    }
}

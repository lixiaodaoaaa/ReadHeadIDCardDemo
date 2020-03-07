package com.brc.idauth.ui.mine;


import com.brc.idauth.base.BaseModel;
import com.daolion.base.control.FramePresenter;
import com.daolion.base.control.FrameView;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/22
       Time     :  13:58
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public interface MineContract {

    interface Model extends BaseModel {
    }

    interface View extends FrameView<Presenter> {
    }

    abstract class Presenter extends FramePresenter<View,Model> {
        protected Presenter(View mView, Model mModel) {
            super(mView, mModel);
        }

    }
}

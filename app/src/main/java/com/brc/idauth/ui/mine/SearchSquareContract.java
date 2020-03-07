package com.brc.idauth.ui.mine;

import com.brc.idauth.base.BaseModel;
import com.daolion.base.control.FramePresenter;
import com.daolion.base.control.FrameView;
import com.daolion.net.response.SquareBean;

import rx.Observable;

public interface SearchSquareContract {
    interface Model extends BaseModel{
        Observable<SquareBean> searchSquare(String searchStr,String page,String pageSize);
    }

    interface View extends FrameView<Presenter> {
        void showSquareList(SquareBean squareBean);
    }

    abstract class Presenter extends FramePresenter<View, Model> {

        protected Presenter(View mView, Model mModel) {
            super(mView, mModel);
        }

        public abstract void searchSquare(String searchStr,String page,String pageSize);
    }
}

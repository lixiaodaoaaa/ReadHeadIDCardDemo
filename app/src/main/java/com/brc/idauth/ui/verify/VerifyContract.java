package com.brc.idauth.ui.verify;

import com.brc.idauth.base.BaseModel;
import com.brc.idauth.bean.EventPara;
import com.brc.idauth.bean.IdCardBean;
import com.daolion.base.control.FramePresenter;
import com.daolion.base.control.FrameView;
import com.daolion.net.response.PictureInfo;
import com.daolion.net.response.Resp;

import java.util.List;

import rx.Observable;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-11
       Time     :  16:10
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public interface VerifyContract {

    interface Model extends BaseModel {

        List<ItemBean> getListItemBean();

        List<ItemBean> getRealItemBeanByIdCard(IdCardBean idCardBean);

        Observable<Resp<PictureInfo>> uploadPicture(String filePath);


        /*
        事件上报，用于图片上传成功后，触发风控，上报事件
         */
        Observable<Resp<String>> uploadEvent(EventPara eventPara);
    }

    interface View extends FrameView<Presenter> {
        void getListItemBeanSuccess(List<ItemBean> beanList);

        void getRealItemBeanSuccess(List<ItemBean> beanList);

    }


    abstract class Presenter extends FramePresenter<View,Model> {

        protected Presenter(View mView, Model mModel) {
            super(mView, mModel);
        }

        public abstract void getListItemBean();

        public abstract void getRealItemBeanByIdCard(IdCardBean idCardBean);

        public abstract void uploadIDcardHeadPicture();

        public abstract void uploadFacePicture(String facePath);

        public abstract void uploadEvent(IdCardBean idCardBean);


    }
}

package com.brc.idauth.ui.verify;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.brc.idauth.base.BlankActivityWithFragment;
import com.daolion.base.anotation.BsActivityAnnotation;
import com.daolion.base.utils.ActivityUtil;
import com.daolion.func.IDCardSDK;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-09
       Time     :  14:58
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
@BsActivityAnnotation(isFullScreen = true)
public class VerifyActivity extends BlankActivityWithFragment {


    public static void launch(Context context) {
        Intent intent = new Intent(context, VerifyActivity.class);
        ActivityUtil.switchTo(context, intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new VerifyPresenter((VerifyContract.View) fragment, new VerifyModel());
        IDCardSDK.getInstance().initSDK(getHandler(), this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);// 屏幕常亮
    }


    @Override
    protected String getFragmentName() {
        return VerifyFragment.class.getName();
    }


}

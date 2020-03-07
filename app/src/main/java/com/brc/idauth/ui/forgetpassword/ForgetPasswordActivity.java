package com.brc.idauth.ui.forgetpassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.brc.idauth.R;
import com.brc.idauth.base.ToolbarActivityWithFragment;
import com.daolion.base.anotation.BsActivityAnnotation;
import com.daolion.base.utils.ActivityUtil;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-06
       Time     :  11:14
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */


@BsActivityAnnotation(toolbarTitleStringID = R.string.toolbar_title_forget_password,
        showBackNavigation = true,
        toolbarMenuLayouId = R.menu.menu_blank)

public class ForgetPasswordActivity extends ToolbarActivityWithFragment {


    public static void launch(Context context) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        ActivityUtil.switchTo(context, intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getFragmentName() {
        return ForgetPasswordFragment.class.getName();
    }

    @Override
    protected void preOnCreate() {
        super.preOnCreate();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}

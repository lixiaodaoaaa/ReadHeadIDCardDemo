package com.daolion.base.control;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.daolion.base.anotation.BsActivityAnnotation;
import com.daolion.base.utils.ActivityUtil;

import java.lang.ref.WeakReference;

import de.greenrobot.event.EventBus;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/7/12
       Time     :  14:07
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public abstract class FrameActivity<P extends FramePresenter> extends AppCompatActivity {


    protected P mPresenter;
    private View statusBarView;
    public ActivityHandler handler;
    private boolean hasEventBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        preOnCreate();
        super.onCreate(savedInstanceState);
        handleAnnotation();
        bindButterKnifer();
        handler = new ActivityHandler(this);
        if (null != mPresenter) {
            mPresenter.onStart();
        }
        if (hasEventBus) {
            EventBus.getDefault().register(this);
        }
    }


    protected abstract void bindButterKnifer();

    private void handleAnnotation() {
        if (getClass().isAnnotationPresent(BsActivityAnnotation.class)) {
            BsActivityAnnotation bsActivityAnnotation = getClass().getAnnotation(BsActivityAnnotation.class);
            if (bsActivityAnnotation.isNeedEventBus()) {
                hasEventBus = true;
            }
            if (bsActivityAnnotation.isFullScreen()) {
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
            //有Layout  需要设
            if (bsActivityAnnotation.layoutId() != -1) {
                setContentView(bsActivityAnnotation.layoutId());
            }

        }
    }


    protected void preOnCreate() {

    }

    public Handler getHandler() {
        return handler;
    }

    protected void handleMessage(Message msg) {
    }

    public static class ActivityHandler extends Handler {
        private WeakReference<FrameActivity> wekReferenceActivity = null;

        public ActivityHandler(FrameActivity activity) {
            wekReferenceActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            FrameActivity currentActivity = wekReferenceActivity.get();
            if (currentActivity == null) {
                super.handleMessage(msg);
                return;
            }
            currentActivity.handleMessage(msg);
        }
    }

    public void initStatusBar() {
        int identifier = getResources().getIdentifier("statusBarBackground", "id", "android");
        if (statusBarView == null) {
            statusBarView = getWindow().findViewById(identifier);
        }
    }

    public Fragment findFragmentByTag(String tagName) {
        return getSupportFragmentManager().findFragmentByTag(tagName);
    }


    public void addFragmentToActivity(@NonNull Fragment fragment, int frameId) {
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, frameId);
    }


    public void addFragmentToActivity(@NonNull Fragment fragment, int frameId, Bundle bundle) {
        fragment.setArguments(bundle);
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, frameId);
    }


    protected void switchToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        ActivityUtil.switchTo(this, intent);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (isNeedTintStatusBar()) {
            Looper.myQueue().addIdleHandler(() -> {
                initStatusBar();
                getWindow()
                        .getDecorView()
                        .addOnLayoutChangeListener(
                                (v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> initStatusBar());

                return false;
            });
        }

    }

    private boolean isNeedTintStatusBar() {
        return !getClass().getName().toLowerCase().contains("login");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBinderButterknife();

        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        if (hasEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract void unBinderButterknife();

}

package com.daolion.base.control;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daolion.base.anotation.BsFragmentAnnotation;
import com.google.common.base.Preconditions;

import java.lang.ref.WeakReference;

import de.greenrobot.event.EventBus;

/**
 * @author miaoxiongfei@foxmail.com
 * @date 2016-06-28 10:33
 */
public abstract class FrameFragment<P extends FramePresenter> extends Fragment {
    public String TAG = getClass().getSimpleName();
    protected View rootView;
    public FragmentHandler mHandler;
    public P mPresenter;
    private boolean isNeedEventBus = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int layoutIdByAnnotation = getLayoutIdByAnnotation();
        rootView = inflater.inflate(layoutIdByAnnotation, null);
        bindButterKnifer();
        if (isNeedEventBus() && !EventBus.getDefault().isRegistered(this)) {//加上判断
            EventBus.getDefault().register(this);
        }

        initViewByRootView(rootView);
        return rootView;
    }

    protected abstract void bindButterKnifer();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handAnnotation();
        initDatas();
        if (null != mPresenter) {
            mPresenter.onStart();
        }

        if (isNeedEventBus) {
            EventBus.getDefault().register(this);
        }
    }

    private void handAnnotation() {
        if (getClass().isAnnotationPresent(BsFragmentAnnotation.class)) {
            BsFragmentAnnotation fragmentAnnotation = getClass().getAnnotation(BsFragmentAnnotation.class);
            isNeedEventBus = fragmentAnnotation.isNeedEventBus();

        }
    }


    protected void initDatas() {
        mHandler = new FragmentHandler(this);
    }

    protected abstract void initViewByRootView(View rootView);


    private int getLayoutIdByAnnotation() {
        if (getClass().isAnnotationPresent(BsFragmentAnnotation.class)) {
            BsFragmentAnnotation digOpsAnnotation = getClass().getAnnotation(BsFragmentAnnotation.class);
            int layoutId = digOpsAnnotation.fragmentLayoutId();
            //如果子类没有定义layoutID 则返回父类的layoutID这里就是RecyclerLayoutID
            if (layoutId == -1) {
                BsFragmentAnnotation superAnnotation = getClass()
                        .getSuperclass()
                        .getAnnotation(BsFragmentAnnotation.class);
                return superAnnotation.fragmentLayoutId();
            }
            return digOpsAnnotation.fragmentLayoutId();
        }
        return -1;
    }

    private boolean isNeedEventBus() {
        if (getClass().isAnnotationPresent(BsFragmentAnnotation.class)) {
            BsFragmentAnnotation digOpsAnnotation = getClass().getAnnotation(BsFragmentAnnotation.class);
            return digOpsAnnotation.isNeedEventBus();
        }
        return false;
    }


    protected abstract void handleMessage(Message msg);


    public static class FragmentHandler extends Handler {
        private WeakReference<FrameFragment> weakRefrenceActivity = null;

        public FragmentHandler(FrameFragment activity) {
            weakRefrenceActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FrameFragment currentFragment = weakRefrenceActivity.get();
            if (currentFragment == null) {
                return;
            }
            currentFragment.handleMessage(msg);
        }
    }


    protected void finish() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unBinderButterknife();

        if (isNeedEventBus) {
            EventBus.getDefault().unregister(this);
        }
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    protected abstract void unBinderButterknife();

    public void setToPresenter(P p) {
        mPresenter = Preconditions.checkNotNull(p);
    }

    public void onBackPressed() {
    }
}

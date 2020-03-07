package com.brc.idauth.base;

import com.daolion.base.control.FrameFragment;
import com.daolion.base.control.FramePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author miaoxiongfei@foxmail.com
 * @date 2016-06-28 10:33
 */
public abstract class BaseFragment<P extends FramePresenter> extends FrameFragment {

    private Unbinder butterBinder;


    protected P mPresenter;

    @Override
    protected void bindButterKnifer() {
        butterBinder = ButterKnife.bind(this, rootView);
    }


    @Override
    protected void unBinderButterknife() {
        if (null != butterBinder) {
            butterBinder.unbind();
        }
    }
}

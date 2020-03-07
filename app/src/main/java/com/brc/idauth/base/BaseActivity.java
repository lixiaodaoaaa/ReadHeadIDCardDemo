package com.brc.idauth.base;

import com.daolion.base.control.FrameActivity;
import com.daolion.base.control.FramePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/7/12
       Time     :  14:07
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public abstract class BaseActivity<P extends FramePresenter> extends FrameActivity {
    private Unbinder butterBinder;

    protected P mPresenter;

    @Override
    protected void bindButterKnifer() {
        butterBinder = ButterKnife.bind(this);
    }


    @Override
    protected void unBinderButterknife() {
        if (null != butterBinder) {
            butterBinder.unbind();
        }
    }
}

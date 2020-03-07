package com.brc.idauth.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.brc.idauth.R;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/7/24
       Time     :  18:05
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public abstract class BlankActivityWithFragment extends BaseActivity {
    
    protected Fragment fragment;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        fragment = findFragmentByTag(getFragmentName());
        String fragmentName = getFragmentName();
        Bundle bundle = new Bundle();
        if (fragment == null) {
            fragment = Fragment.instantiate(this, fragmentName, bundle);
            addFragmentToActivity(fragment, R.id.content_frame, bundle);
        }
    }
    
    protected abstract String getFragmentName();

    
}

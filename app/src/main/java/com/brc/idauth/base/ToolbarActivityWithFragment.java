package com.brc.idauth.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.brc.idauth.Constants;
import com.brc.idauth.R;
import com.daolion.base.anotation.BsActivityAnnotation;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
       Author   :  lixiaodaoaaa
       Date     :  2018/7/24
       Time     :  18:05
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public abstract class ToolbarActivityWithFragment extends BaseActivity {

    protected Toolbar toolbar;
    protected Fragment fragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_base);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getToolBarTitleStringResourceID());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        if (getSupportActionBar() != null && isShowNavitionBack()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        addFragmentToToolbarActivity(getIntent().getExtras());
    }

    protected String getToolbarTitleStr() {
        if (getToolBarTitleStringResourceID() != -1) {
            return getString(getToolBarTitleStringResourceID());
        }
        String title = getIntent().getExtras().getString(Constants.KEY_BUNDLE_TOOLBAR_TITLE);
        return title;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isShowNavitionBack() {
        if (getClass().isAnnotationPresent(BsActivityAnnotation.class)) {
            BsActivityAnnotation bsAnotation = getClass().getAnnotation(BsActivityAnnotation.class);
            return bsAnotation.showBackNavigation();
        }
        return false;
    }

    private void addFragmentToToolbarActivity(Bundle bundle) {
        fragment = (BaseFragment) findFragmentByTag(getFragmentName());
        String fragmentName = getFragmentName();
        if (fragment == null) {
            fragment = (BaseFragment) Fragment.instantiate(this, fragmentName, bundle);
            addFragmentToActivity(fragment, R.id.content_frame, bundle);
        }
    }


    protected abstract String getFragmentName();



    /**
     * 得到 标题栏的String ID;
     *
     * @return
     */
    private int getToolBarTitleStringResourceID() {
        if (getClass().isAnnotationPresent(BsActivityAnnotation.class)) {
            BsActivityAnnotation digOpsAnnotation = getClass().getAnnotation(BsActivityAnnotation.class);
            return digOpsAnnotation.toolbarTitleStringID();
        }
        return -1;
    }

}

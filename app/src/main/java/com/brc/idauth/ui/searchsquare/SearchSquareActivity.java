package com.brc.idauth.ui.searchsquare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.brc.idauth.base.BlankActivityWithFragment;
import com.daolion.base.utils.ActivityUtil;


public class SearchSquareActivity extends BlankActivityWithFragment {


    public static void launch(Context context) {
        Intent intent = new Intent(context, SearchSquareActivity.class);
        ActivityUtil.switchTo(context, intent);
    }


    @Override
    protected String getFragmentName() {
        return SearchSquareFragment.class.getName();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SearchSquarePresenter((SearchSquareContract.View) fragment, new SearchSquareModle());
    }


}

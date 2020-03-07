package com.brc.idauth.ui.searchsquare;

import android.util.Log;

public class SearchSquarePresenter extends SearchSquareContract.Presenter {
    public SearchSquarePresenter(SearchSquareContract.View mView, SearchSquareContract.Model mModel) {
        super(mView, mModel);
    }

    @Override
    public void searchSquare(String searchStr, String page, String pageSize) {
        rxManager.add(schedule(mModel.searchSquare(searchStr, page, pageSize)).subscribe(stringResp -> {
            Log.i("response", stringResp.getTotal() + "");
            mView.showSquareList(stringResp);
        }, throwable -> {
            Log.i("response", throwable.getMessage());
        }));
    }
}

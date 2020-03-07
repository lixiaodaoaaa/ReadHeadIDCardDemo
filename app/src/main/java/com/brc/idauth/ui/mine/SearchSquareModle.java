package com.brc.idauth.ui.mine;

import com.daolion.net.response.SquareBean;

import rx.Observable;

public class SearchSquareModle implements SearchSquareContract.Model{
    @Override
    public Observable<SquareBean> searchSquare(String searchStr,String page,String pageSize) {
        return apiService.searchSquare(searchStr,page,pageSize);
    }
}

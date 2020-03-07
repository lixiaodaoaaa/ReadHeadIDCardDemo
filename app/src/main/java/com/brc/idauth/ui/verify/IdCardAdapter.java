package com.brc.idauth.ui.verify;

import android.content.Context;
import android.view.ViewGroup;

import com.brc.idauth.R;
import com.brc.idauth.ui.view.TitleValueDividerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/7/13
       Time     :  14:03
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class IdCardAdapter extends RecyclerArrayAdapter<ItemBean> {


    public IdCardAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHodler(parent);
    }


    public static class MyViewHodler extends BaseViewHolder<ItemBean> {
        TitleValueDividerView cardView;

        public MyViewHodler(ViewGroup parent) {
            super(parent, R.layout.item_id_card_info);
            cardView = $(R.id.idCardItemLayout);
        }

        @Override
        public void setData(ItemBean itemBean) {
            cardView.setItemTitle(itemBean.getItem());
            cardView.setItemValue(itemBean.getValue());
        }

    }
}

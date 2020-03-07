package com.brc.idauth.ui.searchsquare;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brc.idauth.R;
import com.daolion.net.response.SquareBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import de.greenrobot.event.EventBus;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
       Author   :  lixiaodaoaaa
       Date     :  2018/7/13
       Time     :  14:03
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class SearchSquareAdapter extends RecyclerArrayAdapter<SquareBean.DataBean> {


    private int selectPosition = -1;

    public SearchSquareAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHodler(parent);
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);
        MyViewHodler mHodler = (MyViewHodler) holder;

        boolean isClick = (position == selectPosition);


        if (isClick) {
            mHodler.iv_search_squre.setImageResource(R.drawable.ic_checked);
        } else {
            mHodler.iv_search_squre.setImageResource(R.drawable.ic_nochecked);
        }

    }

    public class MyViewHodler extends BaseViewHolder<SquareBean.DataBean> {

        private TextView tv_square_id;
        private TextView tv_square_name;
        private TextView tv_square_project_name;
        private ImageView iv_search_squre;


        public MyViewHodler(ViewGroup parent) {
            super(parent, R.layout.item_square);
            tv_square_id = $(R.id.tv_square_id);
            tv_square_name = $(R.id.tv_square_name);
            tv_square_project_name = $(R.id.tv_square_project_name);
            iv_search_squre = $(R.id.iv_search_squre);
        }

        @Override
        public void setData(SquareBean.DataBean dataBean) {
            super.setData(dataBean);
            View.OnClickListener clickListener = v -> {
                selectPosition = getAdapterPosition();
                SquareBeanChangeEvent changeEvent = new SquareBeanChangeEvent(selectPosition, dataBean);
                EventBus.getDefault().post(changeEvent);
                notifyDataSetChanged();
            };


            itemView.setOnClickListener(clickListener);

            iv_search_squre.setOnClickListener(clickListener);


            if (dataBean.isChecked()) {
                iv_search_squre.setBackgroundResource(R.drawable.ic_checked);
            } else {
                iv_search_squre.setBackgroundResource(R.drawable.ic_nochecked);
            }


            tv_square_id.setText(dataBean.getId());
            tv_square_name.setText(dataBean.getProjectName());

        }
    }
}

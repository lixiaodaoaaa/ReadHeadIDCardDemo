package com.brc.idauth.ui.searchsquare;

import android.content.Context;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.brc.idauth.R;
import com.brc.idauth.base.RecyclerBaseFragment;
import com.daolion.base.anotation.BsFragmentAnnotation;
import com.daolion.net.response.SquareBean;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/7/25
       Time     :  00:22
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
@BsFragmentAnnotation(fragmentLayoutId = R.layout.fragment_search_square, isNeedEventBus = true)
public class SearchSquareFragment extends
        RecyclerBaseFragment<SquareBean.DataBean,SearchSquareContract.Presenter> implements SearchSquareContract.View {


    private final int MSG_NOTIFY_DATACHANGED = 101;

    @BindView(R.id.et_search) EditText etSearch;
    @BindView(R.id.btn_square_sure) Button btnSquareSure;
    @BindView(R.id.btn_square_cancel) Button btnSquareCancel;


    int page = 0;
    int pageSize = 10;
    int total = 0;

    private int selectSquarePosition = -1;


    @Override
    protected void onRecyclerItemClick(int position) {

    }

    @Override
    public void onResume() {
        super.onResume();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (etSearch !=null && imm != null){
                    imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);  //强制隐藏
                }
            }
        },200);
    }

    @Override
    protected void initViewByRootView(View rootView) {
        super.initViewByRootView(rootView);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.LTGRAY, 1, 0, 0);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1) && adapter.getItemCount() < total) {
                    page++;
                    mPresenter.searchSquare(etSearch.getText().toString(), String.valueOf(page), String.valueOf(pageSize));
                }
            }
        });

        mPresenter.searchSquare(etSearch.getText().toString(), String.valueOf(page), String.valueOf(pageSize));
    }

    @Override
    protected void handleMessage(Message msg) {

        switch (msg.what) {
            case MSG_NOTIFY_DATACHANGED:
                List<SquareBean.DataBean> newDatas = (List<SquareBean.DataBean>) msg.obj;

                if (newDatas != null && newDatas.size() > 0) {
                    listData.clear();
                    listData.addAll(newDatas);
                    adapter.addAll(newDatas);
                }

                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }

    }

    @Override
    protected RecyclerArrayAdapter setupAdapter() {
        return new SearchSquareAdapter(getContext());
    }


    @Override
    protected void initDatas() {
        super.initDatas();
    }


    @Subscribe
    public void onEventMainThread(SquareBeanChangeEvent event) {

        SquareBean.DataBean item = event.getDataBean();
        selectSquarePosition = event.getPosition();
    }



    @OnClick(R.id.btn_square_sure)
    public void onClickSquareSure() {
        if (selectSquarePosition != -1) {
            SquareBean.DataBean selectSquareBean = listData.get(selectSquarePosition);
            SelectSquareEvent selectSquareEvent = new SelectSquareEvent(selectSquareBean);
            EventBus.getDefault().post(selectSquareEvent);
        }
        finish();
    }

    @OnClick({R.id.btn_square_cancel,R.id.back_iv,R.id.back_tv})
    public void onClickSquareCancel() {
        finish();
    }

    @Override
    public void showSquareList(SquareBean squareBean) {
        total = squareBean.getTotal();
        listData.addAll(squareBean.getData());
        adapter.addAll(listData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(SearchSquareContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }
}

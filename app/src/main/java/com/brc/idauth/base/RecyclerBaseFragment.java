package com.brc.idauth.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.brc.idauth.R;
import com.daolion.base.anotation.BsFragmentAnnotation;
import com.daolion.base.control.FramePresenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/7/27
       Time     :  17:06
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
@BsFragmentAnnotation(fragmentLayoutId = R.layout.fragment_common_recyclerview)
public abstract class RecyclerBaseFragment<T, P extends FramePresenter> extends BaseFragment {
    
    
    protected @BindView(R.id.recyclerView) EasyRecyclerView recyclerView;
    protected RecyclerArrayAdapter adapter;
    protected List<T> listData = new ArrayList<>();
    protected P mPresenter;
    
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected void initViewByRootView(View rootView){
        DividerDecoration itemDecoration = new DividerDecoration(Color.LTGRAY, 1, 0, 0);
        if (getShowRecyclerDiver()) {
            recyclerView.addItemDecoration(itemDecoration);
        }
        
        if (isRecyclerLayoutManagerIsLiner()) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        
        adapter = setupAdapter();
        recyclerView.setAdapter(adapter);
        adapter.addAll(listData);
        adapter.setOnItemClickListener(position -> onRecyclerItemClick(position));
    }
    

    
    protected void onRecyclerItemClick(int position){
    
    }
    
    protected abstract RecyclerArrayAdapter setupAdapter();
    


    
    
    private boolean getShowRecyclerDiver(){
        if (getClass().isAnnotationPresent(BsFragmentAnnotation.class)) {
            BsFragmentAnnotation digOpsAnnotation = getClass().getAnnotation(BsFragmentAnnotation.class);
            return digOpsAnnotation.isShowRecyclerDiver();
        }
        return false;
    }
    
    private boolean isRecyclerLayoutManagerIsLiner(){
        if (getClass().isAnnotationPresent(BsFragmentAnnotation.class)) {
            BsFragmentAnnotation digOpsAnnotation = getClass().getAnnotation(BsFragmentAnnotation.class);
            return digOpsAnnotation.isRecyclerItemIsLinerlayout();
        }
        return false;
    }
    
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}

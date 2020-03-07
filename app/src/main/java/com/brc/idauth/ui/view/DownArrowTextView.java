package com.brc.idauth.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brc.idauth.R;
import com.daolion.base.utils.ResourceReader;


public class DownArrowTextView extends RelativeLayout {
    
    private TextView tvSelectText;
    private ImageView ivDownArrow;
    
    public DownArrowTextView(Context context){
        this(context, null);
    }
    
    
    public DownArrowTextView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        initViewsByAttrs(attrs);
    }
    
    public DownArrowTextView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
        initViewsByAttrs(attrs);
    }
    
    
    private void initViewsByAttrs(AttributeSet attrs){
        View layoutDownArrowTv = LayoutInflater.from(getContext()).inflate(R.layout.layout_down_arrow_tv, this, false);
        addView(layoutDownArrowTv, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                                                                   RelativeLayout.LayoutParams.WRAP_CONTENT));
        tvSelectText = findViewById(R.id.tv_select_text);
        ivDownArrow = findViewById(R.id.iv_down_arrow);
    }
    
    public void setSelectText(String strText, boolean isDefaultColor){
        if (isDefaultColor) {
            tvSelectText.setTextColor(ResourceReader.readColor(getContext(), R.color.font_sub));
        }else {
            tvSelectText.setTextColor(ResourceReader.readColor(getContext(), R.color.font_title));
        }
        tvSelectText.setText(strText);
    }
    
    public void setDownArrowIsVisible(boolean isVisible){
        if (isVisible) {
            ivDownArrow.setVisibility(View.VISIBLE);
        }else {
            ivDownArrow.setVisibility(View.GONE);
        }
    }
    
}
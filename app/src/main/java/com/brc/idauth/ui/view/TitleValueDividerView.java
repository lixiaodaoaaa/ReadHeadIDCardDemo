package com.brc.idauth.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brc.idauth.R;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/3
       Time     :  09:54
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛

    带分割线的 ItemView LinerLayout 保护Title 内容 可以设置Title 也可以设置它的值
 */
public class TitleValueDividerView extends LinearLayout {


    private TextView tvItemTitle;
    private TextView tvItemValue;

    private int itemTitleSize = 24;
    private int itemValueSize = 36;


    private void initAllViews() {
        tvItemTitle = findViewById(R.id.tv_item_title);
        tvItemValue = findViewById(R.id.tv_item_value);

    }


    public TitleValueDividerView(Context context) {
        super(context);
    }


    public TitleValueDividerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViewsByAttrs(attrs);
    }

    public TitleValueDividerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setItemTitle(String title) {
        tvItemTitle.setText(title);
    }


    public void setItemValue(String value) {
        tvItemValue.setText(value);
    }


    private void initViewsByAttrs(AttributeSet attrs) {
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.layout_title_value_divider, this, false);
        addView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        initAllViews();
        TypedArray typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.TitleValueDividerView);

    }


}

package com.brc.idauth.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brc.idauth.R;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-16
       Time     :  14:32
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class MainItemView extends LinearLayout {


    private TextView tvChinese;
    private TextView tvEnglish;
    private ImageView ivIcon;

    public MainItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MainItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MainItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {

        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.layout_main_item_view, this, false);
        addView(rootview, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.MainItemView);
        String textChinese = typedArray.getString(R.styleable.MainItemView_textChinese);
        String textEnglish = typedArray.getString(R.styleable.MainItemView_textEnglish);


        int rightIconId = typedArray.getResourceId(R.styleable.MainItemView_rightIconId, -1);
        typedArray.recycle();

        tvChinese = findViewById(R.id.tv_main_item_chinese);
        tvEnglish = findViewById(R.id.tv_main_item_english);
        ivIcon = findViewById(R.id.iv_main_item_icon);

        tvChinese.setText(textChinese);
        tvEnglish.setText(textEnglish);

        if (rightIconId != -1) {
            ivIcon.setImageResource(rightIconId);
        }
    }


}
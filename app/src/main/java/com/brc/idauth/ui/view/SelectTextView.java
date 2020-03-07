package com.digops.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.brc.idauth.R;


public class SelectTextView extends android.support.v7.widget.AppCompatTextView{
    
    private Drawable moreDrawable;
    private Context context;
    
    public SelectTextView(Context context){
        this(context, null);
        this.context=context;
    }
    
    public SelectTextView(Context context, AttributeSet attrs){
        this(context, attrs, android.R.attr.textViewStyle);
        this.context=context;
    }
    
    public SelectTextView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init();
    }
    
    private void init(){
        moreDrawable=getCompoundDrawables()[2];
        if (moreDrawable == null) {
            moreDrawable=getResources().getDrawable(R.drawable.ic_down);
        }
        moreDrawable.setBounds(0, 0, moreDrawable.getMinimumWidth(), moreDrawable.getMinimumHeight());
        setDownDrawableVisible(true);
    }

    protected void setDownDrawableVisible(boolean visible){
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], getCompoundDrawables()[1], visible ? moreDrawable : null, getCompoundDrawables()[3]);
    }
    
}
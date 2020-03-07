package com.android.material.lib.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.Interpolator;

import com.android.material.lib.drawable.CircleDrawable;
import com.android.material.lib.util.ViewUtil;

/**
 * Created by Rey on 2/5/2015.
 */
public class CircleCheckedTextView extends android.widget.CheckedTextView {

    private CircleDrawable mBackground;

    public interface OnCheckedChangeListener{
        void onCheckedChanged(CircleCheckedTextView view, boolean checked);
    }

    private OnCheckedChangeListener mCheckedChangeListener;

    public CircleCheckedTextView(Context context) {
        super(context);

        init(context, null, 0, 0);
    }

    public CircleCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs, 0, 0);
    }

    public CircleCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        setGravity(Gravity.CENTER);
        setPadding(0, 0, 0, 0);

        mBackground = new CircleDrawable();
        mBackground.setInEditMode(isInEditMode());
        mBackground.setAnimEnable(false);
        ViewUtil.setBackground(this, mBackground);
        mBackground.setAnimEnable(true);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener){
        mCheckedChangeListener = listener;
    }

    @Override
    public void setTextAppearance(int resId) {
        ViewUtil.applyTextAppearance(this, resId);
    }

    @Override
    public void setTextAppearance(Context context, int resId) {
        ViewUtil.applyTextAppearance(this, resId);
    }

    @Override
    public void setBackgroundColor(int color) {
        mBackground.setColor(color);
    }

    /**
     * Set the duration of background's animation.
     * @param duration The duration
     */
    public void setAnimDuration(int duration) {
        mBackground.setAnimDuration(duration);
    }

    public void setInterpolator(Interpolator in, Interpolator out) {
        mBackground.setInterpolator(in, out);
    }

    @Override
    public void setChecked(boolean checked) {
        boolean oldCheck = isChecked();

        if(oldCheck != checked) {
            super.setChecked(checked);

            if(mCheckedChangeListener != null)
                mCheckedChangeListener.onCheckedChanged(this, checked);
        }
    }

    public void setCheckedImmediately(boolean checked){
        mBackground.setAnimEnable(false);
        setChecked(checked);
        mBackground.setAnimEnable(true);
    }

}

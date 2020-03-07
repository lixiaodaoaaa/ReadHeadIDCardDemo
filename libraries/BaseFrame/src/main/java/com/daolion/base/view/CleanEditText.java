package com.daolion.base.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.daolion.base.utils.DensityUtils;
import com.ll.base.R;

import java.lang.reflect.Field;

/**
 * 带有清除按钮的输入框
 *
 * @author miaoxiongfei@foxmail.com
 * @date 2016-08-15 16:52
 */
public class CleanEditText extends android.support.v7.widget.AppCompatEditText {


    private int touthRightX;
    private boolean isSupportClean = false;
    private Drawable rightDrawableIcon;

    public CleanEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CleanEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    public CleanEditText(Context context) {
        super(context);
        init(null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //计算右侧清空按钮点击的X位置
        if (null != rightDrawableIcon) {
            touthRightX = getRight() - rightDrawableIcon.getBounds().width() - DensityUtils.dp2px(getContext(), 12);
        }

    }

    private void init(AttributeSet attributeSet) {
        rightDrawableIcon = ContextCompat.getDrawable(getContext(), R.drawable.ic_edt_clean);
        rightDrawableIcon.setBounds(0, 0, rightDrawableIcon.getIntrinsicWidth(), rightDrawableIcon.getIntrinsicHeight());
        setCompoundDrawablePadding(DensityUtils.dp2px(getContext(), 12));
//        TypedArray edtTypedArray = getContext().obtainStyledAttributes(attributeSet,new int[]{android.R.attr.layout_height});
//        int layoutHeight = edtTypedArray.getLayoutDimension(0, ViewGroup.LayoutParams.WRAP_CONTENT);
//        edtTypedArray.recycle();

//        this.setCompoundDrawables(getCompoundDrawables()[0], null, rightDrawableIcon, null);

        setEditTextCursorDrawable();

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isSupportClean && s.length() > 0) {
                    CleanEditText.this.setCompoundDrawables(getCompoundDrawables()[0], null, rightDrawableIcon, null);
                    isSupportClean = true;
                    return;
                }
                if (isSupportClean && s.length() == 0) {
                    CleanEditText.this.setCompoundDrawables(getCompoundDrawables()[0], null, null, null);
                    isSupportClean = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * 设置光标颜色，4.2~4.4 不显示光标的问题，用code创建editTextView，mCursorDrawableRes没有值，且没有公开的函数可以在代码中设置
     */
    private void setEditTextCursorDrawable() {
        //http://stackoverflow.com/questions/7238450/set-edittext-cursor-color
        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(this, R.drawable.cursor_editview);
        } catch (Exception ignored) {
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (isSupportClean == true) {
                    if (event.getRawX() >= touthRightX) {
                        clickRightIcon();
                        return false;  //返回false避免点击时Edtittext会获取焦点的问题
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if (isSupportClean == true) {
                    if (event.getRawX() >= touthRightX) {
                        return true;   //返回true避免多次出发多次点击
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }


    private void clickRightIcon() {
        this.getText().clear();
    }


}

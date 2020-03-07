package com.brc.idauth.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.brc.idauth.R;
import com.daolion.base.utils.ResourceReader;
import com.daolion.base.utils.SpannableStringBuilder;


/**
 * @version V2.0
 * @Description 带边框背景的EditText，边框颜色和字体颜色相同，白色背景
 */
public class InputBgEditText extends LinearLayout {


    private EditText etInput;
    private LinearLayout inputEditTextBgLayout;
    private String hintText = "";


    public InputBgEditText(Context context) {
        super(context);
    }


    public InputBgEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViewsByAttrs(attrs);
    }

    public InputBgEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    private void initViewsByAttrs(AttributeSet attrs) {
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.layout_input_bg_edittext, this, false);
        addView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        TypedArray typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.InputBgEditText);
        hintText = typeArray.getString(R.styleable.InputBgEditText_hintText);
        typeArray.recycle();

        initAllViews();
    }

    public EditText getEditText() {
        return etInput;
    }


    public Editable getText() {
        if (null != etInput) {
            return etInput.getText();
        }
        return null;
    }

    public void setText(CharSequence charSequence) {
        if (null != etInput) {
            etInput.setText(charSequence);
        }
    }

    public EditText getEtInput() {
        return etInput;
    }

    public void setEtInput(EditText etInput) {
        this.etInput = etInput;
    }

    private void initAllViews() {
        etInput = findViewById(R.id.et_input);
        inputEditTextBgLayout = findViewById(R.id.layout_input_bg_et);
        setHint(hintText);
        etInput.setHint(hintText);
    }


    public void setBackGroud(int drawableId) {
        inputEditTextBgLayout.setBackground(ResourceReader.readDrawable(getContext(), drawableId));
    }

    public void setHint(String hint) {
        SpannableStringBuilder.TextSpan textSpan = new SpannableStringBuilder.TextSpan();
        textSpan.setForegroundColor(ContextCompat.getColor(getContext(), R.color.font_sub
        ));
        textSpan.setAbsoluteSize((int) (14 * getResources().getDisplayMetrics().scaledDensity));
        etInput.setHint(new SpannableStringBuilder().append(hint, textSpan).getSpannableString());
    }

}


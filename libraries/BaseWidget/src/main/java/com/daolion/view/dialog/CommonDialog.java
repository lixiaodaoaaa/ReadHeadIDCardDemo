package com.daolion.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.yunlin.basewidget.R;


/**
 * 联豆平台风格的通用型对话框
 *
 * @author miaoxiongfei@foxmail.com
 * @date 2016-07-07 14:28
 */
public final class CommonDialog {

    private Dialog mDialog;


    public static class Builder {
        private Context context;
        private CharSequence titleCharSequence;
        private CharSequence messageCharSequence;

        private CharSequence positiveButtonString;
        private OnConfirmClickListener positiveButtonClickListener;

        private CharSequence negativeButtonString;
        private OnCancleClickListener negativeButtonClickListener;

        private CharSequence checkboxStr;
        private boolean isChecked;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

        private boolean isSystemAlert;
        private boolean cancelable;
        private boolean enableCloseButton = true;       //是否启用关闭按钮；默认true，右上角有关闭按钮

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(@StringRes int titleId) {
            titleCharSequence = context.getString(titleId);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            titleCharSequence = title;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            messageCharSequence = context.getString(messageId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            messageCharSequence = message;
            return this;
        }

        public Builder setPositiveButton(@StringRes int textId, final CommonDialog.OnConfirmClickListener listener) {
            positiveButtonString = context.getString(textId);
            positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final CommonDialog.OnConfirmClickListener listener) {
            positiveButtonString = text;
            positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(@StringRes int textId, final CommonDialog.OnCancleClickListener listener) {
            negativeButtonString = context.getString(textId);
            negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, final CommonDialog.OnCancleClickListener listener) {
            negativeButtonString = text;
            negativeButtonClickListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setSystemAlert(boolean isSystemAlert) {
            this.isSystemAlert = isSystemAlert;
            return this;
        }

        public Builder setCheckable(CharSequence checkboxText, boolean isChecked) {
            this.isChecked = isChecked;
            this.checkboxStr = checkboxText;
            this.onCheckedChangeListener = null;
            return this;
        }

        public Builder setCheckable(CharSequence checkboxText, boolean isChecked, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.isChecked = isChecked;
            this.checkboxStr = checkboxText;
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public CommonDialog create() {
            CommonDialog commonDialog = new CommonDialog();
            View view = buildView(commonDialog);
            commonDialog.mDialog = new Dialog(context, R.style.AppTheme_Dialog);
            commonDialog.mDialog.setContentView(view);
            commonDialog.mDialog.setCancelable(cancelable);
            if (isSystemAlert) {
                commonDialog.mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            }
            return commonDialog;
        }

        private View buildView(final CommonDialog commonDialog) {
            View view = LayoutInflater.from(context).inflate(R.layout.common_dialog, null);
            TextView messageTv = (TextView) view.findViewById(R.id.common_dialog_message_tv);
            if (TextUtils.isEmpty(titleCharSequence)) {
                view.findViewById(R.id.common_dialog_title_tv).setVisibility(View.GONE);
            } else {
                ((TextView) view.findViewById(R.id.common_dialog_title_tv)).setText(titleCharSequence);
            }
            //message
            messageTv.setMovementMethod(ScrollingMovementMethod.getInstance());
            if (TextUtils.isEmpty(messageCharSequence)) {
                messageTv.setVisibility(View.GONE);
            } else {
                //设置TextView 居中 当只有一行的时候  二行的时候就靠左【UI设计】
                messageTv.setText(messageCharSequence);
                setTextCenterIfOnlyOneLine(messageTv);
            }
            //positive button
            if (TextUtils.isEmpty(positiveButtonString) && positiveButtonClickListener == null) {
                view.findViewById(R.id.common_dialog_positive_btn).setVisibility(View.GONE);
            } else {

                Button button = view.findViewById(R.id.common_dialog_positive_btn);
                button.setText(TextUtils.isEmpty(positiveButtonString) ? "确定" : positiveButtonString);
                button.setOnClickListener(v -> {
                    positiveButtonClickListener.onClick();
                    if (commonDialog != null) {
                        commonDialog.dismiss();
                    }
                });
            }
            if (!TextUtils.isEmpty(checkboxStr)) {
                com.android.material.lib.widget.CheckBox checkBox = (com.android.material.lib.widget.CheckBox) view.findViewById(R.id.common_dialog_ck);
                checkBox.setVisibility(View.VISIBLE);
                checkBox.setChecked(isChecked);
                checkBox.setText(checkboxStr);
                checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
            }

            //negative button
            if (TextUtils.isEmpty(negativeButtonString) && negativeButtonClickListener == null) {
                view.findViewById(R.id.common_dialog_negative_btn).setVisibility(View.GONE);
            } else {
                Button button = (Button) view.findViewById(R.id.common_dialog_negative_btn);
                button.setText(TextUtils.isEmpty(negativeButtonString) ? "取消" : negativeButtonString);
                button.setOnClickListener(v -> {
                    if (commonDialog != null) {
                        commonDialog.dismiss();
                    }
                    if (negativeButtonClickListener != null) {
                        negativeButtonClickListener.onClick(((CheckBox) view.findViewById(R.id.common_dialog_ck)).isChecked());
                    }
                });
            }
            if (!enableCloseButton) {
                view.findViewById(R.id.common_dialog_close_iv).setVisibility(View.GONE);
            } else {
                view.findViewById(R.id.common_dialog_close_iv).setOnClickListener(v -> {
                    if (commonDialog != null) {
                        commonDialog.dismiss();
                    }
                });
            }
            return view;
        }

        /**
         * 只有一行的时候设置Textview 居中显示
         *
         * @param messageTv
         */
        private void setTextCenterIfOnlyOneLine(final TextView messageTv) {
            ViewTreeObserver vto = messageTv.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation")
                @Override
                public void onGlobalLayout() {
                    messageTv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    messageTv.getHeight();
                    float w0 = messageTv.getWidth();//控件宽度
                    float w1 = messageTv.getPaint().measureText(messageTv.getText().toString());//文本宽度
                    if (w1 < w0) {
                        messageTv.setGravity(Gravity.CENTER);
                        //相当于Layout中的 android:"gravity_center" 文本居中
                    }
                }
            });
        }
    }


    public void show() {
        if (null != mDialog && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public interface OnCancleClickListener {
        void onClick(boolean isChecked);
    }


    public interface OnConfirmClickListener {
        void onClick();
    }


}

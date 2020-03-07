package com.brc.idauth.ui.forgetpassword;

import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.android.material.lib.widget.Button;
import com.brc.idauth.Constants;
import com.brc.idauth.R;
import com.brc.idauth.base.BaseFragment;
import com.brc.idauth.ui.view.InputBgEditText;
import com.daolion.base.anotation.BsFragmentAnnotation;
import com.daolion.base.utils.ResourceReader;
import com.daolion.base.utils.SharedPrefsUtils;
import com.daolion.base.utils.StringUtils;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-06
       Time     :  11:23
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */

@BsFragmentAnnotation(fragmentLayoutId = R.layout.fragment_forget_password)
public class ForgetPasswordFragment extends BaseFragment {

    @BindView(R.id.et_answer1) InputBgEditText etAnswer1;
    @BindView(R.id.et_answer2) InputBgEditText etAnswer2;
    @BindView(R.id.et_answer3) InputBgEditText etAnswer3;
    @BindView(R.id.btn_next_step) Button buttonNextStep;


    @Override
    protected void initViewByRootView(View rootView) {

        etAnswer1.setBackGroud(R.drawable.shape_input_answer_bg);
        etAnswer2.setBackGroud(R.drawable.shape_input_answer_bg);
        etAnswer3.setBackGroud(R.drawable.shape_input_answer_bg);
        etAnswer1.getEditText().setTextColor(ResourceReader.readColor(getContext(), R.color.black));
        etAnswer2.getEditText().setTextColor(ResourceReader.readColor(getContext(), R.color.black));
        etAnswer3.getEditText().setTextColor(ResourceReader.readColor(getContext(), R.color.black));

        Observable<CharSequence> etAnswerOneObservable = RxTextView.textChanges(etAnswer1.getEditText());
        Observable<CharSequence> etAnswerTwoObservable = RxTextView.textChanges(etAnswer1.getEditText());
        Observable<CharSequence> etAnswerThreeObservable = RxTextView.textChanges(etAnswer1.getEditText());
        Observable.combineLatest(etAnswerOneObservable, etAnswerTwoObservable, etAnswerThreeObservable,
                (answer1, answer2, answer3) -> {
                    if (StringUtils.isEmpty(answer1.toString())) {
                        return false;
                    }
                    if (StringUtils.isEmpty(answer2.toString())) {
                        return false;
                    }
                    if (StringUtils.isEmpty(answer3.toString())) {
                        return false;
                    }
                    if (!SharedPrefsUtils.getStringPreference(getContext(), Constants.KEY_ANSWER1).equals(answer1)) {
                        Toast.makeText(getContext(), "输入的答案不正确", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    if (!SharedPrefsUtils.getStringPreference(getContext(), Constants.KEY_ANSWER2).equals(answer2)) {
                        Toast.makeText(getContext(), "输入的答案不正确", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    if (!SharedPrefsUtils.getStringPreference(getContext(), Constants.KEY_ANSWER1).equals(answer3)) {
                        Toast.makeText(getContext(), "输入的答案不正确", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    return true;
                }).subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Boolean canComplete) {
                buttonNextStep.setEnabled(canComplete);
            }
        });


    }

    @Override
    protected void handleMessage(Message msg) {

    }


    @OnClick(R.id.btn_next_step)
    public void onClickNextStep() {
        //TODO 跳转到重设密码页面；


    }
}

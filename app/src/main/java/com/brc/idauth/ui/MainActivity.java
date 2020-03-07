package com.brc.idauth.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brc.idauth.R;
import com.brc.idauth.base.BaseActivity;
import com.brc.idauth.ui.mine.MineContract;
import com.brc.idauth.ui.mine.MineModle;
import com.brc.idauth.ui.mine.MinePresenter;
import com.brc.idauth.ui.records.RecordsActivity;
import com.brc.idauth.ui.verify.VerifyActivity;
import com.brc.idauth.ui.view.MainItemView;
import com.brc.idauth.utils.DateFormatterUtils;
import com.brc.idauth.utils.FaceSDKUtils;
import com.daolion.base.anotation.BsActivityAnnotation;
import com.daolion.base.utils.ActivityUtil;
import com.google.common.base.Preconditions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;


@BsActivityAnnotation(layoutId = R.layout.activity_main, isFullScreen = true)
public class MainActivity extends BaseActivity<MineContract.Presenter> implements MineContract.View {


    @BindView(R.id.ll_verify_layout)
    LinearLayout llVerifyLayout;
    @BindView(R.id.ll_records)
    MainItemView llRecords;
    @BindView(R.id.ll_owen_center)
    MainItemView llOwenCenter;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_time)
    TextView tvTime;

    public static void launch(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        ActivityUtil.switchTo(context, intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MinePresenter(this, new MineModle());
        FaceSDKUtils.initSDK(getApplicationContext());
        tvDate.setText(DateFormatterUtils.stringData());
        setDateTime();
    }


    @OnClick(R.id.ll_verify_layout)
    public void onClickVerifyLayout() {
        VerifyActivity.launch(this);
    }

    @OnClick(R.id.ll_records)
    public void onClickRecordsLayout() {
        startActivity(new Intent(this, RecordsActivity.class));
    }

    @OnClick(R.id.ll_owen_center)
    public void onViewClicked() {

    }

    @OnClick(R.id.iv_setting)
    public void toSetting(){
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }

    private void setDateTime() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> tvTime.setText(new SimpleDateFormat("HH:mm").format(new Date())));
            }
        }, 0, 1000);
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

}

package com.brc.idauth.ui.records;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.brc.idauth.R;
import com.brc.idauth.utils.DateFormatterUtils;
import com.brc.idauth.utils.picuploaduitls.PicUploadDatabase;
import com.brc.idauth.utils.picuploaduitls.PicUploadInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecordsActivity extends AppCompatActivity {
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.records_recyclerView)
    RecyclerView recordsRecyclerView;

    List<PicUploadInfo> datas;
    RecordsAdapter adapter;

    @BindView(R.id.radio_button_all)
    RadioButton radioButtonAll;
    @BindView(R.id.radio_button_today)
    RadioButton radioButtonToday;
    @BindView(R.id.radio_button_week)
    RadioButton radioButtonWeek;
    @BindView(R.id.radio_button_month)
    RadioButton radioButtonMonth;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.iv_switch)
    ImageView ivSwitch;

    long startTime = 0L,begingTimeSS = 0L ,endTimeSS = 24*60*60*1000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (etSearch !=null && imm != null){
                    imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);  //强制隐藏
                }
            }
        },200);
    }

    private void init() {
        datas = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recordsRecyclerView.setLayoutManager(manager);
        recordsRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new RecordsAdapter(R.layout.records_item, datas);
        recordsRecyclerView.setAdapter(adapter);
        tvDate.setText(DateFormatterUtils.stringData());
        setDateTime();
        selectInfo();
        radioButtonAll.setChecked(true);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                selectInfo();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.radio_button_all:
                    startTime = 0L;
                    selectInfo();
                    break;
                case R.id.radio_button_today:
                    startTime = DateFormatterUtils.getOldDate(-1);
                    selectInfo();
                    break;
                case R.id.radio_button_week:
                    startTime = DateFormatterUtils.getOldDate(-7);
                    selectInfo();
                    break;
                case R.id.radio_button_month:
                    startTime = DateFormatterUtils.getOldDate(-30);
                    selectInfo();
                    break;
            }
        });

        ivSetting.setOnClickListener(view -> {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        });

        tvStartTime.setOnClickListener(view -> {
            TimePickerBuilder pvTime1 = new TimePickerBuilder(this,
                    (date, v) -> {

                        long time = date.UTC(date.getYear(),date.getMonth(),date.getDay(),date.getHours(),date.getMinutes(),date.getSeconds())%(1000*60*60*24);
                        long dateTime = date.getTime();
                        if (time > endTimeSS){
                            Toast.makeText(RecordsActivity.this,"开始时间不得大于结束时间！",Toast.LENGTH_LONG).show();
                            return;
                        }
                        begingTimeSS = time;
                        tvStartTime.setText(new SimpleDateFormat("HH:mm").format(date));
                        selectInfo();
                    });
             pvTime1.setType(new boolean[]{false,false,false,true,true,false}).build().show();
        });

        tvEndTime.setOnClickListener(view -> {
            TimePickerBuilder pvTime1 = new TimePickerBuilder(this,
                    (date, v) ->{
                        long time = date.UTC(date.getYear(),date.getMonth(),date.getDay(),date.getHours(),date.getMinutes(),date.getSeconds())%(1000*60*60*24);
                        if (begingTimeSS > time){
                            Toast.makeText(RecordsActivity.this,"开始时间不得大于结束时间！",Toast.LENGTH_LONG).show();
                            return;
                        }
                        endTimeSS = time;
                        tvEndTime.setText(new SimpleDateFormat("HH:mm").format(date));
                        selectInfo();
                    });
            pvTime1.setType(new boolean[]{false,false,false,true,true,false}).build().show();
        });
    }

    private void setDateTime() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> tvTime.setText(new SimpleDateFormat("HH:mm").format(new Date())));
            }
        }, 0, 1000);
    }

    public void selectInfo() {
        String name = etSearch.getText().toString().trim();
        PicUploadDatabase.getInstance().getPicUploadDao().selectList(name, startTime, System.currentTimeMillis())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PicUploadInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<PicUploadInfo> picUploadInfos) {
                        if (null != picUploadInfos){
                            for (int i=picUploadInfos.size() - 1;i>=0;i--){
                                PicUploadInfo info = picUploadInfos.get(i);
                                Date date = new Date();
                                date.setTime(info.createTime);
                                long time = date.UTC(date.getYear(),date.getMonth(),date.getDay(),date.getHours(),date.getMinutes(),date.getSeconds())%(1000*60*60*24);
                                if (begingTimeSS >= time || time > endTimeSS){
                                    picUploadInfos.remove(i);
                                }
                            }
                            datas = picUploadInfos;
                        }
                        adapter.setNewData(datas);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}

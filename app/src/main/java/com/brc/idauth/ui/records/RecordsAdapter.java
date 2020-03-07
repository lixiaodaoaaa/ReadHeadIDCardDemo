package com.brc.idauth.ui.records;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import com.brc.idauth.R;
import com.brc.idauth.utils.DateFormatterUtils;
import com.brc.idauth.utils.picuploaduitls.PicUploadInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class RecordsAdapter extends BaseQuickAdapter<PicUploadInfo, BaseViewHolder> {
    public RecordsAdapter(int layoutResId, @Nullable List<PicUploadInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PicUploadInfo item) {
        String result = item.verifyResult?"成功":"失败";
        Bitmap bitmap_idcard = BitmapFactory.decodeFile(item.picB);
        Bitmap bitmap_scene = BitmapFactory.decodeFile(item.picA);
        helper.setText(R.id.recordsid,String.valueOf(item.id))
                .setText(R.id.records_name,item.info.getName())
                .setText(R.id.records_result,result)
                .setText(R.id.records_time, DateFormatterUtils.formLongToOrderTime(item.createTime))
                .setImageBitmap(R.id.records_idcard,bitmap_idcard)
                .setImageBitmap(R.id.records_scene,bitmap_scene);
    }


    /*
     * 毫秒转化
     */
    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return strHour+"小时"+strMinute + " 分钟 " + strSecond + " 秒";
    }
}

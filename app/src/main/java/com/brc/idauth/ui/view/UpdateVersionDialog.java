
package com.brc.idauth.ui.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.daolion.base.utils.ActivityUtil;
import com.daolion.base.utils.ResourceReader;
import com.daolion.bean.UpdateVersionBean;
import com.daolion.utils.FileUtil;
import com.daolion.utils.StringUtils;
import com.yunlin.basewidget.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateVersionDialog {

    private Dialog mDialog;

    private String strDownloadUrl;
    private Activity activity;
    private DownloadApkTask downloadTask;
    private boolean isBackGround = false;

    private Notification mNotification;
    private NotificationManager mNotificationManager;
    private String ACTION_CANCEL_DOWNLOAD_APK = "action_cancel_download_apk";
    public static final int NOTIFY_ID = 0;

    private int apkTotalLength = 0;
    private int currentDownloadLength = 0;
    private int currentProgress = 0;
    private Context context;
    private String messageCharSequence;
    private boolean isForceUpdate = false;
    private String fileName = "";

    private UpdateVersionBean updateVersionBean;
    private String title;


    public UpdateVersionDialog(Activity activity, UpdateVersionBean updateVersionBean) {
        this.activity = activity;
        this.context = activity;
        this.messageCharSequence = updateVersionBean.getDescription();
        this.strDownloadUrl = updateVersionBean.getUrl();
        this.isForceUpdate = updateVersionBean.isIsMandatory();

        this.updateVersionBean = updateVersionBean;
        fileName = FileUtil.getDownloadFileName(updateVersionBean.getCode());
        downloadTask = new DownloadApkTask();
    }

    public void setTitle(String title) {
        this.title = title;
    }


    private ProgressBar progressBar;
    private Button positiveBtn;

    private View buildView(final UpdateVersionDialog commonDialog) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_update_dialog, null);
        TextView messageTv = (TextView) view.findViewById(R.id.common_dialog_message_tv);

        progressBar = (ProgressBar) view.findViewById(R.id.download_pb);
        progressBar.setProgressDrawable(ResourceReader.readDrawable(context, R.drawable.progressbar_positive_color));
        Button backDownloadBtn = (Button) view.findViewById(R.id.back_downloadBtn);
        TextView titleText = (TextView) view.findViewById(R.id.common_dialog_title_tv);
        titleText.setVisibility(View.VISIBLE);
        if (StringUtils.isNotEmpty(title)) {
            titleText.setText(title);
        } else {
            titleText.setText("版本更新");
        }

        //message
        messageTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (TextUtils.isEmpty(messageCharSequence)) {
            messageTv.setVisibility(View.GONE);
        } else {
            //设置TextView 居中 当只有一行的时候  二行的时候就靠左【UI设计】
            messageTv.setText(Html.fromHtml(messageCharSequence));
//            setTextCenterIfOnlyOneLine(messageTv);
        }
        //是否强制下载:


        ImageView closeDialogIv = (ImageView) view.findViewById(R.id.common_dialog_close_iv);
        if (isForceUpdate) {
            progressBar.setVisibility(View.VISIBLE);
            backDownloadBtn.setVisibility(View.GONE);
            closeDialogIv.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            backDownloadBtn.setVisibility(View.GONE);
            closeDialogIv.setVisibility(View.VISIBLE);
            closeDialogIv.setOnClickListener(v -> {
                if (null != mDialog) {
                    mDialog.dismiss();
                }
            });
        }

        //positive button

        positiveBtn = (Button) view.findViewById(R.id.common_dialog_positive_btn);
        if (updateVersionBean.isHasDownloadInSdCard()) {
            positiveBtn.setText("马上安装");
        } else {
            positiveBtn.setText("立即下载");
        }
        positiveBtn.setOnClickListener(v -> {
            if (isForceUpdate) {
                positiveBtn.setVisibility(View.GONE);
                downloadTask.execute();
            } else {
                if (updateVersionBean.isHasDownloadInSdCard()) {
                    installApkFile();
                } else {
                    setUpNotification();
                    isBackGround = true;
                    downloadTask.execute();
                    mDialog.dismiss();
                    Toast.makeText(getContext(),"已开始下载最新版本",Toast.LENGTH_SHORT).show();
                }
            }
        });

        com.android.material.lib.widget.CheckBox checkBox = (com.android.material.lib.widget.CheckBox) view.findViewById(R.id.common_dialog_ck);
        if (!updateVersionBean.isFromMain()) {
            checkBox.setVisibility(View.GONE);
        } else {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
//                    String ignoreVersionName = FrameApplication.getInstance().getConfig().getString(Constants.CONFIG_NEW_VERSION_NAME, "");
//                    if (StringUtils.isNotEmpty(ignoreVersionName)) {
//                        SharedPrefsUtils.setStringPreference(context, Constants.CONFIG_IGNORE_VERSION_NAME, ignoreVersionName);
//                    }
                } else {
//                    SharedPrefsUtils.setStringPreference(context, Constants.CONFIG_IGNORE_VERSION_NAME, "");
                }
            });
        }

        Button button = (Button) view.findViewById(R.id.common_dialog_negative_btn);


        if (isForceUpdate) {
            checkBox.setVisibility(View.GONE);
            button.setText("退出应用");
            button.setOnClickListener(v -> {
                downloadTask.cancel(true);
                this.dismiss();
                ActivityUtil.exitApp(activity);
                activity.finish();
            });
        } else {
            if (updateVersionBean.isFromMain()) {
                checkBox.setVisibility(View.VISIBLE);
            } else {
                checkBox.setVisibility(View.GONE);
            }
            button.setText("暂不升级");
            button.setOnClickListener(v -> {
                if (checkBox.isChecked()) {
//                    String ignoreVersionName = PlatformApplication.getInstance().getConfig().getString(Constants.CONFIG_NEW_VERSION_NAME, "");
//                    if (StringUtils.isNotEmpty(ignoreVersionName)) {
//                        SharedPrefsUtils.setStringPreference(context, Constants.CONFIG_IGNORE_VERSION_NAME, ignoreVersionName);
//                    }
                }
                if (mDialog != null) {
                    mDialog.dismiss();
                }
                if (downloadTask != null) {
                    downloadTask.cancel(true);
                }
            });

        }
        return view;
    }


    public Context getContext() {
        return this.mDialog.getContext();
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
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setUpProgressDialog(double currentProgress, double totalLenth) {
//        progressBar.setProgressNumberFormat(String.format("%.2fM/%.2fM", currentProgress, totalLenth));
    }


    int lastProgress = 0;

    private class DownloadApkTask extends AsyncTask<Void,Integer,Void> {
        @Override
        protected void onProgressUpdate(Integer... values) {
            currentDownloadLength += values[0];
            progressBar.setMax(apkTotalLength);
            if (isBackGround) {
                currentProgress = (int) (((float) currentDownloadLength / apkTotalLength) * 100);
                if (currentProgress > lastProgress + 1) {
                    currentProgress = lastProgress;
                    lastProgress++;
                    RemoteViews contentview = mNotification.contentView;
//                    contentview.setTextViewText(R.id.tv_progress, currentProgress + "%");
//                    contentview.setProgressBar(R.id.progressbar, 100, currentProgress, false);
                    mNotificationManager.notify(NOTIFY_ID, mNotification);
                }
                //下载完成
                if (currentDownloadLength == apkTotalLength) {
                    mNotification.flags = Notification.FLAG_AUTO_CANCEL;
                    mNotification.contentView = null;
                    Intent intent = new Intent();
                    intent.putExtra("completed", "yes");
//                    PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                    mNotification.setLatestEventInfo(getContext(), "下载完成", "文件已下载完毕", contentIntent);
                    mNotificationManager.cancel(NOTIFY_ID);
                    installApkFile();
                }
            } else {
                progressBar.setProgress(currentDownloadLength);
                double totalSize = apkTotalLength / 1024 / 1024d;
                double currentSize = currentDownloadLength / 1024 / 1024d;
                setUpProgressDialog(currentSize, totalSize);
            }
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            if (isBackGround) {
                mNotificationManager.cancel(NOTIFY_ID);
            }
            installApkFile();
            super.onPostExecute(result);
        }


        @Override
        protected Void doInBackground(Void... params) {
            URL downloadUrl = null;
            try {
                downloadUrl = new URL(strDownloadUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if (downloadUrl == null) {
                return null;
            }
            HttpURLConnection conn = null;
            InputStream is = null;
            FileOutputStream fileOutputStream = null;
            File downloadFile = null;

            try {
                conn = (HttpURLConnection) downloadUrl.openConnection();
                int length = conn.getContentLength();
                is = conn.getInputStream();
                if (is != null) {
                    downloadFile = FileUtil.getDownloadFile(context, updateVersionBean.getCode());
                    if (null != downloadFile && downloadFile.length() == length) {
                        installApkFile();
                        return null;
                    }
                    fileOutputStream = new FileOutputStream(downloadFile);
                    byte[] buf = new byte[1024 * 1024];
                    int ch = -1;
                    writePathToAndroidSystem();
                    apkTotalLength = length;
                    while ((ch = is.read(buf)) != -1 && !DownloadApkTask.this.isCancelled()) {
                        fileOutputStream.write(buf, 0, ch);
                        publishProgress(ch);
                    }
                }
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                if (null != downloadFile) {
                    downloadFile.delete();
                }
            } finally {
                if (null != conn) {
                    conn.disconnect();
                }
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    private void writePathToAndroidSystem() {
        // [文件夹705:drwx---r-x]
        String path = activity.getCacheDir().getAbsolutePath();
        if (StringUtils.isNotEmpty(path)) {
            String[] args1 = {"chmod", "705", path};
            exec(args1);
            // [文件604:-rw----r--]
            String[] args2 = {"chmod", "604", path + "/" + fileName};
            exec(args2);
        }
    }


    /**
     * 执行Linux命令，并返回执行结果。
     */
    public static void exec(String[] args) {
        try {
            Runtime.getRuntime().exec(args[0] + " " + args[1] + " " + args[2]).waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpNotification() {
        mNotificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_CANCEL_DOWNLOAD_APK);
        activity.registerReceiver(onclickCancelListener, filter);
        // 放置在"正在运行"栏目中
        mNotification = buildNotification();
        mNotification.defaults = Notification.DEFAULT_LIGHTS;
        mNotification.contentView = getRemoteViews();
        mNotificationManager.notify(NOTIFY_ID, mNotification);
    }

    private Notification buildNotification() {
        if (mNotification == null) {
            Notification.Builder builder = new Notification.Builder(getContext());
            builder.setAutoCancel(false);
            builder.setTicker("开始下载");
//            builder.setSmallIcon(R.drawable.ic_about);
            builder.setContentTitle("...");
            builder.setOngoing(true);
            builder.setNumber(100);
            builder.setWhen(System.currentTimeMillis());
//            builder.setContentIntent(getPendingIntent());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mNotification = builder.build();
            } else {
                mNotification = builder.getNotification();
            }
        }
        return mNotification;
    }


    private PendingIntent getPendingIntent(Class<?> cls) {
        return PendingIntent.getActivity(activity, 0, new Intent(activity, cls), PendingIntent.FLAG_UPDATE_CURRENT);
    }


    private RemoteViews getRemoteViews() {
//        RemoteViews contentView = new RemoteViews(activity.getPackageName(), R.layout.view_download_notification);
//        contentView.setTextColor(R.id.download_notification_descriptionTv, ResourceReader.readColor(getContext(), R.color.font_title));
//        contentView.setTextViewText(R.id.download_notification_descriptionTv, "联豆.apk   正在下载...");
//        contentView.setOnClickPendingIntent(R.id.ivDelete, PendingIntent.getBroadcast(activity, 0, new Intent(ACTION_CANCEL_DOWNLOAD_APK), 0));
        return null;
    }

    public void show() {
        UpdateVersionDialog updateVersionDialog = new UpdateVersionDialog(activity, updateVersionBean);
        View view = buildView(updateVersionDialog);
        updateVersionDialog.mDialog = new Dialog(context, R.style.AppTheme_Dialog);
        updateVersionDialog.mDialog.setContentView(view);
        updateVersionDialog.mDialog.setCanceledOnTouchOutside(false);
        updateVersionDialog.mDialog.setCancelable(false);
        setDialog(updateVersionDialog.mDialog);
        if (null != updateVersionDialog.mDialog && !updateVersionDialog.mDialog.isShowing()) {
            updateVersionDialog.mDialog.show();
        }
    }

    public void dismiss() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void setDialog(Dialog mDialog) {
        this.mDialog = mDialog;
    }

    BroadcastReceiver onclickCancelListener = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_CANCEL_DOWNLOAD_APK.equals(intent.getAction())) {
                downloadTask.cancel(true);
                mNotificationManager.cancel(NOTIFY_ID);
            }
        }
    };

    private void installApkFile() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(FileUtil.getDownloadApkPath(context, fileName), fileName)),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (activity != null) {
            activity.startActivity(intent);
            activity.finish();
        }
    }

}

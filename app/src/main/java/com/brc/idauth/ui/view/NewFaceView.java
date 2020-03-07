package com.brc.idauth.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;

import cn.face.sdk.FaceGroup;
import cn.face.sdk.FaceInfo;
import cn.face.utils.DisplayUtil;
import cn.face.view.FaceView;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-16
       Time     :  18:11
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class NewFaceView extends TextureView implements TextureView.SurfaceTextureListener {

    private static final String TAG = FaceView.class.getName();

    Context mContext;

    Paint mLinePaint, mTextPaint, mPointPaint;
    FaceInfo[] faceInfos;
    private int faceNum;
    int textSize = 22;
    private int surfaceW, surfaceH;
    private double scaleX;
    private double scaleY;
    private double scaleCenter1;
    private double scaleCenter2;
    int frameWidth;
    int frameHight;

    private Thread alwaysDrawThread;
    private boolean isStop = false;

    public NewFaceView(Context context) {
        super(context);
        this.mContext = context;
        initPaint();

        initDatas();


    }

    private void initDatas() {
        if (alwaysDrawThread == null) {
            alwaysDrawThread = new Thread(() -> {
                if (!isStop) {
                    executeDrawRect();
                }
            });
        }
        setSurfaceTextureListener(this);
    }

    public NewFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initPaint();
    }

    public NewFaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initPaint();
    }

    public NewFaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        initPaint();
    }


    /**
     * setFaces:设置人脸框和人脸关键点. <br/>
     *
     * @param faceInfos
     * @param faceNum
     * @author:284891377 Date: 2016-4-29 上午9:45:19
     * @since JDK 1.7
     */
    public void setFaces(FaceInfo[] faceInfos, int faceNum) {
        this.faceInfos = faceInfos;
        this.faceNum = faceNum;
        invalidate();
    }


    public void setFaces(FaceGroup faceGroup) {
        this.faceInfos = faceGroup.getFaceInfos();
        this.faceNum = faceGroup.getNum();
        isStop = false;
        if (alwaysDrawThread == null) {
            alwaysDrawThread = new Thread(() -> {
                if (!isStop) {
                    executeDrawRect();
                }
            });
        }

        alwaysDrawThread.start();
    }


    public void setSurfaceWH(int surfaceW, int surfaceH, int frameWidth, int frameHight) {
        this.surfaceW = surfaceW;
        this.surfaceH = surfaceH;
        this.frameWidth = frameWidth;
        this.frameHight = frameHight;
        scaleX = 1.72;
        scaleCenter1 = 1.12;
        scaleCenter2 = 1.59;
        scaleY = 1.756;
        Log.i(TAG, "scale x is" + scaleX);
        Log.i(TAG, "scale y is" + scaleY);
        clearBeforeMarker();
    }

    private void executeDrawRect() {
        Canvas canvas = lockCanvas();
        if (canvas == null) {
            unlockCanvasAndPost(canvas);
            return;
        }
        // 如果没有检测到人脸
        if (faceInfos == null || faceInfos.length == 0) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            unlockCanvasAndPost(canvas);
            return;
        }

        mLinePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(mLinePaint);

        mTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(mTextPaint);

        mTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        mLinePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));


        for (int i = 0; i < faceNum; i++) {
            FaceInfo faceInfo = faceInfos[i];
            // 人脸坐标转换
            int left = (int) (faceInfo.x * scaleX);
            int top = (int) (faceInfo.y * scaleY);

            int right = (int) ((faceInfo.width * scaleX + left * scaleCenter1));
            int bottom = (int) ((faceInfo.height * scaleY + top * scaleCenter2));
            StringBuilder sb = new StringBuilder();
            sb.append("trackId: " + faceInfo.trackId);

            mTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

            canvas.drawText(sb.toString(), left, top - textSize, mTextPaint);
            canvas.drawPoint(left, top, mTextPaint);
            canvas.drawPoint(right, right, mTextPaint);
            canvas.drawRect(new Rect(left, top, right, bottom), mLinePaint);

//            canvas.getClipBounds()
        }
        unlockCanvasAndPost(canvas);
////
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                clearBeforeMarker();
//            }
//        }, 1 * 510);
    }


    public void clearBeforeMarker() {
        Canvas canvas = lockCanvas();
        if (canvas == null) {
            unlockCanvasAndPost(canvas);
            return;
        }
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        mLinePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(mLinePaint);
        mTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(mTextPaint);


        unlockCanvasAndPost(canvas);
    }


    private void initPaint() {
        textSize = DisplayUtil.dip2px(mContext, 16);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // int color = Color.rgb(0, 150, 255);
        int color = Color.rgb(98, 212, 68);
        mLinePaint.setColor(color);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(5f);
        mLinePaint.setAlpha(180);

        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setColor(color);
        mPointPaint.setStrokeWidth(10f);
        mPointPaint.setAlpha(180);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(color);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setAlpha(180);
    }


    private void drawRect(Canvas canvas) {
        if (faceInfos != null) {
            for (int i = 0; i < faceNum; i++) {
                FaceInfo faceInfo = faceInfos[i];
                // 人脸坐标转换
                int left = (int) (faceInfo.x * scaleX);
                int top = (int) (faceInfo.y * scaleY);

                int right = (int) ((faceInfo.width * scaleX + left * scaleCenter1));
                int bottom = (int) ((faceInfo.height * scaleY + top * scaleCenter2));
                StringBuilder sb = new StringBuilder();
                sb.append("trackId: " + faceInfo.trackId);

                canvas.drawText(sb.toString(), left, top - textSize, mTextPaint);
                canvas.drawPoint(left, top, mTextPaint);
                canvas.drawPoint(right, right, mTextPaint);
                canvas.drawRect(new Rect(left, top, right, bottom), mLinePaint);
            }

        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

        isStop = false;
        alwaysDrawThread.start();

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        isStop = true;
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}

package cn.face.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import cn.face.sdk.FaceGroup;
import cn.face.sdk.FaceInfo;
import cn.face.utils.DisplayUtil;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-10
       Time     :  14:49
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class SmartFaceView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = FaceView.class.getName();

    private Canvas canvas;
    //子线程标志位
    private boolean isDrawing;
    private SurfaceHolder surfaceHolder;
    private Context mContext;

    Paint mLinePaint, mTextPaint, mPointPaint;
    private volatile FaceInfo[] faceInfos;
    private volatile int faceNum;
    private int textSize = 44;
    private int surfaceW, surfaceH;
    private double scale;
    int frameWidth = 480;
    int frameHight = 640;


    private Thread drawRectThread;

    public SmartFaceView(Context context) {
        super(context);
        mContext = context;
        initView();
        initPaint();
    }


    public SmartFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
        initPaint();
    }

    public void setSurfaceWH(int surfaceW, int surfaceH, int frameWidth, int frameHight) {
        this.surfaceW = surfaceW;
        this.surfaceH = surfaceH;
        this.frameWidth = frameWidth;
        this.frameHight = frameHight;
        scale = surfaceW * 1d / frameWidth;
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
    }


    public void setFaces(FaceGroup faceGroup) {
        this.faceInfos = faceGroup.getFaceInfos();
        this.faceNum = faceGroup.getNum();
        isDrawing = true;


        if (null == drawRectThread) {
            drawRectThread = new Thread(() -> {
                if (isDrawing) {
                    drawFaceView();
                }
            });
        }
        drawRectThread.start();
    }


    private void initView() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setKeepScreenOn(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    private void drawFaceView() {
        //获得canvas对象
        canvas = getHolder().lockCanvas();

        if (faceInfos != null) {

            for (int i = 0; i < faceNum; i++) {

                FaceInfo faceInfo = faceInfos[i];
                // 人脸坐标转换

                int left = (int) (faceInfo.x * scale);
                int top = (int) (faceInfo.y * scale);

                int right = (int) ((faceInfo.width + faceInfo.x) * scale);
                int bottom = (int) ((faceInfo.height + faceInfo.y) * scale);
                StringBuilder sb = new StringBuilder();
                sb.append("trackId: " + faceInfo.trackId);

                canvas.drawText(sb.toString(), left, top - textSize, mTextPaint);
                canvas.drawPoint(left, top, mTextPaint);
                canvas.drawPoint(right, right, mTextPaint);
                canvas.drawRect(new Rect(left, top, right, bottom), mLinePaint);
                Log.i(TAG,"draing........");
            }

        }
        if (canvas != null) {
            //释放canvas对象并提交画布
            surfaceHolder.unlockCanvasAndPost(canvas);
            Log.i(TAG,"释放画布");
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }

}
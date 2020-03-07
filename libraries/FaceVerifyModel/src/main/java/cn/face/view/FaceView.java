package cn.face.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.face.sdk.FaceGroup;
import cn.face.sdk.FaceInfo;
import cn.face.utils.DisplayUtil;

public class FaceView extends View {
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

    public void setSurfaceWH(int surfaceW, int surfaceH, int frameWidth, int frameHight) {
        this.surfaceW = surfaceW;
        this.surfaceH = surfaceH;
        this.frameWidth = frameWidth;
        this.frameHight = frameHight;

        scaleX = 1.82;
        scaleCenter1=1.19;
        scaleCenter2=1.59;
        scaleY = 1.756;

        Log.i(TAG,"scale x is"+scaleX);
        Log.i(TAG,"scale y is"+scaleY);



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
        invalidate();
    }

    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        initPaint();

    }

    private void initPaint() {
        textSize = DisplayUtil.dip2px(mContext, 16);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // int color = Color.rgb(0, 150, 255);
        int color = Color.rgb(98, 212, 68);
        mLinePaint.setColor(color);
        mLinePaint.setStyle(Style.STROKE);
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

    @Override
    protected void onDraw(Canvas canvas) {
        if (faceInfos != null) {
            for (int i = 0; i < faceNum; i++) {
                FaceInfo faceInfo = faceInfos[i];
                // 人脸坐标转换
                int left = (int) (faceInfo.x * scaleX);
                int top = (int) (faceInfo.y * scaleY);

                int right = (int) ((faceInfo.width * scaleX + left*scaleCenter1));
                int bottom = (int) ((faceInfo.height * scaleY + top*scaleCenter2));
                StringBuilder sb = new StringBuilder();
                sb.append("trackId: " + faceInfo.trackId);

                canvas.drawText(sb.toString(), left, top - textSize, mTextPaint);
                canvas.drawPoint(left, top, mTextPaint);
                canvas.drawPoint(right, right, mTextPaint);
                canvas.drawRect(new Rect(left, top, right, bottom), mLinePaint);
            }

        }
    }

}

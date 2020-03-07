package cn.face.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


public class FaceRectView extends View {
	private static final String TAG =FaceRectView.class.getName();

	Context mContext;

	private Paint mLinePaint;
	private Paint mPointPaint;
	private Rect mFace;

	
	int scale;

	public FaceRectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;

		initPaint();

		

	}

	

	
	public void setFace(Rect face, int  scale) {
		this.mFace = face;
		
		this.scale=scale;
		invalidate();
	}

	
	




	
	private void initPaint() {
		final float scale = getResources().getDisplayMetrics().density;

		mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		int color = Color.rgb(98, 212, 68);
		mLinePaint.setColor(color);
		mLinePaint.setStyle(Style.STROKE);
		mLinePaint.setStrokeWidth(5f);
		mLinePaint.setAlpha(180);
		
		mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		
		mPointPaint.setColor(Color.RED);
		
		mPointPaint.setStrokeWidth(8f);
		mPointPaint.setAlpha(180);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (mFace != null) {
			int left = Math.round(mFace.left * scale);
			int top = Math.round(mFace.top * scale);
			int right = Math.round(mFace.right * scale);
			int bottom = Math.round(mFace.bottom * scale);
			
			canvas.drawRect(new Rect(left, top, right, bottom), mLinePaint);
				
			
		}
		

//		canvas.drawLine(canvas.getWidth() / 2 - max_w / 2 - stroke_w / 2,
//				canvas.getHeight() / 2 - max_h / 2, canvas.getWidth() / 2
//						- max_w / 2 + line_len, canvas.getHeight() / 2 - max_h
//						/ 2, mLinePen); // 水平�?
//		canvas.drawLine(canvas.getWidth() / 2 - max_w / 2, canvas.getHeight()
//				/ 2 - max_h / 2, canvas.getWidth() / 2 - max_w / 2,
//				canvas.getHeight() / 2 - max_h / 2 + line_len, mLinePen); // 垂直�?
//
//		canvas.drawLine(canvas.getWidth() / 2 + max_w / 2 - line_len,
//				canvas.getHeight() / 2 - max_h / 2, canvas.getWidth() / 2
//						+ max_w / 2 + stroke_w / 2, canvas.getHeight() / 2
//						- max_h / 2, mLinePen);
//		canvas.drawLine(canvas.getWidth() / 2 + max_w / 2, canvas.getHeight()
//				/ 2 - max_h / 2, canvas.getWidth() / 2 + max_w / 2,
//				canvas.getHeight() / 2 - max_h / 2 + line_len, mLinePen);
//
//		canvas.drawLine(canvas.getWidth() / 2 - max_w / 2 - stroke_w / 2,
//				canvas.getHeight() / 2 + max_h / 2, canvas.getWidth() / 2
//						- max_w / 2 + line_len, canvas.getHeight() / 2 + max_h
//						/ 2, mLinePen);
//		canvas.drawLine(canvas.getWidth() / 2 - max_w / 2, canvas.getHeight()
//				/ 2 + max_h / 2, canvas.getWidth() / 2 - max_w / 2,
//				canvas.getHeight() / 2 + max_h / 2 - line_len, mLinePen);
//
//		canvas.drawLine(canvas.getWidth() / 2 + max_w / 2 - line_len,
//				canvas.getHeight() / 2 + max_h / 2, canvas.getWidth() / 2
//						+ max_w / 2 + stroke_w / 2, canvas.getHeight() / 2
//						+ max_h / 2, mLinePen);
//		canvas.drawLine(canvas.getWidth() / 2 + max_w / 2, canvas.getHeight()
//				/ 2 + max_h / 2, canvas.getWidth() / 2 + max_w / 2,
//				canvas.getHeight() / 2 + max_h / 2 - line_len, mLinePen);

	}




	

}

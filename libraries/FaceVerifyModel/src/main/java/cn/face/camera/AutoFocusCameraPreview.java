package cn.face.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * 自动聚焦</br>
 * 每隔5000ms调用mCamera.autoFocus
 *
 * @author yusr
 */
public class AutoFocusCameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = AutoFocusCameraPreview.class.getName();
    private Camera mCamera;
    int caremaId = Camera.CameraInfo.CAMERA_FACING_BACK;

    private boolean mPreviewing = true;
    private boolean mAutoFocus = true;
    private boolean mSurfaceCreated = false;

    Context context;
    float touchX;
    float touchY;
    Paint paint = new Paint();
    private CameraConfigurationManager mCameraConfigurationManager;

    public AutoFocusCameraPreview(Context context) {
        super(context);
        this.context = context;
    }

    public AutoFocusCameraPreview(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        this.context = context;

    }

    public AutoFocusCameraPreview(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.context = context;
        this.setKeepScreenOn(true);// 保持屏幕常亮
    }

    public void setCamera(Camera camera) {
        mCamera = camera;
        if (mCamera != null) {
            mCameraConfigurationManager = new CameraConfigurationManager(getContext());
            mCameraConfigurationManager.initFromCameraParameters(mCamera);

            getHolder().addCallback(this);
            if (mPreviewing) {
                requestLayout();
            } else {
                showCameraPreview();
            }
        }
    }

    private void autoFocus() {

        try {
            if (mCamera != null)
                mCamera.autoFocus(autoFocusCB);
        } catch (Exception e) {

            // 聚焦失败
            e.printStackTrace();

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isTouch = true;
        touchX = event.getX();
        touchY = event.getY();
        mDelegate.onFocus(touchX, touchY);

        autoFocus();
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mSurfaceCreated = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        stopCameraPreview();
        showCameraPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mSurfaceCreated = false;
        stopCameraPreview();
    }

    public void showCameraPreview() {
        if (mCamera != null) {
            try {
                mPreviewing = true;
                mCamera.setPreviewDisplay(getHolder());

                mCameraConfigurationManager.setDesiredCameraParameters(mCamera, caremaId);
                mCamera.startPreview();
                if (mAutoFocus) {
                    autoFocus();

                }
            } catch (Exception e) {
                Log.i(TAG, e.toString());
            }
        }
    }

    // public void openOneShotPreview(){
    // if (mCamera != null) {
    // mCamera.setOneShotPreviewCallback(this);
    // }
    // }
    // public void closeOneShotPreview(){
    // if (mCamera != null) {
    // mCamera.setOneShotPreviewCallback(null);
    // }
    // }
    public void stopCameraPreview() {
        if (mCamera != null) {
            try {
                removeCallbacks(doAutoFocus);

                mPreviewing = false;
                mCamera.cancelAutoFocus();
                //closeOneShotPreview();
                mCamera.stopPreview();
            } catch (Exception e) {
                Log.i(TAG, e.toString());
            }
        }
    }

    public void openFlashlight() {
        if (flashLightAvaliable()) {
            mCameraConfigurationManager.openFlashlight(mCamera);
        }
    }

    public void closeFlashlight() {
        if (flashLightAvaliable()) {
            mCameraConfigurationManager.closeFlashlight(mCamera);
        }
    }

    private boolean flashLightAvaliable() {
        return mCamera != null && mPreviewing && mSurfaceCreated
                && getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (mCamera != null && mPreviewing && mAutoFocus && mSurfaceCreated) {

                autoFocus();

            }
        }
    };
    protected boolean isTouch;

    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            //openOneShotPreview();
            if (isTouch) mDelegate.onFocused();
            isTouch = false;

            postDelayed(doAutoFocus, 5000);

        }
    };

    /******************************************************************/
    public Size getPreviewSize() {
        Camera.Parameters parameters = mCamera.getParameters();
        return parameters.getPreviewSize();
    }

    com.brc.idauth.Face.camera.Delegate mDelegate;

    public void setDelegate(com.brc.idauth.Face.camera.Delegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    /**
     * 打开摄像头开始预览，但是并未开始识别
     */
    public void StartCamera() {
        if (mCamera != null) {
            return;
        }

        try {
            mCamera = Camera.open(caremaId);
        } catch (Exception e) {
            if (mDelegate != null) {
                mDelegate.onOpenCameraError();
            }
        }
        setCamera(mCamera);
    }

    /**
     * 关闭摄像头预览，并且隐藏扫描框
     */
    public void StopCamera() {
        if (mCamera != null) {
            stopCameraPreview();

            mCamera.release();
            mCamera = null;
        }
    }

    public void tackPicture(OnCaptureCallback callback) {
        try {
            mCameraConfigurationManager.tackPicture(mCamera, callback);
        } catch (Exception e) {

            // 聚焦失败
            e.printStackTrace();

        }
    }
    // @Override
    // public void draw(Canvas canvas) {
    //
    //// if(flag==1)canvas.drawBitmap(ImgUtil.decodeSampledBitmapFromResource(context.getResources(),
    // R.drawable.focus, //
    //// DisplayUtil.dip2px(context, 84), DisplayUtil.dip2px(context, 63)),
    // touchX, touchY, paint);
    //// if(flag==2)canvas.drawBitmap(ImgUtil.decodeSampledBitmapFromResource(context.getResources(),
    // R.drawable.focused, //
    //// DisplayUtil.dip2px(context, 84), DisplayUtil.dip2px(context, 63)),
    // touchX, touchY, paint);
    // }
}
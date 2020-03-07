package cn.face.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 自动聚焦并预览回调</br>
 * 每隔2000ms调用mCamera.autoFocus并且回调视频帧
 * @author yusr
 *
 */
public class OneShotCameraPreview extends SurfaceView implements SurfaceHolder.Callback, PreviewCallback {

	private static final String TAG =OneShotCameraPreview.class.getName();
	private Camera mCamera;
	int caremaId = Camera.CameraInfo.CAMERA_FACING_BACK;

	private boolean mPreviewing = true;
	private boolean mAutoFocus = true;
	private boolean mSurfaceCreated = false;
	private CameraConfigurationManager mCameraConfigurationManager;

	public OneShotCameraPreview(Context context) {
		super(context);
	}

	public OneShotCameraPreview(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);

	}

	public OneShotCameraPreview(Context context, AttributeSet attrs) {

		super(context, attrs);

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
					mCamera.autoFocus(autoFocusCB);
				}
			} catch (Exception e) {
				Log.i(TAG,e.toString());
			}
		}
	}

	public void stopCameraPreview() {
		if (mCamera != null) {
			try {
				removeCallbacks(doAutoFocus);

				mPreviewing = false;
				mCamera.cancelAutoFocus();
				closeOneShotPreview();
				mCamera.stopPreview();
			} catch (Exception e) {
				Log.i(TAG,e.toString());
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
				Log.i(TAG,"doAutoFocus");
				mCamera.autoFocus(autoFocusCB);
				
			}
		}
	};
	 public void openOneShotPreview(){
		 if (mCamera != null) {
		 mCamera.setOneShotPreviewCallback(this);
		 }
		 }
		 public void closeOneShotPreview(){
		 if (mCamera != null) {
		 mCamera.setOneShotPreviewCallback(null);
		 }
		 }
	Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
		public void onAutoFocus(boolean success, Camera camera) {
			openOneShotPreview();
			postDelayed(doAutoFocus, 2000);
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

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {

	}
}
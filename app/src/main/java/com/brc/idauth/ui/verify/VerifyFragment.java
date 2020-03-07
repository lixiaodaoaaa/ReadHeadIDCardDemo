package com.brc.idauth.ui.verify;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.brc.idauth.FileConstant;
import com.brc.idauth.R;
import com.brc.idauth.base.RecyclerBaseFragment;
import com.brc.idauth.bean.IdCardBean;
import com.brc.idauth.bean.event.VerifyResult;
import com.brc.idauth.control.AjustPara;
import com.brc.idauth.ui.view.NewFaceView;
import com.brc.idauth.utils.DateFormatterUtils;
import com.brc.idauth.utils.FileUtil;
import com.brc.idauth.utils.FileUtils;
import com.brc.idauth.utils.GlideCacheUtil;
import com.brc.idauth.utils.IdCardConvertUtils;
import com.brc.idauth.utils.SoundTools;
import com.brc.idauth.utils.picuploaduitls.PicUploadDBUtil;
import com.brc.idauth.utils.picuploaduitls.PicUploadInfo;
import com.brc.idauth.utils.picuploaduitls.PicUploadTask;
import com.daolion.base.anotation.BsFragmentAnnotation;
import com.daolion.base.utils.LogUtil;
import com.daolion.func.IDCardSDK;
import com.daolion.func.ReadCardEvent;
import com.daolion.net.response.PictureInfo;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import com.huashi.otg.sdk.HSIDCardInfo;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import cn.face.camera.CameraPreview;
import cn.face.camera.Contants;
import cn.face.realtime.FaceInfoCallback;
import cn.face.realtime.LocalFaceSDK;
import cn.face.sdk.FaceGroup;
import cn.face.sdk.FaceInfo;
import cn.face.sdk.FaceRecog;
import cn.face.sdkproc.FeatureBean;
import cn.face.sdkproc.LocalSDK;
import cn.face.sdkproc.VerifyBean;
import cn.face.utils.FrameDataUtils;
import de.greenrobot.event.EventBus;

@BsFragmentAnnotation(fragmentLayoutId = R.layout.fragment_verify, isNeedEventBus = true)
public class VerifyFragment extends RecyclerBaseFragment<ItemBean,VerifyContract.Presenter> implements VerifyContract.View, FaceInfoCallback {

    @BindView(R.id.cameraPreView) CameraPreview cameraPreview;
    @BindView(R.id.texture_faceview) NewFaceView faceView;
    @BindView(R.id.iv_people_head) ImageView ivPeopleHead;
    @BindView(R.id.iv_verify_result) ImageView ivVerifyResult;


    private final static int MESSAGE_CHECKED_FACE = 101;

    private boolean isFaceSameToIdCard = false;

    private boolean isMatchSuccess = false;


    private String faceImagePath = "";


    private IdCardBean idCardBean;


    private LocalFaceSDK faceSDK;

    private FaceGroup currentFaceGroup;

    private boolean isStartMatch = false;
    private long startMatchTime = 0L;

    private boolean verifyResult = false;


    @Override
    public void onResume() {
        super.onResume();
        faceSDK = LocalFaceSDK.getInstance(getContext());
        faceSDK.cwFaceInfoCallback(this);
        int faceSDKInitRect = faceSDK.cwInit();
        cameraPreview.cwStartCamera();
    }

    @Override
    protected void initViewByRootView(View rootView) {
        super.initViewByRootView(rootView);
        cameraPreview.setScreenOrientation(this.getResources().getConfiguration().orientation);
        int previewHeight, previewWidth, frameWidth, frameHeight;
        previewHeight = getDisplayMetrics().heightPixels;
        previewWidth = previewHeight * Contants.PREVIEW_W / Contants.PREVIEW_H;
        frameWidth = Contants.PREVIEW_W;
        frameHeight = Contants.PREVIEW_H;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(previewWidth, previewHeight);
        faceView.setLayoutParams(params);
        faceView.setSurfaceWH(previewWidth, previewHeight, frameWidth, frameHeight);
        mPresenter.getListItemBean();
        IDCardSDK.getInstance().initSDK(mHandler, getContext());
        localSDK = LocalSDK.getInstance(getContext());
    }

    @Override
    protected RecyclerArrayAdapter setupAdapter() {
        return new IdCardAdapter(getContext());
    }


    @Subscribe
    public void onEventMainThread(ReadCardEvent readCardEvent) {


        if (!isStartMatch) {
            isStartMatch = true;
            startMatchTime = System.currentTimeMillis();

            Log.i(TAG, "检测成功，并且读取到身份证信息");

            HSIDCardInfo cardInfo = readCardEvent.getCardInfo();
            idCardBean = IdCardConvertUtils.converToIdCardBean(cardInfo);
            mPresenter.getRealItemBeanByIdCard(idCardBean);
            Log.i("lixiaodaoaaa", "file path is " + FileConstant.ID_CARD_HEAD_FILE_PATH);
            Bitmap bitmapFromPath = FileUtils.getBitmapFromPath(FileConstant.ID_CARD_HEAD_FILE_PATH);
            ivPeopleHead.setImageBitmap(bitmapFromPath);
        }
    }


    private boolean isSamePerson = false;

    @Subscribe
    public void onEventMainThread(VerifyResult verifyResult) {
        if (verifyResult != null && verifyResult.isSamePerson()) {
            //截取当前人脸 开始上传；
            isSamePerson = true;
            //TODO
            SoundTools.playVerifySuccessSound(getContext());
            ivVerifyResult.setImageResource(R.drawable.ic_verify_success);
            ivVerifyResult.setVisibility(View.VISIBLE);
        } else {
            if (isStartMatch) {
                if (isSamePerson) {
                } else {
                    isSamePerson = false;
                    ivVerifyResult.setImageResource(R.drawable.ic_verify_error);
                    ivVerifyResult.setVisibility(View.VISIBLE);
                    SoundTools.playVerifyFailSound(getContext());
                }
            }
        }
        isFaceSameToIdCard = true;
        isStartMatch = false;
        isSamePerson = false;
        ivVerifyResult.setVisibility(View.VISIBLE);
        mHandler.postDelayed(() -> {
            resetVaraAndData();
            ivVerifyResult.setVisibility(View.INVISIBLE);
            PicUploadTask.getInstance().run();
        }, 2 * 1000);

    }


    @Override
    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_CHECKED_FACE:
                FaceGroup faceGroup = (FaceGroup) msg.obj;
                currentFaceGroup = faceGroup;
                if (isStartMatch) {
                    if (System.currentTimeMillis() - startMatchTime <= AjustPara.TOTAL_VERIFY_TIME) {
                        if (null != faceGroup && faceGroup.getNum() > 0) {
                            for (FaceInfo face : faceGroup.getFaceInfoList()) {
                                if (face == null || face.detected != FaceInfo.CHECKED_FACE || face.alignedData == null) {
                                    return;
                                }
                                verifyWithIDcard(face);
                            }
                        }
                    } else {
                        //todo
                        EventBus.getDefault().post(new VerifyResult(false));
                    }
                }
                break;
            default:
                break;
        }
    }

    private LocalSDK localSDK;

    private void verifyWithIDcard(FaceInfo face) {
        FeatureBean faceFeature = new FeatureBean(localSDK.iFeaLen);
        faceFeature.ret = FaceRecog.getInstance().cwGetFaceFeature(localSDK.iRecogHandle,
                face.alignedData,
                face.alignedW,
                face.alignedH,
                face.nChannels,
                faceFeature.btFeature);
        FeatureBean idCardFeature = null;
        try {
            idCardFeature = localSDK.GetFeatureFromImgData(getIDBytes());
            VerifyBean mVerifyBean = localSDK.Verify(faceFeature.btFeature, idCardFeature.btFeature);
            if (mVerifyBean.ret == 0) {
                Log.e("========", "相似度：" + mVerifyBean.score);
                boolean isVerifySucess = mVerifyBean.score > AjustPara.JUGE_IS_SAME_PERSON_SCORE;
                verifyResult = isVerifySucess;
                if (isVerifySucess) {
                    EventBus.getDefault().post(new VerifyResult(isVerifySucess));
                }
            } else {
                Log.e("========", "人脸比对错误：" + mVerifyBean.ret);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到身份证bytes信息
     *
     * @return
     */
    private byte[] getIDBytes() throws IOException {
        byte[] idCardBmpBytes = FileUtil.file2byte(new File("/mnt/sdcard/wltlib/zp.bmp"));
        return idCardBmpBytes;
    }

    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    @Override
    public void detectFaceInfo(FaceGroup faceGroup, byte[] data, int width, int height) {
        //没有检测到人脸信息
        if (null == faceGroup || faceGroup.getNum() < 1 || faceGroup.getFaceInfos() == null) {
            faceView.clearBeforeMarker();
            if (isStartMatch) {
                if (System.currentTimeMillis() - startMatchTime > AjustPara.TOTAL_VERIFY_TIME) {
                    EventBus.getDefault().post(new VerifyResult(false));
                }
            }
            return;
        }

        faceView.setFaces(faceGroup);

        //是同一个人就开始执行保存该照片到本地
        if (isFaceSameToIdCard) {
            Bitmap frameBitmap = FrameDataUtils.yunDataToBitmap(data, width, height);
            try {
                String faceImagePath = FileUtils.saveBitmapFile(getContext(), frameBitmap, "" + System.currentTimeMillis() + ".jpg");
                LogUtil.i(TAG, "save file success " + faceImagePath);
                PicUploadInfo info = new PicUploadInfo();
                info.picA = faceImagePath;
                info.picB = FileUtils.saveBitmapFile(getActivity(),
                        FileUtils.getBitmapFromPath(FileConstant.ID_CARD_HEAD_FILE_PATH),
                        System.currentTimeMillis()+".jpg");
                info.info = idCardBean;
                info.picAUpload = false;
                info.picBUpload = false;
                info.infoUpload = false;
                info.verifyResult = verifyResult;
                info.createTime = System.currentTimeMillis();
                PicUploadDBUtil.getInstance().inster(info);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        isFaceSameToIdCard = false;
        Message msg = new Message();
        msg.obj = faceGroup;
        msg.what = MESSAGE_CHECKED_FACE;
        mHandler.sendMessage(msg);
    }


    @Override
    public void getListItemBeanSuccess(List<ItemBean> beanList) {
        if (listData != null) {
            listData.clear();
        }
        listData.clear();
        adapter.clear();
        listData.addAll(beanList);
        adapter.addAll(listData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getRealItemBeanSuccess(List<ItemBean> beanList) {
        if (listData != null) {
            listData.clear();
        }
        listData.clear();
        adapter.clear();
        listData.addAll(beanList);
        adapter.addAll(listData);
        adapter.notifyDataSetChanged();
    }

    private void resetVaraAndData() {
        mPresenter.getListItemBean();
        GlideCacheUtil.getInstance().clearImageAllCache(getContext());
        getActivity().runOnUiThread(() -> ivPeopleHead.setImageBitmap(null));
    }

    @Override
    public void setPresenter(VerifyContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }
}
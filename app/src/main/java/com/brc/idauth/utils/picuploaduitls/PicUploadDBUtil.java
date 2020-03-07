package com.brc.idauth.utils.picuploaduitls;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PicUploadDBUtil {
    private final Executor mDiskIOForDB;
    private static PicUploadDBUtil instance = new PicUploadDBUtil();
    public PicUploadDBUtil() {
        mDiskIOForDB = Executors.newSingleThreadExecutor();
    }

    public static PicUploadDBUtil getInstance() {
        return instance;
    }

    public void inster(final PicUploadInfo info){
        mDiskIOForDB.execute(new Runnable() {
            @Override
            public void run() {
                PicUploadDatabase.getInstance().getPicUploadDao().insert(info);
            }
        });
    }

    public void updateAstate(final boolean picAupload,final int id){
        mDiskIOForDB.execute(() -> PicUploadDatabase.getInstance().getPicUploadDao().updaePicAState(picAupload,id));
    }
    public void updateBstate(final boolean picBupload, final int id){
        mDiskIOForDB.execute(() -> PicUploadDatabase.getInstance().getPicUploadDao().updaePicBState(picBupload,id));
    }
    public void updateInfoState(final boolean infoUpload, final int id){
        mDiskIOForDB.execute(() -> PicUploadDatabase.getInstance().getPicUploadDao().updaeinfoState(infoUpload,id));
    }

    public void del(final int id){
        mDiskIOForDB.execute(new Runnable() {
            @Override
            public void run() {
                PicUploadDatabase.getInstance().getPicUploadDao().deleter(id);
            }
        });
    }

    public void select(){
        mDiskIOForDB.execute(new Runnable() {
            @Override
            public void run() {
                PicUploadDatabase.getInstance().getPicUploadDao().select(false);
            }
        });
    }
}

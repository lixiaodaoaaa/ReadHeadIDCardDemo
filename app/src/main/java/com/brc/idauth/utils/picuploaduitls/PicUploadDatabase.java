package com.brc.idauth.utils.picuploaduitls;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.daolion.base.control.FrameApplication;

@Database(entities = {PicUploadInfo.class}, version = 1, exportSchema = false)
public abstract class PicUploadDatabase extends RoomDatabase {
    private final static String DB_PICINFO = "picuploadinfo.db";
    private static PicUploadDatabase INSTANCE;
    private static final Object sLock = new Object();
    public abstract PicUploadDao getPicUploadDao();
    public static PicUploadDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (sLock) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(FrameApplication.getCtx(), PicUploadDatabase.class, DB_PICINFO).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}

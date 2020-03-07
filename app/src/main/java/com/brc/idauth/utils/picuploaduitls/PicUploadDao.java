package com.brc.idauth.utils.picuploaduitls;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PicUploadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(PicUploadInfo picUploadInfo);

    @Query("select * from PicUploadInfo where infoUpload = :infoUpload")
    Single<List<PicUploadInfo>> select(boolean infoUpload);

    @Query("DELETE FROM PicUploadInfo WHERE id = :id")
    void deleter(int id);

    @Query("update PicUploadInfo set picAUpload = :picAUpload where id = :id")
    void updaePicAState(boolean picAUpload,int id);

    @Query("update PicUploadInfo set picBUpload = :picBUpload where id = :id")
    void updaePicBState(boolean picBUpload,int id);

    @Query("update PicUploadInfo set infoUpload = :infoUpload where id = :id")
    void updaeinfoState(boolean infoUpload,int id);

    @Query("select * from PicUploadInfo where name like '%' || :name || '%' and :startTime < createTime <= :endTime")
    Single<List<PicUploadInfo>> selectList(String name,long startTime,long endTime);

    @Query("select * from PicUploadInfo")
    Single<List<PicUploadInfo>> selectList();
}

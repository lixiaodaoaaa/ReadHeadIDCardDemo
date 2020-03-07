package com.brc.idauth.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/9/2
       Time     :  15:44
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class FileUtils {

    private final static String APP_DATA_DIR = "BRC_SmartIdAuth";

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        return ByteBuffer.allocate(bitmap.getByteCount()).array();
    }


    /**
     * 把batmap 转file
     *
     * @param bitmap
     */
    public static String saveBitmapFile(Context context, Bitmap bitmap, String fileName) throws IOException {
        String path = getPictureDir(context).getPath();
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile.getCanonicalPath();
    }

    public static File getPictureDir(Context context) {
        File file = new File(getAppDataDir(context), "picture");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    public static File getLogDataDir(Context context) {
        File file = new File(getAppDataDir(context), "logoData");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    public static File getAppDataDir(Context context) {
        File file = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(Environment.getExternalStorageDirectory(), APP_DATA_DIR);
        } else {
            file = new File(context.getFilesDir(), APP_DATA_DIR);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    public static Bitmap getBitmapFromPath(String path) {
        return BitmapFactory.decodeFile(path);
    }
}

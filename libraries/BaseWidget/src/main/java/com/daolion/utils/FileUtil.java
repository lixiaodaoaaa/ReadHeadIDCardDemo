package com.daolion.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.text.format.Formatter;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    private static final String TAG = "FileUtil";

    /**
     * 从文件解析出Bitmap格式的图片
     *
     * @param path
     * @param maxWidth
     * @return
     */
    public static Bitmap decodeFileForPublish(String path,
                                              int selectedImageWidth, int maxWidth) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        double ratio = Math.ceil(selectedImageWidth * 1.0d / maxWidth);

        if (ratio > 1) {
            options.inSampleSize = (int) ratio;
        }
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        return BitmapFactory.decodeFile(path, options);
    }


    /**
     * scale image to fixed height and weight
     *
     * @param imagePath
     * @return
     */
    private static int getImageScale(String imagePath, int maxWidth,
                                     int maxHeight) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        // set inJustDecodeBounds to true, allowing the caller to query the
        // bitmap info without having to allocate the
        // memory for its pixels.
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, option);
//        float ratioW = option.outWidth / maxWidth;
//        float ratiH = option.outHeight / maxHeight;
        //float sampleScale = ratioW>ratiH?ratioW:ratiH;
        //int scale = ratioW<=1?1:(int)ratioW;
        int scale = 1;
        while (option.outWidth / scale > maxWidth
                || option.outHeight / scale > maxHeight) {
            scale++;
        }
        return scale;
    }

    /**
     * Gets the corresponding path to a file from the given content:// URI
     *
     * @param uri             The content:// URI to find the file path from
     * @param contentResolver The content resolver to use to perform the query.
     * @return the file path as a string
     */
    public static String getFilePathFromContentUri(Uri uri,
                                                   ContentResolver contentResolver) {
        String filePath = "";
        String[] filePathColumn = {MediaColumns.DATA};
        Cursor cursor = contentResolver.query(uri, filePathColumn,
                null, null, null);
        // 也可用下面的方法拿到cursor
        // Cursor cursor = this.context.managedQuery(selectedVideoUri,
        // filePathColumn, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            filePath = cursor.getString(columnIndex);
            cursor.close();
        }
        return filePath;
    }

    /**
     * @param context
     * @return
     * @Title getAppCacheSize
     * @Description 计算缓存文件大小
     */
    public static String getAppCacheSizeStr(Context context) {
        long totalSize = getDirSize(context.getCacheDir());
        totalSize += getDirSize(context.getExternalCacheDir());
        return Formatter.formatFileSize(context, totalSize);
    }

    public static long getAppCacheSize(Context context) {
        long totalSize = getDirSize(context.getCacheDir());
        totalSize += getDirSize(context.getExternalCacheDir());
        return totalSize;
    }

    /**
     * @param dir
     * @Title getDirSize
     * @Description 计算目录大小
     */
    private static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * Gets the content:// URI from the given corresponding path to a file
     *
     * @param context
     * @param imageFile
     * @return content Uri
     */
    public static Uri getImageContentUri(Context context, java.io.File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }


    public static File getCatchOfflineDataDir(Context context) {
        String dirName = "offline";
        File cache = getCacheDataDir(context, dirName);
        return cache;
    }

    /**
     * 缓存持久化数据目录
     *
     * @param context
     * @param dirName
     * @return
     */
    public static File getCacheDataDir(Context context, String dirName) {
        File cache = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + context.getPackageName() + "/cache/" + dirName + "/";
            cache = new File(path);
        } else {
            cache = new File(context.getFilesDir() + File.separator + dirName);
        }

        if (!cache.exists()) {
            cache.mkdirs();
        }

        return cache;
    }


    public static String getSdPath() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();// 获取跟目录
        }
        return null;
    }

    public static boolean deleteDirectory(String folder) {
        File directory = new File(folder);
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteDirectory(files[i].getAbsolutePath());
                    } else {
                        files[i].delete();
                    }
                }
            }
        }
        return (directory.delete());
    }

    public static String getDownloadApkPath(Context context, String fileName) {
        String path = context.getCacheDir().getAbsolutePath();
        // [文件夹705:drwx---r-x]
        String[] args1 = {"chmod", "705", path};
        exec(args1);
        // [文件604:-rw----r--]
        String[] args2 = {"chmod", "604", path + "/" + fileName};
        exec(args2);
        return path;
    }


    /**
     * 执行Linux命令，并返回执行结果。
     */
    public static void exec(String[] args) {
        try {
            Runtime.getRuntime().exec(args[0] + " " + args[1] + " " + args[2]).waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getDownloadFile(Context context, String serverVersion) {
        String fileName = getDownloadFileName(serverVersion);
        return new File(FileUtil.getDownloadApkPath(context, fileName), fileName);
    }


    public static String getDownloadFileName(String serverVersion) {
        return "liandou" + "_" + serverVersion.replace(".", "") + ".apk";
    }


    public static String getApkVersionCode(Context context, String filePath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if (info == null) {
            return "";
        }
        return info.versionName;
    }
}

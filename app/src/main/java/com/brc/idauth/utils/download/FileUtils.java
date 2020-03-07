package com.brc.idauth.utils.download;

import android.os.Environment;
import java.io.File;

public class FileUtils {
    /**
     * 删除指定文件
     *
     * @param filePath 文件path
     * @return 删除结果
     */
    public static boolean delFile(String filePath) {
        if (StringUtil.isEmpty(filePath))
            return false;

        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return 删除结果
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹完整绝对路径
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static File getPathFile(String path) {
        String apkName = path.substring(path.lastIndexOf("/"));
        File outputFile = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS), apkName);
        return outputFile;
    }

    public static void rmoveFile(String path) {
        File file = getPathFile(path);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
}

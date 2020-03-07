package com.brc.idauth.utils.download;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

public class InstallApkUtils {

	public static void install(Context context, String filePath) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			startInstallN(context, filePath);
		} else {
			startInstall(context, filePath);
		}
		/*File file = new File(filePath);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		// 仅需改变这一行
		FileProvider7.setIntentDataAndType(context,
				intent, "application/vnd.android.package-archive", file, true);
		context.startActivity(intent);*/
	}

	/**
	 *android1.x-6.x
	 *@param path 文件的路径
	 */
	public static void startInstall(Context context, String path) {
		Intent install = new Intent(Intent.ACTION_VIEW);
		install.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
		install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(install);
	}

	/**
	 * android7.x
	 * @param path 文件路径
	 */
	public static void startInstallN(Context context, String path) {
		//参数1 上下文, 参数2 在AndroidManifest中的android:authorities值, 参数3  共享的文件
		Uri apkUri = FileProvider.getUriForFile(context, "com.brc.acctrl.fileProvider", new File(path));
		Intent install = new Intent(Intent.ACTION_VIEW);
		//由于没有在Activity环境下启动Activity,设置下面的标签
		install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//添加这一句表示对目标应用临时授权该Uri所代表的文件
		install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		install.setDataAndType(apkUri, "application/vnd.android.package-archive");
		context.startActivity(install);
	}
}

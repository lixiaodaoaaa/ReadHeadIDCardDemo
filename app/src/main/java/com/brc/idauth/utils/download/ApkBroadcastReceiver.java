package com.brc.idauth.utils.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ApkBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent != null) {
			String action = intent.getAction();
			switch (action) {
				case Intent.ACTION_PACKAGE_ADDED:
					break;
				case Intent.ACTION_PACKAGE_REMOVED:
					break;
				case Intent.ACTION_PACKAGE_REPLACED:
					String filePath = DownloadHelper.getDownloadParentFilePath();
					FileUtils.delAllFile(filePath);
					break;
			}
		}
	}
}

package com.codebricker.lbsshare;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.codebricker.lbsshare.common.utils.HttpUtil;
import com.codebricker.lbsshare.common.utils.HttpUtil.HttpInputStreamResponse;

public class UpdaterManager {
	private static UpdaterManager sInstance;

	private UpdaterManager(Context context) {
		mContext = context;
	}

	public static UpdaterManager getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new UpdaterManager(context);
		}
		return sInstance;
	}

	private Context mContext;

	public void update() {

	}

	private void install(File apkfile) {
		if (!apkfile.exists()) {
			return;
		}
		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
	}

	private File download(String dirPath) throws Exception {
		String updateUrl = Config.get("update_url", null);
		String latestVer = Config.get("latest_version", null);
		if (updateUrl == null || latestVer == null) {
			return null;
		}
		PackageManager pkgMgr = mContext.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo pkgInfo = pkgMgr.getPackageInfo(mContext.getPackageName(),
				0);
		int curVer = pkgInfo.versionCode;
		if (curVer <= Integer.parseInt(latestVer)) {
			return null;
		}

		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String lbl = pkgInfo.applicationInfo.loadLabel(pkgMgr).toString();
		File f = new File(dir, lbl + "-" + latestVer + ".apk");
		if (f.exists()) {
			f.delete();
		}
		HttpInputStreamResponse resp = HttpUtil.getInputSteam(updateUrl, null, null);
		InputStream is = resp.getInputSteam();
		BufferedInputStream bis = new BufferedInputStream(is);
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(f));
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = bis.read(buffer)) > 0) {
			bos.write(buffer, 0, len);
		}

		bis.close();
		bos.close();
		return f;
	}
}

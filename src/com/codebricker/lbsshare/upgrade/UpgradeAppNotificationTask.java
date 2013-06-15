package com.codebricker.lbsshare.upgrade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Notification;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.codebricker.lbsshare.R;
import com.codebricker.lbsshare.common.utils.DebugUtil;
import com.codebricker.lbsshare.common.utils.HttpUtil;
import com.codebricker.lbsshare.common.utils.HttpUtil.HttpInputStreamResponse;
import com.codebricker.lbsshare.notifications.NotificationMgr;

/**
 * 带有通知栏通知的下载异步任务
 */
public class UpgradeAppNotificationTask extends AsyncTask<Void, Integer, File> {
	private NotificationMgr mNtfMgr;

	private Throwable mException;
	private Context mContext;
	private Notification dlNotification;
	private String url;
	private File destDir;
	private boolean active = true;
	private static final int UPDATE_PROGRESS_INTERVAL = 1000;

	public UpgradeAppNotificationTask(Context context, Bundle bundle, File dir) {
		mContext = context;
		this.url = bundle.getString("url");
		int index = url.lastIndexOf('/');
		String name = url.substring(index + 1);
		this.destDir = new File(dir, name);
		mNtfMgr = NotificationMgr.getInstance(mContext);
	}

	@Override
	protected File doInBackground(Void... params) {
		mNtfMgr.notify(NotificationMgr.APK_DOWNLOADING);
		dlNotification = mNtfMgr
				.getNotification(NotificationMgr.APK_DOWNLOADING);
		HttpInputStreamResponse resp = null;
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			resp = HttpUtil.getInputSteam(url, null, null);
			fos = new FileOutputStream(destDir, false);
			int length = Integer.parseInt(resp.getHeader("Content-Length"));
			is = resp.getInputSteam();
			int done = 0, len = -1, progress = 0;
			byte[] buff = new byte[4096];
			long lastUpdate = System.currentTimeMillis();
			while (active && (len = is.read(buff)) > 0) {
				fos.write(buff, 0, len);
				done += len;
				progress = (done * 100) / length;
				long now = System.currentTimeMillis();
				if (now - lastUpdate > UPDATE_PROGRESS_INTERVAL) {
					publishProgress(progress);
					lastUpdate = now;
				}
			}
		} catch (Throwable e) {
			mException = e;
		} finally {
			DebugUtil.close(is, fos);
		}
		return destDir;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		if (!active) {
			return;
		}
		dlNotification.contentView.setProgressBar(R.id.progressbar, 100,
				values[0], false);
		dlNotification.contentView.setTextViewText(R.id.progress_txt, values[0]
				+ "%");
		mNtfMgr.notify(NotificationMgr.APK_DOWNLOADING);
	}

	@Override
	protected void onPostExecute(File result) {
		mNtfMgr.cancel(NotificationMgr.APK_DOWNLOADING);
		if (mException == null) {
			mNtfMgr.notify(NotificationMgr.APK_DOWNLOADED, result);
		} else {
			mNtfMgr.notify(NotificationMgr.APK_DOWNLOAD_FAIL, result);
		}
	}

	@Override
	protected void onCancelled() {
		mNtfMgr.cancel(NotificationMgr.APK_DOWNLOADING);
	}

	/**
	 * 停止程序更新
	 */
	public void stopUpgradeApp() {
		cancel(true);
		active = false;
	}
}

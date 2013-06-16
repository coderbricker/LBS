package com.codebricker.lbsshare.notifications;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.codebricker.lbsshare.R;

public class NotificationMgr {
	public static final int APK_DOWNLOADING = 1;
	public static final int APK_DOWNLOADED = 2;
	public static final int APK_DOWNLOAD_FAIL = 3;

	private NotificationMgr(Context context) {
		mContext = context;
		mSysManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	private static NotificationMgr sInstance;

	public static NotificationMgr getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new NotificationMgr(context);
		}
		return sInstance;
	}

	private NotificationManager mSysManager;
	private Context mContext;

	public Notification getNotification(int id, Object... params) {
		Notification ntf = cacheMap.get(id);
		if (ntf == null) {
			Notification.Builder builder = new Builder(mContext);
			switch (id) {
			case APK_DOWNLOADING:
				builder.setOngoing(true);
				builder.setSmallIcon(R.drawable.status_bar_downloading);
				builder.setTicker("正在下载");
				builder.setDefaults(Notification.DEFAULT_SOUND);

				RemoteViews downloadingView = new RemoteViews(
						mContext.getPackageName(),
						R.layout.downloading_notification);
				downloadingView.setTextViewText(R.id.title, "sogoumap");
				downloadingView.setTextViewText(R.id.progress_txt, "0%");
				builder.setContent(downloadingView);
				break;
			case APK_DOWNLOADED:
				builder.setOngoing(true);
				builder.setSmallIcon(R.drawable.status_bar_done);
				builder.setTicker("下载完成");
				builder.setDefaults(Notification.DEFAULT_SOUND);
				builder.setAutoCancel(true);
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				intent.setDataAndType(Uri.fromFile((File) params[0]),
						"application/vnd.android.package-archive");
				PendingIntent pendingIntent = PendingIntent.getActivity(
						mContext, 0, intent, 0);
				builder.setContentIntent(pendingIntent);
				break;
			case APK_DOWNLOAD_FAIL:
				builder.setOngoing(true);
				builder.setSmallIcon(R.drawable.status_bar_failed);
				builder.setTicker("下载失败");
				builder.setDefaults(Notification.DEFAULT_SOUND);
				builder.setAutoCancel(true);
				break;
			}
			ntf = builder.build();
			cacheMap.put(id, ntf);
		}
		return ntf;
	}

	public void notify(int id,Object... params) {
		Notification notification = getNotification(id,params);
		mSysManager.notify(id, notification);
	}

	public void cancel(int id) {
		mSysManager.cancel(id);
	}

	private static Map<Integer, Notification> cacheMap = new WeakHashMap<Integer, Notification>();

}

package com.codebricker.lbsshare.common.async;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;

/**
 * 
 * @author qishengxing
 * 
 */
public class DefaultThreadHandler {
	private static final String TAG = "sogou-map-" + DefaultThreadHandler.class.getSimpleName();

	private static LooperThread sThreadInstance = null;

	public static void post(Runnable r) {
		post(r,0);
	};

	public static void post(final Runnable r, final long delay) {
		if (sThreadInstance == null) {
			sThreadInstance = new LooperThread();
			sThreadInstance.setName("Default Thread");
			sThreadInstance.start();
		}
		// ����Ѿ���������ôsHandler������Ϊ�գ�ֱ��ִ�м���.
		if (sThreadInstance.isStarted()) {
			sThreadInstance.postRunnable(r, delay);
		} else {
			// ����ʱsHandlerΪ�գ�˵�������ǵ�һ����������ʱ����Ҫ�ȴ��߳�������
			// �ȴ�Ĺ����Ҫ����һ���̵߳ȴ�����������Ϣ���̡߳�
			new Thread() {
				public void run() {
					while (!sThreadInstance.isStarted()) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							Log.w(TAG, e.toString());
						}
					}
					sThreadInstance.postRunnable(r, delay);
				};
			}.start();
		}
	}

	/***********************************************************************/

	private static class LooperThread extends Thread {
		private Handler mHandler;

		public boolean isStarted() {
			return mHandler != null;
		}

		public void postRunnable(Runnable r) {
			mHandler.post(r);
		}

		public void postRunnable(Runnable r, long delay) {
			mHandler.postDelayed(r, delay);
		}

		@Override
		public void run() {
			Looper.prepare();
			mHandler = new Handler();
			Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
			Looper.loop();
		}
	}

}

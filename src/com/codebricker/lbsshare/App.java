package com.codebricker.lbsshare;

import java.lang.Thread.UncaughtExceptionHandler;

import android.app.Application;
import android.util.Log;

import com.codebricker.lbsshare.common.utils.AndroidUtil;
import com.codebricker.lbsshare.common.utils.CommUtil;

public class App extends Application {
	public static final String TAG = "arith-";
	private static App sInstance;

	public static App get() {
		return sInstance;
	}

	private static UncaughtExceptionHandler sDefaultHandler;

	@Override
	public void onCreate() {
		sInstance = this;
		AndroidUtil.init(this);
		sDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(mExceptionHandler);
		super.onCreate();
	}

	private static UncaughtExceptionHandler mExceptionHandler = new UncaughtExceptionHandler() {
		private static final String TAG_EXPT = "exception";

		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			Log.e(TAG_EXPT, "thread:" + thread.getName() + " exception:"
					+ CommUtil.formatException(ex));
			// Thread.getDefaultUncaughtExceptionHandler().uncaughtException(
			// thread, ex);
			if (sDefaultHandler != null) {
				sDefaultHandler.uncaughtException(thread, ex);
			}

		}
	};

}

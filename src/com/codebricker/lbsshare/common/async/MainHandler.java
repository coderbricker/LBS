package com.codebricker.lbsshare.common.async;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class MainHandler {
	private static Handler sMainLooperHandler;

	private MainHandler() {}

	private static void init() {
		if (sMainLooperHandler == null) {
			sMainLooperHandler = new Handler(Looper.getMainLooper());
		}
	}

	public static void post2Main(Runnable r) {
		if (Looper.myLooper() == Looper.getMainLooper()) {
			r.run();
			return;
		}
		init();
		sMainLooperHandler.post(r);
	}

	public static void postNow(Runnable r) {
		if (Looper.myLooper() == Looper.getMainLooper()) {
			r.run();
			return;
		}
		init();
		sMainLooperHandler.postAtFrontOfQueue(r);
	}

	public static void post2Main(Runnable r, long delayed) {
		init();
		sMainLooperHandler.postDelayed(r, delayed);
	}

	public static void post2Main(Message msg) {
		init();
		sMainLooperHandler.sendMessage(msg);
	}
}

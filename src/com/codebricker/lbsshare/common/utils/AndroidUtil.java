package com.codebricker.lbsshare.common.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AndroidUtil {
	private static final String KV_PREF = "kv_pref";
	private static Application mApp;

	public static void init(Application app) {
		mApp = app;
	}

	public static Application getApp() {
		return mApp;
	}

	public static void savePref(Context context, String... kvs) {
		if (kvs == null || kvs.length < 2) {
			return;
		}
		SharedPreferences userInfo = context.getSharedPreferences(KV_PREF,
				Context.MODE_PRIVATE);
		Editor editor = userInfo.edit();
		for (int i = 0; i < kvs.length; i = i + 2) {
			editor.putString(kvs[i], kvs[i + 1]);
		}
		editor.commit();
	}

	public static void getPref(Context context, String key) {
		SharedPreferences userInfo = context.getSharedPreferences(KV_PREF,
				Context.MODE_PRIVATE);
		userInfo.getString(key, null);
	}

}

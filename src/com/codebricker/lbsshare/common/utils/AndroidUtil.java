package com.codebricker.lbsshare.common.utils;

import com.codebricker.lbsshare.App;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

public final class AndroidUtil {
	private static final String KV_PREF = "kv_pref";
	private static App sApp;
	private static Resources sRes;
	private static LayoutInflater sInflater;

	public static void init(Application app) {
		sApp = (App) app;
	}

	public static Application getApp() {
		return sApp;
	}

	protected static void init() {
		sApp = App.get();
		sRes = sApp.getResources();
	}

	public static String getString(int resId) {
		return sRes.getString(resId);
	}

	public static String getString(int resId, Object... formatArgs) {
		return sRes.getString(resId, formatArgs);
	}

	public static Drawable getDrawable(int restId) {
		return sRes.getDrawable(restId);
	}

	public static int getColor(int resId) {
		return sRes.getColor(resId);
	}

	public static View inflate(int resId) {
		return View.inflate(sApp, resId, null);
	}

	public static void savePref(Context context, String... kvs) {
		if (kvs == null || kvs.length < 2) {
			return;
		}
		SharedPreferences userInfo = context.getSharedPreferences(KV_PREF, Context.MODE_PRIVATE);
		Editor editor = userInfo.edit();
		for (int i = 0; i < kvs.length; i = i + 2) {
			editor.putString(kvs[i], kvs[i + 1]);
		}
		editor.commit();
	}

	public static void getPref(Context context, String key) {
		SharedPreferences userInfo = context.getSharedPreferences(KV_PREF, Context.MODE_PRIVATE);
		userInfo.getString(key, null);
	}

}

package com.codebricker.lbsshare.common.utils;

import java.net.InetSocketAddress;

import org.apache.http.HttpHost;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;

public class NetUtil {

	private static String getApnName(Context context) {
		ConnectivityManager mgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifiConn = mgr
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiConn.isConnected()) { // 如果有wifi连接，不需要代理
			return null;
		}
		NetworkInfo net = mgr.getActiveNetworkInfo();
		if (net != null) {
			return net.getExtraInfo();
		}
		return null;
	}

	public static java.net.Proxy getApnJavaProxy(Context context) {
		String apnName = getApnName(context);
		if (apnName != null && apnName.length() > 0) {
			apnName = apnName.toLowerCase();
			if (apnName.contains("uninet") || apnName.contains("3gnet")
					|| apnName.contains("cmnet") || apnName.contains("ctnet")) {
				return null;
			}
			String host = "";
			int port;
			if (apnName.contains("cmwap") || apnName.contains("3gwap")
					|| apnName.contains("uniwap")) {
				host = "10.0.0.172";
				port = 80;
			} else if (apnName.contains("ctwap")) {
				host = "10.0.0.200";
				port = 80;
			} else {
				host = Proxy.getDefaultHost();
				port = Proxy.getDefaultPort();

			}
			if (host != null) {
				java.net.Proxy p = new java.net.Proxy(java.net.Proxy.Type.HTTP,
						new InetSocketAddress(host, port));
				return p;
			}

		}
		return null;
	}

	public static HttpHost getApnHostProxy(Context context) {
		String apnName = getApnName(context);
		if (apnName != null && apnName.length() > 0) {
			apnName = apnName.toLowerCase();
			if (apnName.contains("uninet") || apnName.contains("3gnet")
					|| apnName.contains("cmnet") || apnName.contains("ctnet")) {
				return null;
			}

			String host = "";
			int port;
			if (apnName.contains("cmwap") || apnName.contains("3gwap")
					|| apnName.contains("uniwap")) {
				host = "10.0.0.172";
				port = 80;
			} else if (apnName.contains("ctwap")) {
				host = "10.0.0.200";
				port = 80;
			} else {
				host = Proxy.getDefaultHost();
				port = Proxy.getDefaultPort();
			}

			if (host != null) {
				return new HttpHost(host, port);
			}
		}
		return null;
	}

}

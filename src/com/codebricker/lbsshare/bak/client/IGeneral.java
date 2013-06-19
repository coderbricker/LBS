/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\git\\LBS\\src\\com\\codebricker\\lbsshare\\bak\\client\\IGeneral.aidl
 */
package com.codebricker.lbsshare.bak.client;

import com.codebricker.lbsshare.bak.listeners.IGeneralListener;

public interface IGeneral {
	public void authenticate(java.lang.String appName, java.lang.String appId, java.lang.String vendor,
			java.lang.String certificate, IGeneralListener listener);

	public boolean registerInToLBSPlatform(java.lang.String packageName, java.lang.String appId,
			IGeneralListener listener, int eventTypes);

	public boolean isServiceAvailable();

	public com.codebricker.lbsshare.vos.LBSUser getUserInfo();

	public android.os.IBinder getFriendReadBinder(java.lang.String appId);

	public android.os.IBinder getFriendWriteBinder(java.lang.String appId);

	public android.os.IBinder getGroupReadBinder(java.lang.String appId);

	public android.os.IBinder getGroupWriteBinder(java.lang.String appId);

	public android.os.IBinder getPublishBinder(java.lang.String appId);

	public android.os.IBinder getSubscribeBinder(java.lang.String appId);

	public android.os.IBinder getInternalBinder(java.lang.String appId);
}

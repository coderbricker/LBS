/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\git\\LBS\\src\\com\\codebricker\\lbsshare\\bak\\client\\internal\\IInternalListener.aidl
 */
package com.codebricker.lbsshare.bak.listeners;

public interface IInternalListener {
	public void receiveResultSetUserMap(java.lang.String email, boolean result);

	public void receiveGetUserMap(java.lang.String email, java.lang.String phone, java.lang.String userId,
			java.lang.String nick, boolean result);

	public void receiveLoginResult(java.lang.String email, java.lang.String userId, java.lang.String nick,
			java.lang.String phone, boolean result);
}

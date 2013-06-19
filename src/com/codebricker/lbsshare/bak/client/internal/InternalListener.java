package com.codebricker.lbsshare.bak.client.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.codebricker.lbsshare.bak.CommandEngine;
import com.codebricker.lbsshare.bak.listeners.IInternalListener;

public class InternalListener implements IInternalListener {

	private static final String TAG = "InternalListener";

	private Handler mainHandler;

	public InternalListener(Handler handler) {
		this.mainHandler = handler;
	}

	@Override
	public void receiveResultSetUserMap(String email, boolean result) {
		Log.d(TAG, "receiveResultSetUserMap");

		Message msg = new Message();
		msg.what = CommandEngine.INTERAL_REGISTER_RESULT_MSG;

		Bundle bl = new Bundle();
		bl.putString("email", email);
		bl.putBoolean("result", result);
		msg.setData(bl);

		mainHandler.sendMessage(msg);
	}

	@Override
	public void receiveGetUserMap(String email, String phone, String userId, String nick, boolean result) {
		Log.d(TAG, "receiveGetUserMap");

		Message msg = new Message();
		msg.what = CommandEngine.INTERAL_GET_USER_MSG;

		Bundle bl = new Bundle();
		bl.putString("email", email);
		bl.putString("phone", phone);
		bl.putString("userId", userId);
		bl.putString("nick", nick);
		bl.putBoolean("result", result);
		msg.setData(bl);

		mainHandler.sendMessage(msg);

	}

	@Override
	public void receiveLoginResult(String email, String userId, String nick, String phone, boolean result) {
		Log.d(TAG, "receiveLoginResult");

		Message msg = new Message();
		msg.what = CommandEngine.SELF_LONGIN_FINISHED;

		Bundle bl = new Bundle();
		bl.putString("email", email);
		bl.putString("userId", userId);
		bl.putString("nick", nick);
		bl.putString("phone", phone);
		bl.putBoolean("result", result);
		msg.setData(bl);

		mainHandler.sendMessage(msg);

	}

}

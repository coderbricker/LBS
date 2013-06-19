package com.codebricker.lbsshare.bak.client;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.codebricker.lbsshare.bak.CommandEngine;
import com.codebricker.lbsshare.bak.listeners.IGeneralListener;

public class GeneralListener implements IGeneralListener {

	private static final String TAG = "GeneralListener";

	private Handler mainHandler;

	public GeneralListener(Handler mainHandler) {
		this.mainHandler = mainHandler;
	}

	@Override
	public void receiveAuthenticateResult(boolean succeeded, String reason) {
		Log.d(TAG, "receiveAuthenticateResult");

		Message msg = new Message();
		msg.what = CommandEngine.AUTHETICATE_RESULT_MSG;

		Bundle bl = new Bundle();
		bl.putBoolean("result", succeeded);
		bl.putString("reason", reason);
		msg.setData(bl);

		mainHandler.sendMessage(msg);
	}

	@Override
	public void receiveServiceStatusChanged(int statusType, String statusMsg) {
		Message msg = new Message();
		msg.what = CommandEngine.CONNECTION_STATE_MSG;

		Bundle bl = new Bundle();
		bl.putInt("status", statusType);
		bl.putString("message", statusMsg);
		msg.setData(bl);

		mainHandler.sendMessage(msg);

	}

	@Override
	public void receiveUnusefulInfo() {

	}
}

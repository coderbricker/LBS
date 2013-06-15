package com.codebricker.lbsshare.common.async;

import android.os.AsyncTask;
import android.os.Process;
import android.util.Log;

import com.codebricker.lbsshare.App;
import com.codebricker.lbsshare.common.utils.CommUtil;

public abstract class CbkAsyncTask<Result> extends AsyncTask<Void, Void, Result> {
	private static final String TAG = App.TAG
			+ CbkAsyncTask.class.getSimpleName();

	private MfAsyncCallback<Result> mCbk;
	private Throwable exception;

	public CbkAsyncTask(MfAsyncCallback<Result> cbk) {
		mCbk = cbk;
	}

	abstract public Result runInBg() throws Throwable;

	@Override
	final protected void onPreExecute() {
		try {
			if (mCbk != null && !mCbk.onPreExecute()) {
				this.cancel(true);
			}
		} catch (Throwable e) {
			this.cancel(true);
			mCbk.onFail(e);
		}
	}

	@Override
	final protected Result doInBackground(Void... params) {
		int tid = Process.myTid();
		int priority = Process.getThreadPriority(tid);
		Process.setThreadPriority(Process.THREAD_PRIORITY_DEFAULT);
		try {
			return runInBg();
		} catch (Throwable t) {
			exception = t;
		} finally {
			Process.setThreadPriority(priority);
		}
		// TODO Auto-generated method stub
		return null;
	}

	final protected void onPostExecute(Result result) {
		if (mCbk == null) {
			return;
		}
		if (exception != null) {
			mCbk.onFail(exception);
			return;
		}
		mCbk.onSucess(result);
	};

	public static abstract class MfAsyncCallback<Result> {
		public boolean onPreExecute() {
			return true;
		}

		abstract public void onSucess(Result result);

		public void onFail(Throwable e) {
			Log.e(TAG, CommUtil.formatException(e));
		}
	}

}

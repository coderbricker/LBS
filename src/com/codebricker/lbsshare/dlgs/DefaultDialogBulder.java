package com.codebricker.lbsshare.dlgs;

import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

/**
 * @author qishengxing
 * 
 */
public class DefaultDialogBulder extends AlertDialog.Builder {
	private static final String TAG = "test";

	private Map<Integer, Integer> mBtnIdMap = new HashMap<Integer, Integer>(3);
	private SgDlgClickListener mClickListener;
	private Context mContext;

	public DefaultDialogBulder(Context context) {
		super(context);
		mContext = context;
	}

//	private View buildTitle(CharSequence title){
//		LinearLayout ll = new LinearLayout(mContext);
//		ll.setOrientation(LinearLayout.VERTICAL);
//		
//		TextView tv = new TextView(mContext);
//		tv.setText(title);
//		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//		
//		View.inflate(mContext, R.layout.divider_view, ll);
//		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		ll.addView(tv,0);
//		
//		ll.setLayoutParams(params);
//		
//		return ll;
//	}

	public void setBtnName(int... btnIdNameArr) {
		if (btnIdNameArr == null || btnIdNameArr.length < 2) {
			return;
		}
		for (int i = 0; i < btnIdNameArr.length; i += 2) {
			mBtnIdMap.put(btnIdNameArr[i], btnIdNameArr[i + 1]);
		}
	}

	public void setOnClickListener(SgDlgClickListener listener) {
		mClickListener = listener;
	}

	@Override
	public AlertDialog create() {
		if (mClickListener != null) {
			OnClickListener clickListener = new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case DialogInterface.BUTTON_POSITIVE:
						mClickListener.onOK(dialog, null);
						break;
					case DialogInterface.BUTTON_NEGATIVE:
						mClickListener.onCancel(dialog, null);
						break;
					}
				}
			};

			if (mBtnIdMap.containsKey(DialogInterface.BUTTON_NEGATIVE)) {
				int name = mBtnIdMap.get(DialogInterface.BUTTON_NEGATIVE);
				this.setNegativeButton(name, clickListener);
			} 
			if (mBtnIdMap.containsKey(DialogInterface.BUTTON_POSITIVE)) {
				int name = mBtnIdMap.get(DialogInterface.BUTTON_POSITIVE);
				this.setPositiveButton(name, clickListener);
			} 

//			Integer OKName = mBtnIdMap.get(DialogInterface.BUTTON_POSITIVE);
//			if (OKName == null) {
//				OKName = android.R.string.ok;
//			}
//			this.setPositiveButton(OKName, clickListener);
		}
		return super.create();
	}

	/**
	 * 
	 * @author qishengxing
	 * 
	 */
	public static class SgDlgClickListener {
		public void onOK(DialogInterface dlg, Bundle data) {
			dlg.dismiss();
		}

		public void onCancel(DialogInterface dlg, Bundle data) {
			dlg.cancel();
		}

		public void onNature(DialogInterface dlg, Bundle data) {

		}
	}
}

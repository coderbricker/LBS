// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.

package com.codebricker.lbsshare.dlgs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.codebricker.lbsshare.R;

public class WaitDialog extends Dialog {

	private TextView titleView;
	private TextView messageView;
	
	public WaitDialog(Context context) {
		super(context, R.style.Dialog_No_Title);
		
		View progressView = View.inflate(context,R.layout.progress_dialog, null);
		setContentView(progressView);
		
		titleView = (TextView) progressView.findViewById(R.id.progress_dialog_title);
		messageView = (TextView) progressView.findViewById(R.id.progress_dialog_message);
		
		this.setCancelable(false);
	}
	
	
	public void setTitle(String title) {
		titleView.setText(title); 
	}
	
	public void setMessage(String message) {
		messageView.setText(message);
	}

}

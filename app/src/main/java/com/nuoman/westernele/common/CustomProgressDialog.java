package com.nuoman.westernele.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.nuoman.tabletattendance.R;


/**
 *  加载数据进度框
 */
public class CustomProgressDialog extends Dialog {

	public CustomProgressDialog(Context context) {
		super(context, R.style.selection_dialog_theme);
		getWindow().getAttributes().alpha = 0.6f;
	}

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
		getWindow().getAttributes().alpha = 0.6f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_dialog);
		setCanceledOnTouchOutside(false);
	}


	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}


}

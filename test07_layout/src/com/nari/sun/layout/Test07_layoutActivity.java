package com.nari.sun.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Test07_layoutActivity extends Activity {
	// 按钮
	private Button button0;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	// 按钮监听器
	private OnClickListener onClickListener0;
	private OnClickListener onClickListener1;
	private OnClickListener onClickListener2;
	private OnClickListener onClickListener3;
	private OnClickListener onClickListener4;
	private OnClickListener onClickListener5;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		onClickListener0 = new OnClickListener() {

			public void onClick(View v) {
				Intent intent0 = new Intent(Test07_layoutActivity.this, Test07_layoutActivityRelativeLayout.class);
				startActivity(intent0);
			}
		};

		onClickListener1 = new OnClickListener() {

			public void onClick(View v) {
				Intent intent1 = new Intent(Test07_layoutActivity.this, Test07_layoutActivityFrameLayout.class);
				setTitle("FrameLayout布局");
				startActivity(intent1);

			}
		};

		onClickListener2 = new OnClickListener() {

			public void onClick(View v) {
				Intent intent2 = new Intent(Test07_layoutActivity.this, Test07_layoutActivityLayout.class);
				setTitle("混合布局");
				startActivity(intent2);
			}
		};

		onClickListener3 = new OnClickListener() {

			public void onClick(View v) {
				Intent intent3 = new Intent(Test07_layoutActivity.this, Test07_layoutActivityTableLayout.class);
				setTitle("表格布局");
				startActivity(intent3);
			}
		};
		onClickListener4 = new OnClickListener() {

			public void onClick(View v) {
				Intent intent4 = new Intent(Test07_layoutActivity.this, Test07_Widget.class);
				// setTitle("WIDGET应用");
				startActivity(intent4);
			}
		};
		onClickListener5 = new OnClickListener() {

			public void onClick(View v) {
				Intent intent5 = new Intent(Test07_layoutActivity.this, GridViewActivity.class);
				startActivity(intent5);
			}
		};

		setContentView(R.layout.main);

		button0 = (Button) findViewById(R.id.button0);
		button0.setOnClickListener(onClickListener0);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(onClickListener1);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(onClickListener2);
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(onClickListener3);
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(onClickListener4);
		button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(onClickListener5);
	}
}
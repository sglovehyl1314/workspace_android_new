package com.nari.sun.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class Test07_layoutActivityLayout extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		setContentView(linearLayout);

		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout relativeLeft = (RelativeLayout) layoutInflater.inflate(R.layout.left, null);
		RelativeLayout relativeRight = (RelativeLayout) layoutInflater.inflate(R.layout.right, null);
		RelativeLayout.LayoutParams relParam = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		linearLayout.addView(relativeLeft, 100, 100);
		linearLayout.addView(relativeRight, relParam);
	}

}

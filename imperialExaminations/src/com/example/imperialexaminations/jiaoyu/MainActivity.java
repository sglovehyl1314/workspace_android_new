package com.example.imperialexaminations.jiaoyu;

import com.example.imperialexaminations.R;
import com.example.imperialexaminations.data.ImgData;
import com.example.imperialexaminations.tools.IO;
import com.example.imperialexaminations.tools.ScreenView;
import com.example.imperialexaminations.view.StartView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements Callback {
	public static MainActivity ma;
	public static Handler handler;
	public ScreenView screen;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		ma =this;
		handler = new Handler();
		screen = StartView.getInstance(this);
		ImgData.io = new IO(this);
		ImgData.loading();
		setContentView(screen);
	}


	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		return false;
	}

}

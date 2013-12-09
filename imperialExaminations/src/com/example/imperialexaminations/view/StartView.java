package com.example.imperialexaminations.view;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.imperialexaminations.data.ImgData;
import com.example.imperialexaminations.jiaoyu.MainActivity;
import com.example.imperialexaminations.tools.ScreenView;

public class StartView extends ScreenView {
	public static StartView sv = null;
	int machedonghuaxiabiao = 0;
	int n = 0;
	int machex = 10;
	int machey = 10;
	int kaishix = 150;
	int kaishiy = 700;

	boolean iskaishi = false;

	public StartView(Activity context) {
		super(context);
	}

	public static ScreenView getInstance(MainActivity mainActivity) {
		if (sv == null) {
			sv = new StartView(mainActivity);
		}
		return sv;
	}

	int m = 0;

	@Override
	public void DrawScreen(Canvas canvas, Paint paint) {
		canvas.drawBitmap(ImgData.caidanbeijing, 0, 10, paint);
		canvas.drawBitmap(ImgData.mache[machedonghuaxiabiao], machex, machey, paint);
		if (iskaishi == false) {
			canvas.drawBitmap(ImgData.kaishi, kaishix, kaishiy, paint);
		} else {
			/*canvas.drawBitmap(ImgData.zhuangzaizhong, kaishix + 30, kaishiy, paint);
			m++;
			if (m == 10) {
				m = 0;
				iskaishi = false;
				MainActivity.handler.sendEmptyMessage(1);
			}*/
		}
	}

	@Override
	public void logic() {
		n++;
		if (n == 5) {
			n = 0;
			machedonghuaxiabiao++;
		}
		if (machedonghuaxiabiao > 5) {
			machedonghuaxiabiao = 0;
		}
	}

	@Override
	public void touchScreen(MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			int x = (int) event.getX();
			int y = (int) event.getY();
			if (x >= kaishix && x < kaishix + 250 && y >= kaishiy && y < kaishiy + 80) {
				iskaishi = true;
			}
		}

	}

	@Override
	public void Destroy() {
		// TODO Auto-generated method stub

	}

}

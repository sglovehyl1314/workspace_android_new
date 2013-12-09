package com.example.imperialexaminations.tools;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public abstract class ScreenView extends SurfaceView implements Runnable, Callback {

	public Thread th = null;
	public SurfaceHolder holder = null;
	public Paint pa;
	public int number = 0;
	public long sTime = 0;

	public ScreenView(Activity context) {
		super(context);
		holder = this.getHolder();
		holder.addCallback(this);
		pa = new Paint();
	}

	public abstract void DrawScreen(Canvas canvas, Paint paint);

	public abstract void logic();

	public abstract void Destroy();
	
	public abstract void touchScreen(MotionEvent event);
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		th = new Thread(this);
		th.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		th = null;
	}

	@Override
	public void run() {
		Canvas ca = null;
		while (th != null) {
			sTime = System.currentTimeMillis();
			ca = holder.lockCanvas();
			try {
				logic();
				DrawScreen(ca, pa);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ca != null) {
					holder.unlockCanvasAndPost(ca);
				}
			}
			sTime = System.currentTimeMillis() - sTime;
			if (sTime < 50) {
				try {
					Thread.sleep(50 - sTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

			}
		}
		if (th == null) {
			Destroy();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		touchScreen(event);
		return true;
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		super.onWindowFocusChanged(hasWindowFocus);
	}


}

package com.nari.sun;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WelcomeView extends SurfaceView implements SurfaceHolder.Callback {
	MainActivity mainActivity;
	private TutorialThread thread;
	private WelcomeViewThread moveThread;
	
	Bitmap welcomebackage;
	Bitmap logo;
	Bitmap boy;
	Bitmap oldboy;
	Bitmap bordbackground;
	Bitmap logo2;
	Bitmap menu;

	int logoX = -120;
	int boyX = -100;
	int oldboyX = -120;
	int logo2X = 320;
	int bordbackgroundY = -100;
	int menuY = 520;

	public WelcomeView(Context context, MainActivity mainActivity) {
		super(context);
		this.mainActivity = mainActivity;
		getHolder().addCallback(this);
		this.thread = new TutorialThread(getHolder(), this);
		this.moveThread = new WelcomeViewThread(this);
		initBitmap();
	}

	public void initBitmap() {
		welcomebackage = BitmapFactory.decodeResource(getResources(), R.drawable.welcomebackage);
		logo = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
		boy = BitmapFactory.decodeResource(getResources(), R.drawable.boy);
		oldboy = BitmapFactory.decodeResource(getResources(), R.drawable.oldboy);
		bordbackground = BitmapFactory.decodeResource(getResources(), R.drawable.bordbackground);
		logo2 = BitmapFactory.decodeResource(getResources(), R.drawable.logo2);
		menu = BitmapFactory.decodeResource(getResources(), R.drawable.menu);
	}

	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(welcomebackage, 0, 100, null);
		canvas.drawBitmap(logo, logoX, 110, null);
		canvas.drawBitmap(boy, boyX, 210, null);
		canvas.drawBitmap(oldboy, oldboyX, 270, null);
		canvas.drawBitmap(bordbackground, 150, bordbackgroundY, null);
		canvas.drawBitmap(logo2, logo2X, 100, null);
		canvas.drawBitmap(menu, 200, menuY, null);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setFlag(true);
		thread.start();

		moveThread.setFlag(true);
		moveThread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		thread.setFlag(false);
		moveThread.setFlag(false);

		while (retry) {
			try {
				thread.join();
				moveThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (event.getX() > 200 && event.getX() < 200 + menu.getWidth() && event.getY() > 355 && event.getY() < 355 + menu.getHeight()) {
				mainActivity.myHandler.sendEmptyMessage(1);
			}
		}
		return super.onTouchEvent(event);
	}

	class TutorialThread extends Thread {
		private int span = 100;
		private SurfaceHolder surfaceHolder;
		private WelcomeView welcomeView;
		private boolean flag = false;

		public TutorialThread(SurfaceHolder surfaceHolder, WelcomeView welcomeView) {
			this.surfaceHolder = surfaceHolder;
			this.welcomeView = welcomeView;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		@SuppressLint("WrongCall")
		public void run() {
			Canvas c;
			while (this.flag) {
				c = null;
				try {
					c = this.surfaceHolder.lockCanvas(null);
					synchronized (this.surfaceHolder) {
						welcomeView.onDraw(c);
					}
				} finally {
					if (c != null) {
						this.surfaceHolder.unlockCanvasAndPost(c);
					}
				}
				try {
					Thread.sleep(span);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

}

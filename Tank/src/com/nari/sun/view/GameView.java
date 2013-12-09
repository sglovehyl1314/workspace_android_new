package com.nari.sun.view;

import com.nari.sun.activity.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.View;

public class GameView extends View {
	private int x;
	private int y;
	private Bitmap bitmap;

	public GameView(Context context) {
		super(context);
		x = 0;
		y = 0;
		Resources res = context.getResources();
		bitmap = BitmapFactory.decodeResource(res, R.drawable.battlecity);
		setFocusable(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// canvas.drawText("Ì¹¿Ë´óÕ½", 0, 50, new Paint());
		canvas.drawBitmap(bitmap, x, y, new Paint());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		System.out.println("onKeyDown");
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			y -= 10;
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			y += 10;
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			x -= 10;
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			x += 10;
			break;
		}
		postInvalidate();
		return super.onKeyDown(keyCode, event);
	}

}

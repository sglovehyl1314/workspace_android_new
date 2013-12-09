package com.nari.sun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Enemy {
	public int type;
	public static final int TYPE_FLY = 1;
	public static final int TYPE_DUCKL = 2;
	public static final int TYPE_DUCKR = 3;
	public Bitmap bmpEnemy;
	public int x, y;
	public int frameW, frameH;
	private int frameIndex;
	private int speed;
	public boolean isDead;

	public Enemy(Bitmap bmpEnemy, int enemyType, int x, int y) {
		this.bmpEnemy = bmpEnemy;
		this.type = enemyType;
		this.x = x;
		this.y = y;
		frameW = bmpEnemy.getWidth() / 10;
		frameH = bmpEnemy.getHeight();
		switch (type) {
		case TYPE_FLY:
			speed = 25;
			break;
		case TYPE_DUCKL:
			speed = 3;
			break;
		case TYPE_DUCKR:
			speed = 3;
			break;
		}

	}

	public void draw(Canvas canvas, Paint paint) {
		canvas.save();
		canvas.clipRect(x, y, x + frameW, y + frameH);
		canvas.drawBitmap(bmpEnemy, x - frameIndex * frameW, y, paint);
		canvas.restore();
	}

	public void logic() {
		frameIndex++;
		if (frameIndex >= 10) {
			frameIndex = 0;
		}
		switch (type) {
		case TYPE_FLY:
			if (isDead == false) {
				speed -= 1;
				y += speed;
				if (y <= -200) {
					isDead = true;
				}
			}
			break;
		case TYPE_DUCKL:
			if (isDead == false) {
				x += speed / 2;
				y += speed;
				if (x > MySurfaceView.screenW) {
					isDead = true;
				}
			}
			break;
		case TYPE_DUCKR:
			if (isDead == false) {
				x -= speed / 2;
				y += speed;
				if (x < -50) {
					isDead = true;
				}
			}
			break;
		}
	}

	public boolean isCollisionWith(Bullet bullet) {
		int x2 = bullet.bulletX;
		int y2 = bullet.bulletY;
		int w2 = bullet.bmpBullet.getWidth();
		int h2 = bullet.bmpBullet.getHeight();
		if (x >= x2 && x >= x2 + w2) {
			return false;
		} else if (x <= x2 && x + frameW <= x2) {
			return false;
		} else if (y >= y2 && y > y2 + h2) {
			return false;
		} else if (y <= y2 && y + frameH <= y2) {
			return false;
		}
		isDead = true;
		return true;
	}
}

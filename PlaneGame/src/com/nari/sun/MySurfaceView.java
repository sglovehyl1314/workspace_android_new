package com.nari.sun;

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class MySurfaceView extends SurfaceView implements Callback, Runnable {
	private SurfaceHolder sfh;
	private Thread th;

	private Paint paint;
	private Canvas canvas;

	private boolean flag;
	public static int screenW, screenH;

	public static final int GAME_MENU = 0;
	public static final int GAME_ING = 1;
	public static final int GAME_WIN = 2;
	public static final int GAME_LOST = 3;
	public static final int GAME_PAUSE = -1;

	public static int gameState = GAME_MENU;

	private Resources res = this.getResources();

	private Bitmap bmpBackGroud;
	private Bitmap bmpBoom;
	private Bitmap bmpBossBoom;
	private Bitmap bmpButton;
	private Bitmap bmpButtonPress;
	private Bitmap bmpEnemyDuck;
	private Bitmap bmpEnemyFly;
	private Bitmap bmpEnemyBoss;
	private Bitmap bmpGameWin;
	private Bitmap bmpGameLost;
	private Bitmap bmpPlayer;
	private Bitmap bmpPlayerHp;
	private Bitmap bmpMenu;
	public static Bitmap bmpBullet;
	public static Bitmap bmpEnemyBullet;
	public static Bitmap bmpBossBullet;
	// ²Ëµ¥
	private GameMenu gameMenu;
	// ±³¾°
	private GameBg backGround;

	private Player player;

	private Vector<Enemy> vcEnemy;

	private int createEnemyTime = 50;
	private int count;

	private int enemyArray[][] = { { 1, 2 }, { 1, 1 }, { 1, 3, 1, 2 }, { 1, 2 }, { 2, 3 }, { 3, 1, 3 }, { 2, 2 }, { 1, 2 }, { 2, 2 }, { 1, 3, 1, 1 }, { 2, 1 }, { 1, 3 }, { 2, 1 },
			{ -1 } };
	private int enemyArrayIndex;
	private boolean isBoss;
	private Random random;

	private Vector<Bullet> vcBullet;
	private int countEnemyBullet;
	private Vector<Bullet> vcBulletPalyer;
	private int countPlayerBullet;

	private Vector<Boom> vcBoom;
	private Boss boss;
	public static Vector<Bullet> vcBulletBoss;

	public MySurfaceView(Context context) {
		super(context);
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setKeepScreenOn(true);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenW = this.getWidth();
		screenH = this.getHeight();
		initGame();
		flag = true;
		th = new Thread(this);
		th.start();
	}

	private void initGame() {
		if (gameState == GAME_MENU) {
			bmpBackGroud = BitmapFactory.decodeResource(res, R.drawable.background);
			bmpBoom = BitmapFactory.decodeResource(res, R.drawable.boom);
			bmpBossBoom = BitmapFactory.decodeResource(res, R.drawable.boos_boom);
			bmpButton = BitmapFactory.decodeResource(res, R.drawable.button);
			bmpButtonPress = BitmapFactory.decodeResource(res, R.drawable.button_press);
			bmpEnemyDuck = BitmapFactory.decodeResource(res, R.drawable.enemy_duck);
			bmpEnemyFly = BitmapFactory.decodeResource(res, R.drawable.enemy_fly);
			bmpEnemyBoss = BitmapFactory.decodeResource(res, R.drawable.enemy_pig);
			bmpGameWin = BitmapFactory.decodeResource(res, R.drawable.gamewin);
			bmpGameLost = BitmapFactory.decodeResource(res, R.drawable.gamelost);
			bmpPlayer = BitmapFactory.decodeResource(res, R.drawable.player);
			bmpPlayerHp = BitmapFactory.decodeResource(res, R.drawable.hp);
			bmpMenu = BitmapFactory.decodeResource(res, R.drawable.menu);
			bmpBullet = BitmapFactory.decodeResource(res, R.drawable.bullet);
			bmpEnemyBullet = BitmapFactory.decodeResource(res, R.drawable.bullet_enemy);
			bmpBossBullet = BitmapFactory.decodeResource(res, R.drawable.boosbullet);

			vcBoom = new Vector<Boom>();
			vcBullet = new Vector<Bullet>();
			vcBulletPalyer = new Vector<Bullet>();
			gameMenu = new GameMenu(bmpMenu, bmpButton, bmpButtonPress);
			backGround = new GameBg(bmpBackGroud);

			player = new Player(bmpPlayer, bmpPlayerHp);
			vcEnemy = new Vector<Enemy>();
			random = new Random();
			boss = new Boss(bmpEnemyBoss);
			vcBulletBoss = new Vector<Bullet>();
		}
	}

	private void myDraw() {
		try {
			canvas = sfh.lockCanvas();
			if (canvas != null) {
				canvas.drawColor(Color.WHITE);
				switch (gameState) {
				case GAME_MENU:
					gameMenu.draw(canvas, paint);
					break;
				case GAME_ING:
					backGround.draw(canvas, paint);
					player.draw(canvas, paint);
					if (isBoss == false) {
						for (int i = 0; i < vcEnemy.size(); i++) {
							vcEnemy.elementAt(i).draw(canvas, paint);
						}
						for (int i = 0; i < vcBullet.size(); i++) {
							vcBullet.elementAt(i).draw(canvas, paint);
						}
					} else {
						boss.draw(canvas, paint);
						for (int i = 0; i < vcBulletBoss.size(); i++) {
							vcBulletBoss.elementAt(i).draw(canvas, paint);
						}
					}
					for (int i = 0; i < vcBulletPalyer.size(); i++) {
						vcBulletPalyer.elementAt(i).draw(canvas, paint);
					}
					for (int i = 0; i < vcBoom.size(); i++) {
						vcBoom.elementAt(i).draw(canvas, paint);
					}
					break;
				case GAME_PAUSE:
					break;
				case GAME_WIN:
					canvas.drawBitmap(bmpGameWin, 0, 0, paint);
					break;
				case GAME_LOST:
					canvas.drawBitmap(bmpGameLost, 0, 0, paint);
					break;
				}
			}
		} catch (Exception e) {
		} finally {
			if (canvas != null) {
				sfh.unlockCanvasAndPost(canvas);

			}
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (gameState) {
		case GAME_MENU:
			gameMenu.onTouchEvent(event);
			break;
		case GAME_ING:
			break;
		case GAME_PAUSE:
			break;
		case GAME_WIN:
			break;
		case GAME_LOST:

			break;
		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (gameState == GAME_ING || gameState == GAME_WIN || gameState == GAME_LOST) {
				gameState = GAME_MENU;
				isBoss = false;
				initGame();
				enemyArrayIndex = 0;
			} else if (gameState == GAME_MENU) {
				MainActivity.instance.finish();
				System.exit(0);
			}
			return true;
		}
		switch (gameState) {
		case GAME_MENU:
			break;
		case GAME_ING:
			player.onKeyDown(keyCode, event);
			break;
		case GAME_PAUSE:
			break;
		case GAME_WIN:
			break;
		case GAME_LOST:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (gameState == GAME_ING || gameState == GAME_WIN || gameState == GAME_LOST) {
				gameState = GAME_MENU;
			}
			return true;
		}
		switch (gameState) {
		case GAME_MENU:
			break;
		case GAME_ING:
			player.onKeyUp(keyCode, event);
			break;
		case GAME_PAUSE:
			break;
		case GAME_WIN:
			break;
		case GAME_LOST:
			break;
		}
		return super.onKeyUp(keyCode, event);
	}

	private void logic() {
		switch (gameState) {
		case GAME_MENU:
			break;
		case GAME_ING:
			backGround.logic();
			player.logic();
			if (isBoss == false) {
				for (int i = 0; i < vcEnemy.size(); i++) {
					Enemy en = vcEnemy.elementAt(i);
					if (en.isDead) {
						vcEnemy.removeElementAt(i);
					} else {
						en.logic();
					}
				}

				count++;
				if (count % createEnemyTime == 0) {
					for (int i = 0; i < enemyArray[enemyArrayIndex].length; i++) {
						if (enemyArray[enemyArrayIndex][i] == 1) {
							int x = random.nextInt(screenW - 100) + 50;
							vcEnemy.addElement(new Enemy(bmpEnemyFly, 1, x, -50));
						} else if (enemyArray[enemyArrayIndex][i] == 2) {
							int y = random.nextInt(20);
							vcEnemy.addElement(new Enemy(bmpEnemyDuck, 2, -50, y));
						} else if (enemyArray[enemyArrayIndex][i] == 3) {
							int y = random.nextInt(20);
							vcEnemy.addElement(new Enemy(bmpEnemyDuck, 3, screenW + 50, y));
						}
					}
					if (enemyArrayIndex == enemyArray.length - 1) {
						isBoss = true;
					} else {
						enemyArrayIndex++;
					}
				}
				for (int i = 0; i < vcEnemy.size(); i++) {
					if (player.isCollisonWith(vcEnemy.elementAt(i))) {
						player.setPlayerHp(player.getPlayerHp() - 1);
						if (player.getPlayerHp() <= -1) {
							gameState = GAME_LOST;
						}
					}
				}
				countEnemyBullet++;
				if (countEnemyBullet % 40 == 0) {
					for (int i = 0; i < vcEnemy.size(); i++) {
						Enemy en = vcEnemy.elementAt(i);
						int bulletType = 0;
						switch (en.type) {
						case Enemy.TYPE_FLY:
							bulletType = Bullet.BULLET_FLY;
						case Enemy.TYPE_DUCKL:
						case Enemy.TYPE_DUCKR:
							bulletType = Bullet.BULLET_DUCK;
						}
						vcBullet.add(new Bullet(bmpEnemyBullet, en.x + 10, en.y + 20, bulletType));
					}
				}
				for (int i = 0; i < vcBullet.size(); i++) {
					Bullet b = vcBullet.elementAt(i);
					if (b.isDead) {
						vcBullet.removeElement(b);
					} else {
						b.logic();
					}
				}
				for (int i = 0; i < vcBullet.size(); i++) {
					if (player.isCollisonWith(vcBullet.elementAt(i))) {
						player.setPlayerHp(player.getPlayerHp() - 1);
						if (player.getPlayerHp() <= -1) {
							gameState = GAME_LOST;
						}
					}
				}
				for (int i = 0; i < vcBulletPalyer.size(); i++) {
					Bullet blPlayer = vcBulletPalyer.elementAt(i);
					for (int j = 0; j < vcEnemy.size(); j++) {
						if (vcEnemy.elementAt(j).isCollisionWith(blPlayer)) {
							vcBoom.add(new Boom(bmpBoom, vcEnemy.elementAt(j).x, vcEnemy.elementAt(j).y, 7));
						}
					}
				}
			} else {
				boss.logic();
				if (countPlayerBullet % 10 == 0) {
					vcBulletBoss.add(new Bullet(bmpBossBullet, boss.x + 35, boss.y + 40, Bullet.BULLET_BOSS));
				}
				for (int i = 0; i < vcBulletBoss.size(); i++) {
					Bullet b = vcBulletBoss.elementAt(i);
					if (b.isDead) {
						vcBulletBoss.removeElement(b);
					} else {
						b.logic();
					}
				}
				for (int i = 0; i < vcBulletBoss.size(); i++) {
					if (player.isCollisonWith(vcBulletBoss.elementAt(i))) {
						player.setPlayerHp(player.getPlayerHp() - 1);
						if (player.getPlayerHp() <= -1) {
							gameState = GAME_LOST;
						}
					}
				}

				for (int i = 0; i < vcBulletPalyer.size(); i++) {
					Bullet b = vcBulletPalyer.elementAt(i);
					if (boss.isCollisionWith(b)) {
						if (boss.hp <= 0) {
							gameState = GAME_WIN;
						} else {
							b.isDead = true;
							boss.setHp(boss.hp - 1);
							vcBoom.add(new Boom(bmpBossBoom, boss.x + 25, boss.y + 30, 5));
							vcBoom.add(new Boom(bmpBossBoom, boss.x + 35, boss.y + 40, 5));
							vcBoom.add(new Boom(bmpBossBoom, boss.x + 45, boss.y + 50, 5));
						}
					}
				}
			}
			countPlayerBullet++;
			if (countPlayerBullet % 20 == 0) {
				vcBulletPalyer.add(new Bullet(bmpBullet, player.x + 15, player.y - 20, Bullet.BULLET_PLAYER));
			}
			for (int i = 0; i < vcBulletPalyer.size(); i++) {
				Bullet b = vcBulletPalyer.elementAt(i);
				if (b.isDead) {
					vcBullet.removeElement(b);
				} else {
					b.logic();
				}
			}
			for (int i = 0; i < vcBoom.size(); i++) {
				Boom boom = vcBoom.elementAt(i);
				if (boom.playEnd) {
					vcBoom.removeElementAt(i);
				} else {
					vcBoom.elementAt(i).logic();
				}
			}
			break;
		case GAME_PAUSE:
			break;
		case GAME_WIN:
			break;
		case GAME_LOST:
			break;

		}
	}

	@Override
	public void run() {
		while (flag) {
			long start = System.currentTimeMillis();
			myDraw();
			logic();
			long end = System.currentTimeMillis();

			if (end - start < 50) {
				try {
					Thread.sleep(50 - (end - start));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		flag = false;
	}

}

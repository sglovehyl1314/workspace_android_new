package com.nari.sun;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class GameView extends SurfaceView implements Callback {
	private Guize guize;

	private MainActivity mainActivity;
	private TutorialThread thread;
	private TimeThread timeThread;

	Paint paint;

	Bitmap qipan;
	Bitmap qizibackground;
	Bitmap win;
	Bitmap lost;
	Bitmap ok;
	Bitmap vs;
	Bitmap right;
	Bitmap left;
	Bitmap current;
	Bitmap exit2;
	Bitmap sound2;
	Bitmap sound3;
	Bitmap time;
	Bitmap redtime;
	Bitmap background;

	Bitmap[] heizi = new Bitmap[7];
	Bitmap[] hongzi = new Bitmap[7];

	Bitmap[] number = new Bitmap[10];
	Bitmap[] redNumber = new Bitmap[10];

	private MediaPlayer go;

	boolean caiPan = true;
	boolean focus = false;
	int selectqizi = 0;

	int startI, startJ;
	int endI, endJ;

	int status = 0;
	int heiTime = 0;
	int hongTime = 0;

	int[][] qizi = new int[][] { { 2, 3, 6, 5, 1, 5, 6, 3, 2 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 4, 0, 0, 0, 0, 0, 4, 0 }, { 7, 0, 7, 0, 7, 0, 7, 0, 7 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 14, 0, 14, 0, 14, 0, 14, 0, 14 }, { 0, 11, 0, 0, 0, 0, 0, 11, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 9, 10, 13, 12, 8, 12, 13, 10, 9 } };

	public GameView(Context context, MainActivity mainActivity) {
		super(context);
		this.mainActivity = mainActivity;
		getHolder().addCallback(this);
		go = MediaPlayer.create(this.getContext(), R.raw.go);
		this.thread = new TutorialThread(getHolder(), this);
		this.timeThread = new TimeThread(this);
		init();
		guize = new Guize();
	}

	public void init() {
		paint = new Paint();
		qipan = BitmapFactory.decodeResource(getResources(), R.drawable.qipan);
		qizibackground = BitmapFactory.decodeResource(getResources(), R.drawable.qizi);
		win = BitmapFactory.decodeResource(getResources(), R.drawable.win);
		lost = BitmapFactory.decodeResource(getResources(), R.drawable.lost);
		ok = BitmapFactory.decodeResource(getResources(), R.drawable.ok);
		vs = BitmapFactory.decodeResource(getResources(), R.drawable.vs);
		right = BitmapFactory.decodeResource(getResources(), R.drawable.right);
		left = BitmapFactory.decodeResource(getResources(), R.drawable.left);
		current = BitmapFactory.decodeResource(getResources(), R.drawable.current);
		exit2 = BitmapFactory.decodeResource(getResources(), R.drawable.exit2);
		sound2 = BitmapFactory.decodeResource(getResources(), R.drawable.sound2);
		sound3 = BitmapFactory.decodeResource(getResources(), R.drawable.sound3);
		time = BitmapFactory.decodeResource(getResources(), R.drawable.time);
		redtime = BitmapFactory.decodeResource(getResources(), R.drawable.redtime);
		background = BitmapFactory.decodeResource(getResources(), R.drawable.bacnground);

		heizi[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heishuai);
		heizi[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heiju);
		heizi[2] = BitmapFactory.decodeResource(getResources(), R.drawable.heima);
		heizi[3] = BitmapFactory.decodeResource(getResources(), R.drawable.heipao);
		heizi[4] = BitmapFactory.decodeResource(getResources(), R.drawable.heishi);
		heizi[5] = BitmapFactory.decodeResource(getResources(), R.drawable.heixiang);
		heizi[6] = BitmapFactory.decodeResource(getResources(), R.drawable.heibing);

		hongzi[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hongjiang);
		hongzi[1] = BitmapFactory.decodeResource(getResources(), R.drawable.hongju);
		hongzi[2] = BitmapFactory.decodeResource(getResources(), R.drawable.hongma);
		hongzi[3] = BitmapFactory.decodeResource(getResources(), R.drawable.hongpao);
		hongzi[4] = BitmapFactory.decodeResource(getResources(), R.drawable.hongshi);
		hongzi[5] = BitmapFactory.decodeResource(getResources(), R.drawable.hongxiang);
		hongzi[6] = BitmapFactory.decodeResource(getResources(), R.drawable.hongzu);

		number[0] = BitmapFactory.decodeResource(getResources(), R.drawable.number0);
		number[1] = BitmapFactory.decodeResource(getResources(), R.drawable.number1);
		number[2] = BitmapFactory.decodeResource(getResources(), R.drawable.number2);
		number[3] = BitmapFactory.decodeResource(getResources(), R.drawable.number3);
		number[4] = BitmapFactory.decodeResource(getResources(), R.drawable.number4);
		number[5] = BitmapFactory.decodeResource(getResources(), R.drawable.number5);
		number[6] = BitmapFactory.decodeResource(getResources(), R.drawable.number6);
		number[7] = BitmapFactory.decodeResource(getResources(), R.drawable.number7);
		number[8] = BitmapFactory.decodeResource(getResources(), R.drawable.number8);
		number[9] = BitmapFactory.decodeResource(getResources(), R.drawable.number9);

		redNumber[0] = BitmapFactory.decodeResource(getResources(), R.drawable.rednumber0);
		redNumber[1] = BitmapFactory.decodeResource(getResources(), R.drawable.rednumber1);
		redNumber[2] = BitmapFactory.decodeResource(getResources(), R.drawable.rednumber2);
		redNumber[3] = BitmapFactory.decodeResource(getResources(), R.drawable.rednumber3);
		redNumber[4] = BitmapFactory.decodeResource(getResources(), R.drawable.rednumber4);
		redNumber[5] = BitmapFactory.decodeResource(getResources(), R.drawable.rednumber5);
		redNumber[6] = BitmapFactory.decodeResource(getResources(), R.drawable.rednumber6);
		redNumber[7] = BitmapFactory.decodeResource(getResources(), R.drawable.rednumber7);
		redNumber[8] = BitmapFactory.decodeResource(getResources(), R.drawable.rednumber8);
		redNumber[9] = BitmapFactory.decodeResource(getResources(), R.drawable.rednumber9);

	}

	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(background, 0, 0, null);
		canvas.drawBitmap(qipan, 10, 10, null);

		for (int i = 0; i < qizi.length; i++) {
			for (int j = 0; j < qizi[i].length; j++) {
				if (qizi[i][j] != 0) {
					canvas.drawBitmap(qizibackground, 9 + j * 34, 10 + i * 35, null);
					if (qizi[i][j] == 1) {
						canvas.drawBitmap(heizi[0], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 2) {
						canvas.drawBitmap(heizi[1], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 3) {
						canvas.drawBitmap(heizi[2], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 4) {
						canvas.drawBitmap(heizi[3], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 5) {
						canvas.drawBitmap(heizi[4], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 6) {
						canvas.drawBitmap(heizi[5], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 7) {
						canvas.drawBitmap(heizi[6], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 8) {
						canvas.drawBitmap(hongzi[0], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 9) {
						canvas.drawBitmap(hongzi[1], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 10) {
						canvas.drawBitmap(hongzi[2], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 11) {
						canvas.drawBitmap(hongzi[3], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 12) {
						canvas.drawBitmap(hongzi[4], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 13) {
						canvas.drawBitmap(hongzi[5], 12 + j * 34, 13 + i * 35, paint);
					} else if (qizi[i][j] == 14) {
						canvas.drawBitmap(hongzi[6], 12 + j * 34, 13 + i * 35, paint);
					}
				}
			}
		}
		canvas.drawBitmap(vs, 10, 360, paint);

		canvas.drawBitmap(time, 81, 411, paint);
		int temp = this.heiTime / 60;
		String timeStr = temp + "";
		if (timeStr.length() < 2) {
			timeStr = "0" + timeStr;
		}
		for (int i = 0; i < 2; i++) {
			int tempScore = timeStr.charAt(i) - '0';
			canvas.drawBitmap(number[tempScore], 65 + i * 7, 412, paint);
		}

		temp = this.heiTime % 60;
		timeStr = temp + "";
		if (timeStr.length() < 2) {
			timeStr = "0" + timeStr;
		}
		for (int i = 0; i < 2; i++) {
			int tempScore = timeStr.charAt(i) - '0';
			canvas.drawBitmap(number[tempScore], 85 + i * 7, 412, paint);
		}

		canvas.drawBitmap(redtime, 262, 410, paint);
		int temp2 = this.hongTime / 60;
		String timeStr2 = temp + "";
		if (timeStr2.length() < 2) {
			timeStr2 = "0" + timeStr2;
		}
		for (int i = 0; i < 2; i++) {
			int tempScore = timeStr2.charAt(i) - '0';
			canvas.drawBitmap(redNumber[tempScore], 247 + i * 7, 411, paint);
		}

		temp2 = this.heiTime % 60;
		timeStr2 = temp + "";
		if (timeStr2.length() < 2) {
			timeStr2 = "0" + timeStr2;
		}
		for (int i = 0; i < 2; i++) {
			int tempScore = timeStr2.charAt(i) - '0';
			canvas.drawBitmap(redNumber[tempScore], 267 + i * 7, 411, paint);
		}

		if (caiPan == true) {
			canvas.drawBitmap(right, 155, 420, paint);
		} else {
			canvas.drawBitmap(left, 120, 420, paint);
		}
		canvas.drawBitmap(current, 138, 445, paint);
		canvas.drawBitmap(sound2, 10, 440, paint);

		if (mainActivity.isSound) {
			canvas.drawBitmap(sound3, 80, 452, paint);
		}
		canvas.drawBitmap(exit2, 250, 440, paint);

		if (status == 1) {
			canvas.drawBitmap(win, 85, 150, paint);
			canvas.drawBitmap(ok, 113, 240, paint);
		}
		if (status == 2) {
			canvas.drawBitmap(lost, 85, 150, paint);
			canvas.drawBitmap(ok, 113, 236, paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (event.getX() > 10 && event.getX() < 10 + sound2.getWidth() && event.getY() > 440 && event.getY() < 440 + sound2.getHeight()) {// 按下了声音按钮
				mainActivity.isSound = !mainActivity.isSound;// 声音取反
				if (mainActivity.isSound) {// 当需要放声音时
					if (mainActivity.gameSound != null) {// gamesound不为空时
						if (!mainActivity.gameSound.isPlaying()) {// 当前没有音乐时
							mainActivity.gameSound.start();// 播放音乐
						}
					}
				} else {
					if (mainActivity.gameSound != null) {// gamesound不为空时
						if (mainActivity.gameSound.isPlaying()) {// 当前有音乐时
							mainActivity.gameSound.pause();// 停止音乐
						}
					}
				}
			}// end 按下了声音按钮
			if (event.getX() > 250 && event.getX() < 250 + exit2.getWidth() && event.getY() > 440 && event.getY() < 440 + exit2.getHeight()) {// 按下了退出按钮
				mainActivity.myHandler.sendEmptyMessage(1);// 发送消息，切换到MenuView
			}

			if (status == 1) {// 胜利后
				if (event.getX() > 135 && event.getX() < 190 && event.getY() > 249 && event.getY() < 269) {// 点击了确定按钮
					mainActivity.myHandler.sendEmptyMessage(1);// 发送消息，切换到MenuView
				}
			} else if (status == 2) {// 失败后
				if (event.getX() > 135 && event.getX() < 190 && event.getY() > 245 && event.getY() < 265) {// 点击了确定按钮
					mainActivity.myHandler.sendEmptyMessage(1);// 发送消息，切换到MenuView
				}
			} else if (status == 0) {
				if (event.getX() > 10 && event.getX() < 310 && event.getY() > 10 && event.getY() < 360) {// 点击的位置在棋盘内时
					if (caiPan == true) {// 如果是该玩家走棋
						int i = -1, j = -1;
						int[] pos = getPos(event);// 根据坐标换算成所在的行和列
						i = pos[0];
						j = pos[1];
						if (focus == false) {// 之前没有选中的棋子
							if (qizi[i][j] != 0) {// 点击的位置有棋子
								if (qizi[i][j] > 7) {// 点击的是自己的棋子。即下面的黑色棋子
									selectqizi = qizi[i][j];// 将该棋子设为选中的棋子
									focus = true;// 标记当前有选中的棋子
									startI = i;
									startJ = j;
								}
							}
						} else {// 之前选中过棋子
							if (qizi[i][j] != 0) {// 点击的位置有棋子
								if (qizi[i][j] > 7) {// 如果是自己的棋子.
									selectqizi = qizi[i][j];// 将该棋子设为选中的棋子
									startI = i;
									startJ = j;
								} else {// 如果是对方的棋子
									endI = i;
									endJ = j;// 保存该点
									boolean canMove = guize.canMove(qizi, startI, startJ, endI, endJ);
									if (canMove) {// 如果可以移动过去
										caiPan = false;// 不让玩家走了
										if (qizi[endI][endJ] == 1 || qizi[endI][endJ] == 8) {// 如果是“帅”或“将”
											this.success();// 胜利了
										} else {
											if (mainActivity.isSound) {
												go.start();// 播放下棋声音
											}
											qizi[endI][endJ] = qizi[startI][startJ];// 移动棋子
											qizi[startI][startJ] = 0;// 将原来处设空
											startI = -1;
											startJ = -1;
											endI = -1;
											endJ = -1;// 还原保存点
											focus = false;// 标记当前没有选中棋子

											ChessMove cm = guize.searchAGoodMove(qizi);// 根据当前局势查询一个最好的走法
											if (mainActivity.isSound) {
												go.start();// 播放下棋声音
											}
											qizi[cm.toX][cm.toY] = qizi[cm.fromX][cm.fromY];// 移动棋子
											qizi[cm.fromX][cm.fromY] = 0;
											caiPan = true;// 恢复玩家响应
										}
									}
								}
							}// end点击的位置有棋子
							else {// 如果点击的位置没有棋子
								endI = i;
								endJ = j;
								boolean canMove = guize.canMove(qizi, startI, startJ, endI, endJ);// 查看是否可走
								if (canMove) {// 如果可以移动
									caiPan = false;// 不让玩家走了
									if (mainActivity.isSound) {
										go.start();// 播放下棋声音
									}
									qizi[endI][endJ] = qizi[startI][startJ];// 移动棋子
									qizi[startI][startJ] = 0;// 将原来处置空
									startI = -1;
									startJ = -1;
									endI = -1;
									endJ = -1;// 还原保存点
									focus = false;// 标志位设false

									ChessMove cm = guize.searchAGoodMove(qizi);// 得到一步走法
									if (qizi[cm.toX][cm.toY] == 8) {// 电脑吃了您的将
										status = 2;// 切换游戏状态为失败
									}
									if (mainActivity.isSound) {// 需要播放声音时
										go.start();// 播放下棋声音
									}
									qizi[cm.toX][cm.toY] = qizi[cm.fromX][cm.fromY];// 移动棋子
									qizi[cm.fromX][cm.fromY] = 0;
									caiPan = true;// 恢复玩家响应
								}
							}
						}// end 之前选中过棋子
					}
				}// end点击的位置在棋盘内时
			}// end游戏中时

		}

		return super.onTouchEvent(event);
	}

	public int[] getPos(MotionEvent e) {
		int[] pos = new int[2];
		double x = e.getX();
		double y = e.getY();
		if (x > 10 && y > 10 && x < 10 + qipan.getWidth() && y < 10 + qipan.getHeight()) {
			pos[0] = Math.round((float) ((y - 21) / 36));
			pos[1] = Math.round((float) ((x - 21) / 35));
		} else {
			pos[0] = -1;
			pos[1] = -1;
		}
		return pos;
	}

	public void success() {
		status = 1;
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		this.thread.setFlag(true);
		this.thread.start();
		this.timeThread.setFlag(true);
		this.timeThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		this.thread.setFlag(false);
		this.timeThread.setFlag(false);
		while (retry) {
			try {
				this.thread.join();
				this.timeThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}

	}

	class TutorialThread extends Thread {
		private int span = 300;
		private SurfaceHolder surfaceholder;
		private GameView gameView;
		private boolean flag = false;

		public TutorialThread(SurfaceHolder surfaceHolder, GameView gameView) {
			this.surfaceholder = surfaceHolder;
			this.gameView = gameView;
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
					c = this.surfaceholder.lockCanvas(null);
					synchronized (this.surfaceholder) {
						gameView.onDraw(c);
					}
				} finally {
					if (c != null) {
						this.surfaceholder.unlockCanvasAndPost(c);
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

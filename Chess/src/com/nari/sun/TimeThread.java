package com.nari.sun;

public class TimeThread extends Thread {
	private boolean flag = true;
	private GameView gameView;

	public TimeThread(GameView gameView) {
		this.gameView = gameView;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		while (flag) {
			if (gameView.caiPan == false) {
				gameView.heiTime++;
			} else if (gameView.caiPan == true) {
				gameView.hongTime++;
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

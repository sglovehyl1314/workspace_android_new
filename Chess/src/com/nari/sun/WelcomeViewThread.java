package com.nari.sun;

public class WelcomeViewThread extends Thread {
	private boolean flag = true;
	WelcomeView welcomeView;

	public WelcomeViewThread(WelcomeView welcomeView) {
		this.welcomeView = welcomeView;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void run() {
		try {
			Thread.sleep(300);
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (flag) {
			welcomeView.logoX += 10;
			if (welcomeView.logoX > 0) {
				welcomeView.logoX = 0;
			}
			welcomeView.boyX += 20;
			if (welcomeView.boyX > 70) {
				welcomeView.boyX = 70;
			}
			welcomeView.oldboyX += 15;
			if (welcomeView.oldboyX > 0) {
				welcomeView.oldboyX = 0;
			}
			
			welcomeView.bordbackgroundY+=50;
			if(welcomeView.bordbackgroundY>240){
				welcomeView.bordbackgroundY =240;
			}
			
			welcomeView.logo2X-=30;
			if(welcomeView.logo2X<150){
				welcomeView.logo2X=150;
			}
			if(welcomeView.logo2X==150){
				welcomeView.menuY-=30;
				if(welcomeView.menuY<355){
					welcomeView.menuY=355;
				}
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
			
	}
}

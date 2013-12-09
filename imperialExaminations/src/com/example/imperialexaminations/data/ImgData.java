package com.example.imperialexaminations.data;

import android.graphics.Bitmap;

import com.example.imperialexaminations.tools.IO;

public class ImgData {
	public static IO io;

	public static Bitmap caidanbeijing;
	public static Bitmap kaishi;
	public static Bitmap[] mache = new Bitmap[6];
	public static Bitmap zhuangzaizhong;

	public static void loading() {

		caidanbeijing = io.loadBitmap("img/zhucaidan/beijing.png");
		kaishi = io.loadBitmap("img/zhucaidan/kaishi.png");
		for (int i = 1; i <= 6; i++) {
			mache[i - 1] = io.loadBitmap("img/zhucaidan/machedonghua000" + i + ".png");
		}
		zhuangzaizhong = io.loadBitmap("img/zhucaidan/zhuangzaizhong");
	}

}

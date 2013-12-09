package com.example.imperialexaminations.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class IO {
	AssetManager assets;
	String externalStoragePath;

	public IO(Activity activity) {
		this.assets = activity.getAssets();
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
		System.out.println(this.externalStoragePath);
	}
	
	public InputStream readAsset(String fileName) throws IOException{
		return assets.open(fileName);
	}
	public Bitmap loadBitmap(String fileName){
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inInputShareable=true;
		opt.inPurgeable=true;
		InputStream io =null;
		try{
			io = readAsset(fileName);
		}catch (IOException e) {
			System.out.println("Õº∆¨◊ ‘¥º”‘ÿ ß∞‹!");
		}
		return BitmapFactory.decodeStream(io, null, opt);
		
	}
	
}

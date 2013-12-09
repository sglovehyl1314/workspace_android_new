package com.nari.sun.common.util;

import android.util.Log;

public class MyLog {
	/**
	 * 写日志
	 * 
	 * @param logMsg
	 */
	public static void writeLog(String logMsg) {
		writeLog("MyLog", logMsg);
	}

	/**
	 * 写日志
	 * 
	 * @param logTag
	 * @param logMsg
	 */
	private static void writeLog(String logTag, String logMsg) {
		Log.v(logTag, logMsg);

	}
}

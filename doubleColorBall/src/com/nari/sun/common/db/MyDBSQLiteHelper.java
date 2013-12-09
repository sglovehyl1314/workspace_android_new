package com.nari.sun.common.db;

import com.nari.sun.common.util.MyLog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBSQLiteHelper extends SQLiteOpenHelper {
	// 数据库版本
	private static final int DATABASE_VERSION = 2;
	private String dbName = null;

	public MyDBSQLiteHelper(Context context, String dbName) {
		super(context, dbName, null, 2);
		this.dbName = dbName;
	}

	@Override
	public void onCreate(SQLiteDatabase sqldb) {
		MyLog.writeLog("*******onCreate begin");
		sqldb.execSQL("CREATE TABLE IF NOT EXISTS caipiaoinfo (_id INTEGER PRIMARY KEY autoincrement,hong TEXT,lan TEXT)");
		MyLog.writeLog("*******onCreate end");
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqldb, int oldVersion, int newVersion) {
		MyLog.writeLog("*******onUpgrade begin");
		onCreate(sqldb);
		MyLog.writeLog("*******onUpgrade end");
	}

}

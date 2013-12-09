package com.nari.sun.common.db;

import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyDBHelper {
	private MyDBSQLiteHelper myDBSqlHelper;
	private Context myContext;
	private SQLiteDatabase myDB = null;

	public MyDBHelper(Context context) {
		this.myContext = context;
	}

	public MyDBHelper(Context context, String dbName) {
		this.myContext = context;
		try {
			if (this.myDB == null) {
				MyDBSQLiteHelper mh = new MyDBSQLiteHelper(context, dbName);
				this.myDBSqlHelper = mh;
				openDataBase();
			}
			return;
		} catch (Exception e) {
			Log.v("MyLog", "********create sqlite failure");
		}
	}

	/**
	 * 打开数据库
	 */
	private void openDataBase() {
		try {
			if ((this.myDB == null) || (!this.myDB.isOpen())) {
				SQLiteDatabase sd = this.myDBSqlHelper.getWritableDatabase();
				this.myDB = sd;
				Log.v("MyLog", "********sqlite open success");
			}
		} catch (Exception e) {
			Log.v("MyLog", "********sqlite open failure");
		}

	}

	/**
	 * 关闭数据库
	 */
	public void closeConnection() {
		if ((this.myDB == null) || (!this.myDB.isOpen()))
			return;
		this.myDB.close();
		this.myDB = null;
	}

	/**
	 * 执行sql
	 * 
	 * @param sql
	 * @throws Exception
	 */
	private void execSql(String sql) throws Exception {
		openDataBase();
		this.myDB.execSQL(sql);
	}

	/**
	 * 创建表
	 * 
	 * @param sql
	 * @return
	 */
	public boolean createTable(String sql) {
		boolean flag = true;
		try {
			Log.v("MyLog", "********sqlite create table sql:" + sql);
			execSql(sql);

		} catch (Exception e) {
			Log.v("MyLog", "********sqlite create table exception");
			flag = false;
		}
		return flag;
	}

	/**
	 * 查询表
	 * 
	 * @param sql
	 * @return
	 */
	public Cursor selectTable(String sql) {
		Cursor cursor = null;
		try {
			Log.v("MyLog", "********sqlite select table sql:" + sql);
			cursor = this.myDB.rawQuery(sql, null);
		} catch (Exception e) {
			Log.v("MyLog", "********sqlite select table exception");
		}
		return cursor;
	}

	/**
	 * 插入数据
	 * 
	 * @param dbName
	 * @param contentValues
	 * @return
	 * @throws Exception
	 */
	public long insertDate(String dbName, ContentValues contentValues) throws Exception {
		return this.myDB.insert(dbName, null, contentValues);
	}

}
package com.nari.db;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class DBSharedPreferences3Activity extends Activity {

	public static final String SETTING_INFOS = "SETTING_INFOS";
	public static final String NAME = "NAME";
	public static final String PASSWORD = "PASSWORD";

	private EditText filed_name;
	private EditText filed_pass;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		filed_name = (EditText) findViewById(R.id.name);
		filed_pass = (EditText) findViewById(R.id.password);

		SharedPreferences sharedPreferences = getSharedPreferences(SETTING_INFOS, 0);
		String name = sharedPreferences.getString(NAME, "");
		String pass = sharedPreferences.getString(PASSWORD, "");

		filed_name.setText(name);
		filed_pass.setText(pass);

	}

	@Override
	protected void onStop() {
		super.onStop();
		SharedPreferences sharedPreferences = getSharedPreferences(SETTING_INFOS, 0);
		// 保护用户名和密码
		sharedPreferences.edit().putString(NAME, filed_name.getText().toString()).putString(PASSWORD, filed_pass.getText().toString()).commit();

	}

}
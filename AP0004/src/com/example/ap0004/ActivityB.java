package com.example.ap0004;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityB extends Activity {

	final String TAG = "States";
	TextView tvTextLife;
	List<ActivityManager.RunningTaskInfo> list;
	ActivityManager am;
	Integer TotalActCount;
	Boolean FirstStart;
	Boolean NextAct;
	TextView MyTextB, MyTextCount;
	Integer MyCount = 0;
	Button MyCountButton;
	EditText MyEditTextB;
	static final String SaveMyCount = "MY_CLICK_COUNT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_b);
		setTitle(getResources().getString(R.string.app_name) + " | "
				+ getLocalClassName() + " | TaskID: " + getTaskId());
		// флаг что активность запущена впервые
		FirstStart = true;
		// кнопка запуска следующей Активности не нажималась
		NextAct = false;
		if (savedInstanceState != null) {
			// Restore value of members from saved state
			// восстанавливаем значение счетчика
			MyCount = savedInstanceState.getInt(SaveMyCount);
			// находим текстовое поле по его идентификатору
			MyTextCount = (TextView) findViewById(R.id.textViewCount);
			// присваиваем значение атрибуту Text для выбранного TextView
			MyTextCount.setText("Count = " + MyCount);
			// находим кнопку по ее идентификатору
			MyCountButton = (Button) findViewById(R.id.buttonCount);
			// присваиваем значение атрибуту Text для выбранной кнопки
			MyCountButton.setText("Count " + MyCount);
			// получаем ссылку на поле ввода текста
			MyTextB = (EditText) findViewById(R.id.editTextB);
			Log.d(TAG, "ActivityB: onCreate() NOT NULL: "
					+ MyTextB.getText().toString());
		} else {
			// Probably initialize members with default values for a new
			// instance
			Log.d(TAG, "ActivityB: onCreate("+getTaskId()+") NULL");
		}
		Log.d(TAG, "ActivityB: onCreate("+getTaskId()+")");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "ActivityB: onStart("+getTaskId()+")");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "ActivityB: onResume("+getTaskId()+")");
		// получаем список 10 последних задач
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		list = am.getRunningTasks(10);
		// перебираем список задач и выбираем свою по TaskID
		for (RunningTaskInfo task : list) {
			if (task.id==getTaskId()) {
				// находим поле для вывода информации о количестве запущенных
				// Активностей
				tvTextLife = (TextView) findViewById(R.id.textActCountB);
				TotalActCount = task.numActivities;
				// коррекция счетчика для кнопки ОБРАТНО
				if (NextAct == true & FirstStart == false)
					TotalActCount = TotalActCount - 1;
				// выводим количество Активностей в задаче
				tvTextLife.setText("Activites in task " + TotalActCount);
				// коррекция счетчика для кнопки ДОМОЙ
				NextAct = false;
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "ActivityB: onPause("+getTaskId()+")");
		// флаг что активность уже была запущена
		FirstStart = false;
		// находим текстовое поле по его идентификатору
		tvTextLife = (TextView) findViewById(R.id.textStateActB);
		// присваиваем значение атрибуту Text для выбранного TextView
		tvTextLife.setText("Этот экземпляр ActivityB уже был запущен!");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "ActivityB: onStop("+getTaskId()+")");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "ActivityB: onRestart("+getTaskId()+")");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "ActivityB: onDestroy("+getTaskId()+")");
	}

	public void onClickStartC(View v) {
		Intent intent = new Intent(ActivityB.this, ActivityC.class);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;
	}
	
	public void onClickStartD0004 (View v){
		Intent startDAP0004 = new Intent("AP0004_ActD");
		startDAP0004.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startDAP0004.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startDAP0004);
		NextAct = true;
	}
	
	public void onClickStartD0003 (View v){
		Intent startDAP0003 = new Intent("AP0003_ActD");
		startDAP0003.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startDAP0003.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startDAP0003);
		NextAct = true;
	}
	
	public void onClickStartD0003NT (View v){
		Intent startDNT0003 = new Intent("AP0003_ActD");
		startDNT0003.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startDNT0003);
		NextAct = true;
	}
	
	public void onInfoClick(View v) {
		final String TAG = "States";
		// получаем список 10 последних задач
		
		list = am.getRunningTasks(10);
		// перебираем список задач и выбираем свои по имени пакетов
		// com.example.ap000
		for (RunningTaskInfo task : list) {
			if (task.baseActivity.flattenToShortString().startsWith(
					"com.example.ap000")) {
				// находим поле для вывода информации о количестве запущенных
				// Активностей

				Log.d(TAG, "------------------");
				Log.d(TAG, "TaskID: " + task.id); 
				Log.d(TAG, "Num: " + task.numActivities);
				Log.d(TAG, "Base: " + task.baseActivity.flattenToShortString());
				Log.d(TAG, "Top: " + task.topActivity.flattenToShortString());
				Log.d(TAG, "Thread ID: "+ android.os.Process.myTid());
				Log.d(TAG, "Process ID: "+ android.os.Process.myPid());
				Log.d(TAG, "------------------");

			}
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		// сохраняем значение счетчика
		savedInstanceState.putInt(SaveMyCount, MyCount);
		// получаем ссылку на поле ввода текста
		MyTextB = (EditText) findViewById(R.id.editTextB);
		Log.d(TAG, "onSaveInstanceState B text: "
				+ MyTextB.getText().toString());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// получаем ссылку на поле ввода текста
		MyTextB = (EditText) findViewById(R.id.editTextB);
		Log.d(TAG, "onRestoreInstanceState B text: "
				+ MyTextB.getText().toString());
	}

	public void onClickCount(View v) {
		Log.d(TAG, "Click Count Button");
		MyCount = MyCount + 1;
		// находим текстовое поле по его идентификатору
		MyTextCount = (TextView) findViewById(R.id.textViewCount);
		// присваиваем значение атрибуту Text для выбранного TextView
		MyTextCount.setText("Count = " + MyCount);
		// находим кнопку по ее идентификатору
		MyCountButton = (Button) findViewById(R.id.buttonCount);
		// присваиваем значение атрибуту Text для выбранной кнопки
		MyCountButton.setText("Count " + MyCount);
		// находим поле ввода текста по его идентификатору
		MyEditTextB = (EditText) findViewById(R.id.editTextB);
		// присваиваем значение атрибуту Text для выбранного поля ввода текста
		MyEditTextB.setText("Count = " + MyCount);
	}

}
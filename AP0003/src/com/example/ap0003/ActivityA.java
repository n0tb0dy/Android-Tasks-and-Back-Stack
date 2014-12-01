package com.example.ap0003;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityA extends Activity {

	final String TAG = "States";
	TextView tvTextLife, MyTextA, vTextA;
	List<ActivityManager.RunningTaskInfo> list;
	ActivityManager am;
	Integer TotalActCount;
	Boolean FirstStart;
	Boolean NextAct;
	static final String SaveTextEditA = "TEXTEDIT_A_STATE";
	SharedPreferences sPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // супер класс всегда вызывайте
											// первым
		setContentView(R.layout.layout_a);
		// записали название приложения, активности и TaskID в заголовок приложения
		setTitle(getResources().getString(R.string.app_name) + " | "
				+ getLocalClassName() + " | TaskID: " + getTaskId());

		// флаг что активность запущена впервые
		FirstStart = true;
		// кнопка запуска следующей Активности не нажималась
		NextAct = false;
		if (savedInstanceState != null) {
			// Restore value of members from saved state
			// находим текстовое поле по его идентификатору
			vTextA = (TextView) findViewById(R.id.textViewA);
			// и присваиваем ему сохраненное в методе onSaveInstanceState
			// значение
			vTextA.setText(savedInstanceState.getString(SaveTextEditA));
			Log.d(TAG, "ActivityA: onCreate("+getTaskId()+") NOT NULL: "
					+ vTextA.getText().toString());
		} else {
			// Probably initialize members with default values for a new
			// instance
			Log.d(TAG, "ActivityA: onCreate("+getTaskId()+") NULL");
		}

		Log.d(TAG, "ActivityA: onCreate("+getTaskId()+")");

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "ActivityA: onStart("+getTaskId()+")");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "ActivityA: onResume("+getTaskId()+")");
		// получаем список 10 последних задач
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		list = am.getRunningTasks(10);
		// перебираем список задач и выбираем свою по TaskID
		for (RunningTaskInfo task : list) {
			if (task.id==getTaskId()) {
				// находим поле для вывода информации о количестве запущенных
				// Активностей
				tvTextLife = (TextView) findViewById(R.id.textActCountA);
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
		Log.d(TAG, "ActivityA: onPause("+getTaskId()+")");
		// флаг что активность уже была запущена
		FirstStart = false;
		// находим текстовое поле по его идентификатору
		tvTextLife = (TextView) findViewById(R.id.textStateActA);
		// присваиваем значение атрибуту Text для выбранного TextView
		tvTextLife.setText("Этот экземпляр ActivityA уже был запущен!");

	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "ActivityA: onStop("+getTaskId()+")");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "ActivityA: onRestart("+getTaskId()+")");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "ActivityA: onDestroy("+getTaskId()+")");
	}

	public void onClickStartB(View v) {
		Intent intent = new Intent(ActivityA.this, ActivityB.class);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;

	}
	
	public void onClickStartE(View v) {
		Intent intent = new Intent(ActivityA.this, ActivityE.class);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;

	}
	
	public void onClickStartF(View v) {
		Intent intent = new Intent(ActivityA.this, ActivityF.class);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;

	}
	
	public void onClickStartFNT(View v) {
		Intent intent = new Intent(ActivityA.this, ActivityF.class).addFlags(
		        Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;

	}
	
	public void onClickStartG(View v) {
		Intent intent = new Intent(ActivityA.this, ActivityG.class);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;

	}
	
	public void onClickStartD0003 (View v){
		Intent startDAP0003 = new Intent("AP0003_ActD");
		startDAP0003.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(startDAP0003);
		NextAct = true;
	}
	
	public void onClickStartD0004 (View v){
		Intent startDAP0004 = new Intent("AP0004_ActD");
		startDAP0004.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(startDAP0004);
		NextAct = true;
	}
	
	public void onClickStartK(View v) {
		Intent intent = new Intent(ActivityA.this, ActivityK.class);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
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
		// получаем ссылку на поле ввода текста
		MyTextA = (EditText) findViewById(R.id.editTextA);
		// сохраняем состояние поля ввода текста
		savedInstanceState.putString(SaveTextEditA, MyTextA.getText()
				.toString());
		Log.d(TAG, "onSaveInstanceState A text: "
				+ MyTextA.getText().toString());
		// всегда вызывайте супер класс чтобы сохранить
		// состояние всех view активности
		super.onSaveInstanceState(savedInstanceState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// находим текстовое поле по его идентификатору
		vTextA = (TextView) findViewById(R.id.textViewA);
		// и присваиваем ему сохраненное в методе onSaveInstanceState значение
		vTextA.setText(savedInstanceState.getString(SaveTextEditA));
		Log.d(TAG, "onRestoreInstanceState A: " + vTextA.getText().toString());
	}

}

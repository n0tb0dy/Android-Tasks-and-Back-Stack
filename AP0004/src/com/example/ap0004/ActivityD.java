package com.example.ap0004;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ActivityD extends Activity {

	final String TAG = "States";
	TextView tvTextLife;
	List<ActivityManager.RunningTaskInfo> list;
	ActivityManager am;
	Integer TotalActCount;
	Boolean FirstStart;
	Boolean NextAct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_d);
		setTitle(getResources().getString(R.string.app_name) + " | "
				+ getLocalClassName() + " | TaskID: " + getTaskId());
		Log.d(TAG, "ActivityD: onCreate("+getTaskId()+")");
		// активность запущена впервые
		FirstStart = true;
		// кнопка запуска следующей Активности не нажималась
		NextAct = false;
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "ActivityD: onStart("+getTaskId()+")");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "ActivityD: onResume("+getTaskId()+")");
		// получаем список 10 последних задач
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		list = am.getRunningTasks(10);
		// перебираем список задач и выбираем свою по TaskID
		for (RunningTaskInfo task : list) {
			if (task.id == getTaskId()) {
				// находим поле для вывода информации о количестве запущенных
				// Активностей
				tvTextLife = (TextView) findViewById(R.id.textActCountD);
				TotalActCount = task.numActivities;
				// коррекция счетчика для кнопки ДОМОЙ и ОБРАТНО
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
		// флаг что активность уже была запущена
		FirstStart = false;
		// находим текстовое поле по его идентификатору
		tvTextLife = (TextView) findViewById(R.id.textStateActD);
		// присваиваем значение атрибуту Text для выбранного TextView
		tvTextLife.setText("Этот экземпляр ActivityD уже был запущен!");
		Log.d(TAG, "ActivityD: onPause("+getTaskId()+")");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "ActivityD: onStop("+getTaskId()+")");
		// получаем список 10 последних задач
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		list = am.getRunningTasks(10);
		// перебираем список задач и выбираем свою по TaskID
		for (RunningTaskInfo task : list) {
			if (task.id == getTaskId()) {
				// коррекция счетчика на singleTask Activity
				if (TotalActCount == task.numActivities & NextAct == true) {
					NextAct = false;
				}
			}
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "ActivityD: onRestart("+getTaskId()+")");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "ActivityD: onDestroy("+getTaskId()+")");
	}

	public void onClickStartE(View v) {
		Intent intent = new Intent(ActivityD.this, ActivityE.class);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;
	}

	public void onClickStartD(View v) {
		startActivity(new Intent(this, ActivityD.class));
	}
	
	public void onClickStartDTOP(View v) {
		Intent startCDAP0004 = new Intent("AP0004_ActD");
		startCDAP0004.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(startCDAP0004);
		//NextAct = true;
		//FirstStart=false;
	}
	
	public void onClickStartDSTOP(View v) {
		Intent startSTDAP0004 = new Intent("AP0004_ActD");
		startSTDAP0004.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(startSTDAP0004);
		//NextAct = true;
		//FirstStart=false;
	}
	
	public void onClickStartC(View v) {
		Intent intent = new Intent(ActivityD.this, ActivityC.class);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;
	}
	
	public void onClickStartB(View v) {
		Intent intent = new Intent(ActivityD.this, ActivityB.class);
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
				Log.d(TAG, "Thread ID: " + android.os.Process.myTid());
				Log.d(TAG, "Process ID: " + android.os.Process.myPid());
				Log.d(TAG, "------------------");

			}
		}

	}

}

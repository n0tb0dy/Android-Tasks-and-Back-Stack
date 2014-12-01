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

public class ActivityC extends Activity {

	final String TAG = "States";
	TextView tvTextLife;
	List<ActivityManager.RunningTaskInfo> list;
	List<ActivityManager.RunningAppProcessInfo> tasks;
	ActivityManager am;
	ActivityManager.RunningAppProcessInfo prinf;
	Integer TotalActCount, i;
	Boolean FirstStart;
	Boolean NextAct;
	public static String PACKAGE_NAME;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_c);
		setTitle(getResources().getString(R.string.app_name) + " | "
				+ getLocalClassName() + " | TaskID: " + getTaskId());
		Log.d(TAG, "ActivityC: onCreate(" + getTaskId() + ")");
		// активность запущена впервые
		FirstStart = true;
		// кнопка запуска следующей Активности не нажималась
		NextAct = false;

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "ActivityC: onStart(" + getTaskId() + ")");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "ActivityC: onResume(" + getTaskId() + ")");
		// получаем список 10 последних задач
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		list = am.getRunningTasks(10);
		// перебираем список задач и выбираем свою по TaskID
		for (RunningTaskInfo task : list) {
			if (task.id == getTaskId()) {
				// находим поле для вывода информации о количестве запущенных
				// Активностей
				tvTextLife = (TextView) findViewById(R.id.textActCountC);
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
		tvTextLife = (TextView) findViewById(R.id.textStateActC);
		// присваиваем значение атрибуту Text для выбранного TextView
		tvTextLife.setText("Этот экземпляр ActivityC уже был запущен!");
		Log.d(TAG, "ActivityC: onPause(" + getTaskId() + ")");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "ActivityC: onStop(" + getTaskId() + ")");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "ActivityC: onRestart(" + getTaskId() + ")");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "ActivityC: onDestroy(" + getTaskId() + ")");
	}

	public void onClickStartD(View v) {
		Intent intent = new Intent(ActivityC.this, ActivityD.class);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;
	}

	public void onClickStartE0003(View v) {
		startActivity(new Intent("AP0003_ActE"));
		NextAct = true;
	}

	public void onClickStartDTOP(View v) {
		Intent startCDAP0004 = new Intent("AP0004_ActD");
		startCDAP0004.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(startCDAP0004);
		NextAct = true;
		// FirstStart=false;
	}

	public void onClickStartDNT(View v) {
		Intent intent = new Intent(ActivityC.this, ActivityD.class)
				.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;

	}

	public void onClickStartDMT(View v) {
		Intent intent = new Intent(ActivityC.this, ActivityD.class).addFlags(
				Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(
				Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;

	}

	public void onClickStartL0003(View v) {
		// создали интент
		Intent startLAP0003 = new Intent("AP0003_ActL");

		startLAP0003.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(startLAP0003);
		NextAct = true;

	}

	public void onClickStartDPIT(View v) {
		Intent intent = new Intent(ActivityC.this, ActivityD.class)
				.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;
	}
	
	public void onClickStartDRTIN(View v) {
		Intent intent = new Intent(ActivityC.this, ActivityD.class)
				.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
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

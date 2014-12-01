package com.example.ap0003;

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
	static final String SaveMyPID = "MY_OLD_PID";
	static final String SaveMyNextClick = "MY_NEXT_CLICK";
	Integer SavedPID;

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
		// определяем есть ли сохранненные данные
		if (savedInstanceState != null) {
			// Restore value of members from saved state
			Log.d(TAG, "ActivityD: onCreate("+getTaskId()+") NOT NULL: ");
			// сравниваем сохраненный и текущий PID
			SavedPID = savedInstanceState.getInt(SaveMyPID);
			if (SavedPID != android.os.Process.myPid()) {
				Log.d(TAG, "!!! Pocess AP0003 was killed !!!!");
				Log.d(TAG, "Old PID: " + SavedPID);
				Log.d(TAG, "NEW PID: " + android.os.Process.myPid());
				//коррекция счетчика если процесс приложения был убит
				FirstStart = false;
				//коррекция счетчика на запуск другой Активности
				if (savedInstanceState.getInt(SaveMyNextClick)==1){
					NextAct=true;
					Log.d(TAG, "!!! NextAct=true !!!");
				}

			}
		} else {
			// Probably initialize members with default values for a new
			// instance
			Log.d(TAG, "ActivityD: onCreate("+getTaskId()+") NULL");
		}
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
				Log.d(TAG, "ActivityD: onResume TotalActCount: "+TotalActCount);
				// коррекция счетчика для кнопки ДОМОЙ и ОБРАТНО
				if (NextAct == true & FirstStart == false)
					TotalActCount = TotalActCount - 1;
				Log.d(TAG, "ActivityD: onResume TotalActCount: "+TotalActCount);
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

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		// сохраняем значение PID
		savedInstanceState.putInt(SaveMyPID, android.os.Process.myPid());
		Log.d(TAG, "ActivityD: onSave("+getTaskId()+") PID:" + android.os.Process.myPid());
		//сохраняем нажатие запуска другой Активности
		if (NextAct==true){
		savedInstanceState.putInt(SaveMyNextClick, 1);
		Log.d(TAG, "ActivityD: onSave("+getTaskId()+") NextAct=true");
		}
	}

	public void onClickStartA(View v) {
		Intent intent = new Intent(ActivityD.this, ActivityA.class);
		startActivity(intent);
		// кнопка запуска следующей Активности была нажата
		NextAct = true;
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

	public void onClickStartB0004(View v) {
		startActivity(new Intent("AP0004_ActB"));
		NextAct = true;
	}
	
	public void onClickStartDTOP(View v) {
		Intent startCDAP0003 = new Intent("AP0003_ActD");
		startCDAP0003.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(startCDAP0003);
		//NextAct = true;
		//FirstStart=false;
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

package com.example.ap0003;

import android.app.Activity;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ActivityL extends Activity {
	
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_l);
		setTitle(getResources().getString(R.string.app_name) + " | "
				+ getLocalClassName() + " | TaskID: " + getTaskId());
		Log.d(TAG, "ActivityL: onCreate(" + getTaskId() + ")");
		// ���������� �������� �������
		FirstStart = true;
		// ������ ������� ��������� ���������� �� ����������
		NextAct = false;

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "ActivityL: onStart(" + getTaskId() + ")");
	}

	@Override
	protected void onResume() {
		super.onResume();
		setTitle(getResources().getString(R.string.app_name) + " | "
				+ getLocalClassName() + " | TaskID: " + getTaskId());
		Log.d(TAG, "ActivityL: onResume(" + getTaskId() + ")");
		// �������� ������ 10 ��������� �����
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		list = am.getRunningTasks(10);
		// ���������� ������ ����� � �������� ���� �� TaskID
		for (RunningTaskInfo task : list) {
			if (task.id == getTaskId()) {
				// ������� ���� ��� ������ ���������� � ���������� ����������
				// �����������
				tvTextLife = (TextView) findViewById(R.id.textActCountL);
				TotalActCount = task.numActivities;
				// ��������� �������� ��� ������ ����� � �������
				if (NextAct == true & FirstStart == false)
					TotalActCount = TotalActCount - 1;
				// ������� ���������� ����������� � ������
				tvTextLife.setText("Activites in task " + TotalActCount);
				// ��������� �������� ��� ������ �����
				NextAct = false;
			}

		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		// ���� ��� ���������� ��� ���� ��������
		FirstStart = false;
		// ������� ��������� ���� �� ��� ��������������
		tvTextLife = (TextView) findViewById(R.id.textStateActL);
		// ����������� �������� �������� Text ��� ���������� TextView
		tvTextLife.setText("���� ��������� ActivityL ��� ��� �������!");
		Log.d(TAG, "ActivityL: onPause(" + getTaskId() + ")");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "ActivityL: onStop(" + getTaskId() + ")");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "ActivityL: onRestart(" + getTaskId() + ")");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "ActivityL: onDestroy(" + getTaskId() + ")");
	}

	public void onClickStartA(View v) {
		Intent intent = new Intent(ActivityL.this, ActivityA.class);
		startActivity(intent);
		// ������ ������� ��������� ���������� ���� ������
		NextAct = true;
	}

	public void onInfoClick(View v) {
		final String TAG = "States";
		// �������� ������ 10 ��������� �����

		list = am.getRunningTasks(10);
		// ���������� ������ ����� � �������� ���� �� ����� �������
		// com.example.ap000
		for (RunningTaskInfo task : list) {
			if (task.baseActivity.flattenToShortString().startsWith(
					"com.example.ap000")) {
				// ������� ���� ��� ������ ���������� � ���������� ����������
				// �����������

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

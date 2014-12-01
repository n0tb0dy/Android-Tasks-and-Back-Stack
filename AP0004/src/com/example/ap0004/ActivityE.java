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

public class ActivityE extends Activity {

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
		setContentView(R.layout.layout_f);
		setTitle(getResources().getString(R.string.app_name) + " | "
				+ getLocalClassName() + " | TaskID: " + getTaskId());
		Log.d(TAG, "ActivityE: onCreate("+getTaskId()+")");
		// ���������� �������� �������
		FirstStart = true;
		// ������ ������� ��������� ���������� �� ����������
		NextAct = false;
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "ActivityE: onStart("+getTaskId()+")");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "ActivityE: onResume("+getTaskId()+")");
		// �������� ������ 10 ��������� �����
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		list = am.getRunningTasks(10);
		// ���������� ������ ����� � �������� ���� �� TaskID
		for (RunningTaskInfo task : list) {
			if (task.id == getTaskId()) {
				// ������� ���� ��� ������ ���������� � ���������� ����������
				// �����������
				tvTextLife = (TextView) findViewById(R.id.textActCountE);
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
		tvTextLife = (TextView) findViewById(R.id.textStateActE);
		// ����������� �������� �������� Text ��� ���������� TextView
		tvTextLife.setText("���� ��������� ActivityE ��� ��� �������!");
		Log.d(TAG, "ActivityE: onPause("+getTaskId()+")");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "ActivityE: onStop("+getTaskId()+")");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "ActivityE: onRestart("+getTaskId()+")");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "ActivityE: onDestroy("+getTaskId()+")");
	}

	public void onClickStartA(View v) {
		Intent intent = new Intent(ActivityE.this, ActivityA.class);
		startActivity(intent);
		// ������ ������� ��������� ���������� ���� ������
		//NextAct = true;
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

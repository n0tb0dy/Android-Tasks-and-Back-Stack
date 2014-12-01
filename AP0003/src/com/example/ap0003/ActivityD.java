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
		// ���������� �������� �������
		FirstStart = true;
		// ������ ������� ��������� ���������� �� ����������
		NextAct = false;
		// ���������� ���� �� ������������ ������
		if (savedInstanceState != null) {
			// Restore value of members from saved state
			Log.d(TAG, "ActivityD: onCreate("+getTaskId()+") NOT NULL: ");
			// ���������� ����������� � ������� PID
			SavedPID = savedInstanceState.getInt(SaveMyPID);
			if (SavedPID != android.os.Process.myPid()) {
				Log.d(TAG, "!!! Pocess AP0003 was killed !!!!");
				Log.d(TAG, "Old PID: " + SavedPID);
				Log.d(TAG, "NEW PID: " + android.os.Process.myPid());
				//��������� �������� ���� ������� ���������� ��� ����
				FirstStart = false;
				//��������� �������� �� ������ ������ ����������
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
		// �������� ������ 10 ��������� �����
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		list = am.getRunningTasks(10);
		// ���������� ������ ����� � �������� ���� �� TaskID
		for (RunningTaskInfo task : list) {
			if (task.id == getTaskId()) {
				// ������� ���� ��� ������ ���������� � ���������� ����������
				// �����������
				tvTextLife = (TextView) findViewById(R.id.textActCountD);
				TotalActCount = task.numActivities;
				Log.d(TAG, "ActivityD: onResume TotalActCount: "+TotalActCount);
				// ��������� �������� ��� ������ ����� � �������
				if (NextAct == true & FirstStart == false)
					TotalActCount = TotalActCount - 1;
				Log.d(TAG, "ActivityD: onResume TotalActCount: "+TotalActCount);
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
		tvTextLife = (TextView) findViewById(R.id.textStateActD);
		// ����������� �������� �������� Text ��� ���������� TextView
		tvTextLife.setText("���� ��������� ActivityD ��� ��� �������!");
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
		// ��������� �������� PID
		savedInstanceState.putInt(SaveMyPID, android.os.Process.myPid());
		Log.d(TAG, "ActivityD: onSave("+getTaskId()+") PID:" + android.os.Process.myPid());
		//��������� ������� ������� ������ ����������
		if (NextAct==true){
		savedInstanceState.putInt(SaveMyNextClick, 1);
		Log.d(TAG, "ActivityD: onSave("+getTaskId()+") NextAct=true");
		}
	}

	public void onClickStartA(View v) {
		Intent intent = new Intent(ActivityD.this, ActivityA.class);
		startActivity(intent);
		// ������ ������� ��������� ���������� ���� ������
		NextAct = true;
	}
	
	public void onClickStartE(View v) {
		Intent intent = new Intent(ActivityD.this, ActivityE.class);
		startActivity(intent);
		// ������ ������� ��������� ���������� ���� ������
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

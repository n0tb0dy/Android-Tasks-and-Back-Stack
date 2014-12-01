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
		// ���� ��� ���������� �������� �������
		FirstStart = true;
		// ������ ������� ��������� ���������� �� ����������
		NextAct = false;
		if (savedInstanceState != null) {
			// Restore value of members from saved state
			// ��������������� �������� ��������
			MyCount = savedInstanceState.getInt(SaveMyCount);
			// ������� ��������� ���� �� ��� ��������������
			MyTextCount = (TextView) findViewById(R.id.textViewCount);
			// ����������� �������� �������� Text ��� ���������� TextView
			MyTextCount.setText("Count = " + MyCount);
			// ������� ������ �� �� ��������������
			MyCountButton = (Button) findViewById(R.id.buttonCount);
			// ����������� �������� �������� Text ��� ��������� ������
			MyCountButton.setText("Count " + MyCount);
			// �������� ������ �� ���� ����� ������
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
		// �������� ������ 10 ��������� �����
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		list = am.getRunningTasks(10);
		// ���������� ������ ����� � �������� ���� �� TaskID
		for (RunningTaskInfo task : list) {
			if (task.id==getTaskId()) {
				// ������� ���� ��� ������ ���������� � ���������� ����������
				// �����������
				tvTextLife = (TextView) findViewById(R.id.textActCountB);
				TotalActCount = task.numActivities;
				// ��������� �������� ��� ������ �������
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
		Log.d(TAG, "ActivityB: onPause("+getTaskId()+")");
		// ���� ��� ���������� ��� ���� ��������
		FirstStart = false;
		// ������� ��������� ���� �� ��� ��������������
		tvTextLife = (TextView) findViewById(R.id.textStateActB);
		// ����������� �������� �������� Text ��� ���������� TextView
		tvTextLife.setText("���� ��������� ActivityB ��� ��� �������!");
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
		// ������ ������� ��������� ���������� ���� ������
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
				Log.d(TAG, "Thread ID: "+ android.os.Process.myTid());
				Log.d(TAG, "Process ID: "+ android.os.Process.myPid());
				Log.d(TAG, "------------------");

			}
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		// ��������� �������� ��������
		savedInstanceState.putInt(SaveMyCount, MyCount);
		// �������� ������ �� ���� ����� ������
		MyTextB = (EditText) findViewById(R.id.editTextB);
		Log.d(TAG, "onSaveInstanceState B text: "
				+ MyTextB.getText().toString());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// �������� ������ �� ���� ����� ������
		MyTextB = (EditText) findViewById(R.id.editTextB);
		Log.d(TAG, "onRestoreInstanceState B text: "
				+ MyTextB.getText().toString());
	}

	public void onClickCount(View v) {
		Log.d(TAG, "Click Count Button");
		MyCount = MyCount + 1;
		// ������� ��������� ���� �� ��� ��������������
		MyTextCount = (TextView) findViewById(R.id.textViewCount);
		// ����������� �������� �������� Text ��� ���������� TextView
		MyTextCount.setText("Count = " + MyCount);
		// ������� ������ �� �� ��������������
		MyCountButton = (Button) findViewById(R.id.buttonCount);
		// ����������� �������� �������� Text ��� ��������� ������
		MyCountButton.setText("Count " + MyCount);
		// ������� ���� ����� ������ �� ��� ��������������
		MyEditTextB = (EditText) findViewById(R.id.editTextB);
		// ����������� �������� �������� Text ��� ���������� ���� ����� ������
		MyEditTextB.setText("Count = " + MyCount);
	}

}
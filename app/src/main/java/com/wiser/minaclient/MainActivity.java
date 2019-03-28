package com.wiser.minaclient;

import com.wiser.minaclient.mina.MinaServiceManage;
import com.wiser.minaclient.mina.MinaSessionManage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void openService(View view) {
		MinaServiceManage.getInstants().initMinaService(this);
	}

	public void resetConnect(View view) {
		MinaServiceManage.getInstants().resetInitMinaConnect();
	}

	public void closeService(View view) {
		MinaServiceManage.getInstants().closeMinaService();
	}

	public void sendMessage(View view) {
		MinaSessionManage.getInstance().writeToServer("你好你好你真好你太好了998");
	}
}

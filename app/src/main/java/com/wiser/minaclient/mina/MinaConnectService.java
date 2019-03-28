package com.wiser.minaclient.mina;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Wiser
 * 
 *         mina长连接
 */
public class MinaConnectService extends Service {

	private MinaConnectThread minaConnectThread;

	@Override public IBinder onBind(Intent intent) {
		return null;
	}

	@Override public void onCreate() {
		super.onCreate();
		minaConnectThread = new MinaConnectThread("mina", getApplicationContext());
		minaConnectThread.start();
	}

	@Override public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null) {
			if (intent.getBooleanExtra(MinaServiceManage.IS_RESET_CONNECT, false)) {
				minaConnectThread = new MinaConnectThread("mina", getApplicationContext());
				minaConnectThread.start();
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override public void onDestroy() {
		super.onDestroy();
		minaConnectThread.disConnection();
		minaConnectThread = null;
	}
}

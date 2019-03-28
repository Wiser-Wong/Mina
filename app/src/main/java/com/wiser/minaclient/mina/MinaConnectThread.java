package com.wiser.minaclient.mina;

import android.content.Context;
import android.os.HandlerThread;

/**
 * @author Wiser
 * 
 *         连接线程
 */
public class MinaConnectThread extends HandlerThread {

	private MinaConnectManage mConnectManage;

	private boolean				isClose;

	public MinaConnectThread(String name, Context context) {
		super(name);

		MinaConnectConfig connectConfig = new MinaConnectConfig.Builder(context)
				.setIp(MinaConstantsConfig.MINA_IP)
				.setPort(MinaConstantsConfig.MINA_PORT)
				.setReadBufferSize(MinaConstantsConfig.MINA_READ_BUFFER)
				.setConnectionTimeout(MinaConstantsConfig.MINA_TIME_OUT)
				.setMaxLineLength(MinaConstantsConfig.MINA_MAX_LINE_LENGTH)
				.setTextFormat(MinaConstantsConfig.MINA_TEXT_FORMAT)
				.setHeartInterval(MinaConstantsConfig.MINA_HEART_INTERVAL)
				.setHeartTimeOut(MinaConstantsConfig.MINA_HEART_TIME_OUT).build();

		mConnectManage = new MinaConnectManage(connectConfig);
	}

	@Override protected void onLooperPrepared() {
		// 利用循环请求连接
		while (!isClose) {
			if (mConnectManage == null) break;
			boolean isConnection = mConnectManage.connect();
			if (isConnection) {
				// 当请求成功的时候,跳出循环
				System.out.println("------------>>连接成功");
				break;
			}
			System.out.println("------------>>连接失败");
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 销毁连接
	 */
	public void disConnection() {
		isClose = true;
		if (getLooper() != null) getLooper().quit();
		if (mConnectManage != null) {
			mConnectManage.disConnect();
			mConnectManage = null;
		}
	}
}

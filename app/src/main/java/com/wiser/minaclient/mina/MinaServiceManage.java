package com.wiser.minaclient.mina;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.content.Intent;

/**
 * @author Wiser
 * 
 *         Mina服务管理类
 */
public class MinaServiceManage {

	private WeakReference<Context>	mContext;

	public final static String		IS_RESET_CONNECT	= "IS_RESET_CONNECT";

	public static MinaServiceManage getInstants() {
		return MinaServiceHolder.minaServiceManage;
	}

	private static class MinaServiceHolder {

		private final static MinaServiceManage minaServiceManage = new MinaServiceManage();
	}

	/**
	 * 初始化Mina服务
	 *
	 * @param context
	 */
	public void initMinaService(Context context) {
		if (mContext == null || mContext.get() == null) mContext = new WeakReference<>(context);
		Intent intent = new Intent(mContext.get(), MinaConnectService.class);
		mContext.get().startService(intent);
	}

	/**
	 * 重新建立Mina服务
	 */
	public void resetInitMinaConnect() {
		if (mContext != null && mContext.get() != null) {
			Intent intent = new Intent(mContext.get(), MinaConnectService.class);
			intent.putExtra(IS_RESET_CONNECT, true);
			mContext.get().startService(intent);
		}
	}

	/**
	 * 关闭Mina服务
	 */
	public void closeMinaService() {
		if (mContext != null && mContext.get() != null) {
			Intent intent = new Intent(mContext.get(), MinaConnectService.class);
			mContext.get().stopService(intent);
			mContext.clear();
			mContext = null;
		}
	}

}

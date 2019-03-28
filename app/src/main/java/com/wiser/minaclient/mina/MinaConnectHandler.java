package com.wiser.minaclient.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import android.content.Context;
import android.util.Log;

/**
 * @author Wiser
 * 
 *         IoHandler 接受消息
 */
public class MinaConnectHandler extends IoHandlerAdapter {

	private Context				context;

	private MinaConnectManage minaConnectManage;

	public MinaConnectHandler(Context context, MinaConnectManage minaConnectManage) {
		this.context = context;
		this.minaConnectManage = minaConnectManage;
	}

	@Override public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		super.exceptionCaught(session, cause);
		Log.d("----->>", "异常" + cause);
	}

	@Override public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
		Log.d("----->>", "创建连接");
	}

	/**
	 * 连接成功时回调的方法
	 *
	 * @param session
	 * @throws Exception
	 */
	@Override public void sessionOpened(IoSession session) throws Exception {
		// 当与服务器连接成功时,将我们的session保存到我们的sesscionmanager类中,从而可以发送消息到服务器
		MinaSessionManage.getInstance().setIoSession(session);
		Log.d("----->>", "打开连接");
	}

	@Override public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		Log.d("----->>", "关闭连接");
		if (!MinaSessionManage.getInstance().isCloseSession()) {// 意外关闭自动重连
			if (minaConnectManage != null && minaConnectManage.isCloseSession()) minaConnectManage.connect();
		} else {
			detach();
		}
	}

	@Override public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		super.sessionIdle(session, status);
		Log.d("----->>", "空闲");
	}

	/**
	 * 接收到消息时回调的方法
	 * 
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override public void messageReceived(IoSession session, Object message) throws Exception {
		Log.d("----->>", "接收消息" + message);
	}

	@Override public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
		Log.d("----->>", "发送消息" + message);
	}

	private void detach() {
		if (minaConnectManage != null) minaConnectManage.disConnect();
		minaConnectManage = null;
	}
}

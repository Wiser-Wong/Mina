package com.wiser.minaclient.mina;

import org.apache.mina.core.session.IoSession;

/**
 * @author Wiser
 * 
 *         session管理类,通过ioSession与服务器通信
 */
public class MinaSessionManage {

	private IoSession	ioSession;		// 最终与服务器 通信的对象

	private boolean		isCloseSession;

	public boolean isCloseSession() {
		return isCloseSession;
	}

	public void setCloseSession(boolean closeSession) {
		isCloseSession = closeSession;
	}

	public static MinaSessionManage getInstance() {
		return SessionManageHolder.sessionManage;
	}

	private static class SessionManageHolder {

		private final static MinaSessionManage sessionManage = new MinaSessionManage();
	}

	public void setIoSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}

	/**
	 * 将对象写到服务器
	 */
	public void writeToServer(Object msg) {
		if (ioSession != null) {
			// IoBuffer ioBuffer= IoBuffer.allocate(msg.toString().length());
			// ioBuffer.setAutoExpand(true);
			ioSession.write(msg.toString());
		}
	}

	/**
	 * 关闭连接
	 */
	public void closeSession() {
		if (ioSession != null) {
			ioSession.closeNow();
			ioSession.closeOnFlush();
			ioSession.getService().dispose();
		}
		ioSession = null;
		setCloseSession(false);
	}

}

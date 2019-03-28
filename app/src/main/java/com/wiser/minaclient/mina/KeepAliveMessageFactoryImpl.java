package com.wiser.minaclient.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * @author Wiser
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

	/** 心跳包内容 */
	private final String	HEARTBEAT_REQUEST	= "go";

	private final String	HEARTBEAT_RESPONSE	= "back";

	@Override public boolean isRequest(IoSession ioSession, Object o) {
		return o.equals(HEARTBEAT_REQUEST);
	}

	@Override public boolean isResponse(IoSession ioSession, Object o) {
		return o.equals(HEARTBEAT_RESPONSE);
	}

	@Override public Object getRequest(IoSession ioSession) {
		return HEARTBEAT_REQUEST;
	}

	@Override public Object getResponse(IoSession ioSession, Object o) {
		return HEARTBEAT_RESPONSE;
	}
}

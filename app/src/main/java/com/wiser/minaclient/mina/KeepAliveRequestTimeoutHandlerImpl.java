package com.wiser.minaclient.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

/**
 * @author Wiser
 */
public class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler {

	@Override public void keepAliveRequestTimedOut(KeepAliveFilter keepAliveFilter, IoSession ioSession) throws Exception {
		ioSession.close(true);
	}
}

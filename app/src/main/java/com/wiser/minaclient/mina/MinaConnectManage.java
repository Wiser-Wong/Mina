package com.wiser.minaclient.mina;

import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import android.content.Context;

/**
 * @author Wiser
 * 
 *         连接管理类
 */
public class MinaConnectManage {

	private WeakReference<Context>	mContext;

	private MinaConnectConfig mConfig;	// 配置文件

	private IoConnector				mConnector;

	private IoSession				mSession;

	private InetSocketAddress		mAddress;

	private ConnectFuture			future;

	public MinaConnectManage(MinaConnectConfig connectConfig) {
		this.mConfig = connectConfig;
		mContext = new WeakReference<>(mConfig.getContext());
		initConfig();
	}

	/**
	 * 初始化连接配置
	 */
	private void initConfig() {
		try {
			// // 创建连接对象
			mConnector = new NioSocketConnector();
			mAddress = new InetSocketAddress(mConfig.getIp(), mConfig.getPort());
			// 设置连接地址
			mConnector.setDefaultRemoteAddress(mAddress);
			mConnector.getSessionConfig().setReadBufferSize(mConfig.getReadBufferSize());
			// 设置过滤
			mConnector.getFilterChain().addLast("logger", new LoggingFilter());
			TextLineCodecFactory textLineCodecFactory = new TextLineCodecFactory(Charset.forName(mConfig.getTextFormat()), LineDelimiter.CRLF, LineDelimiter.CRLF);
			textLineCodecFactory.setDecoderMaxLineLength(mConfig.getMaxLineLength());
			mConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(textLineCodecFactory));
			// 心跳
			// 设置心跳包
			KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
			KeepAliveRequestTimeoutHandler heartBeatHandler = new KeepAliveRequestTimeoutHandlerImpl();
			KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory, IdleStatus.BOTH_IDLE, heartBeatHandler);
			heartBeat.setForwardEvent(true);
			// 设置当连接的读取通道空闲的时候，心跳包请求时间间隔
			heartBeat.setRequestInterval(mConfig.getHeartInterval());
			// 设置心跳包请求后 等待反馈超时时间。 超过该时间后则调用KeepAliveRequestTimeoutHandler.CLOSE
			heartBeat.setRequestTimeout(mConfig.getHeartTimeOut());// 3秒后超时
			mConnector.getFilterChain().addLast("heartbeat", heartBeat);
			mConnector.getSessionConfig().setUseReadOperation(true);
			// 设置连接监听
			mConnector.setHandler(new MinaConnectHandler(mContext.get(), this));
			// 超时设置
			mConnector.setConnectTimeoutMillis(mConfig.getConnectionTimeout());
		} catch (Exception e) {

		}
	}

	/**
	 * 建立连接
	 * 
	 * @return
	 */
	public boolean connect() {
		try {
			future = mConnector.connect(mAddress);
			future.awaitUninterruptibly();
			mSession = future.getSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return mSession != null;
	}

	/**
     * 是否关闭Session
	 * 
	 * @return
     */
	public boolean isCloseSession() {
		if (mSession != null) return mSession.isClosing();
		return false;
	}

	/**
	 * 销毁连接
	 */
	public void disConnect() {
		if (mConnector != null) mConnector.dispose();
		if (future != null) future.cancel();
		mConnector = null;
		mSession = null;
		mAddress = null;
		mContext = null;
		future = null;
		MinaSessionManage.getInstance().closeSession();
	}

}

package com.wiser.minaclient.mina;

import android.content.Context;

/**
 * @author Wiser
 * 
 *         连接配置
 */
public class MinaConnectConfig {

	private Context	context;

	private String	ip;

	private int		port;

	private int		readBufferSize;		// 缓存大小

	private int		connectionTimeout;	// 连接超时时间

	private int		maxLineLength;		// 最大传递信息数

	private String	textFormat;			// 文本格式

	private int		heartInterval;		// 心跳间隔时间

	private int		heartTimeOut;		// 心跳请求超时时间

	public Context getContext() {
		return context;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public int getReadBufferSize() {
		return readBufferSize;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public int getMaxLineLength() {
		return maxLineLength;
	}

	public String getTextFormat() {
		return textFormat;
	}

	public int getHeartInterval() {
		return heartInterval;
	}

	public int getHeartTimeOut() {
		return heartTimeOut;
	}

	public static class Builder {

		private Context	context;

		private String	ip;

		private int		port;

		private int		readBufferSize;		// 缓存大小

		private int		connectionTimeout;	// 连接超时时间

		private int		maxLineLength;		// 最大传递信息数

		private String	textFormat;			// 文本格式

		private int		heartInterval;		// 心跳间隔时间

		private int		heartTimeOut;		// 心跳请求超时时间

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setIp(String ip) {
			this.ip = ip;
			return this;
		}

		public Builder setPort(int port) {
			this.port = port;
			return this;
		}

		public Builder setReadBufferSize(int readBufferSize) {
			this.readBufferSize = readBufferSize;
			return this;
		}

		public Builder setConnectionTimeout(int connectionTimeout) {
			this.connectionTimeout = connectionTimeout;
			return this;
		}

		public Builder setMaxLineLength(int maxLineLength) {
			this.maxLineLength = maxLineLength;
			return this;
		}

		public Builder setTextFormat(String textFormat) {
			this.textFormat = textFormat;
			return this;
		}

		public Builder setHeartInterval(int heartInterval) {
			this.heartInterval = heartInterval;
			return this;
		}

		public Builder setHeartTimeOut(int heartTimeOut) {
			this.heartTimeOut = heartTimeOut;
			return this;
		}

		public MinaConnectConfig build() {
			MinaConnectConfig connectConfig = new MinaConnectConfig();
			connectConfig.context = this.context;
			connectConfig.ip = this.ip;
			connectConfig.port = this.port;
			connectConfig.readBufferSize = this.readBufferSize;
			connectConfig.connectionTimeout = this.connectionTimeout;
			connectConfig.maxLineLength = this.maxLineLength;
			connectConfig.textFormat = this.textFormat;
			connectConfig.heartInterval = this.heartInterval;
			connectConfig.heartTimeOut = this.heartTimeOut;
			return connectConfig;
		}
	}
}

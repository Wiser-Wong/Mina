package com.wiser.minaclient.mina;

/**
 * @author Wiser
 * 
 *         配置文件常量
 */
public interface MinaConstantsConfig {

	String	MINA_IP					= "192.168.0.142";	// IP地址

	int		MINA_PORT				= 19123;			// 端口号

	int		MINA_READ_BUFFER		= 2048 * 50;		// 超时时间

	int		MINA_TIME_OUT			= 5000;				// 超时时间

	int		MINA_MAX_LINE_LENGTH	= 512000;			// 最大传递信息数

	String	MINA_TEXT_FORMAT		= "gb2312";			// 文本格式
	// String MINA_TEXT_FORMAT = "UTF-8"; // 文本格式

	int		MINA_HEART_INTERVAL		= 15;				// 心跳包请求时间间隔

	int		MINA_HEART_TIME_OUT		= 3;				// 心跳包请求3秒后超时

}

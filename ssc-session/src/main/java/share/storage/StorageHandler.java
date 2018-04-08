/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package share.storage;

import java.io.Serializable;

/**
 * 会话存储器, 负责存储会话数据
 */
public interface StorageHandler extends Serializable {
	
	/**
	 * 创建新的会话id
	 * @param request - 请求
	 * @param response - 响应
	 * @return 会话id
	 * @throws SessionException - 如果发生会话异常
	 */
	public String createSessionId();
	
	/**
	 * 判断会话id是否可用
	 * @param sessionId - 会话id
	 * @return
	 * @throws Exception - 如果发生异常
	 */
	public boolean existsSessionId(String sessionId);

	/**
	 * 保存会话属性
	 * @param sessionId - 会话id
	 * @param name - 属性名称
	 * @param value - 属性值
	 * @throws SessionException - 如果发生会话异常
	 */
	public void setAttribute(String sessionId, String name, String value);

	/**
	 * 加载会话属性
	 * @param sessionId - 会话id
	 * @param name - 属性名称
	 * @return 属性值
	 * @throws SessionException - 如果发生会话异常
	 */
	public String getAttribute(String sessionId, String name);

	/**
	 * 删除会话属性
	 * @param sessionId - 会话id
	 * @param name - 属性名称
	 * @throws SessionException - 如果发生会话异常
	 */
	public void removeAttribute(String sessionId, String name);

	/**
	 * 设置最大过期时间
	 * @throws Exception
	 */
	public void setMaxInactiveInterval(String sessionId, int seconds);
}

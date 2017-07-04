package com.project.manager;

public interface AccountManager {

	/**
	 * 用户注册
	 * @param accountName
	 * @param password
	 */
	public void register(String accountName, String password);
	
	public String login(String accountName, String password);
	
	public int changePassword(String accountId, String password, String newPassword);
	
	public int changePayPassword(String accountId, String password, String payPassword);
	
	public int changePayeeName(String accountId, String payeeName);
	
	public String getPayeeName(String accountId);

	/**
	 * 绑定银行卡
	 * @param accountId
	 * @param payeeName
	 * @param cardNumber
	 * @param bankAllas
	 * @param province
	 * @param city
	 * @param place
	 * @return
	 */
	public int bindingBank(String accountId, String payeeName, String cardNumber, String bankAllas,
			String province, String city, String place);

	/**
	 * 创建代理、会员用户
	 * @param name
	 * @param password
	 * @param point
	 * @param userType
	 * @param parentId
	 * @return
	 */
	public int createAgent(String name, String password, String point, String userType, String parentId);


	/**
	 * 通过短链接得到长链接
	 * @param shortUrl		短链接
	 * @return
	 */
	public String getlongUrl(String shortUrl);
	
	public int createLink(String shortUrl, String url, String accountId);

}

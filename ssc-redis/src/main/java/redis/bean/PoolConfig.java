package redis.bean;

import java.util.List;

import redis.HostPort;

public class PoolConfig {

	private List<HostPort> hostPort;
	private int maxTotal;
	private int maxIdle;
	private long maxWaitMillions;
	private boolean testOnBorrow;
	private int timeOut;
	private String password;
	private String masterName;  //用于sentinel模式
	
	//set get
	public List<HostPort> getHostPort() {
		return hostPort;
	}
	public void setHostPort(List<HostPort> hostPort) {
		this.hostPort = hostPort;
	}
	public int getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public long getMaxWaitMillions() {
		return maxWaitMillions;
	}
	public void setMaxWaitMillions(long maxWaitMillions) {
		this.maxWaitMillions = maxWaitMillions;
	}
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	@Override
	public String toString() {
		return "PoolConfig [hostPort=" + hostPort + ", maxTotal=" + maxTotal
				+ ", maxIdle=" + maxIdle + ", maxWaitMillions="
				+ maxWaitMillions + ", testOnBorrow=" + testOnBorrow
				+ ", timeOut=" + timeOut + ", password=" + password
				+ ", masterName=" + masterName + "]";
	}
}

package com.project.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class HttpUtil {
	
	/**
	 * 根据前缀的不同获取获取URLConnection
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static HttpURLConnection openHttpURLConnection(String url)
			throws IOException {
		URL getUrl = new URL(url);
		// 根据拼凑的URL，打开连接，URL.openConnection()函数会根据
		if (url.startsWith("https")) { // https
			HttpsURLConnection connection = (HttpsURLConnection) getUrl
					.openConnection();
			connection.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			return connection;
		} else {
			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();
			return connection;
		}
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		StringBuffer result = new StringBuffer();
		try {
			HttpURLConnection connection = openHttpURLConnection(url);// 获取http连接
			connection.connect();// 建立与服务器的连接，并未发送数据
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));// 发送数据到服务器并使用Reader读取返回的数据
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			reader.close();
			connection.disconnect();// 断开连接
		} catch (Exception e) {
			return null;
		}
		return result.toString();
	}

	/**
	 * post请求
	 * 
	 * @param url
	 * @return
	 */
	public static String post(String url, String params) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			HttpURLConnection connection = openHttpURLConnection(url);// 获取http连接

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.connect();// 建立与服务器的连接，并未发送数据

			out = new PrintWriter(connection.getOutputStream());
			out.write(params);
			out.flush();

			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}

			out.close();
			in.close();
			connection.disconnect();// 断开连接
		} catch (IOException e) {
			return null;
		}
		return result.toString();
	}

}
package com.project.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
//import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpServletUtil {

	/**
	 * 获取返回前端的数据JSON
	 * 
	 * @param status
	 *            处理结果状态 0：成功 1失败
	 * @param errMsg
	 *            消息
	 * @return 数据JSON
	 */
	public static String getResponseJsonData(int status, String data, String errMsg) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		jsonObject.put("data", data);
		jsonObject.put("errMsg", errMsg);
		
		return jsonObject.toString();
	}
	
	public static String getResponseJsonData(int status, String errMsg) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		jsonObject.put("errMsg", errMsg);
		
		return jsonObject.toString();
	}

	/**
	 * 
	 * 拼装返回前端的数据JSON
	 * @param status 处理结果状态
	 * @param data 业务数据
	 * @param errMsg 错误信息
	 * @return 数据JSON
	 */
	public static String getResponseJsonData(int status,
			Map<String, Object> data, String errMsg) {
		JSONObject jsonObject = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		if (data != null) {
			map.put("data", data);
		}
		map.put("errMsg", errMsg);
		jsonObject = JSON.parseObject(map.toString());
		return jsonObject.toString();
	}
	
	/**
	 * 初始化Response，支持跨域请求
	 * @param response
	 */
	public static void initResponse(HttpServletResponse response) {
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
	}
	
	public static String sendPost(String jsonStr, String path) throws IOException {
			
		java.net.URL url = new java.net.URL(path);
			
		java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);// 设置连接超时时间为5秒 
		conn.setReadTimeout(20 * 1000);// 设置读取超时时间为20秒 
		// 使用 URL 连接进行输出，则将 DoOutput标志设置为 true
		conn.setDoOutput(true);

		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
		
		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
		outStream.writeBytes(jsonStr);
		outStream.close();//关闭流
		String msg = "";// 保存调用http服务后的响应信息
		// 如果请求响应码是200，则表示成功
		if (conn.getResponseCode() == 200) {
			// HTTP服务端返回的编码是UTF-8,故必须设置为UTF-8,保持编码统一,否则会出现中文乱码
			BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) conn.getInputStream(), "UTF-8"));
			msg = in.readLine();
			in.close();
		}
		conn.disconnect();// 断开连接
		return msg;
	}
}

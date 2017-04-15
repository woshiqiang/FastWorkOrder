package com.hbck.fastworkorder.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpClientUtil {
	private String PATH;
	private static URL url;

	public HttpClientUtil(String str) {
		this.PATH = str;
	}

	public String sendPostMessage(Map<String, String> params, String encode) {
		// 作为StringBuffer初始化的字符�?
		StringBuffer buffer = new StringBuffer();
		try {
			if (params != null && !params.isEmpty()) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String encode1 = "";
					if (entry != null&&entry.getValue()!=null){
						encode1 = URLEncoder.encode(entry.getValue(), encode);
					}
					buffer.append(entry.getKey())
							.append("=")
							.append(encode1)
							.append("&");
				}
			}

			buffer.deleteCharAt(buffer.length() - 1);
			try {
				url = new URL(PATH);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				HttpURLConnection huc = (HttpURLConnection) url
						.openConnection();
				huc.setConnectTimeout(10000);
				huc.setReadTimeout(10000);
				huc.setRequestMethod("POST");
				huc.setDoInput(true);// 表示从服务器获取数据
				huc.setDoOutput(true);// 表示向服务器写数据
				// 获得上传信息的字节大小以及长度
				byte[] data = buffer.toString().getBytes();
				// 表示设置请求体的类型是文本类
				huc.setRequestProperty("content-Type",
						"application/x-www-form-urlencoded");
				huc.setRequestProperty("content-Length",
						String.valueOf(data.length));
				// 获得输出流，向服务器输出数据
				OutputStream os = huc.getOutputStream();
				os.write(data, 0, data.length);
				os.close();
				// 获得服务器的响应结果和响应码
				int responseCode = huc.getResponseCode();
				if (responseCode == 200) {
					return changeInputStream(huc.getInputStream(), encode);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return "";
	}

	/**
	 * @param encode
	 *            字节编码
	 */
	public String sendPostMessage(String encode) {
		try {
			url = new URL(PATH);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			huc.setConnectTimeout(5000);
			huc.setRequestMethod("POST");
			huc.setDoInput(true);// 表示从服务器获取数据
			huc.setDoOutput(true);// 表示向服务器写数�?
			// 获得上传信息的字节大小以及长�?
			// 表示设置请求体的类型是文本类�?
			huc.setRequestProperty("content-Type",
					"application/x-www-form-urlencoded");
			// 获得输出流，向服务器输出数据
			OutputStream os = huc.getOutputStream();
			os.close();
			// 获得服务器的响应结果和响应码
			int responseCode = huc.getResponseCode();
			if (responseCode == 200) {
				return changeInputStream(huc.getInputStream(), encode);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 将一个输入流转换成指定编码的字符
	 * 
	 */
	private static String changeInputStream(InputStream inputStream,
											String encode) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] mydata = new byte[1024];
		int len = 0;
		String result = "";
		if (inputStream != null) {
			try {
				while ((len = inputStream.read(mydata)) != -1) {
					baos.write(mydata, 0, len);
				}
				result = new String(baos.toByteArray(), encode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}

package com.hbck.fastworkorder.task;

import android.os.AsyncTask;

import com.hbck.fastworkorder.util.HttpClientUtil;
import com.hbck.fastworkorder.util.UrlPathUtil;

import java.util.HashMap;
import java.util.Map;

public class SetLoginLogTask extends AsyncTask<String, String, String> {

	private String name;
	private String imei;
	private String content;
	private String CID;

	public SetLoginLogTask(String name, String imei, String content,String CID) {
		super();
		this.name = name;
		this.imei = imei;
		this.content = content;	
		this.CID = CID;
		
	}

	@Override
	protected String doInBackground(String... params) {
		Map<String, String> map = new HashMap<String, String>();
		System.out.println(name+"==="+imei+"--"+content+"===="+CID);
		map.put("name", name+"");
		map.put("imei", imei+"");
		map.put("content", content+"");
		map.put("CID", CID+"");
		HttpClientUtil hcu = new HttpClientUtil(UrlPathUtil.LoginLog_URL);
		String result = hcu.sendPostMessage(map, "utf-8");
		return result;
	}

}
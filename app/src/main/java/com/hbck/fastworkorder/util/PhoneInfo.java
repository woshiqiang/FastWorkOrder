package com.hbck.fastworkorder.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.hbck.fastworkorder.task.SetLoginLogTask;


public class PhoneInfo {

	private String name;
	private Context cxt;
	private int i;
	private String cid;

	public PhoneInfo(Context context, int i, String name, String CID) {
		this.cxt = context;
		this.i = i;
		this.name = name;
		this.cid = CID;
	}

	public void getPhoneInfo() {
		// String version = "版本号:" + getVersionCode(cxt);
		TelephonyManager tm = (TelephonyManager) cxt
				.getSystemService(Context.TELEPHONY_SERVICE);
		WifiManager wifiManager = (WifiManager) cxt
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		String ch = "串号:" + tm.getDeviceId();
		String hm = "号码:" + tm.getLine1Number();
		String fws = "服务商: " + tm.getNetworkOperatorName();
		String sjxh = "手机型号 : " + android.os.Build.MODEL;
		String sjcs = "手机厂商: " + android.os.Build.MANUFACTURER;
		String dhfw = "电话方位： : " + tm.getCellLocation();
		String mac = "MAC: " + wifiInfo.getMacAddress();
		String nr = name + "," + sjcs + "," + sjxh + "," + dhfw + "," + fws
				+ "," + hm + "," + mac;
		if (i == 1) {
			nr = "成功：" + nr;
		} else {
			nr = "失败：" + nr;
		}
		SetLoginLogTask llt = new SetLoginLogTask(name, ch, nr,cid);
		llt.execute();
	}

	public static String getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}

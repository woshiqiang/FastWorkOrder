package com.hbck.fastworkorder.util;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WebServiceUtil {

    public interface WSCallBack {
        void onSuccess(String result);

        void onFail(String msg);
    }


    public static void getResult(final String url, final String methodName, final Map<String, String> map,
                                 final WSCallBack call) {
        if (call == null) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 命名空间
                String nameSpace = "http://lwyText/";
                // 调用的方法名称
                String soapAction = nameSpace + methodName;
                System.out.println(soapAction);
                // 指定WebService的命名空间和调用的方法名
                SoapObject rpc = new SoapObject(nameSpace, methodName);
                Set<Map.Entry<String, String>> set = map.entrySet();
                for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext(); ) {
                    Map.Entry<String, String> entry = it.next();
                    rpc.addProperty(entry.getKey(), entry.getValue());
                }

                // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.bodyOut = rpc;
                // 设置是否调用的是dotNet开发的WebService
                envelope.dotNet = true;
                // 等价于envelope.bodyOut = rpc;
                envelope.setOutputSoapObject(rpc);

                HttpTransportSE transport = new HttpTransportSE(url);

                String result = "";
                try {
                    // 调用WebService
                    transport.call(soapAction, envelope);
                    System.out.println("HttpTransportSE");
                    // 获取返回的数据
                    Object object = envelope.bodyIn;
                    System.out.println("获取返回的数据--ss-" + object);
                    // 获取返回的结果
                    System.out.println("-----------");
                    result = ((SoapObject) object).getProperty(0).toString();
                    System.out.println("获取返回的结果---" + result);
                    call.onSuccess(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    result = "失败";
                    call.onFail(result+e.getMessage());
                }
            }
        }).start();

    }

}

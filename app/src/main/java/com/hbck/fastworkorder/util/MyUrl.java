package com.hbck.fastworkorder.util;


/**
 * @author 丁建强
 * @time 2017-03-28 16:56
 * @类描述：
 * @变更记录:
 */

public interface MyUrl {
    String SERVER_URL = "http://211.161.25.48:101/FastWorkOrder/";
    String WEB_SERVICE_URL_4 = "http://211.161.25.48:88/WeS/WebUserService.asmx";


    String UpdateVersion = SERVER_URL + "GetVersion";// 版本更新
    String LOGIN = SERVER_URL + "Login";// 登陆
    String GetContact = SERVER_URL + "GetContact";// 通讯录
    String RestImei = SERVER_URL + "RestImei";//解绑账号接口


}

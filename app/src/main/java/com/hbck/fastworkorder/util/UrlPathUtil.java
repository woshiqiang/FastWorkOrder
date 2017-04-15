package com.hbck.fastworkorder.util;


public class UrlPathUtil {

    //    正式
//    public static final String SERVER_URL = "http://211.161.26.66:14445/FastWorkOrder/";
//    public static final String URL = "http://211.161.26.66/WebS/WebServiceText.asmx/";// 服务端web地址
//    public static final String URL2 = "http://211.161.26.66/WebS/WebPhoneOrderTrouble.asmx/";//测试
//    public static final String URL3 = "http://211.161.26.66/WebS/WebPhoneNumber.asmx/";
//    public static final String LIANG_HUA = "http://211.161.26.66/LiangHua/index.html";//量化
//    public static final String CreateOrder = "http://mm.hb95079.com/Handler/WorkOrderService.aspx?method=CreateOrder";//咨询录入
//    public static final String WEB_SERVICE_URL_1 = "http://211.161.26.66/WebS/WebServiceText.asmx";
//    public static final String WEB_SERVICE_URL_2 = "http://211.161.26.66/WebS/WebPhoneOrderTrouble.asmx";
//    public static final String WEB_SERVICE_URL_4 = "http://211.161.26.66/WebS/WebUserService.asmx";

    //测试
    public static final String SERVER_URL = "http://211.161.25.48:101/FastWorkOrder/";
    public static final String URL = "http://211.161.25.48:88/WebS/WebServiceText.asmx/";// 服务端web地址
    public static final String URL2 = "http://211.161.25.48:88/WebS/WebPhoneOrderTrouble.asmx/";//测试
    public static final String URL3 = "http://211.161.25.48:88/WebS/WebPhoneNumber.asmx/";
    public static final String LIANG_HUA = "http://211.161.25.48:88/LiangHua/index.html"; //量化
    public static final String CreateOrder = "http://211.161.25.48:10001/Handler/WorkOrderService.aspx?method=CreateOrder";//咨询录入
    public static final String WEB_SERVICE_URL_1 = "http://211.161.25.48:88/WebS/WebServiceText.asmx";
    public static final String WEB_SERVICE_URL_2 = "http://211.161.25.48:88/WebS/WebPhoneOrderTrouble.asmx";
    public static final String WEB_SERVICE_URL_4 = "http://211.161.25.48:88/WebS/WebUserService.asmx";


    public static String updateSession = SERVER_URL + "GetVersion";// 版本更新
    public static String LoginLog_URL = SERVER_URL + "SetLoginLog";// 添加登陆日志接口
    public static String Login_URL = SERVER_URL + "Login";// 登陆接口
    public static String SetSignIn_URL = SERVER_URL + "SetSignIn";// 签到的接口
    public static String MySignIn_URL = SERVER_URL + "MySignIn";// 查看我的当天签到的接口
    public static String GetContact = SERVER_URL + "GetContact";// 通讯录
    public static final String GetIP = SERVER_URL + "GetIpAccount";// 获取IP
    public static final String VerifyIP = SERVER_URL + "VerifyIP";// 匹配IP
    public static final String GetSerialNum = SERVER_URL + "GetSerialNum";//
    public static final String GetPushMsg = SERVER_URL + "GetPushMsg";// 查看我接受的推送消息
    public static final String GetMyMonthSignIn = SERVER_URL + "GetMyMonthSignIn";// 获取某月的签到情况
    public static String GetCustomerType = SERVER_URL + "GetCustomerType";// 获取客户类型接口
    public static String GetAera = SERVER_URL + "GetAera";// 获取行政区接口
    public static String GetAreaList = SERVER_URL + "GetAreaList";// 获取区域接口
    public static String GetOldPackage = SERVER_URL + "GetOldPackage";// 历史套餐
    public static String GetCommunity = SERVER_URL + "GetCommunity";// 获取社区接口
    public static String SetPushMsg = SERVER_URL + "SetPushMsg";// 消息推送
    public static final String GetRetreatReason_URL = SERVER_URL + "GetRetreatReason";// 退转工单
    public static final String GetMyWorkOrder_URL = SERVER_URL + "GetMyWorkOrder";// 我的工单
    public static final String GetMyPayWorkOrder = SERVER_URL + "GetMyPayWorkOrder";// 我的缴费工单
    public static final String GetCommunityOnLineContact = SERVER_URL + "GetCommunityOnLineContact";//
    public static final String GetWorkOrderFlow = SERVER_URL + "GetWorkOrderFlow";// 获取工单流转过程
    public static final String UpdateRerurnOrder_URL = SERVER_URL + "UpdateRerurnOrder";// 不通过的回单
    public static final String SetPrintCount = SERVER_URL + "SetPrintCount";// 打印状态
    public static final String SetReturnOrder_URL = SERVER_URL + "SetReturnOrder";
    public static final String GetOnLineContact_URL = SERVER_URL + "GetOnLineContact";// 获取本城签到人员
    public static final String SetTransferRecord = SERVER_URL + "SetTransferRecord";// 转派

    public static final String GetMyPayReturnOrder = SERVER_URL + "GetMyPayReturnOrder";// 我的回单缴费

    public static final String GetPrint_URL = SERVER_URL + "GetPrintReturnOrder";
    public static final String UploadImg_URL = SERVER_URL + "UploadImg";// 上传接口

    public static final String GetMySendWorkOrder_URL = SERVER_URL + "GetMySendWorkOrder";// 查看我派出的工单接口
    public static final String GetContactById = SERVER_URL + "GetContactById";// 根据id查name

    public static final String GetPropagandaInfo = SERVER_URL + "GetPropagandaInfo";//获取选宣传品

    public static final String GetJobCategory = SERVER_URL + "GetJobCategory";//获取工作内容

    public static final String SetWorkPlan = SERVER_URL + "SetWorkPlan";//量化计划保存

    public static final String SetProWorkUseInfo = SERVER_URL + "SetProWorkUseInfo";//实际上报宣传品接口


    public static final String SetTrueWorkInfo = SERVER_URL + "SetTrueWorkInfo";//上报实际工作量接口

    public static final String AddTime = SERVER_URL + "GetAddTime";//获取工作计划上报时间

    public static final String GetSelectWorkPlan = SERVER_URL + "GetSelectWorkPlan";//获取工作计划数据
    public static final String GetSelectTrueWork = SERVER_URL + "GetSelectTrueWork";//获取实际消减
    public static final String GetSelectProWork = SERVER_URL + "GetSelectProWork";//获取宣传品消减
    public static final String SetActivity = SERVER_URL + "SetActivity";//插入活动
    public static final String GetWareHouse = SERVER_URL + "GetWareHouse";//获取三级库
    public static final String GetSelectActivity = SERVER_URL + "GetSelectActivity";//获取活动

    public static final String GetEmpName = SERVER_URL + "GetEmpName";//获取个人id

    public static final String PositionStar = SERVER_URL + "PositionStar";//工作定位开始

    public static final String PositionEnd = SERVER_URL + "PositionEnd";//工作定位结束

    public static final String GetPotentialInfo = SERVER_URL + "GetPotentialInfo";//查询我的潜户录入

    public static final String GetPotentialIsOver = SERVER_URL + "GetIsOver";//查询潜户录入状态

    public static final String RestImei = SERVER_URL + "RestImei";//解绑账号接口

    public static final String GetCMTelSale = SERVER_URL + "GetCMTelSale";
    public static final String GetCMTelSale1 = SERVER_URL + "GetCMTelSale";
    public static final String GetCMTelSale2 = SERVER_URL + "GetCMTelSale";//预约工单调整接口

    public static final String GetCMTelSale3 = SERVER_URL + "GetCMTelSale";//判定签到接口


    public static final String GetNTelSale_New = URL + "GetNTelSale_New";//获取抢单工单赤池接口
    public static final String GrabOrders = URL + "GrabOrders";//抢单占用接口


    public static final String GetPhoneNumList = URL3 + "GetPhoneNumList";//
    public static final String GetPhoneForLastChar = URL3 + "GetPhoneNumForLastChar";
    public static final String CheckPhoneNum = URL3 + "CheckPhoneNum";
    public static final String UsePhoneNum = URL3 + "UsePhoneNum";

    public static final String GetPhoneWork = URL + "GetPhoneWork";//获取我的回单内容

    public static final String GetBossProduct = URL + "GetBossProduct";// 获取Boss中产品接口
    public static final String GetBossPackage = URL + "GetBossPackage";// 获取Boss中套餐接口
    public static final String GetDeviceByBoxNum = URL + "GetDeviceByBoxNum";// 获取机箱下设备信息接口
    public static final String GetDeviceByCommunity = URL + "GetDeviceByCommunity";// 获取社区下所有机箱接口
    public static final String DeviceOnLine = URL + "DeviceOnLine";// 设备上线接口
    public static final String DeviceOffLine = URL + "DeviceOffLine";// 设备下线接口
    public static final String SavePhoneSend = URL + "SavePhoneSend";//
    public static final String GetWareHouseThreeNow = URL + "GetWareHouseThreeNow";// 获取三级库库存
    public static final String GetWareHouseTwo = URL + "GetWareHouseTwo";// 获取二级库库存
    public static final String GetDeviceByWareHouseThree = URL + "GetDeviceByWareHouseThree";// 获取三级库设备明细接口
    public static final String WareHouseThreeCut = URL + "WareHouseThreeCut";// 三级库消减接口
    public static final String GetWatingOnLineDevicByThreeMan = URL + "GetWatingOnLineDevicByThreeMan";//
    public static final String GetWatingOnLineDevicByWhere = URL + "GetWatingOnLineDevicByWhere";// 三级库设备状态
    public static final String SelectWareHouseThreeSum = URL + "SelectWareHouseThreeSum";// 查询个人三级库
    public static final String SaveHiddenUser = URL + "SaveHiddenUser";// 查询个人三级库
    public static final String GetCustomerList = URL + "GetCustomerList";// 获取客户信息
    public static final String GetTroubleOrderType = URL + "GetTroubleOrderType";// 获取各级故障（1 2 3）
    public static final String SavePhoneOrderTrouble = URL + "SavePhoneOrderTrouble";// 获取各级故障（1 2 3）

    public static final String GetGiftInfoByCityID = URL + "GetGiftInfoByCityID";// 礼品信息

    public static final String GetServicHallInfo = URL + "GetServicHallInfo";// 获取受理地点
    public static final String GetOrderList = URL + "GetOrderList";// 获取故障列表

    public static final String GetCustomerDetail = URL2 + "GetCustomerDetail";// 获取客户详情

    public static final String SelectTroubleOrderList = URL2 + "SelectTroubleOrderList";// 获取故障工单
    public static final String SelectTroubleOrderResultType = URL2 + "SelectTroubleOrderResultType";// 获取故障结论级别
    public static final String WareHouseThreeCutTrouble = URL2 + "WareHouseThreeCutTrouble";// 三级库消减（故障）

    public static final String SavePhoneWork = URL + "SavePhoneWork";// 回单

    public static final String SavePhoneWorkSecond = URL + "SavePhoneWorkSecond";

    public static final String SetVisitLog = SERVER_URL + "SetVisitLog";//量化点击次数
    public static final String SelectServiceStartTime = SERVER_URL + "SelectServiceStartTime";//获取续费单子的上个服务的结束时间

}

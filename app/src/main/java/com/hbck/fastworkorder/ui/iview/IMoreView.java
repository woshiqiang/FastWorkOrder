package com.hbck.fastworkorder.ui.iview;


/**
 * @author 丁建强
 * @time 2017-04-10 10:09
 * @类描述：更多页面
 * @变更记录:
 */
public interface IMoreView {
    /**
     * 解绑
     * @param result 解绑成功 或失败的回调
     */
    void onUnBind(String result);

    /**
     * 获取各个单子的数量
     * 待改动
     * @param o
     */
    void onCallNumber(Object o);


}

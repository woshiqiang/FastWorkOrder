package com.hbck.fastworkorder.ui.iview;


import android.app.ProgressDialog;

import com.hbck.fastworkorder.bean.json.User;

/**
 * @author 丁建强
 * @time 2017-04-06 15:49
 * @类描述：登录
 * @变更记录:
 */

public interface ILoginView {
    ProgressDialog showDialog();

    /**
     * 登录成功
     *
     * @param user
     */
    void onLoginSucceed(User user);

    /**
     * 登录失败
     *
     * @param msg
     */
    void onLoginFailed(String msg);

    /**
     * 登录量化
     *
     * @param result
     */
    void onLoginLianghua(String result);


}

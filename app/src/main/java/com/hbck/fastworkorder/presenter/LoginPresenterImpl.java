package com.hbck.fastworkorder.presenter;

import android.app.ProgressDialog;
import android.util.Log;

import com.google.gson.Gson;
import com.hbck.fastworkorder.base.BasePresenter;
import com.hbck.fastworkorder.bean.json.User;
import com.hbck.fastworkorder.presenter.impl.ILoginPresenter;
import com.hbck.fastworkorder.ui.iview.ILoginView;
import com.hbck.fastworkorder.util.MyUrl;
import com.hbck.fastworkorder.util.WebServiceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by Administrator on 2017-04-06.
 */

public class LoginPresenterImpl extends BasePresenter<ILoginView> implements ILoginPresenter {
    private ProgressDialog pd;

    @Override
    public void login(String username, String password, String IMEI) {
        pd = mViewRef.get().showDialog();

        OkHttpUtils.post()
                .url(MyUrl.LOGIN)
                .tag("login")
                .addParams("name", username)
                .addParams("pwd", password)
                .addParams("IMEI", IMEI)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        pd.dismiss();
                        mViewRef.get().onLoginFailed("登录失败(" + e.getMessage() + ")");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        pd.dismiss();
                        Log.d("LoginPresenterImpl", response);
                        if (response.contains("{") && response.contains("EmpID")) {
                            Gson gson = new Gson();
                            User user = gson.fromJson(response, User.class);
                            mViewRef.get().onLoginSucceed(user);
                        } else {
                            mViewRef.get().onLoginFailed(response);
                        }
                    }
                });
    }

    @Override
    public void loginLianghua(String username, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", username);
        map.put("passwd", password);
        WebServiceUtil.getResult(MyUrl.WEB_SERVICE_URL_4, "Login", map, new WebServiceUtil.WSCallBack() {
            @Override
            public void onSuccess(String result) {
                mViewRef.get().onLoginLianghua(result);
            }

            @Override
            public void onFail(String msg) {
                mViewRef.get().onLoginLianghua(msg);
            }
        });
    }
}

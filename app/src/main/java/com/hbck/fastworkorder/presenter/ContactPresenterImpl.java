package com.hbck.fastworkorder.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.hbck.fastworkorder.base.BasePresenter;
import com.hbck.fastworkorder.bean.json.RootBean;
import com.hbck.fastworkorder.bean.json.User;
import com.hbck.fastworkorder.presenter.impl.IContactPresenter;
import com.hbck.fastworkorder.ui.iview.IContactView;
import com.hbck.fastworkorder.util.MyUrl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;


/**
 * @author 丁建强
 * @time 2017-04-07 14:08
 * @类描述：通讯录
 * @变更记录:
 */

public class ContactPresenterImpl extends BasePresenter<IContactView> implements IContactPresenter {

    @Override
    public void getContact(String CityID) {
        OkHttpUtils.post()
                .addParams("CityID", CityID)
                .url(MyUrl.GetContact)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mViewRef.get().onContactResult(null);
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Log.d("ContactPresenterImpl", result);
                        if (result.contains("{") && result.length() > 2) {
                            String strResult = "{\"users\":" + result + "}";
                            Gson gson = new Gson();
                            RootBean rb = gson.fromJson(strResult, RootBean.class);
//                            if (rb.users != null)
//                                SQLiteHelper.InsertContact(msh, binfo.getUsers());
//                            List<User> users = SQLiteHelper.getContacts(msh);
                            mViewRef.get().onContactResult(rb.users);
                        } else {
                            mViewRef.get().onContactResult(new ArrayList<User>());
                        }
                    }
                });
    }
}

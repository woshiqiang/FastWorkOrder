package com.hbck.fastworkorder.presenter;

import com.hbck.fastworkorder.base.BasePresenter;
import com.hbck.fastworkorder.presenter.impl.IMorePresenter;
import com.hbck.fastworkorder.ui.iview.IMoreView;
import com.hbck.fastworkorder.util.MyUrl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


/**
 * @author 丁建强
 * @time 2017-04-10 10:23
 * @类描述：更多Fragment
 * @变更记录:
 */
public class MorePresenterImpl extends BasePresenter<IMoreView> implements IMorePresenter {

    @Override
    public void unBind(String username) {
        OkHttpUtils.post()
                .addParams("empid", username)
                .url(MyUrl.RestImei)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mViewRef.get().onUnBind("解绑失败,请检查网络！");
            }

            @Override
            public void onResponse(String response, int id) {
                mViewRef.get().onUnBind(response);
            }
        });
    }
}

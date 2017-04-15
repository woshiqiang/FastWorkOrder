package com.hbck.fastworkorder.presenter;

import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.hbck.fastworkorder.base.BasePresenter;
import com.hbck.fastworkorder.bean.json.VersionBean;
import com.hbck.fastworkorder.ui.iview.IVersionView;
import com.hbck.fastworkorder.presenter.impl.IVersionPresenter;
import com.hbck.fastworkorder.util.MyUrl;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;

import static com.zhy.http.okhttp.OkHttpUtils.post;


/**
 * @author 丁建强
 * @time 2017-04-01 11:26
 * @类描述：mvp-presenter
 * @变更记录:
 */
public class VersionPresenterImpl extends BasePresenter<IVersionView> implements IVersionPresenter {

    /**
     * 检查版本
     *
     * @param currVersion
     */
    @Override
    public void checkVersion(String currVersion) {
        post()
                .url(MyUrl.UpdateVersion)
                .addParams("version", currVersion)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d("SplashActivity", "e:" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("SplashActivity", "" + response + " id:" + id);
                        if (response != null && response.contains("{")) {//需要更新
                            Gson gson = new Gson();
                            VersionBean versionBean = gson.fromJson(response, VersionBean.class);
                            mViewRef.get().onUpdate(versionBean);
                        } else {
                            mViewRef.get().onNoUpdate();
                        }
                    }
                });
    }

    @Override
    public void downLoadApk(VersionBean versionBean) {
        String apkUrl = MyUrl.SERVER_URL + versionBean.loadUrl;
        String destFileDir = Environment.getExternalStorageDirectory() + "/apk";
        String destFileName = "FastOrder-" + versionBean.versionId + ".apk";
        post()
                .url(apkUrl)
                .tag("apk")
                .build()
                .execute(new FileCallBack(destFileDir, destFileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                        mViewRef.get().onDownLoadError("下载出错:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        mViewRef.get().onDownLoadSuccess(response);
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        mViewRef.get().onDownLoadProgress(progress, total);
                    }
                });

    }
}

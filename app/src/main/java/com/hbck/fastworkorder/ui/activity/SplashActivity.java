package com.hbck.fastworkorder.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hbck.fastworkorder.R;
import com.hbck.fastworkorder.bean.json.VersionBean;
import com.hbck.fastworkorder.util.MyUrl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;

import static com.zhy.http.okhttp.OkHttpUtils.post;


public class SplashActivity extends AppCompatActivity {


    private ProgressBar pb;
    private TextView tv_progress;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
        String version = "0";
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            version = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            version = "0";
        }

        checkVersion(version);
    }

    private void checkVersion(String version) {
        Log.d("version", "" + version);
        post()
                .url(MyUrl.UpdateVersion)
                .addParams("version", version)
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
                            downLoadFile(versionBean);
                        } else {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }

    /**
     * 下载apk
     *
     * @param versionBean
     */
    private void downLoadFile(final VersionBean versionBean) {
        View view = View.inflate(this, R.layout.view_progressbar, null);
        pb = (ProgressBar) view.findViewById(R.id.update_progress);
        tv_progress = (TextView) view.findViewById(R.id.tv_progress);

        builder = new AlertDialog.Builder(this);
        dialog = new AlertDialog.Builder(this).setTitle("软件版本更新")
                .setView(view)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OkHttpUtils.getInstance().cancelTag("apk");
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
        downLoadApk(versionBean);
    }

    private void downLoadApk(VersionBean versionBean) {
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
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        dialog.dismiss();
                        installApk(response);
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        pb.setMax(100);
                        pb.setProgress((int)(progress*100));
                        String c = total * progress / 1024 / 1024 + "M/";
                        String to = total / 1024 / 1024 + "M";
                        int p  = (int)(progress*100);
                        tv_progress.setText(p+"/100   "+c + to);
                    }
                });

    }

    private void installApk(File response) {
        if (response != null && response.exists()) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(Uri.parse("file://" + response.toString()),
                    "application/vnd.android.package-archive");
            startActivity(i);
        } else {
            return;
        }
    }

}

package com.hbck.fastworkorder.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hbck.fastworkorder.R;
import com.hbck.fastworkorder.base.BaseActivity;
import com.hbck.fastworkorder.bean.json.VersionBean;
import com.hbck.fastworkorder.ui.iview.IVersionView;
import com.hbck.fastworkorder.presenter.VersionPresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.lang.reflect.Field;

public class WelcomeActivity extends BaseActivity<IVersionView,VersionPresenterImpl> implements IVersionView {
    private ProgressBar pb;
    private TextView tv_progress;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String currVersion = getCurrVersion();
        mPresenter.checkVersion(currVersion);

    }

    @Override
    protected VersionPresenterImpl createPresenter() {
        return new VersionPresenterImpl();
    }

    /**
     * 获取当前的版本名称
     *
     * @return
     */
    private String getCurrVersion() {
        String version = "0";
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            version = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            version = "0";
        }
        return version;
    }


    @Override
    public void onUpdate(final VersionBean versionBean) {
        View view = View.inflate(this, R.layout.view_progressbar, null);
        pb = (ProgressBar) view.findViewById(R.id.update_progress);
        tv_progress = (TextView) view.findViewById(R.id.tv_progress);

        builder = new AlertDialog.Builder(this);
        dialog = new AlertDialog.Builder(this).setTitle("软件版本更新")
                .setView(view)
                .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       if (dialog !=null){
                           try {
                               Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                               // 将mShowing变量设为false，表示对话框已关闭
                               field.setAccessible( true );
                               field.set(dialog, false );
                               mPresenter.downLoadApk(versionBean);
                           } catch (NoSuchFieldException e) {
                               e.printStackTrace();
                           } catch (IllegalAccessException e) {
                               e.printStackTrace();
                           }
                       }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OkHttpUtils.getInstance().cancelTag("apk");
                        dialog.dismiss();
                        onNoUpdate();
                    }
                })
                .create();
        dialog.show();


    }

    @Override
    public void onNoUpdate() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onDownLoadError(String errerMsg) {
        if (dialog != null)
            dialog.dismiss();
        Toast.makeText(this, errerMsg, Toast.LENGTH_SHORT).show();
        onNoUpdate();
    }

    @Override
    public void onDownLoadSuccess(File response) {
        if (dialog != null)
            dialog.dismiss();
        if (response != null && response.exists()) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(Uri.parse("file://" + response.toString()),
                    "application/vnd.android.package-archive");
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "安装包出错", Toast.LENGTH_SHORT).show();
            onNoUpdate();
        }
    }

    @Override
    public void onDownLoadProgress(float progress, long total) {
        pb.setMax(100);
        pb.setProgress((int) (progress * 100));
        String c = (int) (total * progress / 1024) + "K/";
        String to = (int) (total / 1024) + "k";
        int p = (int) (progress * 100);
        tv_progress.setText(c + to);
    }
}

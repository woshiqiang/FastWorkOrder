package com.hbck.fastworkorder.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.hbck.fastworkorder.R;
import com.hbck.fastworkorder.base.BaseActivity;
import com.hbck.fastworkorder.bean.json.User;
import com.hbck.fastworkorder.presenter.LoginPresenterImpl;
import com.hbck.fastworkorder.ui.iview.ILoginView;
import com.hbck.fastworkorder.util.PhoneInfo;
import com.hbck.fastworkorder.util.SPUtil;
import com.igexin.sdk.PushManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 丁建强
 * @time 2017-04-06 14:49
 * @类描述：登录
 * @变更记录:
 */
public class LoginActivity extends BaseActivity<ILoginView, LoginPresenterImpl> implements ILoginView {

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.cb_remember)
    CheckBox cbRemember;
    @Bind(R.id.btn_login)
    Button btnLogin;

    private String userName;
    private String password;
    private String tCid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initPush();
        init();
    }

    private void initPush() {
        tCid = PushManager.getInstance().getClientid(this);
        // com.getui.demo.DemoPushService 为第三方自定义推送服务
//        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
//        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
    }

    private void init() {
        boolean isAutoSavePassword = SPUtil.getBoolean(SPUtil.REMEMBER_PWD, false);
        if (isAutoSavePassword) {
            cbRemember.setChecked(true);
            userName = SPUtil.getString(SPUtil.USER_NAME);
            password = SPUtil.getString(SPUtil.PASSWORD);
            etUsername.setText(userName);
            etPassword.setText(password);
            btnLogin.performClick();
        } else {
            cbRemember.setChecked(false);
            etUsername.setText("");
            etPassword.setText("");
        }
    }

    @Override
    protected LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl();
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        userName = etUsername.getText().toString();
        password = etPassword.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            showToast("请输入用户名！");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            showToast("请输入密码！");
            return;
        }

        //登录
        String IMIE = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        mPresenter.login(userName, password, IMIE);
    }

    @Override
    public ProgressDialog showDialog() {
        return ProgressDialog.show(this, "登录中...", "", false, true);
    }

    @Override
    public void onLoginSucceed(User user) {
        Log.d("LoginActivity", "user:" + user);
        mPresenter.loginLianghua(userName, password);
        SPUtil.put(SPUtil.USER_NAME, user.getEmpNumber());
        SPUtil.put(SPUtil.PASSWORD,password);
        SPUtil.put(SPUtil.REMEMBER_PWD, cbRemember.isChecked());
        SPUtil.put(SPUtil.CITY_ID, user.getCityID());
        SPUtil.put(SPUtil.EMP_NAME,user.getEmpName());
        showToast("登录成功");
    }

    @Override
    public void onLoginFailed(String msg) {
        PhoneInfo pi = new PhoneInfo(getApplicationContext(), 0, userName, tCid);
        pi.getPhoneInfo();
        showToast(msg);
    }

    @Override
    public void onLoginLianghua(String result) {
        //登录量化成功，保存token
        if (!TextUtils.isEmpty(result) && !result.contains("失败")) {
            SPUtil.put(SPUtil.TOKEN, result);
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

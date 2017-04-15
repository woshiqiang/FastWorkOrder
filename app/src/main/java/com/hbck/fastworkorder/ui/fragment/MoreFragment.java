package com.hbck.fastworkorder.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hbck.fastworkorder.R;
import com.hbck.fastworkorder.base.BaseFragment;
import com.hbck.fastworkorder.presenter.MorePresenterImpl;
import com.hbck.fastworkorder.ui.iview.IMoreView;
import com.hbck.fastworkorder.util.SPUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author 丁建强
 * @time 2017-04-06 10:36
 * @类描述：云应用
 * @变更记录:
 */

public class MoreFragment extends BaseFragment<IMoreView, MorePresenterImpl> implements IMoreView {

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_sign_in)
    TextView tvSignIn;
    @Bind(R.id.tv_open_account)
    TextView tvOpenAccount;
    @Bind(R.id.tv_send_order)
    TextView tvSendOrder;
    @Bind(R.id.tv_renewal)
    TextView tvRenewal;
    @Bind(R.id.tv_hidden_door)
    TextView tvHiddenDoor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_more, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_sign_in, R.id.tv_open_account, R.id.tv_send_order, R.id.tv_renewal, R.id.tv_hidden_door, R.id.tv_my_msg, R.id.tv_unbind, R.id.tv_query, R.id.tv_enterprise_info, R.id.tv_version_info, R.id.tv_update_db, R.id.tv_setting, R.id.tv_clear_cache, R.id.tv_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_in://签到
                break;
            case R.id.tv_open_account://开户
                break;
            case R.id.tv_send_order://派单
                break;
            case R.id.tv_renewal://续费
                break;
            case R.id.tv_hidden_door://潜户
                break;
            case R.id.tv_my_msg://我的消息
                break;
            case R.id.tv_unbind://解绑
                mPresenter.unBind(SPUtil.getString(SPUtil.USER_NAME));
                break;
            case R.id.tv_query://量化查询
                break;
            case R.id.tv_enterprise_info://企业信息
                break;
            case R.id.tv_version_info://版本消息
                break;
            case R.id.tv_update_db://更新本地库
                break;
            case R.id.tv_setting://系统设置
                break;
            case R.id.tv_clear_cache://清除缓存
                break;
            case R.id.tv_exit://退出
                break;
        }
    }

    @Override
    protected MorePresenterImpl createPresenter() {
        return new MorePresenterImpl();
    }

    @Override
    public void onUnBind(String result) {
        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCallNumber(Object o) {

    }
}

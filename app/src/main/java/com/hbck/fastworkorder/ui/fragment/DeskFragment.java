package com.hbck.fastworkorder.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.hbck.fastworkorder.R;
import com.hbck.fastworkorder.adapter.DeskAdapter;
import com.hbck.fastworkorder.bean.local.DesktopApps;
import com.hbck.fastworkorder.ui.activity.OpenAccountEntryActivity;
import com.hbck.fastworkorder.util.SPUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author 丁建强
 * @time 2017-04-06 10:36
 * @类描述：云应用
 * @变更记录:
 */

public class DeskFragment extends Fragment implements AdapterView.OnItemClickListener {

    @Bind(R.id.account_head)
    TextView accountHead;
    @Bind(R.id.signIn_head)
    TextView signInHead;
    @Bind(R.id.gridView)
    GridView gridView;
    private DeskAdapter deskAdapter;
    private ArrayList<DesktopApps> list_app;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_desk, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        accountHead.setText(SPUtil.getString(SPUtil.EMP_NAME));
        int[] app_imgs = {R.mipmap.open_account, R.mipmap.renewal,
                R.mipmap.fault_management, R.mipmap.open_entry,
                R.mipmap.img_potential, R.mipmap.falt_entry, R.mipmap.zixun,
                R.mipmap.online, R.mipmap.img_quantitive,
                R.mipmap.qiangdan, R.mipmap.statistical,
                R.mipmap.signin, R.mipmap.more, R.mipmap.img_three_lib, R.mipmap.huifang};
        String[] app_names = {"开户工单", "续费工单", "故障工单", "开户录入", "潜户录入", "故障录入", "咨询录入",
                "设备上下线", "市场量化", "区域抢单", "业务统计", "我的考勤", "自定义", "三级库", "客服回访"};
        list_app = new ArrayList<DesktopApps>();
        for (int i = 0; i < app_imgs.length; i++) {
            DesktopApps da = new DesktopApps();
            da.setName(app_names[i]);
            da.setImg(app_imgs[i]);
            list_app.add(da);
        }
        deskAdapter = new DeskAdapter(getContext(), list_app);
        gridView.setAdapter(deskAdapter);
        list_app.get(2).setCount(1);
        deskAdapter.notifyDataSetChanged();

        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.signIn_head)
    public void onClick() {
        Toast.makeText(getContext(), "签到", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DesktopApps desktopApps = list_app.get(position);
        Toast.makeText(getContext(), desktopApps.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(getContext(), OpenAccountEntryActivity.class);
        switch (desktopApps.getName()) {
            case "开户工单":
                break;
            case "续费工单":
                break;
            case "故障工单":
                break;
            case "开户录入":
                intent.setClass(getContext(), OpenAccountEntryActivity.class);
                break;
            case "潜户录入":
                break;
            case "故障录入":
                break;
            case "咨询录入":
                break;
            case "设备上下线":
                break;
            case "市场量化":
                break;
            case "区域抢单":
                break;
            case "业务统计":
                break;
            case "我的考勤":
                break;
            case "自定义":
                break;
            case "三级库":
                break;
            case "客服回访":
                break;
        }

        startActivity(intent);
    }
}

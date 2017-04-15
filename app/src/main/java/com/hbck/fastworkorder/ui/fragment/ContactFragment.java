package com.hbck.fastworkorder.ui.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.hbck.fastworkorder.R;
import com.hbck.fastworkorder.adapter.EaseContactAdapter;
import com.hbck.fastworkorder.base.BaseFragment;
import com.hbck.fastworkorder.bean.json.User;
import com.hbck.fastworkorder.presenter.ContactPresenterImpl;
import com.hbck.fastworkorder.ui.iview.IContactView;
import com.hbck.fastworkorder.ui.widget.DrawableCenterEditText;
import com.hbck.fastworkorder.ui.widget.EaseContactList;
import com.hbck.fastworkorder.util.SPUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author 丁建强
 * @time 2017-04-06 9:27
 * @类描述：通讯录
 * @变更记录:
 */

public class ContactFragment extends BaseFragment<IContactView, ContactPresenterImpl> implements IContactView {

    @Bind(R.id.friend_et_search)
    DrawableCenterEditText friendEtSearch;
    @Bind(R.id.contact_list)
    EaseContactList contactList;
    @Bind(R.id.content_container)
    FrameLayout contentContainer;
    @Bind(R.id.ll_net_error)
    LinearLayout llNetError;

    private ListView listView;
    private EaseContactAdapter adapter;
    private ArrayList<User> listUsers = new ArrayList<>();
    private ProgressDialog pd;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_contact, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        //获取联系人
        pd = ProgressDialog.show(getContext(), "", "加载中...", false, true);
        mPresenter.getContact(SPUtil.getString(SPUtil.CITY_ID));
    }

    private void init() {
        listView = contactList.getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String depName = listUsers.get(position).getDepName();
                String name = listUsers.get(position).getEmpName();
                final String phone = listUsers.get(position).getEmployeePhone();

                StringBuffer sb = new StringBuffer();
                sb.append("部门：" + depName)
                        .append("\n")
                        .append("姓名：" + name)
                        .append("\n")
                        .append("电话：" + phone);
                new AlertDialog.Builder(getContext())
                        .setTitle("拨打电话")
                        .setMessage(sb)
                        .setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (TextUtils.isEmpty(phone)) {
                                    Toast.makeText(getContext(), "电话号码为空！", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Intent phoneIntent = new Intent(
                                        "android.intent.action.CALL", Uri.parse("tel:" + phone));
                                startActivity(phoneIntent);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create()
                        .show();
            }
        });

        adapter = new EaseContactAdapter(getContext(), R.layout.ease_row_contact, listUsers);
        listView.setAdapter(adapter);

        //init list
        contactList.getSidebar().setListView(listView);

        friendEtSearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 排序
     */
    private void sort() {
        Collections.sort(listUsers, new Comparator<User>() {

            @Override
            public int compare(User lhs, User rhs) {
                if (lhs.getInitialLetter().equals(rhs.getInitialLetter())) {
                    return lhs.getEmpName().compareTo(rhs.getEmpName());
                } else {
                    if ("#".equals(lhs.getInitialLetter())) {
                        return 1;
                    } else if ("#".equals(rhs.getInitialLetter())) {
                        return -1;
                    }
                    return lhs.getInitialLetter().compareTo(rhs.getInitialLetter());
                }
            }
        });
    }


    @Override
    protected ContactPresenterImpl createPresenter() {
        return new ContactPresenterImpl();
    }

    @Override
    public void onContactResult(List<User> users) {
        if (pd != null && pd.isShowing()) pd.dismiss();
        if (users == null) {
            llNetError.setVisibility(View.VISIBLE);
            return;
        }
        llNetError.setVisibility(View.GONE);
        listUsers.clear();
        listUsers.addAll(users);
        sort();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ll_net_error)
    public void onClick() {
        mPresenter.getContact(SPUtil.getString(SPUtil.CITY_ID));
    }
}

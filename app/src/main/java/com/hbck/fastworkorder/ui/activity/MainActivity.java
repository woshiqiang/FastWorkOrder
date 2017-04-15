package com.hbck.fastworkorder.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hbck.fastworkorder.R;
import com.hbck.fastworkorder.ui.fragment.ContactFragment;
import com.hbck.fastworkorder.ui.fragment.DeskFragment;
import com.hbck.fastworkorder.ui.fragment.MoreFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_rg)
    RadioGroup mainRg;
    @Bind(R.id.main_tv_title)
    TextView mainTvTitle;

    private Fragment[] fragments;
    private ContactFragment contactFragment;
    private DeskFragment deskFragment;
    private MoreFragment moreFragment;

    private int index;//记录点击的tab位置
    private int currentIndex = 1;//当前显示的tab位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        contactFragment = new ContactFragment();
        deskFragment = new DeskFragment();
        moreFragment = new MoreFragment();
        fragments = new Fragment[]{contactFragment, deskFragment, moreFragment};
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, deskFragment).show(deskFragment).commit();

        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_contact:
                        index = 0;
                        mainTvTitle.setText(getString(R.string.address_book));
                        break;
                    case R.id.rb_desk:
                        index = 1;
                        mainTvTitle.setText(getString(R.string.cloud_app));
                        break;
                    case R.id.rb_more:
                        index = 2;
                        mainTvTitle.setText(getString(R.string.more));
                        break;
                }

                showFragment(index);
            }
        });
    }


    /**
     * 切换fragment
     *
     * @param index：0-4
     */
    public void showFragment(int index) {
        if (currentIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fl_main, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        currentIndex = index;
    }
}

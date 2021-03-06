package com.nuoman.westernele.numberDetail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.projectmanage.ProjectManageDetailNewActivity;
import com.nuoman.westernelectric.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumberDetailActivity extends BaseActivity {

    @Bind(R.id.tl_detail_tab)
    TabLayout tl_detail_tab;
    @Bind(R.id.vp_detail)
    ViewPager vp_detail;
    @Bind(R.id.title_right_tv)
    TextView titleRightTv;
    @Bind(R.id.title_mid_tv)
    TextView titleMidTv;

    private NumberDetailAdapter numberDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_infomation_layout);
        ButterKnife.bind(this);
        initVariable();
        initView();
        bindListener();
        bindAdapter();
    }

    private void initVariable() {
        numberDetailAdapter = new NumberDetailAdapter(getSupportFragmentManager());
    }

    private void initView() {

        if (!TextUtils.isEmpty(getIntent().getStringExtra("title"))) {
            titleMidTv.setText(getIntent().getStringExtra("title"));
            titleRightTv.setVisibility(View.VISIBLE);
        }
    }

    private void bindListener() {

    }

    private void bindAdapter() {
        vp_detail.setAdapter(numberDetailAdapter);
        tl_detail_tab.setupWithViewPager(vp_detail, true);
    }

    @OnClick({R.id.title_left_tv, R.id.title_right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
            case R.id.title_right_tv:
                startActivity(new Intent(this, ProjectManageDetailNewActivity.class)
                        .putExtra("id", getIntent().getStringExtra("number")));
                break;
        }
    }

    class NumberDetailAdapter extends FragmentPagerAdapter {

        private List<String> title;
        private List<Fragment> fragments;


        NumberDetailAdapter(FragmentManager fm) {
            super(fm);
            initData();
        }

        private void initData() {
            title = new ArrayList<>();
            fragments = new ArrayList<>();
            title.add("进度图表");
            title.add("基本信息");
            fragments.add(ProgressChartFragment.newInstance
                    (getIntent().getStringExtra("number")));
            fragments.add(BasicInformationFragment.newInstance
                    (getIntent().getStringExtra("number")));
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }

}

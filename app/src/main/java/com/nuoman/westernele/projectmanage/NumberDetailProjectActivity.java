package com.nuoman.westernele.projectmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.numberDetail.view.ProgressChartFragment;
import com.nuoman.westernelectric.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumberDetailProjectActivity extends BaseActivity {

    @Bind(R.id.title_right_tv)
    TextView titleRightTv;
    @Bind(R.id.title_mid_tv)
    TextView titleMidTv;
    @Bind(R.id.container_layout)
    FrameLayout containerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_infomation_project_layout);
        ButterKnife.bind(this);
        initView();
        bindListener();
    }


    private void initView() {

        if (!TextUtils.isEmpty(getIntent().getStringExtra("title"))) {
            titleMidTv.setText(getIntent().getStringExtra("title"));
            titleRightTv.setVisibility(View.VISIBLE);
        }
    }

    private void bindListener() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container_layout, ProgressChartFragment.newInstance
                (getIntent().getStringExtra("number")));
        transaction.commit();
    }

    @OnClick({R.id.title_left_tv, R.id.title_right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
            case R.id.title_right_tv:
                Intent intent = new Intent(this, ProjectManageDetailNewActivity.class);
                intent.putExtra("id", getIntent().getStringExtra("number"));
                startActivity(intent);

                break;
        }
    }


}

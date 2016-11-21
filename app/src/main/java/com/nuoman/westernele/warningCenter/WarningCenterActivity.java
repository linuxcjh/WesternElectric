package com.nuoman.westernele.warningCenter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WarningCenterActivity extends BaseActivity {

    @Bind(R.id.title_mid_tv)
    TextView title_mid_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_center_layout);
        ButterKnife.bind(this);
        initVariable();
        initView();
    }

    private void initVariable() {

    }

    private void initView() {
        title_mid_tv.setText("预警信息");
    }


    @OnClick({R.id.title_left_tv})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
        }
    }
}

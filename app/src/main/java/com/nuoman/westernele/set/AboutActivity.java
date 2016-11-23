package com.nuoman.westernele.set;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity implements ICommonAction {


    CommonPresenter commonPresenter = new CommonPresenter(this);
    @Bind(R.id.title_left_tv)
    TextView titleLeftTv;
    @Bind(R.id.title_mid_tv)
    TextView titleMidTv;
    @Bind(R.id.title_right_tv)
    TextView titleRightTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_layout);
        ButterKnife.bind(this);
        initView();

    }


    private void initView() {
        titleMidTv.setText("关于");
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {

    }


    @OnClick({R.id.title_left_tv, R.id.title_mid_tv, R.id.title_right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
            case R.id.title_mid_tv:
                break;
            case R.id.title_right_tv:
                break;
        }
    }
}

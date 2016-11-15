package com.nuoman.westernele.numberDetail;

import android.os.Bundle;
import android.view.View;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumberDetailActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_infomation_layout);
        ButterKnife.bind(this);
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

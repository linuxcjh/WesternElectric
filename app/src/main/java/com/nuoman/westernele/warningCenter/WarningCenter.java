package com.nuoman.westernele.warningCenter;

import android.os.Bundle;
import android.view.View;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;

import butterknife.OnClick;

public class WarningCenter extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

package com.nuoman.westernele;

import android.os.Bundle;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.ICommonAction;

import java.util.Map;

import butterknife.ButterKnife;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements ICommonAction {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_layout);
        ButterKnife.bind(this);
    }


    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {

    }
}
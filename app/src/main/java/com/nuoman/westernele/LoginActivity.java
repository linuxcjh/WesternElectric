package com.nuoman.westernele;

import android.os.Bundle;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.model.BaseTransModel;

import java.util.Map;

import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements ICommonAction {


    private CommonPresenter commonPresenter;

    private BaseTransModel transModel = new BaseTransModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {

    }


    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {

    }
}

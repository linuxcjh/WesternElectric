package com.nuoman.westernele.set;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PwdActivity extends BaseActivity implements ICommonAction{


    @Bind(R.id.title_left_tv)
    TextView titleLeftTv;
    @Bind(R.id.title_mid_tv)
    TextView titleMidTv;
    @Bind(R.id.title_right_tv)
    TextView titleRightTv;
    @Bind(R.id.old_pwd_et)
    EditText oldPwdEt;
    @Bind(R.id.new_pwd_et)
    EditText newPwdEt;
    @Bind(R.id.confirm_pwd_et)
    EditText confirmPwdEt;
    @Bind(R.id.confirm_bt)
    Button confirmBt;

    CommonPresenter commonPresenter =new CommonPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_layout);
        ButterKnife.bind(this);
        initView();

    }


    private void initView() {

    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {

    }

    @OnClick({R.id.title_left_tv, R.id.confirm_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
            case R.id.confirm_bt:


                break;
        }
    }


}

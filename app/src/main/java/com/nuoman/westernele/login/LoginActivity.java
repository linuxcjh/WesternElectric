package com.nuoman.westernele.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nuoman.tabletattendance.R;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.NuoManConstant;
import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.home.MainTableActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements Contract.LoginView {

    @Bind(R.id.et_user_name)
    EditText et_user_name;

    @Bind(R.id.et_password)
    EditText et_password;

    private LoginPresenterImp loginPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        ButterKnife.bind(this);
        initVariable();
        initView();

    }

    private void initVariable() {
        loginPresenterImp = new LoginPresenterImp(this);
    }

    private void initView() {

    }

    @OnClick({R.id.btn_login})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginPresenterImp.login(et_user_name.getText().toString(), et_password.getText().toString());
                break;
        }
    }

    @Override
    public void jumpToMain() {
        Intent intent = new Intent(this, MainTableActivity.class);
        startActivity(intent);
        AppConfig.setBooleanConfig(NuoManConstant.IS_LOGIN, true);
        finish();
    }

    @Override
    public void loginError(String errorMsg) {
        Toast.makeText(AppConfig.getContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }
}

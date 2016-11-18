package com.nuoman.westernele.login;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.Md5AndBase64;
import com.nuoman.westernele.login.model.LoginParameter;
import com.nuoman.westernele.login.model.LoginReturn;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 登陆Presenter
 * Created by 杨小过 on 2016/11/18.
 */

public class LoginPresenterImp implements ICommonAction, Contract.LoginPresenter {

    private CommonPresenter commonPresenter;
    private Contract.LoginView mLoginView;

    public LoginPresenterImp(Contract.LoginView loginView) {
        mLoginView = loginView;
        commonPresenter = new CommonPresenter(this);
    }

    @Override
    public void login(String userName, String passWord) {
        if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(passWord)) {
            mLoginView.loginError("账户或密码为空");
            return;
        }

        LoginParameter loginParameter = new LoginParameter();
        try {
            loginParameter.setUserName(userName);
            loginParameter.setPassword(Md5AndBase64.encrypt(passWord));
            loginParameter.setPhoneType("1");
            commonPresenter.invokeInterfaceObtainData(true, "appUserCtrl", NuoManService.LOGIN,
                    loginParameter, new TypeToken<LoginReturn>() {
                    });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.LOGIN:
                LoginReturn loginReturn = (LoginReturn) data;
                if (status == 1) {
//                    AppTools.setUser(loginReturn.getData());
                    Log.d("test", loginReturn.getData().getAppVersion());
                    mLoginView.jumpToMain();
                } else {
                    mLoginView.loginError("服务器出错");
                }
                break;
        }
    }
}

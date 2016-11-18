package com.nuoman.westernele.login;

/**
 *  契约类
 * Created by 杨小过 on 2016/11/18.
 */

public interface Contract {

    interface LoginView{
        void showLoading();
        void jumpToMain();
        void loginError(String errorMsg);
    }

    interface LoginPresenter{
        void login(String userName,String passWord);
    }

}

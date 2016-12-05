package com.nuoman.westernele.login;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.common.utils.Md5AndBase64;
import com.nuoman.westernele.common.utils.PushUtil;
import com.nuoman.westernele.login.model.CompanyInfoModel;
import com.nuoman.westernele.login.model.LoginParameter;
import com.nuoman.westernele.login.model.UploadDeviceTokenParameter;
import com.nuoman.westernele.login.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * 登陆Presenter
 * Created by 杨小过 on 2016/11/18.
 */

class LoginPresenterImp implements ICommonAction, Contract.LoginPresenter {

    /**
     * 选择子公司
     */
    public static final int SELECT_SUB_COMPANY_INDEX = 1;

    private CommonPresenter commonPresenter;
    private Contract.LoginView mLoginView;

    LoginPresenterImp(Contract.LoginView loginView) {
        mLoginView = loginView;
        commonPresenter = new CommonPresenter(this);
    }

    /**
     * 登录操作
     *
     * @param userName 用户名
     * @param passWord 用户密码
     */
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
                    loginParameter, new TypeToken<User>() {
                    });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将选择的子公司名字存到reference中
     *
     * @param selectedSubCompany 选中的子公司名
     */
    @Override
    public void saveSelectedSubCompany(String selectedSubCompany) {
        AppConfig.setStringConfig("subCompanyName", selectedSubCompany);
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.LOGIN:
                if (status == 1) {
                    User user = (User) data;
                    AppTools.saveUser(user);
                    uploadDeviceToken();
                    if (!user.getRoleId().equals("5")) {
                        List<CompanyInfoModel> subCompanies = user.getSubcompany();
                        String[] subCompanyNames = new String[subCompanies.size()];
                        for (int i = 0; i < subCompanyNames.length; i++) {
                            subCompanyNames[i] = subCompanies.get(i).getDataName();
                        }

                        mLoginView.showSubCompanyDialog(subCompanyNames);

                    } else {
                        AppConfig.setStringConfig("subCompanyName", "变压器服务平台");
                        mLoginView.jumpToMain();
                    }
                }
                break;
            case NuoManService.UPLOAD_DEVICE_TOKEN:
                if (status == 1) {
                    Log.d("push", "上传deviceToken成功");
                } else {
                    Log.e("push", "上传deviceToken失败");
                }
                break;
        }
    }

    private void uploadDeviceToken() {
        UploadDeviceTokenParameter uploadDeviceTokenParameter = new UploadDeviceTokenParameter();
        uploadDeviceTokenParameter.setUserId(AppTools.getUser().getUserId());
        uploadDeviceTokenParameter.setDeviceType("1");
        uploadDeviceTokenParameter.setDeviceToken(PushUtil.getDeviceToken());
        if (TextUtils.isEmpty(PushUtil.getDeviceToken()))
            Log.e("push", "获取deviceToken失败");
        commonPresenter.invokeInterfaceObtainData(true, "appUserCtrl", NuoManService.UPLOAD_DEVICE_TOKEN,
                uploadDeviceTokenParameter, null);
    }
}

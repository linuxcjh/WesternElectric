package com.nuoman.westernele.set;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.common.utils.Md5AndBase64;
import com.nuoman.westernele.model.BaseTransModel;
import com.nuoman.westernelectric.R;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PwdActivity extends BaseActivity implements ICommonAction {


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

    CommonPresenter commonPresenter = new CommonPresenter(this);

    private BaseTransModel transModel = new BaseTransModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_layout);
        ButterKnife.bind(this);
        initView();

    }


    private void initView() {

    }


    /**
     * 调用方法获取数据
     */
    public void invoke(BaseTransModel transModel) {
        transModel.setUserId(AppTools.getUser().getUserId());
        commonPresenter.invokeInterfaceObtainData(true, "appUserCtrl", NuoManService.CHECKPWD, transModel, new TypeToken<BaseTransModel>() {
        });
    }

    public void invokeChangePwd(BaseTransModel transModel) {
        transModel.setUserId(AppTools.getUser().getUserId());
        commonPresenter.invokeInterfaceObtainData(true, "appUserCtrl", NuoManService.CHANGEPWD, transModel, new TypeToken<BaseTransModel>() {
        });
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {


        switch (methodIndex) {
            case NuoManService.CHANGEPWD:
                if (status == 1) {
                    AppTools.getToast("修改成功");
                    finish();

                }

                    break;
            case NuoManService.CHECKPWD:

                if (status == 1) {

                    if (TextUtils.isEmpty(newPwdEt.getText().toString()) || TextUtils.isEmpty(confirmPwdEt.getText().toString())
                            ) {
                        AppTools.getToast("新密码或确认密码不能为空");
                        return;

                    }
                    if (newPwdEt.getText().toString().equals(confirmPwdEt.getText().toString())) {
                        try {
                            BaseTransModel changePwd = new BaseTransModel();
                            changePwd.setNewPwd(Md5AndBase64.encrypt(confirmPwdEt.getText().toString()));
                            invokeChangePwd(changePwd);

                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }

                    } else {
                        AppTools.getToast("新密码和确认密码不一致");
                    }

                }
                break;
        }
    }

    @OnClick({R.id.title_left_tv, R.id.confirm_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
            case R.id.confirm_bt:

                if (TextUtils.isEmpty(oldPwdEt.getText().toString())) {
                    AppTools.getToast("请填写账户密码");
                    return;
                }
                try {
                    BaseTransModel checkPwd = new BaseTransModel();
                    checkPwd.setUserPwd(Md5AndBase64.encrypt(oldPwdEt.getText().toString()));
                    invoke(checkPwd);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                break;
        }
    }


}

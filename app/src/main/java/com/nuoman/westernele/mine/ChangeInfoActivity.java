package com.nuoman.westernele.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.nuoman.westernele.mine.model.UserInfo;
import com.nuoman.westernele.model.BaseTransModel;
import com.nuoman.westernelectric.R;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeInfoActivity extends BaseActivity implements ICommonAction {


    @Bind(R.id.title_left_tv)
    TextView titleLeftTv;
    @Bind(R.id.title_mid_tv)
    TextView titleMidTv;
    @Bind(R.id.title_right_tv)
    TextView titleRightTv;
    @Bind(R.id.name_tv)
    EditText nameTv;
    @Bind(R.id.company_name_tv)
    EditText companyNameTv;
    @Bind(R.id.dep_name_tv)
    EditText depNameTv;
    @Bind(R.id.position_tv)
    EditText positionTv;
    @Bind(R.id.phone_tv)
    EditText phoneTv;
    @Bind(R.id.email_tv)
    EditText emailTv;
    @Bind(R.id.confirm_bt)
    Button confirmBt;

    private CommonPresenter commonPresenter = new CommonPresenter(this);
    private BaseTransModel transModel = new BaseTransModel();
    private UserInfo info = new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info_layout);
        ButterKnife.bind(this);
        initView();

    }


    private void initView() {
        titleMidTv.setText("信息编辑");
        info = (UserInfo) getIntent().getSerializableExtra("model");
        nameTv.setText(info.getUserName());
        positionTv.setText(info.getJob());
        depNameTv.setText(info.getDepartment());
        phoneTv.setText(info.getUserTel());
        emailTv.setText(info.getMailBox());
        companyNameTv.setText(info.getCompany());
        if (AppTools.getUser().getRoleId().equals("5")) {
            companyNameTv.setBackgroundColor(Color.parseColor("#d4d4d4"));
            companyNameTv.setEnabled(false);
        } else {
            depNameTv.setBackgroundColor(Color.parseColor("#d4d4d4"));
            depNameTv.setEnabled(false);
        }
    }

    /**
     * 调用方法获取数据
     */
    public void invoke() {
        transModel.setUserId(AppTools.getUser().getUserId());
        commonPresenter.invokeInterfaceObtainData(true, "appUserCtrl", NuoManService.SAVEACCOUNTINFODETAIL, info, new TypeToken<UserInfo>() {
        });
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {


        if (status == 1) {
            AppTools.getToast("修改成功");
            setResult(RESULT_OK, new Intent());
            finish();
        } else {
            AppTools.getToast("修改失败");

        }

    }


    @OnClick({R.id.title_left_tv, R.id.title_mid_tv, R.id.title_right_tv, R.id.confirm_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_tv:
                finish();
                break;
            case R.id.title_mid_tv:
                break;
            case R.id.title_right_tv:
                break;
            case R.id.confirm_bt:
                info.setUserName(nameTv.getText().toString());
                info.setJob(positionTv.getText().toString());
                info.setCompany(companyNameTv.getText().toString());
                info.setDepartment(depNameTv.getText().toString());
                info.setUserTel(phoneTv.getText().toString());
                info.setMailBox(emailTv.getText().toString());
                invoke();
                break;
        }
    }

}

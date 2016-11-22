package com.nuoman.westernele.numberDetail.presenter;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.numberDetail.Contract;
import com.nuoman.westernele.numberDetail.model.BasicInformation;
import com.nuoman.westernele.numberDetail.model.BasicInformationParameter;

import java.util.Map;

/**
 * 基本信息presenter
 * Created by 杨小过 on 2016/11/22.
 */

public class BasicInformationPresenterImp implements Contract.BasicInformationPresenter, ICommonAction {

    private Contract.BasicInformationView mBasicInformationView;
    private CommonPresenter commonPresenter;

    public BasicInformationPresenterImp(Contract.BasicInformationView basicInformationView) {
        mBasicInformationView = basicInformationView;
        commonPresenter = new CommonPresenter(this);
    }

    @Override
    public void requestBasicInformation(String number) {
        BasicInformationParameter basicInformationParameter = new BasicInformationParameter();
        basicInformationParameter.setOrderId(number);
        commonPresenter.invokeInterfaceObtainData(true, "appNodeCtrl", NuoManService.NUMBER_BASIC_INFORMATION,
                basicInformationParameter, new TypeToken<BasicInformation>() {
                });
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.NUMBER_BASIC_INFORMATION:
                if (status == 1) {
                    BasicInformation basicInformation = (BasicInformation) data;
                    mBasicInformationView.setView(basicInformation);
                }
                break;
        }
    }
}

package com.nuoman.westernele.numberQuery;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.numberQuery.model.Number;
import com.nuoman.westernele.numberQuery.model.NumberQueryParameter;

import java.util.List;
import java.util.Map;

/**
 * numberQuery的presenter
 * Created by 杨小过 on 2016/11/14.
 */

public class NumberQueryPresenterImp implements Contract.NumberQueryPresenter, ICommonAction {

    private Contract.NumberQueryView mNumberQueryView;
    private CommonPresenter commonPresenter;

    public NumberQueryPresenterImp(Contract.NumberQueryView numberQueryView) {
        mNumberQueryView = numberQueryView;
        commonPresenter = new CommonPresenter(this);
    }

    @Override
    public void queryNumber(String number) {
        if (!TextUtils.isEmpty(number)) {
            NumberQueryParameter numberQueryParameter = new NumberQueryParameter();
            numberQueryParameter.setOrderNo(number);
            numberQueryParameter.setUserId(AppTools.getUser().getUserId());
            commonPresenter.invokeInterfaceObtainData(true, "appNodeCtrl",
                    NuoManService.NUMBER_QUERY, numberQueryParameter,
                    new TypeToken<List<Number>>() {
                    });
        } else {
            mNumberQueryView.showNotification("请输入工号");
        }
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        if (status == 1) {
            @SuppressWarnings("unchecked")
            List<Number> numbers = (List<Number>) data;
            mNumberQueryView.refreshNumber(numbers);
        }
    }
}

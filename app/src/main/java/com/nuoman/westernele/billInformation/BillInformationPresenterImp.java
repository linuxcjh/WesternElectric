package com.nuoman.westernele.billInformation;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.billInformation.model.BillInformation;
import com.nuoman.westernele.billInformation.model.BillInformationParameter;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppTools;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * BillInformationView的presenter类
 * Created by 杨小过 on 2016/11/21.
 */

public class BillInformationPresenterImp implements Contract.BillInformationPresenter, ICommonAction {

    private Contract.BillInformationView mbillInformationView;

    private CommonPresenter commonPresenter;
    private int nowPage = 0;

    public BillInformationPresenterImp(Contract.BillInformationView billInformationView) {
        mbillInformationView = billInformationView;
        commonPresenter = new CommonPresenter(this);
    }


    /**
     * 获取billInformation信息
     *
     * @param type 0是刷新，1是加载更多
     */
    @Override
    public void requestBillInformation(int type) {
        BillInformationParameter billInformationParameter = new BillInformationParameter();
        billInformationParameter.setUserId(AppTools.getUser().getUserId());
        if (type == 1) {
            billInformationParameter.setPages(String.valueOf(nowPage + 1));
        } else {
            billInformationParameter.setPages("0");
        }
        commonPresenter.invokeInterfaceObtainData(true, "appAccountInfoCtrl", "GetAccountInfoByPage",
                billInformationParameter, new TypeToken<List<BillInformation>>() {
                });
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case "GetAccountInfoByPage":
                if (status == 1) {
                    @SuppressWarnings("unchecked")
                    List<BillInformation> billInformationList = (List<BillInformation>) data;
                    Set<Map.Entry<String, String>> mapEntries = parameterMap.entrySet();
                    for (Map.Entry<String, String> entry : mapEntries) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (key.equals("pages"))
                            nowPage = Integer.parseInt(value);
                    }
                    if (nowPage == 0) {
                        mbillInformationView.refreshInformation(billInformationList);
                    } else {
                        mbillInformationView.loadMoreInformation(billInformationList);
                    }

                }
                break;
        }
    }
}

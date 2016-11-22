package com.nuoman.westernele.warningCenter;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.warningCenter.model.WarningInformation;
import com.nuoman.westernele.warningCenter.model.WarningParameter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 预警中心Presenter
 * Created by 杨小过 on 2016/11/21.
 */

public class WarningCenterPresenterImp implements Contract.WarningCenterPresenter, ICommonAction {

    private Contract.WarningCenterView mWarningCenterView;
    private CommonPresenter commonPresenter;
    private int nowPage = 0;

    public WarningCenterPresenterImp(Contract.WarningCenterView warningCenterView) {
        mWarningCenterView = warningCenterView;
        commonPresenter = new CommonPresenter(this);
    }

    /**
     * 获取预警信息
     *
     * @param type 0刷新列表，1添加列表
     */
    @Override
    public void requestWarningInformation(int type) {
        WarningParameter warningParameter = new WarningParameter();
        warningParameter.setUserId(AppTools.getUser().getUserId());
        if (type == 1) {
            warningParameter.setPages(String.valueOf(nowPage + 1));
        } else {
            warningParameter.setPages("0");
        }
        commonPresenter.invokeInterfaceObtainData(true, "appAlertInfoCtrl", NuoManService.WARNING_CENTER,
                warningParameter, new TypeToken<List<WarningInformation>>() {
                });
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.WARNING_CENTER:
                if (status == 1) {
                    @SuppressWarnings("unchecked")
                    List<WarningInformation> warningInformationList = (List<WarningInformation>) data;
                    Set<Map.Entry<String,String>> mapEntry=parameterMap.entrySet();
                    for (Map.Entry<String, String> entry : mapEntry) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (key.equals("pages"))
                            nowPage = Integer.parseInt(value);
                    }
                    if (nowPage == 0) {
                        mWarningCenterView.refreshInformation(warningInformationList);
                    } else {
                        mWarningCenterView.loadMoreInformation(warningInformationList);
                    }
                }
                break;
        }
    }
}

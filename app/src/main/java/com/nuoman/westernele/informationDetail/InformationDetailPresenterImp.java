package com.nuoman.westernele.informationDetail;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.informationDetail.model.CheckParameter;
import com.nuoman.westernele.informationDetail.model.InformationDetail;
import com.nuoman.westernele.informationDetail.model.InformationDetailParameter;

import java.util.Map;
import java.util.Set;

/**
 * 项目审核presenter
 * Created by 杨小过 on 2016/11/23.
 */

public class InformationDetailPresenterImp implements Contract.InformationDetailPresenter
        , ICommonAction {

    private Contract.InformationDetailView mInformationDetailView;
    private CommonPresenter commonPresenter;

    public InformationDetailPresenterImp(Contract.InformationDetailView informationDetailView) {
        mInformationDetailView = informationDetailView;
        commonPresenter = new CommonPresenter(this);
    }

    @Override
    public void requestInformationDetail(String nodeId) {
        InformationDetailParameter informationDetailParameter = new InformationDetailParameter();
        informationDetailParameter.setUserId(AppTools.getUser().getUserId());
        informationDetailParameter.setNodeId(nodeId);
        commonPresenter.invokeInterfaceObtainData(true, "appNodeCtrl", NuoManService.RELEASE_INFORMATION_DETAIL
                , informationDetailParameter, new TypeToken<InformationDetail>() {
                });
    }

    @Override
    public void requestCheck(String nodeId, String oper) {
        CheckParameter checkParameter = new CheckParameter();
        checkParameter.setNodeId(nodeId);
        checkParameter.setUserId(AppTools.getUser().getUserId());
        checkParameter.setOper(oper);
        commonPresenter.invokeInterfaceObtainData(true, "appNodeCtrl", NuoManService.RELEASE_INFORMATION_CHECK
                , checkParameter, null);
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        if (status == 1) {
            switch (methodIndex) {
                case NuoManService.RELEASE_INFORMATION_DETAIL:
                    InformationDetail informationDetail = (InformationDetail) data;
                    mInformationDetailView.formatView(informationDetail);
                    break;
                case NuoManService.RELEASE_INFORMATION_CHECK:
                    String oper = null;
                    Set<Map.Entry<String, String>> entries = parameterMap.entrySet();
                    for (Map.Entry<String, String> entry : entries) {
                        if (entry.getKey().equals("oper"))
                            oper = entry.getValue();
                    }
                    mInformationDetailView.checkFinish(oper);
                    break;
            }
        }
    }
}

package com.nuoman.westernele.informationRelease;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.informationRelease.model.ReleaseInformation;
import com.nuoman.westernele.informationRelease.model.ReleaseInformationParameter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 信息发布presenter
 * Created by 杨小过 on 2016/11/22.
 */

public class InformationReleasePresenterImp implements Contract.InformationReleasePresenter, ICommonAction {

    private Contract.InformationReleaseView mInformationReleaseView;
    private CommonPresenter commonPresenter;

    private int nowPage = 0;

    public InformationReleasePresenterImp(Contract.InformationReleaseView informationReleaseView) {
        mInformationReleaseView = informationReleaseView;
        commonPresenter = new CommonPresenter(this);
    }

    /**
     * 获取发布信息
     *
     * @param type 0：刷新，1：加载更多
     */
    @Override
    public void requestInformation(int type) {
        ReleaseInformationParameter releaseInformationParameter = new ReleaseInformationParameter();
        releaseInformationParameter.setUserId(AppTools.getUser().getUserId());
        if (type == 0) {
            releaseInformationParameter.setPages("0");
        } else {
            releaseInformationParameter.setPages(String.valueOf(nowPage + 1));
        }
        commonPresenter.invokeInterfaceObtainData(true, "appProjectCtrl", NuoManService.RELEASE_INFORMATION_LIST,
                releaseInformationParameter, new TypeToken<List<ReleaseInformation>>() {
                });
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        if (status == 1) {

            @SuppressWarnings("unchecked")
            List<ReleaseInformation> releaseInformationList = (List<ReleaseInformation>) data;

            Set<Map.Entry<String, String>> entries = parameterMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                if (entry.getKey().equals("pages"))
                    nowPage = Integer.parseInt(entry.getValue());
            }
            if (nowPage == 0) {
                mInformationReleaseView.refreshInformation(releaseInformationList);
            } else {
                mInformationReleaseView.loadMoreInformation(releaseInformationList);
            }
        }

    }
}

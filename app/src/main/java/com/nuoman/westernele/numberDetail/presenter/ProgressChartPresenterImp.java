package com.nuoman.westernele.numberDetail.presenter;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.numberDetail.Contract;
import com.nuoman.westernele.numberDetail.model.ProgressChart;
import com.nuoman.westernele.numberDetail.model.ProgressChartParameter;

import java.util.Map;

/**
 * 进度表的presenter
 * Created by 杨小过 on 2016/11/24.
 */

public class ProgressChartPresenterImp implements Contract.ProgressChartPresenter, ICommonAction {

    private Contract.ProgressChartView mProgressChartView;
    private CommonPresenter commonPresenter;
    private int nowPage = 0;

    public ProgressChartPresenterImp(Contract.ProgressChartView progressChartView) {
        this.mProgressChartView = progressChartView;
        commonPresenter = new CommonPresenter(this);
    }

    @Override
    public void requestProgressChart(String orderId) {
        ProgressChartParameter progressChartParameter = new ProgressChartParameter();
        progressChartParameter.setOrderId(orderId);
        progressChartParameter.setUserId(AppTools.getUser().getUserId());
        commonPresenter.invokeInterfaceObtainData(true, "appNodeCtrl", NuoManService.PROGRESS_CHART,
                progressChartParameter, new TypeToken<ProgressChart>() {
                });
    }

    @Override
    public void nextPage() {
        if (nowPage == 3) {
            mProgressChartView.setNodeDetail(0);
            nowPage = 0;
        } else {
            mProgressChartView.setNodeDetail(nowPage + 1);
            nowPage = nowPage + 1;
        }
    }

    @Override
    public void lastPage() {
        if (nowPage == 0) {
            mProgressChartView.setNodeDetail(3);
            nowPage = 3;
        } else {
            mProgressChartView.setNodeDetail(nowPage - 1);
            nowPage = nowPage - 1;
        }
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        if (status == 1) {
            switch (methodIndex) {
                case NuoManService.PROGRESS_CHART:
                    ProgressChart progressChart = (ProgressChart) data;
                    mProgressChartView.setView(progressChart);
                    break;
            }
        }
    }
}

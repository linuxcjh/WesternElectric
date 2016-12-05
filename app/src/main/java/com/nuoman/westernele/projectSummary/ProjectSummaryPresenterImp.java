package com.nuoman.westernele.projectSummary;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.projectSummary.model.ProjectData;
import com.nuoman.westernele.projectSummary.model.ProjectSummaryParameter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 项目汇总界面的presenter
 * Created by 杨小过 on 2016/11/28.
 */

public class ProjectSummaryPresenterImp implements Contract.ProjectSummaryPresenter, ICommonAction {

    private Contract.ProjectSummaryView mProjectSummaryView;
    private CommonPresenter commonPresenter;
    private int nowPage;

    public ProjectSummaryPresenterImp(Contract.ProjectSummaryView projectSummaryView) {
        mProjectSummaryView = projectSummaryView;
        commonPresenter = new CommonPresenter(this);
    }

    @Override
    public void requestProject(int kind, int type) {
        ProjectSummaryParameter projectSummaryParameter = new ProjectSummaryParameter();
        projectSummaryParameter.setUserId(AppTools.getUser().getUserId());
        projectSummaryParameter.setKind(String.valueOf(kind));
        switch (type) {
            case 0:
                projectSummaryParameter.setPages("0");
                break;
            case 1:
                projectSummaryParameter.setPages(String.valueOf(nowPage + 1));
                break;
            default:
                break;
        }
        commonPresenter.invokeInterfaceObtainData(true, "appUserCtrl", NuoManService.PROJECT_SUMMARY,
                projectSummaryParameter, new TypeToken<List<ProjectData>>() {
                });
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.PROJECT_SUMMARY:
                if (status == 1) {
                    @SuppressWarnings("unchecked")
                    List<ProjectData> projects = (List<ProjectData>) data;
                    Set<Map.Entry<String, String>> parameters = parameterMap.entrySet();
                    for (Map.Entry<String, String> parameter : parameters) {
                        if (parameter.getKey().equals("pages"))
                            nowPage = Integer.parseInt(parameter.getValue());
                    }
                    if (nowPage == 0) {
                        mProjectSummaryView.refreshList(projects);
                    } else {
                        mProjectSummaryView.loadMoreList(projects);
                    }

                }
                break;
        }
    }
}

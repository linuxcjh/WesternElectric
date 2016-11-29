package com.nuoman.westernele.projectSummary;

/**
 * 项目汇总界面的presenter
 * Created by 杨小过 on 2016/11/28.
 */

public class ProjectSummaryPresenterImp implements Contract.ProjectSummaryPresenter {

    private Contract.ProjectSummaryView mProjectSummaryView;

    public ProjectSummaryPresenterImp(Contract.ProjectSummaryView projectSummaryView) {
        mProjectSummaryView = projectSummaryView;
    }
}

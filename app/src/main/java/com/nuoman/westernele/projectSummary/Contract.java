package com.nuoman.westernele.projectSummary;

import com.nuoman.westernele.projectSummary.model.ProjectData;

import java.util.List;

/**
 * 项目汇总契约类
 * Created by 杨小过 on 2016/11/28.
 */

public interface Contract {

    interface ProjectSummaryView {
        void refreshList(List<ProjectData> projects);

        void loadMoreList(List<ProjectData> projects);
    }

    interface ProjectSummaryPresenter {
        void requestProject(int kind, int type);
    }

}

package com.nuoman.westernele.projectSummary.model;

/**
 * 项目汇总实体类
 * Created by 杨小过 on 2016/12/5.
 */

public class ProjectData {

    /*
    {
        "fdate":"2016-11-22",
        "isCollected":"",
        "projectId":"2",
        "projectManager":"",
        "projectName":"新测试项目",
        "projectPic":""
    }
     */

    private String fdate;
    private String isCollected;
    private String projectId;
    private String projectManager;
    private String projectName;
    private String projectPic;

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(String isCollected) {
        this.isCollected = isCollected;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPic() {
        return projectPic;
    }

    public void setProjectPic(String projectPic) {
        this.projectPic = projectPic;
    }
}

package com.nuoman.westernele.projectmanage.model;

import org.json.*;


public class ProjectModel {
	
    private String fdate;
    private String projectId;
    private String projectManager;
    private String isCollected;
    private String projectName;
    private String projectPic;
    
    
	public ProjectModel () {
		
	}	
        
    public ProjectModel (JSONObject json) {
    
        this.fdate = json.optString("fdate");
        this.projectId = json.optString("projectId");
        this.projectManager = json.optString("projectManager");
        this.isCollected = json.optString("isCollected");
        this.projectName = json.optString("projectName");
        this.projectPic = json.optString("projectPic");

    }
    
    public String getFdate() {
        return this.fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectManager() {
        return this.projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getIsCollected() {
        return this.isCollected;
    }

    public void setIsCollected(String isCollected) {
        this.isCollected = isCollected;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPic() {
        return this.projectPic;
    }

    public void setProjectPic(String projectPic) {
        this.projectPic = projectPic;
    }


    
}

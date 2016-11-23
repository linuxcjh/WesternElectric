package com.nuoman.westernele.home.model;

import org.json.*;


public class MainModel {
	
    private String hasNewAlertInfo;
    private String noticeInfo;
    private String finished;
    private String hasNewAccountInfo;
    private String producing;
    private String unStart;
    
    
	public MainModel () {
		
	}	
        
    public MainModel (JSONObject json) {
    
        this.hasNewAlertInfo = json.optString("hasNewAlertInfo");
        this.noticeInfo = json.optString("noticeInfo");
        this.finished = json.optString("finished");
        this.hasNewAccountInfo = json.optString("hasNewAccountInfo");
        this.producing = json.optString("producing");
        this.unStart = json.optString("unStart");

    }
    
    public String getHasNewAlertInfo() {
        return this.hasNewAlertInfo;
    }

    public void setHasNewAlertInfo(String hasNewAlertInfo) {
        this.hasNewAlertInfo = hasNewAlertInfo;
    }

    public String getNoticeInfo() {
        return this.noticeInfo;
    }

    public void setNoticeInfo(String noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public String getFinished() {
        return this.finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getHasNewAccountInfo() {
        return this.hasNewAccountInfo;
    }

    public void setHasNewAccountInfo(String hasNewAccountInfo) {
        this.hasNewAccountInfo = hasNewAccountInfo;
    }

    public String getProducing() {
        return this.producing;
    }

    public void setProducing(String producing) {
        this.producing = producing;
    }

    public String getUnStart() {
        return this.unStart;
    }

    public void setUnStart(String unStart) {
        this.unStart = unStart;
    }


    
}

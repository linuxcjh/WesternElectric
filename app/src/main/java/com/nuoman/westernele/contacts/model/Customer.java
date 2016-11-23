package com.nuoman.westernele.contacts.model;

import org.json.*;


public class Customer {
	
    private String firstName;
    private String tel;
    private String company;
    private String fullName;
    private String job;
    
    
	public Customer () {
		
	}	
        
    public Customer (JSONObject json) {
    
        this.firstName = json.optString("firstName");
        this.tel = json.optString("tel");
        this.company = json.optString("company");
        this.fullName = json.optString("fullName");
        this.job = json.optString("job");

    }
    
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    
}

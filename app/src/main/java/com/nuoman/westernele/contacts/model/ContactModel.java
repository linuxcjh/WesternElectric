package com.nuoman.westernele.contacts.model;

import java.util.ArrayList;

public class ContactModel {
	
    private ArrayList<Customer> mate;
    private ArrayList<Customer> customer;
    
    
	public ContactModel () {
		
	}	
        

    public ArrayList<Customer> getMate() {
        return this.mate;
    }

    public void setMate(ArrayList<Customer> mate) {
        this.mate = mate;
    }

    public ArrayList<Customer> getCustomer() {
        return this.customer;
    }

    public void setCustomer(ArrayList<Customer> customer) {
        this.customer = customer;
    }


    
}

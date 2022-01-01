package io.drivers_app.my_app.domain;

import java.util.ArrayList;
import java.util.Scanner;

import io.drivers_app.my_app.operations.DriverOperations;

public class Driver extends Person {

    private String nationalID;
    public ArrayList<String> favArea;
    public ArrayList<Trip> availableTrips;
    public double avgRate;
    public ArrayList<Trip> historyList;
    public Trip currTrip = null;
    
    public DriverOperations driverOperations = new DriverOperations(this);
    
    public Driver() {}
    
    public Driver(String userName, String pass, String mobile)
    {
    	super(userName, pass, mobile);
    	favArea = new ArrayList<>();
    	availableTrips = new ArrayList<>();
    	historyList = new ArrayList<>();
    	avgRate = 0;
    }
    
    
    
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    private String license;

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    

	@Override
	public String toString() {
		return "Driver [nationalID=" + nationalID + ", license=" + license + ", getUserName()=" + getUsername()
				+ ", getEmail()=" + getEmail() + "]";
	}
    
    
    
}

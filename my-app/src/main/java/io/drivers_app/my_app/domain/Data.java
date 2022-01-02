package io.drivers_app.my_app.domain;

import java.util.ArrayList;

import io.drivers_app.my_app.operations.DataOperations;

public class Data {
	
    public static ArrayList<Person> userList;
    public static ArrayList<Driver> pendingDrivers;
    public static ArrayList<Person> suspended;
   
    public static ArrayList<Trip> tripList;
    public static ArrayList<Driver> driverList;
    
    public static ArrayList<String> destAreaWithDiscount;
    
    public static Data instance = null;
    public Person currUser = null;
    
    public DataOperations dataOperation = new DataOperations(this);
    
    private Data()
    {
    	userList = new ArrayList<>();
    	pendingDrivers = new ArrayList<>();
    	suspended = new ArrayList<>();
    	tripList = new ArrayList<>();
		driverList = new ArrayList<>();
		destAreaWithDiscount = new ArrayList<>();
		
    	Admin defaultAdmin = new Admin("admin", "admin", "011000000");
    	currUser = defaultAdmin;
    	dataOperation.addUser(defaultAdmin);

    }
    
    
    
    




    public static Data getInstance()
    {
    	if(instance == null)
		{
    		instance = new Data();
		}
		
		return instance;
    }

    
	
}
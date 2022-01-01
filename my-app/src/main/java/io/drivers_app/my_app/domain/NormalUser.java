package io.drivers_app.my_app.domain;

import java.util.ArrayList;
import java.util.Scanner;

import io.drivers_app.my_app.operations.UserOperations;

public class NormalUser extends Person{
	
	public ArrayList<Trip> offersList;
	public ArrayList<Trip> historyList;
	public Trip currTrip;
	public UserOperations userOperation = new UserOperations(this);
	
	
	public NormalUser() {}
	
	public NormalUser(String userName, String pass, String mobile)
	{
		super(userName, pass, mobile);
		
		offersList = new ArrayList<>();
		historyList = new ArrayList<>();
		currTrip = null;
	}

	@Override
	public String toString() {
		return "NormalUser [getUsername()=" + getUsername() + ", getEmail()=" + getEmail() + "]";
	}
	
	
	
	
	
}

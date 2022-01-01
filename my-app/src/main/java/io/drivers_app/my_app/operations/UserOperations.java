package io.drivers_app.my_app.operations;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.drivers_app.my_app.domain.Data;
import io.drivers_app.my_app.domain.NormalUser;
import io.drivers_app.my_app.domain.Trip;


@Component
public class UserOperations {
	
	NormalUser user;
	
	public UserOperations() {}
	
	public UserOperations(NormalUser user)
	{
		this.user = user;
	}
	
	public void showOffersList()
	{
		Trip tmp = null;
		System.out.println("Offers List");
		for(int i=0; i<user.offersList.size(); i++)
		{
			tmp = user.offersList.get(i);
			System.out.print((i+1) + "- ");
			System.out.print("driver Name: " + tmp.driver.getUsername());
			System.out.println("  driver Rate: " + tmp.driver.avgRate);
			System.out.println("  Trip Price: " + tmp.getPrice());
		}	
		
	}
	
	public void addToOfferList(Trip trip)
	{
		user.offersList.add(trip);
	}
	
	public void showHistoryList()
	{
		Trip tmp = null;
		System.out.println("History List");
		for(int i=0; i<user.historyList.size(); i++)
		{
			tmp = user.historyList.get(i);
			System.out.print((i+1) + "- ");
			System.out.println("source: " + tmp.source + " destination: " + tmp.destination);
			System.out.println("driver Name: " + tmp.driver.getUsername());
			System.out.println("  driver Rate: " + tmp.driver.avgRate);
			System.out.println("  Trip Price: " + tmp.getPrice());
		}	
	}
	
	private void addToHistory(Trip trip)
	{
		user.historyList.add(trip);
	}
	
	public void acceptOffer(Trip trip)
	{
		trip.driver.currTrip = trip;
		Data.getInstance().deleteTrip(user.currTrip);
		user.currTrip = trip;
		
		user.offersList.clear();
	}
	
	public void requestTrip(String src, String dest)
	{
		user.currTrip = new Trip(src, dest);
		user.currTrip.setUser(user);
		Data.getInstance().addTrip(user.currTrip);
	}
	
	public void finishTrip()
	{
		if(user.currTrip.driver != null)
		{
			int rate = 0;
			/*
			Scanner scan = new Scanner(System.in);
			while(true)
			{
				System.out.println("rate driver from (1-5)");
				rate = scan.nextInt();
				
				if(rate>=1 && rate <=5)
				{
					break;
				}
			}*/
			
			user.currTrip.setRate(rate);
			user.currTrip.setStatus(true);
			
			user.currTrip.driver.driverOperations.updateAvgRate();
			
			addToHistory(user.currTrip);
			user.currTrip.driver.driverOperations.addToHistory(new Trip(user.currTrip));
			
			System.out.println(user.currTrip.toString());
			
			user.currTrip.driver.currTrip = null;
			user.currTrip = null;
		}
		else
		{
			System.out.println("this ride didn't assign to any driver yet");
		}
		
	}


	@Override
	public String toString() {
		return "NormalUser [getUserName()=" + user.getUsername() + ", getMobileNumber()=" + user.getMobileNumber()
				+ ", getEmail()=" + user.getEmail() + "]";
	}
	
}

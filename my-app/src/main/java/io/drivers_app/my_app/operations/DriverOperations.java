package io.drivers_app.my_app.operations;

import java.util.Scanner;

import io.drivers_app.my_app.domain.*;

public class DriverOperations {

	Driver driver;
	
	public DriverOperations() {}
	
	public DriverOperations(Driver driver)
	{
		this.driver = driver;
	}
	
	public void showAvailableTrips()
    {
    	for(int i=0; i< driver.availableTrips.size(); i++)
    	{
    		System.out.print((i+1)+"- ");
    		System.out.println(driver.availableTrips.get(i).toString() );
    	}
    }

    
    public void addToAvailableTrips(Trip t)
    {
    	if(driver.favArea.indexOf(t.source) != -1 && driver.currTrip != null)
    	{
    		driver.availableTrips.add(t);
    	}
        
    }
    
    
    public void deleteFromAvailableTrips(Trip t)
    {
        for(int i=0 ; i<driver.availableTrips.size() ; i++){
            if(driver.availableTrips.get(i) == t)
            {
            	driver.availableTrips.remove(i);
                return;
            }
        }
        
        System.out.println("Trip not found");
        //availableTrips.remove(t);
    }
    
    
    public void addFavArea(String str){
    	driver.favArea.add(str);
    }
    
    
    public void deleteFromFavArea(String str){
    	driver.favArea.remove(str);
    }
    
    
    public void sendOffer(Trip t){
        Trip tmp = new Trip(t);
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter your offer price");
        double price = scan.nextDouble();
        
        tmp.setPrice(price);
        tmp.setDriver(driver);
        
  
        //tmp.getUser().userOperation.addToOfferList(tmp);
        
        for(NormalUser user: tmp.getUsers())
        {
        	user.userOperation.addToOfferList(tmp);
        }
        Event event= new Event("Captain put a price to ride" , driver.getUsername() , price);
        Data.getInstance().dataOperation.addEvent(event);

        	
    }

    public void deleteFromAvailabeTrips(Trip t)
    {
    	driver.availableTrips.remove(t);
    }
    
    public void updateAvgRate()
    {
    	double num = 0;
    	for(Trip t: driver.historyList)
    	{
    		num += t.rate;
    	}
    	
    	driver.avgRate = num/(double)driver.historyList.size();
    }
    
    public void showHistoryList()
    {
    	for(int i=0; i<driver.historyList.size(); i++)
    	{
    		System.out.print((i+1) + "- ");
    		System.out.println(driver.historyList.get(i).toString());
    	}
    }
    
    public void addToHistory(Trip trip)
	{
    	driver.historyList.add(new Trip(trip));
	}
	
}

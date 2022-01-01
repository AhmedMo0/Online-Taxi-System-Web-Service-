package io.drivers_app.my_app.domain;

import java.util.ArrayList;

public class Trip {

	public String source;
	public String destination;
	public int rate;
	public double price; // change in Class D
	public ArrayList<NormalUser> users;
	public Driver driver;
	public boolean status;
	
	public Trip() {}
	
	public Trip(String src, String dest)
	{
		source = src;
		destination = dest;
		rate = 0;
		price = 0;
		driver = null;
		users = new ArrayList<>();
		status = false;
	}
	
	public Trip(Trip that) {
	    this(that.source, that.destination);
	    this.rate = that.getRate();
	    this.price = that.getPrice();
	    this.users = new ArrayList<>(that.users);
	    this.driver = that.driver;
	    this.status = that.status;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
		driver.driverOperations.updateAvgRate();
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setUser(NormalUser user)
	{
		users.add(user);
	}
	
	public ArrayList<NormalUser> getUsers()
	{
		return users;
	}
	
	public void setDriver(Driver driver)
	{
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "Trip [source=" + source + ", destination=" + destination + ", rate=" + rate + ", price=" + price
				+ ", users=" + users + ", driver=" + driver + ", status=" + status + "]";
	}
	
	

	
	
	
	
}

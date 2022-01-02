package io.drivers_app.my_app.domain;

import java.time.*;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.drivers_app.my_app.domain.discount.DiscountBuilder;

public class Trip {

	public String source;
	public String destination;
	
	@JsonIgnore
	public int rate;
	
	@JsonIgnore
	public double price; // change in Class D
	
	@JsonIgnore
	public double totalDiscount = 0;
	
	@JsonIgnore
	public ArrayList<NormalUser> users;
	
	@JsonIgnore
	public Driver driver;
	
	@JsonIgnore
	public boolean status;
	
	@JsonIgnore
	public int passengers;
	public LocalDateTime dateTime;
	
	@JsonIgnore
	private DiscountBuilder discountBuilder;
	
	
	public Trip() {}
	
	public Trip(String src, String dest)
	{
		source = src;
		destination = dest;
		rate = 0;
		price = 0;
		passengers = 0;
		driver = null;
		users = new ArrayList<>();
		status = false;
		dateTime = LocalDateTime.now();
		discountBuilder = new DiscountBuilder(this);
		
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

	
	public void checkDiscount()
	{
		discountBuilder.applyDiscountPhases();
		price = totalDiscount == 0? price: price*totalDiscount;
	}
	
	@Override
	public String toString() {
		return "Trip [source=" + source + ", destination=" + destination + ", rate=" + rate + ", price=" + price
				+ ", users=" + users + ", driver=" + driver + ", status=" + status + "]";
	}
	
	

	
	
	
	
}

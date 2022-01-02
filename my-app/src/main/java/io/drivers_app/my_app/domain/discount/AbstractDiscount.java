package io.drivers_app.my_app.domain.discount;

import io.drivers_app.my_app.domain.Trip;

public abstract class AbstractDiscount {

	public Trip trip;
	public AbstractDiscount next = null;
	
	
	public AbstractDiscount(Trip trip)
	{
		this.trip = trip;
	}
	
	public void setNext(AbstractDiscount next)
	{
		this.next = next;
	}
	
	public void applyNextIfExists()
	{
		if(next != null)
		{
			next.applyDiscount();
		}
	}
	
	public void applyDiscount()
	{
		discountLogic();
		applyNextIfExists();
	}
	public abstract void discountLogic();
	
}

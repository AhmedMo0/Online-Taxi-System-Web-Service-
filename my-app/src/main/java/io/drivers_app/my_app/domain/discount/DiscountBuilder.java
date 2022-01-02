package io.drivers_app.my_app.domain.discount;

import io.drivers_app.my_app.domain.Trip;

public class DiscountBuilder {
	private Trip trip;
	private AbstractDiscount first = new BirthDay10(trip);
	private AbstractDiscount firstRide = new FirstRide10(trip);
	private AbstractDiscount holiday = new Holiday5(trip);
	private AbstractDiscount twoPassengers = new TwoPassengers5(trip);
	
	public DiscountBuilder(Trip trip)
	{
		this.trip = trip;
	}
	
	public void applyDiscountPhases()
	{
		first.setNext(firstRide);
		firstRide.setNext(holiday);
		holiday.setNext(twoPassengers);
		
		first.applyDiscount();
	}
}

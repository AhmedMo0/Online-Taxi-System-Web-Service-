package io.drivers_app.my_app.domain.discount;

import java.time.DayOfWeek;

import io.drivers_app.my_app.domain.Trip;

public class Holiday5 extends AbstractDiscount{

	public Holiday5(Trip trip) {
		super(trip);
	}

	
	@Override
	public void discountLogic()
	{
		if(trip.dateTime.getDayOfWeek() == DayOfWeek.FRIDAY || 
			trip.dateTime.getDayOfWeek() == DayOfWeek.SATURDAY)
		{
			trip.totalDiscount += 0.05;
		}
	}

}

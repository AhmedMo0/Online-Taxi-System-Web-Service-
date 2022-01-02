package io.drivers_app.my_app.domain.discount;

import io.drivers_app.my_app.domain.Trip;

public class TwoPassengers5 extends AbstractDiscount{

	public TwoPassengers5(Trip trip) {
		super(trip);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void discountLogic() {
		if(trip.passengers > 1)
		{
			trip.totalDiscount += 0.05;
		}
		
	}

}

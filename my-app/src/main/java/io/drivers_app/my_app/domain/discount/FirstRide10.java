package io.drivers_app.my_app.domain.discount;

import io.drivers_app.my_app.domain.Trip;

public class FirstRide10 extends AbstractDiscount{

	public FirstRide10(Trip trip) {
		super(trip);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void discountLogic() {
		if(trip.users.get(0).historyList.size() == 0)
		{
			trip.totalDiscount += 0.10;
		}
	}

}

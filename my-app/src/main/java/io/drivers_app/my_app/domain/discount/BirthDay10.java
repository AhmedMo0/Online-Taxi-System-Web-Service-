package io.drivers_app.my_app.domain.discount;

import io.drivers_app.my_app.domain.Trip;

public class BirthDay10 extends AbstractDiscount{

	public BirthDay10(Trip trip) {
		super(trip);
	}

	@Override
	public void discountLogic() {
		if(trip.users.get(0).getBirthDate().getMonth() == trip.dateTime.getMonth() &&
			trip.users.get(0).getBirthDate().getDayOfMonth() == trip.dateTime.getDayOfMonth())
		{
			trip.totalDiscount += 0.10;
		}
	}

}

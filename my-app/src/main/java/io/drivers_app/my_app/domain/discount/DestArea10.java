package io.drivers_app.my_app.domain.discount;

import io.drivers_app.my_app.domain.Data;
import io.drivers_app.my_app.domain.Trip;

public class DestArea10 extends AbstractDiscount{

	public DestArea10(Trip trip) {
		super(trip);
	}


	@Override
	public void discountLogic() {
		String destArea = trip.destination.toLowerCase();
		
		if(Data.getInstance().dataOperation.areaHasDiscount(destArea))
		{
			trip.totalDiscount += 0.10;
		}
		
		
	}

	

}

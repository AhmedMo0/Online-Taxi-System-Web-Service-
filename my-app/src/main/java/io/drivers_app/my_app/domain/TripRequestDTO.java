package io.drivers_app.my_app.domain;

public class TripRequestDTO {

	private String source;
	private String dest;
	private int otherPassengers;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public int getOtherPassengers() {
		return otherPassengers;
	}
	public void setOtherPassengers(int otherPassengers) {
		this.otherPassengers = otherPassengers;
	}
	
	
	
}

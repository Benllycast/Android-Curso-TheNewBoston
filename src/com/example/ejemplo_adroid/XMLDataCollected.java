package com.example.ejemplo_adroid;

public class XMLDataCollected {
	private int temp = 0;
	private String city = null;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	
	public String dataToString(){
		return "In city "+ this.city+" the current Time in F is"+this.temp+ "degress";
	}
}

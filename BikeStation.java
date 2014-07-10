package com.example.tpbicloo;

public class BikeStation {
	String name;
	Integer availablePlace;
	Integer totalPlace;
	
	public BikeStation(){}
	public BikeStation(String name, Integer availablePlace, Integer totalPlace){
		this.setName(name);
		this.setAvailablePlace(availablePlace);
		this.setTotalPlace(totalPlace);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAvailablePlace() {
		return availablePlace;
	}
	public void setAvailablePlace(Integer availablePlace) {
		this.availablePlace = availablePlace;
	}
	public Integer getTotalPlace() {
		return totalPlace;
	}
	public void setTotalPlace(Integer totalPlace) {
		this.totalPlace = totalPlace;
	}
	
	@Override
	public String toString(){
		return name + " " + availablePlace + "/" + totalPlace;
		
	}

}

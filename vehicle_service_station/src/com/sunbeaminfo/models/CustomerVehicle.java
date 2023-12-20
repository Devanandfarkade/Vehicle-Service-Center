package com.sunbeaminfo.models;

public class CustomerVehicle {
	private Vehicle vehicle;
	private String vehicleNumber;
	
	public CustomerVehicle(Vehicle vehicle, String vehicleNumber) {
		this.vehicle = vehicle;
		this.vehicleNumber = vehicleNumber;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	@Override
	public String toString() {
		return "CustomerVehicle [vehicle=" + vehicle + ", vehicleNumber=" + vehicleNumber + "]";
	}

	
}

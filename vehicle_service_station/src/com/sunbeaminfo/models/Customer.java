package com.sunbeaminfo.models;
import java.util.List;


public class Customer{
	private int id;
	private String name;
	private String address;
	private String mobile;
	private String email;
	private List<CustomerVehicle> vehicles_list;
	
	public Customer() {
	}

	public Customer(String name, String address, String mobile,String email) {
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
	}
	
	public Customer(int id,String name, String address, String mobile,String email) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
	public void setVehicles_list(List<CustomerVehicle> vehicles_list) {
		this.vehicles_list = vehicles_list;
	}
	
	public List<CustomerVehicle> getVehicles_list() {
		return vehicles_list;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", mobile=" + mobile + ", email="
				+ email + "]";
	}


//	public Vehicle addVehical() {
//		Vehicle vh = new Vehicle();
//		vh.acceptVehicle();
//		veh_list.put(vh.getNumber(), vh);
//		return vh;
//	}
//
//	public void dispalyVehicles() {
//		for (Map.Entry<String, Vehicle> entry : this.veh_list.entrySet())
//			System.out.println(entry.getValue().toString());
//	}

	
}

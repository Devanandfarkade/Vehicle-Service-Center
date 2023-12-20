package com.sunbeaminfo.models;


public class Vehicle{
	private int vid;
	private String company;
	private String model;

	public Vehicle() {
	}

	public Vehicle(int vid,String company, String model) {
		this.vid = vid;
		this.company = company;
		this.model = model;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public void setVid(int vid) {
		this.vid = vid;
	}
	
	public int getVid() {
		return vid;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s,%s",this.vid,this.company,this.model);
	}

}

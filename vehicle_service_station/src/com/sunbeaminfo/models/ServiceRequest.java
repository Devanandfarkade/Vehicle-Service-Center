package com.sunbeaminfo.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ServiceRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String vehicalNumber;
	private LocalDate reqDate;
	private double billAmount;
	private List<Service> serviceList;
	
	//initializer block
	{
		this.serviceList = new ArrayList<Service>();
	}

	public ServiceRequest(int id) {
		this.id = id;
	
	}
	
	public ServiceRequest(int id, String vehicalNumber) {
		this.id = id;
		this.vehicalNumber = vehicalNumber;
	}
	
	public ServiceRequest(int id, String vehicalNumber, LocalDate reqDate,double billAmount) {
		this.id = id;
		this.vehicalNumber = vehicalNumber;
		this.reqDate = reqDate;
		this.billAmount = billAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehicalNumber() {
		return vehicalNumber;
	}

	public void setVehicalNumber(String vehicalNumber) {
		this.vehicalNumber = vehicalNumber;
	}

	public LocalDate getReqDate() {
		return reqDate;
	}

	public void setReqDate(LocalDate reqDate) {
		this.reqDate = reqDate;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	
	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}
	
	@Override
	public String toString() {
		return "ServiceRequest [id=" + id + ", vehicalNumber=" + vehicalNumber + ", reqDate=" + reqDate
				+ ", billAmount=" + billAmount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceRequest other = (ServiceRequest) obj;
		return id == other.id;
	}

	
	
	
//	public double processRequest() {
//		double total = 0;
//		for (Service service : serviceList)
//			total+=service.calculatePrice();
//		return total;
//	}
//	
//	public double labourCharges() {
//		double total = 0;
//		for (Service service : serviceList)
//			 if (service instanceof Maintainance) {
//				Maintainance maintainance = (Maintainance) service;	
//				total+=maintainance.getLabourCharges();
//			}	
//		return total;
//	}
//	
//	public double OilCharges() {
//		double total = 0;
//		for (Service service : serviceList)
//			 if (service instanceof Oil) {
//				 Oil oil = (Oil) service;	
//				total+=oil.getCost();
//			}	
//		return total;
//	}
//	
//	public void PartCharges(){
//		for (Service service : serviceList)
//			 if (service instanceof Maintainance) {
//				Maintainance maintainance = (Maintainance) service;
//				maintainance.printParts();
//				}
//		}
}

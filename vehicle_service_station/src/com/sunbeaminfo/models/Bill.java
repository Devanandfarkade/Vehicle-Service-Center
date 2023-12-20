package com.sunbeaminfo.models;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sunbeaminfo.service.CustomerService;
import com.sunbeaminfo.service.PartService;
import com.sunbeaminfo.service.ServiceRequestService;
import com.sunbeaminfo.service.ServiceService;
import com.sunbeaminfo.service.VehicleService;

public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private CustomerVehicle customerVehicle;
	private ServiceRequest serviceRequest;
	private Map<Part, Integer> partsList;

	public void prepareBill() {
		Scanner sc = new Scanner(System.in);
		this.serviceRequest = ServiceRequestService.getExistingService();
		if (this.serviceRequest != null) {
			ServiceService.getServiceList(this.serviceRequest);
			List<Service> serviceList = this.serviceRequest.getServiceList();
			double bill = 0;
			for (Service service : serviceList)
				bill = bill + service.getTotal_cost();
			this.serviceRequest.setBillAmount(bill);
			ServiceRequestService.addBill(bill, this.serviceRequest.getId());
			fetchAllData();
		} else {
			System.out.println("Service does not exists ...");
		}
	}

	private void fetchAllData() {
		this.customerVehicle = VehicleService.getCustomerVehicle(this.serviceRequest.getVehicalNumber());
		this.customer = CustomerService.getCustomer(this.serviceRequest.getVehicalNumber());

		List<Service> serviceList = this.serviceRequest.getServiceList();
		Maintainance maintainance = null;

		for (Service service : serviceList)
			if (service instanceof Maintainance)
				maintainance = (Maintainance) service;

		if (maintainance != null) {
			ServiceService.getServicePartsList(maintainance);
			this.partsList = PartService.getAllServiceParts(maintainance.getPartsList());	
		}
		
		displayBill();
	}

	private void displayBill() {
		System.out.println("*************************Bill*************************");
		System.out.println("Service Date - " + this.serviceRequest.getReqDate());
		System.out.println("Customer Name - " + this.customer.getName() + "			" + "Customer Mobile - "
				+ this.customer.getMobile());
		System.out.println("Vehicle Number - " + this.customerVehicle.getVehicleNumber());
		System.out.println("Company - " + this.customerVehicle.getVehicle().getCompany() + "			" + "Model - "
				+ this.customerVehicle.getVehicle().getModel());

		List<Service> serviceList = this.serviceRequest.getServiceList();
		for (Service service : serviceList)
			if (service instanceof Oil) {
				Oil oil = (Oil) service;
				System.out.println(oil.getRemark() + " - " + oil.getTotal_cost());
			} else {
				Maintainance maintainance = (Maintainance) service;
					System.out.println("-----------------------------------------------------");
				for (Map.Entry<Part, Integer> entry : partsList.entrySet()) {
					System.out.println("Part Name - " + entry.getKey().getName() + "		" + "Price - "
							+ entry.getKey().getPrice());
					System.out.println(
							"Quantity - " + entry.getValue() + "			" + "Total Parts Price - " +(entry.getKey().getPrice() * entry.getValue()));
					System.out.println("-----------------------------------------------------");	
				}
				System.out.println("Labour Charges - "+maintainance.getLabourCharges());
				System.out.println("Total Maintainance Cost - " + maintainance.getTotal_cost());
			}
		System.out.println("-----------------------------------------------------");
		System.out.println("Total Amount is - " + this.serviceRequest.getBillAmount());
		System.out.println("Total Tax 12.6% is - " + this.computeTax() );
		System.out.println("Total Bill amount is - " + this.computeTotalBill());
		
		System.out.println("*************************END*************************");
	}

	private double computeTax() {
		double tax = (12.6 * this.serviceRequest.getBillAmount())/100;
		return tax;
	}
	
	private double computeTotalBill() {
		return this.serviceRequest.getBillAmount() + this.computeTax();
	}
}

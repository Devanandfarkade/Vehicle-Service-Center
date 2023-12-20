package com.sunbeaminfo.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sunbeaminfo.dao.CustomerDao;
import com.sunbeaminfo.models.Bill;
import com.sunbeaminfo.models.Customer;
import com.sunbeaminfo.models.Maintainance;
import com.sunbeaminfo.models.Oil;
import com.sunbeaminfo.models.Part;
import com.sunbeaminfo.models.Service;
import com.sunbeaminfo.models.ServiceRequest;
import com.sunbeaminfo.models.ServiceStation;
import com.sunbeaminfo.models.Vehicle;
import com.sunbeaminfo.service.CustomerService;
import com.sunbeaminfo.service.PartService;
import com.sunbeaminfo.service.PaymentService;
import com.sunbeaminfo.service.ServiceRequestService;
import com.sunbeaminfo.service.ServiceService;
import com.sunbeaminfo.service.VehicleService;

public class MenuOptions {
	// MAIN MENU
	public void mainMenuOptions() {
		E_Menu choice;
		while ((choice = Menu.mainMenu()) != E_Menu.EXIT)
			switch (choice) {
			case CUSTOMER:
				subMenuCutomerOptions();
				break;
			case VEHICLES:
				subMenuVehicalOptions();
				break;
			case SERVICE_REQUEST:
				subMenuServiceRequestOptions();
				break;
			case PARTS:
				subMenuPartsOptions();
				break;
			case TODAYS_BUSINESS:
				PaymentService.getTodaysPayments();
				break;
			case DATE_WISE_BUSINESS:
				PaymentService.getSpecificDatePayments();
				break;
			}
	}

	// CUSTOMER SUB MENU
	public void subMenuCutomerOptions() {
		System.out.println("Customer  Menu");
		System.out.println("**************");
		E_SubMenuCustomer choice;
		Scanner sc = new Scanner(System.in);
		while ((choice = Menu.subMenuCustomer()) != E_SubMenuCustomer.EXIT)
			switch (choice) {
			case ADD_CUSTOMER:
				CustomerService.addCustomer();
				break;
			case DISPLAY_ALL_CUSTOMERS:
				CustomerService.displayAllCustomers();
				break;
			case DISPLAY_CUSTOMER_DETAILS:
				CustomerService.displayCustomer();
				break;
			case EDIT_CUSTOMER:
				CustomerService.editCustomer();
				break;
			case DELETE_CUSTOMER:
				CustomerService.deleteCustomer();
				break;
			}
	}
	
	// VEHICLE SUB MENU
	public void subMenuVehicalOptions() {
		System.out.println("Vehicle  Menu");
		System.out.println("*************");
		E_SubMenuVehical choice;
		Scanner sc = new Scanner(System.in);
		while ((choice = Menu.subMenuVehical()) != E_SubMenuVehical.EXIT)
			switch (choice) {
			case ADD_VEHICLE:
				VehicleService.addVehicle();
				break;
			case DISPLAY_ALL_VEHICLES:
				VehicleService.displayAllVehicles();
				break;
			case DISPLAY_CUSTOMER_VEHICLES:
				VehicleService.displayVehicles();
				break;
			case EDIT_VEHICLE:
				VehicleService.editVehicle();
				break;
			case DELETE_VEHICLE:
				VehicleService.deleteVehicle();
				break;
			}
	}

	// SERVICE REQUEST SUB MENU
	public void subMenuServiceRequestOptions() {
		E_SubMenuServiceRequest choice;
		Bill bill = null;
		String vehicleNumber = null;
		while ((choice = Menu.subMenuServiceRequest()) != E_SubMenuServiceRequest.EXIT)
			switch (choice) {
			case SELECT_CUSTOMER_VEHICAL:
				VehicleService.displayVehicles();
				System.out.print("Select the vehicle number = ");
				vehicleNumber = new Scanner(System.in).next().toUpperCase();
				break;
			case PROCESS_REQUEST:
				if (vehicleNumber != null)
					subMenuProcessRequestOptions(vehicleNumber);
				else
					System.out.println("Select the customer vehicle first ...");
				break;
			case PREPARE_DISPLAY_BILL:
				new Bill().prepareBill();
				break;
			case GET_PAYMENT:
				PaymentService.payBill();
				break;
			}
	}

	// PROCESS REQUEST SUB MENU
	public void subMenuProcessRequestOptions(String vehicleNumber) {
		E_SubMenuProcessRequest choice;
		ServiceRequest serviceRequest = null;
		Service service = null;
		while ((choice = Menu.subMenuProcessRequest()) != E_SubMenuProcessRequest.EXIT)
			switch (choice) {
			case NEW_SERVICE:
				serviceRequest = ServiceRequestService.createNewService(vehicleNumber);
				if (serviceRequest != null)
					System.out.println("New Service Created ...");
				break;
			case EXISTING_SERVICE:
				serviceRequest = ServiceRequestService.getExistingService();
				if (serviceRequest != null)
					ServiceService.getServiceList(serviceRequest);
				break;
			case MAINTAINANCE:
				if (serviceRequest != null) 
					ServiceService.doMaintainance(serviceRequest);
				else
					System.out.println("Select the Service...");
				break;
			case REPARING:
				if (serviceRequest != null) 
					ServiceService.doRepair(serviceRequest);
				else
					System.out.println("Select the Service...");
				break;
			case OIL_CHANGE_ADDITIVES:
				if (serviceRequest != null) 
					ServiceService.doOilChange(serviceRequest);
				else
					System.out.println("Select the Service...");
				break;
			}

	}

	// PARTS SUB MENU
	public void subMenuPartsOptions() {
		E_SubMenuParts e_SubMenuParts;
		while ((e_SubMenuParts = Menu.subMenuParts()) != E_SubMenuParts.EXIT) {
			switch (e_SubMenuParts) {
			case ADD_PART:
				PartService.addPart();
				break;
			case DISPLAY_ALL_PARTS:
				PartService.displayAllParts();
				break;
			case EDIT_PART:
				PartService.editPart();
				break;
			case DELETE_PART:
				PartService.deletePart();
				break;
			}
		}
	}

}

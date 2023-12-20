package com.sunbeaminfo.menu;

import java.util.Scanner;

import com.sunbeaminfo.models.ServiceStation;

enum E_Menu {
	EXIT, CUSTOMER, VEHICLES, SERVICE_REQUEST, PARTS, TODAYS_BUSINESS, DATE_WISE_BUSINESS
}

enum E_SubMenuCustomer {
	EXIT, ADD_CUSTOMER, DISPLAY_ALL_CUSTOMERS, DISPLAY_CUSTOMER_DETAILS, EDIT_CUSTOMER, DELETE_CUSTOMER
}

enum E_SubMenuVehical {
	EXIT, ADD_VEHICLE, DISPLAY_ALL_VEHICLES, DISPLAY_CUSTOMER_VEHICLES, EDIT_VEHICLE, DELETE_VEHICLE
}

enum E_SubMenuServiceRequest {
	EXIT, SELECT_CUSTOMER_VEHICAL, PROCESS_REQUEST, PREPARE_DISPLAY_BILL, GET_PAYMENT
}

enum E_SubMenuProcessRequest {
	EXIT, NEW_SERVICE,EXISTING_SERVICE,MAINTAINANCE, REPARING, OIL_CHANGE_ADDITIVES
}

enum E_SubMenuParts {
	EXIT, ADD_PART, DISPLAY_ALL_PARTS, EDIT_PART, DELETE_PART
}

public class Menu {

	public static E_Menu mainMenu() {
		System.out.println("Main Menu");
		System.out.println("*********");
		Scanner sc = new Scanner(System.in);
		System.out.println("0. Exit");
		System.out.println("1. Customer");
		System.out.println("2. Vehicles");
		System.out.println("3. Service Request");
		System.out.println("4. Parts");
		System.out.println("5. Todays Business");
		System.out.println("6. Given Date's Business");
		System.out.print("Enter Your Choice from above List = ");
		int choice = sc.nextInt();
		System.out.println("----------------------------------------------------------");
		return (choice > 6 || choice < 0) ? E_Menu.EXIT : E_Menu.values()[choice];

	}

	public static E_SubMenuCustomer subMenuCustomer() {
		Scanner sc = new Scanner(System.in);
		System.out.println("0. Go Back");
		System.out.println("1. Add Cusomer");
		System.out.println("2. Display All Customers");
		System.out.println("3. Display Customer Details");
		System.out.println("4. Edit Customer");
		System.out.println("5. Delete Customer");
		System.out.print("Enter Your Choice from above List = ");
		int choice = sc.nextInt();
		System.out.println("----------------------------------------------------------");
		return (choice > 5 || choice < 0) ? E_SubMenuCustomer.EXIT : E_SubMenuCustomer.values()[choice];
	}

	public static E_SubMenuVehical subMenuVehical() {
		Scanner sc = new Scanner(System.in);
		System.out.println("0. Go Back");
		System.out.println("1. Add Vehicle");
		System.out.println("2. Display ALL Vehicles");
		System.out.println("3. Display Customer Vehicles");
		System.out.println("4. Edit Vehicle");
		System.out.println("5. Delete Vehicle");
		System.out.print("Enter Your Choice from above List = ");
		int choice = sc.nextInt();
		System.out.println("----------------------------------------------------------");
		return (choice > 5 || choice < 0) ? E_SubMenuVehical.EXIT : E_SubMenuVehical.values()[choice];
	}

	public static E_SubMenuServiceRequest subMenuServiceRequest() {
		Scanner sc = new Scanner(System.in);
		System.out.println("----------------------------------------------------------");
		System.out.println("0. Go Back");
		System.out.println("1. Select Customer Vehical");
		System.out.println("2. Process Request");
		System.out.println("3. Prepare and Display Bill");
		System.out.println("4. Get bill payment from customer");
		System.out.println("Enter Your Choice from above List");
		int choice = sc.nextInt();
		return (choice > 4 || choice < 0) ? E_SubMenuServiceRequest.EXIT : E_SubMenuServiceRequest.values()[choice];
	}

	public static E_SubMenuProcessRequest subMenuProcessRequest() {
		Scanner sc = new Scanner(System.in);
		System.out.println("----------------------------------------------------------");
		System.out.println("0. Go Back");
		System.out.println("1. New Service");
		System.out.println("2. Existing Service");
		System.out.println("3. Maintainance");
		System.out.println("4. Reparing");
		System.out.println("5. Engine Oil Change / Additives");
		System.out.println("Enter Your Choice from above List");
		int choice = sc.nextInt();
		return (choice > 5 || choice < 0) ? E_SubMenuProcessRequest.EXIT : E_SubMenuProcessRequest.values()[choice];
	}

	public static E_SubMenuParts subMenuParts() {
		Scanner sc = new Scanner(System.in);
		System.out.println("----------------------------------------------------------");
		System.out.println("0. Go Back");
		System.out.println("1. Add Part");
		System.out.println("2. Display All Parts");
		System.out.println("3. Edit Part Price");
		System.out.println("4. Delete Part");
		System.out.println("Enter Your Choice from above List");
		int choice = sc.nextInt();
		return (choice > 4 || choice < 0) ? E_SubMenuParts.EXIT : E_SubMenuParts.values()[choice];
	}

}
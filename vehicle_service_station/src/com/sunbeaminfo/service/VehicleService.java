package com.sunbeaminfo.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.sunbeaminfo.dao.VehicleDao;
import com.sunbeaminfo.models.Customer;
import com.sunbeaminfo.models.CustomerVehicle;
import com.sunbeaminfo.models.Vehicle;

public class VehicleService {

	// ADD VEHICLE
	public static void addVehicle() {
		Customer customer = CustomerService.findCustomer();
		if (customer != null) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Vehicle Number");
			String vehicleNumber = sc.next().toUpperCase();
			try (VehicleDao vehicleDao = new VehicleDao()) {
				List<Vehicle> vehicles_list = vehicleDao.fetchVehicle();
				for (Vehicle vehicle : vehicles_list)
					System.out.println(vehicle);
				System.out.println("Select the vehicle ->");
				System.out.println("Enter the vehicle id OR Enter 0 for adding the Company & Model");
				int choice = sc.nextInt();
				if (choice == 0) {
					System.out.print("Enter Company Name = ");
					sc.nextLine();
					String company = sc.nextLine();
					System.out.print("Enter Company Model = ");
					String model = sc.nextLine();
					choice = vehicleDao.addVehicle(company, model);
				}
				vehicleDao.addCustomerVehicle(customer.getId(), vehicleNumber, choice);
			} catch (SQLException e) {
				e.printStackTrace();

			}
		} else
			System.out.println("Customer Not Found");
	}

	// DISPLAY ALL VEHICLES
	public static void displayAllVehicles() {
		try (VehicleDao vehicleDao = new VehicleDao()) {
			List<CustomerVehicle> vehiclesList = vehicleDao.fetchAllVehicles();
			for (CustomerVehicle customerVehicle : vehiclesList)
				System.out.println(customerVehicle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DISPLAY CUSTOMER VEHICLE
	public static void displayVehicles() {
		Customer customer = CustomerService.findCustomer();
		if (customer != null) {
			try (VehicleDao vehicleDao = new VehicleDao()) {
				vehicleDao.fetchCustomerVehicles(customer);
				if (customer.getVehicles_list() != null)
					for (CustomerVehicle vehicle : customer.getVehicles_list())
						System.out.println(vehicle);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("Customer Not Found");
	}

	// EDIT CUSTOMER VEHICLE
	public static void editVehicle() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Customer Vehicle Number to edit = ");
		String oldVehicleNumber = sc.next().toUpperCase();
		try (VehicleDao vehicleDao = new VehicleDao()) {
			if (vehicleDao.findCustomerVehicle(oldVehicleNumber)) {
				System.out.println("Enter the New Vehicle Number = ");
				String newVehicleNumber = sc.next().toUpperCase();
				vehicleDao.editVehicle(oldVehicleNumber, newVehicleNumber);
			} else
				System.out.println("Vehicle Not Found ...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DELETE CUSTOMER VEHICLE
	public static void deleteVehicle() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Customer Vehicle Number to edit = ");
		String vehicleNumber = sc.next().toUpperCase();
		try (VehicleDao vehicleDao = new VehicleDao()) {
			if (vehicleDao.deleteVehicle(vehicleNumber) > 0)
				System.out.println("Customer Vehicle Deleted Successfully ...");
			else
				System.out.println("Vehicle does not exists ...");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static CustomerVehicle getCustomerVehicle(String vehicle_number) {
		try (VehicleDao vehicleDao = new VehicleDao()) {
			return vehicleDao.getCustomerVehicle(vehicle_number);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.sunbeaminfo.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.sunbeaminfo.dao.CustomerDao;
import com.sunbeaminfo.dao.VehicleDao;
import com.sunbeaminfo.models.Customer;
import com.sunbeaminfo.models.CustomerVehicle;

public class CustomerService {

	// ADD CUSTOMER
	public static void addCustomer() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Name");
		String name = sc.nextLine();
		System.out.println("Enter Mobile");
		String mobile = sc.nextLine();
		System.out.println("Enter Email");
		String email = sc.nextLine();
		System.out.println("Enter Address");
		String address = sc.nextLine();
		try (CustomerDao customerDao = new CustomerDao()) {
			customerDao.insertCustomer(name, mobile, email, address);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// FIND CUSTOMER
	public static Customer findCustomer() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Customer Mobile No = ");
		String mobile = sc.nextLine();
		try (CustomerDao customerDao = new CustomerDao()) {
			return customerDao.fetchCustomer(mobile);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// DISPLAY ALL CUSTOMERS
	public static void displayAllCustomers() {
		try (CustomerDao customerDao = new CustomerDao()) {
			List<Customer> customerList = customerDao.fetchAllCustomers();
			for (Customer customer : customerList)
				System.out.println(customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DISPLAY CUSTOMER DETAILS
	public static void displayCustomer() {
		Customer customer = findCustomer();
		if (customer != null)
			System.out.println(customer);
		else
			System.out.println("Customer Not Found");
	}

	// EDIT CUSTOMER
	public static void editCustomer() {
		Scanner sc = new Scanner(System.in);
		Customer customer = findCustomer();
		if (customer != null) {
			System.out.print("Enter the Customer mobile number to edit = ");
			String mobile = sc.next();
			System.out.print("Enter the Customer address to edit = ");
			String address = sc.next();
			try (CustomerDao customerDao = new CustomerDao()) {
				customerDao.editCustomer(customer.getId(), mobile, address);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("Customer not found ...");
	}

	// DELETE CUSTOMER
	public static void deleteCustomer() {
		Customer customer = findCustomer();
		try (CustomerDao customerDao = new CustomerDao()) {
			if (customerDao.deleteCustomer(customer.getId()) > 0)
				System.out.println("Customer Deleted Successfully ...");
			else
				System.out.println("Customer does not exists ...");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Customer getCustomer(String vehicalNumber) {
		try (CustomerDao customerDao = new CustomerDao()) {
			return customerDao.getCustomer(vehicalNumber);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

package com.sunbeaminfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.models.Customer;
import com.sunbeaminfo.models.CustomerVehicle;
import com.sunbeaminfo.models.Vehicle;
import com.sunbeaminfo.utils.DBUtil;

public class CustomerDao implements AutoCloseable {
	private Connection connection;

	public CustomerDao() throws SQLException {
		this.connection = DBUtil.getConnection();
	}

	public void insertCustomer(String name, String mobile, String email, String address) throws SQLException {
		String sql = "INSERT INTO customer(name,mobile,email,address) VALUES(?,?,?,?)";
		try (PreparedStatement insertCustomer = this.connection.prepareStatement(sql)) {
			insertCustomer.setString(1, name);
			insertCustomer.setString(2, mobile);
			insertCustomer.setString(3, email);
			insertCustomer.setString(4, address);
			insertCustomer.executeUpdate();
		}

	}
	
	public List<Customer> fetchAllCustomers() throws SQLException {
		String sql = "SELECT id,name,mobile,email,address FROM customer";
		List<Customer> customers_list = new ArrayList<Customer>();
		try (PreparedStatement fetchCustomers= connection.prepareStatement(sql)) {
			ResultSet resultSet = fetchCustomers.executeQuery();
			while (resultSet.next())
				customers_list.add(new Customer(resultSet.getInt(1),resultSet.getString(2),
						resultSet.getString(5),resultSet.getString(3),resultSet.getString(4)));
		}
		return customers_list;
	}
	
	public Customer fetchCustomer(String mobile) throws SQLException {
		String sql = "SELECT id,name,address,email FROM customer WHERE mobile=?";
		try (PreparedStatement fetchCustomer = connection.prepareStatement(sql)) {
			fetchCustomer.setString(1, mobile);
			ResultSet resultSet = fetchCustomer.executeQuery();
			if (resultSet.next()) {
				return new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), mobile,
						resultSet.getString(4));
			}
		}
		return null;
	}

	public void editCustomer(int id, String mobile,String address)throws SQLException {
		String sql = "UPDATE customer SET mobile = ? and address = ? WHERE id = ?";
		try (PreparedStatement editCustomer = connection.prepareStatement(sql)) {
			editCustomer.setString(1, mobile);
			editCustomer.setString(2, address);
			editCustomer.setInt(3, id);
			editCustomer.executeUpdate();
		}
	}
	
	public int deleteCustomer(int id) throws SQLException {
		String sql = "DELETE FROM customer WHERE id = ?";
		try (PreparedStatement deleteCustomer = connection.prepareStatement(sql)) {
			deleteCustomer.setInt(1, id);
			return deleteCustomer.executeUpdate();
		}

	}
	
	public Customer getCustomer(String vehicalNumber)throws SQLException {
		String sql = "SELECT id,name,address,mobile,email FROM customer INNER JOIN customer_vehicles cv ON id=cv.customer_id WHERE cv.vehicle_number=?";
		try (PreparedStatement fetchCustomer = connection.prepareStatement(sql)) {
			fetchCustomer.setString(1, vehicalNumber);
			ResultSet resultSet = fetchCustomer.executeQuery();
			if (resultSet.next()) {
				return new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5));
			}
		}
		return null;
	}
	
	@Override
	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}

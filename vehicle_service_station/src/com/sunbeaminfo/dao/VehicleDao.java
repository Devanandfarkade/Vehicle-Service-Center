package com.sunbeaminfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.models.Customer;
import com.sunbeaminfo.models.CustomerVehicle;
import com.sunbeaminfo.models.Vehicle;
import com.sunbeaminfo.utils.DBUtil;

public class VehicleDao implements AutoCloseable {
	private Connection connection;

	public VehicleDao() throws SQLException {
		this.connection = DBUtil.getConnection();
	}

	public void fetchCustomerVehicles(Customer customer) throws SQLException {
		String sql = "SELECT cv.vehicle_id,cv.vehicle_number,v.company,v.model FROM customer_vehicles cv INNER JOIN vehicle v ON cv.vehicle_id = v.id WHERE cv.customer_id=?";
		List<CustomerVehicle> vehicles_list = new ArrayList<CustomerVehicle>();
		try (PreparedStatement fetchVehicles = connection.prepareStatement(sql)) {
			fetchVehicles.setInt(1, customer.getId());
			ResultSet resultSet = fetchVehicles.executeQuery();

			while (resultSet.next())
				vehicles_list.add(new CustomerVehicle(
						new Vehicle(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4)),
						resultSet.getString(2)));

			if (!vehicles_list.isEmpty())
				customer.setVehicles_list(vehicles_list);
		}
	}

	public List<Vehicle> fetchVehicle() throws SQLException {
		String sql = "SELECT id,company,model FROM vehicle";
		try (PreparedStatement fetchVehicle = connection.prepareStatement(sql)) {
			ResultSet resultSet = fetchVehicle.executeQuery();
			List<Vehicle> vehicles_list = new ArrayList<Vehicle>();
			while (resultSet.next())
				vehicles_list.add(new Vehicle(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));

			return vehicles_list;
		}
	}

	public void addCustomerVehicle(int cid, String vehicleNumber, int vehicleid) throws SQLException {
		String sql = "INSERT INTO customer_vehicles VALUES(?,?,?)";
		try (PreparedStatement addCustomerVehicle = connection.prepareStatement(sql)) {
			addCustomerVehicle.setString(1, vehicleNumber);
			addCustomerVehicle.setInt(2, cid);
			addCustomerVehicle.setInt(3, vehicleid);
			if (addCustomerVehicle.executeUpdate() == 1)
				System.out.println("Vehicle Added");
			else
				System.out.println("Vehicle Adding failed");
		}

	}

	public int addVehicle(String company, String model) throws SQLException {
		String sql = "INSERT INTO vehicle(company,model) VALUES(?,?)";
		try (PreparedStatement addVehicle = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			addVehicle.setString(1, company);
			addVehicle.setString(2, model);
			addVehicle.executeUpdate();
			ResultSet resultSet = addVehicle.getGeneratedKeys();
			if (resultSet.next())
				return resultSet.getInt(1);
			return 0;

		}
	}

	public List<CustomerVehicle> fetchAllVehicles() throws SQLException {
		String sql = "SELECT cv.vehicle_number,v.company,v.model FROM customer_vehicles cv INNER JOIN vehicle v ON cv.vehicle_id = v.id";
		List<CustomerVehicle> vehicles_list = new ArrayList<CustomerVehicle>();
		try (PreparedStatement fetchVehicles = connection.prepareStatement(sql)) {
			ResultSet resultSet = fetchVehicles.executeQuery();
			while (resultSet.next())
				vehicles_list.add(new CustomerVehicle(new Vehicle(0, resultSet.getString(2), resultSet.getString(3)),
						resultSet.getString(1)));
		}
		return vehicles_list;
	}

	public boolean findCustomerVehicle(String vehicleNumber) throws SQLException {
		String sql = "SELECT * from customer_vehicles WHERE vehicle_number = ?";
		try (PreparedStatement findVehicle = connection.prepareStatement(sql)) {
			findVehicle.setString(1, vehicleNumber);
			ResultSet resultSet = findVehicle.executeQuery();
			if (resultSet.next())
				return true;
			return false;
		}
	}

	public void editVehicle(String oldVehicleNumber,String newVehicleNumber)throws SQLException {
		String sql = "UPDATE customer_vehicles SET vehicle_number = ? WHERE vehicle_number = ?";
		try (PreparedStatement editVehicle = connection.prepareStatement(sql)) {
			editVehicle.setString(1, newVehicleNumber);
			editVehicle.setString(2, oldVehicleNumber);
			editVehicle.executeUpdate();
		}
			
	}

	public int deleteVehicle(String vehicleNumber) throws SQLException {
		String sql = "DELETE FROM customer_vehicles WHERE vehicle_number = ?";
		try (PreparedStatement deleteVehicle = connection.prepareStatement(sql)) {
			deleteVehicle.setString(1, vehicleNumber);
			return deleteVehicle.executeUpdate();
		}

	}
	
	public CustomerVehicle getCustomerVehicle(String vehicleNumber) throws SQLException {
		String sql = "SELECT v.id, v.company,v.model FROM vehicle v INNER JOIN customer_vehicles cv ON cv.vehicle_id = v.id WHERE cv.vehicle_number = ?";
		try (PreparedStatement findCustomerVehicle = connection.prepareStatement(sql)) {
			findCustomerVehicle.setString(1, vehicleNumber);
			ResultSet resultSet = findCustomerVehicle.executeQuery();
			if (resultSet.next())
				return new CustomerVehicle(new Vehicle(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)),vehicleNumber);
			return null;
		}
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

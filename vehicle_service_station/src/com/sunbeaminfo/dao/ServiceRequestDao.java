package com.sunbeaminfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.models.ServiceRequest;
import com.sunbeaminfo.utils.DBUtil;

public class ServiceRequestDao implements AutoCloseable {
	private Connection connection;

	public ServiceRequestDao() throws SQLException {
		this.connection = DBUtil.getConnection();
	}

	public ServiceRequest insertServiceRequest(String vehicleNumber) throws SQLException {
		String sql = "INSERT INTO service_requests (vehicle_number) VALUES (?)";
		try (PreparedStatement insertServiceRequest = this.connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
			insertServiceRequest.setString(1, vehicleNumber);
			insertServiceRequest.executeUpdate();
			ResultSet resultSet = insertServiceRequest.getGeneratedKeys();
			if (resultSet.next())
				return new ServiceRequest(resultSet.getInt(1), vehicleNumber);
			return null;
		}
	}

	public List<ServiceRequest> fetchTodaysService() throws SQLException {
		String sql = "SELECT id,vehicle_number,DATE(request_date),bill_amount FROM service_requests WHERE DATE(request_date)=DATE(SYSDATE())";
		try (PreparedStatement fetchTodaysService= this.connection.prepareStatement(sql)) {
			ResultSet resultSet = fetchTodaysService.executeQuery();
			List<ServiceRequest> serviceRequestsList = new ArrayList<>();
			while(resultSet.next())
				serviceRequestsList.add(new ServiceRequest(resultSet.getInt(1),resultSet.getString(2),resultSet.getDate(3).toLocalDate(),resultSet.getDouble(4)));
			
			return serviceRequestsList;
		}
	}
	
	public ServiceRequest fetchServiceRequest(int nextInt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updateBill(double bill,int service_request_id) throws SQLException{
		String sql = "UPDATE service_requests SET bill_amount = ? WHERE id = ?";
		try (PreparedStatement updateBill= this.connection.prepareStatement(sql)) {
			updateBill.setDouble(1, bill);
			updateBill.setInt(2, service_request_id);
			updateBill.executeUpdate();
		}
	}

	
	@Override
	public void close() throws SQLException {
		if (this.connection != null)
			this.connection.close();
	}

	


}

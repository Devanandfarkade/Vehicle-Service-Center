package com.sunbeaminfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sunbeaminfo.models.Maintainance;
import com.sunbeaminfo.models.Oil;
import com.sunbeaminfo.models.Service;
import com.sunbeaminfo.models.ServiceParts;
import com.sunbeaminfo.models.ServiceRequest;
import com.sunbeaminfo.utils.DBUtil;

public class ServiceDao implements AutoCloseable {
	private Connection connection;

	public ServiceDao() throws SQLException {
		this.connection = DBUtil.getConnection();
	}

	// TO GET ALL SERVICES FROM SERVICES TABLE
	public void getAllServices(ServiceRequest serviceRequest) throws SQLException {
		String sql = "SELECT id,type,oil_cost,labour_charges,total_cost,remark FROM services WHERE service_request_id = ?";
		try (PreparedStatement getAllService = this.connection.prepareStatement(sql)) {
			getAllService.setInt(1, serviceRequest.getId());
			ResultSet resultSet = getAllService.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString(2).equals("maintainance")) {
					Maintainance maintainance = new Maintainance();
					maintainance.setId(resultSet.getInt(1));
					maintainance.setLabourCharges(resultSet.getDouble(4));
					maintainance.setTotal_cost(resultSet.getDouble(5));
					maintainance.setRemark(resultSet.getString(6));
					// getServicePartsList(maintainance);
					serviceRequest.getServiceList().add(maintainance);

				} else {
					Oil oil = new Oil();
					oil.setId(resultSet.getInt(1));
					oil.setOil_cost(resultSet.getDouble(3));
					oil.setTotal_cost(resultSet.getDouble(5));
					oil.setRemark(resultSet.getString(6));
					serviceRequest.getServiceList().add(oil);
				}
			}
		}
	}

	// TO GET ALL PARTSLIST IF SERVICE TYPE IS MAINTAINANCE/REPAIR-NotUSED
	public void getServicePartsList(Maintainance maintainance) {
		String sql = "SELECT part_id,quantity from service_parts WHERE service_id = ?";
		try (PreparedStatement getAllServiceParts = this.connection.prepareStatement(sql)) {
			getAllServiceParts.setInt(1, maintainance.getId());
			ResultSet resultSet = getAllServiceParts.executeQuery();
			List<ServiceParts> serviceParts = maintainance.getPartsList();
			while (resultSet.next())
				serviceParts.add(new ServiceParts(resultSet.getInt(1), resultSet.getInt(2)));
			maintainance.setPartsList(serviceParts);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ADD SERVICE IN SERVICE TABLE
	public void insertService(Service service, int service_request_id) throws SQLException {
		String sql = "INSERT INTO services(type,oil_cost,labour_Charges,total_cost,remark,service_request_id) VALUES(?,?,?,?,?,?)";
		try (PreparedStatement insertService = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			if (service instanceof Oil) {
				Oil oil = (Oil) service;
				insertService.setString(1, "oil");
				insertService.setDouble(2, oil.getOil_cost());
				insertService.setDouble(3, 0);
			} else {
				Maintainance maintainance = (Maintainance) service;
				insertService.setString(1, "maintainance");
				insertService.setDouble(2, 0);
				insertService.setDouble(3, maintainance.getLabourCharges());
			}
			insertService.setDouble(4, service.getTotal_cost());
			insertService.setString(5, service.getRemark());
			insertService.setInt(6, service_request_id);
			insertService.executeUpdate();
			ResultSet resultSet = insertService.getGeneratedKeys();
			if (resultSet.next())
				service.setId(resultSet.getInt(1));
		}

	}

	// TO ADD ALL SERVICE PARTS USED FOR REPAIR
	public void insertServiceParts(Service service) throws SQLException {
		Maintainance maintainance = (Maintainance) service;
		List<ServiceParts> servicePartsList = maintainance.getPartsList();
		String sql = "INSERT INTO service_parts(service_id,part_id,quantity) VALUES(?,?,?)";
		try (PreparedStatement insertServiceParts = this.connection.prepareStatement(sql)) {
			for (ServiceParts serviceParts : servicePartsList) {
				insertServiceParts.setInt(1, service.getId());
				insertServiceParts.setInt(2, serviceParts.getPartid());
				insertServiceParts.setInt(3, serviceParts.getQuantity());
				insertServiceParts.executeUpdate();
			}
			servicePartsList.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// UPDATE THE MAINTAINANCE SERVICE IF ALREADY EXIXTS
	public void updateService(Service service) throws SQLException {
		String sql = "UPDATE services SET labour_Charges = ?,total_cost = ?,remark = ? WHERE id = ?";
		try (PreparedStatement updateMaintainanceService = this.connection.prepareStatement(sql)) {
			Maintainance maintainance = (Maintainance) service;
			updateMaintainanceService.setDouble(1, maintainance.getLabourCharges());
			updateMaintainanceService.setDouble(2, maintainance.getTotal_cost());
			updateMaintainanceService.setString(3, maintainance.getRemark());
			updateMaintainanceService.setInt(4, service.getId());
			updateMaintainanceService.executeUpdate();
		}
	}

	@Override
	public void close() throws Exception {
		if (this.connection != null)
			this.connection.close();

	}

}

package com.sunbeaminfo.service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Scanner;

import com.sunbeaminfo.dao.ServiceRequestDao;
import com.sunbeaminfo.models.ServiceRequest;

public class ServiceRequestService {

	public static ServiceRequest createNewService(String vehicleNumber) {
		try (ServiceRequestDao serviceRequestDao = new ServiceRequestDao()) {
			return serviceRequestDao.insertServiceRequest(vehicleNumber);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("You have entered wrong Vehicle number...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ServiceRequest getExistingService() {
		try (ServiceRequestDao serviceRequestDao = new ServiceRequestDao()) {
			List<ServiceRequest> serviceRequestList = serviceRequestDao.fetchTodaysService();
			for (ServiceRequest serviceRequest : serviceRequestList)
				System.out.println(serviceRequest);
			System.out.print("Enter the Service id = ");
			return serviceRequestList
					.get(serviceRequestList.indexOf(new ServiceRequest(new Scanner(System.in).nextInt())));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Such service does not exists... cretae new service instead");
		}
		return null;
	}

	public static void addBill(double bill, int service_request_id) {
		try (ServiceRequestDao serviceRequestDao = new ServiceRequestDao()) {
			serviceRequestDao.updateBill(bill, service_request_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

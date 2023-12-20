package com.sunbeaminfo.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.sunbeaminfo.dao.ServiceDao;
import com.sunbeaminfo.models.Maintainance;
import com.sunbeaminfo.models.Oil;
import com.sunbeaminfo.models.Part;
import com.sunbeaminfo.models.Service;
import com.sunbeaminfo.models.ServiceParts;
import com.sunbeaminfo.models.ServiceRequest;

public class ServiceService {

	public static void getServiceList(ServiceRequest serviceRequest) {
		try (ServiceDao serviceDao = new ServiceDao()) {
			serviceDao.getAllServices(serviceRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void doMaintainance(ServiceRequest serviceRequest) {
		List<Service> serviceList = serviceRequest.getServiceList();
		Maintainance service = null;
		boolean serviceFound = false;

		if (serviceList.isEmpty()) {
			service = new Maintainance();
			serviceList.add(service);
		} else {
			for (Service tempService : serviceList) {
				if (tempService instanceof Maintainance) {
					service = (Maintainance) tempService;
					serviceFound = true;
				}
			}
			if (!serviceFound) {
				service = new Maintainance();
				serviceList.add(service);
			}
		}
		service.addMaintainance(false);
		addService(service, serviceRequest.getId());
	}

	public static void doRepair(ServiceRequest serviceRequest) {
		List<Service> serviceList = serviceRequest.getServiceList();
		Maintainance service = null;
		boolean serviceFound = false;

		if (serviceList.isEmpty()) {
			service = new Maintainance();
			serviceList.add(service);
		} else {
			for (Service tempService : serviceList) {
				if (tempService instanceof Maintainance) {
					service = (Maintainance) tempService;
					serviceFound = true;
				}
			}
			if (!serviceFound) {
				service = new Maintainance();
				serviceList.add(service);
			}
		}

		service.addMaintainance(true);
		addService(service, serviceRequest.getId());
	}

	public static void doOilChange(ServiceRequest serviceRequest) {
		Oil service = new Oil();
		service.acceptService();
		addService(service, serviceRequest.getId());
	}

	private static void addService(Service service, int service_request_id) {

		try (ServiceDao serviceDao = new ServiceDao()) {
			if (service.getId() == 0)
				serviceDao.insertService(service, service_request_id);
			else
				serviceDao.updateService(service);
			if (service instanceof Maintainance)
				serviceDao.insertServiceParts(service);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void getServicePartsList(Maintainance maintainance) {
		try(ServiceDao serviceDao = new ServiceDao()){
			serviceDao.getServicePartsList(maintainance);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

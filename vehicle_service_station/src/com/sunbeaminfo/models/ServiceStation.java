package com.sunbeaminfo.models;

import java.util.List;
import java.util.Scanner;

import com.sunbeaminfo.menu.MenuOptions;
import com.sunbeaminfo.service.ServiceRequestService;
import com.sunbeaminfo.utils.Constants;

public class ServiceStation {
	private static ServiceStation serviceStation = null;
	private static String service_station_name;

	private ServiceStation(String service_station_name) {
		service_station_name = service_station_name;
	}

	public double computeCash() {
		return 0;

	}

	public void showMainMenu() {
		new MenuOptions().mainMenuOptions();
	}

	public static ServiceStation getServiceStationInstance(String name) {
		if (ServiceStation.serviceStation == null) {
			System.out.println("***************************************");
			System.out.println("Welcome to " + name + " Service Station");
			System.out.println("***************************************");
			ServiceStation.serviceStation = new ServiceStation(name);
		}
		return ServiceStation.serviceStation;
	}
		
	
}

package com.sunbeaminfo.main;

import java.sql.Date;
import java.util.Calendar;

import com.sunbeaminfo.models.ServiceRequest;
import com.sunbeaminfo.models.ServiceStation;
import com.sunbeaminfo.service.ServiceRequestService;

public class Program {

	public static void main(String[] args) {
		ServiceStation.getServiceStationInstance("Devaa Honda").showMainMenu();
	}

}

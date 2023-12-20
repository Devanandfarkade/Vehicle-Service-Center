package com.sunbeaminfo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sunbeaminfo.dao.PartDao;
import com.sunbeaminfo.models.Part;
import com.sunbeaminfo.models.ServiceParts;

public class PartService {

	public static void addPart() {
		Part part = new Part();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the part name = ");
		part.setName(sc.nextLine());
		
		System.out.print("Enter the part description = ");
		part.setDescription(sc.nextLine());
		
		System.out.print("Enter the part price = ");
		part.setPrice(sc.nextDouble());
		
		try (PartDao partDao = new PartDao()){
			partDao.insertPart(part);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void editPart() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the part id = ");
		int id = sc.nextInt();
		
		System.out.print("Enter the part price = ");
		double price = sc.nextDouble();
		
		try (PartDao partDao = new PartDao()){
			if(partDao.editPart(id,price))
					System.out.println("Part Updated ...");
			else
				System.out.println("Part does not exists ...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deletePart() {
		
		System.out.print("Enter the part id = ");
		int id = new Scanner(System.in).nextInt();
		
		try (PartDao partDao = new PartDao()){
			if(partDao.deletePart(id))
					System.out.println("Part Deleted ...");
			else
				System.out.println("Part does not exists ...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Part> getAllParts() {
		List<Part> partsList = null;
		try (PartDao partDao = new PartDao()){
		partsList = partDao.fetchAllParts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return partsList;
	}
	
	public static void displayAllParts() {
		List<Part> partsList = getAllParts();
		for (Part part : partsList) 
			System.out.println(part);
	}

	// TO GET ALL THE PART DETAILS USED IN MAINTAINANCE
	public static Map<Part,Integer> getAllServiceParts(List<ServiceParts> partsList) {
		List<Part> allParts = getAllParts();
		Map<Part,Integer> servicePartsList = new HashMap<Part,Integer>();
		for(ServiceParts serviceParts : partsList) {
			Part part = new Part(serviceParts.getPartid());
			servicePartsList.put(allParts.get(allParts.indexOf(part)),serviceParts.getQuantity());
		}
		return servicePartsList;
	}
	
	
}

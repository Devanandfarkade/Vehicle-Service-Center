package com.sunbeaminfo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sunbeaminfo.service.PartService;

public class Maintainance extends Service {
	private static final long serialVersionUID = 1L;
	private double labourCharges;
	private double parts_cost;
	private List<ServiceParts> partsList;

	public Maintainance() {
		super("maintainance");
		this.labourCharges = 0;
		this.partsList = new ArrayList<ServiceParts>();
	}

	public double getLabourCharges() {
		return labourCharges;
	}

	public void setLabourCharges(double labourCharges) {
		this.labourCharges = labourCharges;
	}

	public List<ServiceParts> getPartsList() {
		return partsList;
	}

	public void setPartsList(List<ServiceParts> partsList) {
		this.partsList = partsList;
	}

	public void addMaintainance(boolean choice) {
		Scanner sc = new Scanner(System.in);
		while (choice) {
			addPart();
			System.out.print("Do you wish to add more parts ? Press `Y` for yes and `N` for NO = ");
			if (!sc.next().toUpperCase().equals("Y"))
				choice = false;
		}
		acceptService();
	}

	@Override
	public void acceptService() {
		if (this.remark == null)
			this.remark = "";
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter what is done for maintainance = ");
		this.remark = this.remark + sc.nextLine() + ", ";
		System.out.print("Enter the labour cost for the same = ");
		this.labourCharges = this.labourCharges + sc.nextDouble();
		calculateTotalCost();
	}

	private void addPart() {
		ServiceParts servicePart = new ServiceParts();
		servicePart.addServicePart();
		List<Part> parts = PartService.getAllParts();
		Part part = new Part(servicePart.getPartid());
		if (parts.contains(part)) {
			this.partsList.add(servicePart);
			this.parts_cost = this.parts_cost+ parts.get(parts.indexOf(part)).getPrice() * servicePart.getQuantity();
		}
	}

	@Override
	public void calculateTotalCost() {
		this.total_cost = this.labourCharges + this.parts_cost;
	}

}

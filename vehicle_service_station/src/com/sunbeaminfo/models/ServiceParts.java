package com.sunbeaminfo.models;

import java.util.Objects;
import java.util.Scanner;

import com.sunbeaminfo.service.PartService;

public class ServiceParts {
	private int partid;
	private int quantity;
	
	public ServiceParts() {
	}

	public ServiceParts(int partid, int quantity) {
		this.partid = partid;
		this.quantity = quantity;
	}

	public int getPartid() {
		return partid;
	}

	public void setPartid(int partid) {
		this.partid = partid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ServiceParts [partid=" + partid + ", quantity=" + quantity + "]";
	}
	
	public void addServicePart() {
		PartService.displayAllParts();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the part id = ");
		this.partid = sc.nextInt();
		System.out.print("Enter the part quantity = ");
		this.quantity = sc.nextInt();
	}

	@Override
	public int hashCode() {
		return Objects.hash(partid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceParts other = (ServiceParts) obj;
		return partid == other.partid;
	}
	
	
	
	
}

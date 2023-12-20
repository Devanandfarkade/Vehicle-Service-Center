package com.sunbeaminfo.models;

import java.util.Scanner;

public class Oil extends Service {
	private static final long serialVersionUID = 1L;
	private double oil_cost;

	public Oil() {
		super("oil");
	}

	public double getOil_cost() {
		return oil_cost;
	}

	public void setOil_cost(double oil_cost) {
		this.oil_cost = oil_cost;
	}

	@Override
	public void acceptService() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter which oil or additive is added = ");
		this.remark = sc.nextLine();
		System.out.print("Enter the oil/Additive cost for the same = ");
		this.oil_cost = sc.nextDouble();
		calculateTotalCost();
	}

	@Override
	public void calculateTotalCost() {
		this.total_cost = this.oil_cost;
	}

}

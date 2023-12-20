package com.sunbeaminfo.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Part implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private double price;
	
	public Part() {
	}
	
	public Part(int id) {
	this.id = id;
	}
	
	public Part(int id, String name, String description, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Part other = (Part) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}
	
	
	
	

	
}

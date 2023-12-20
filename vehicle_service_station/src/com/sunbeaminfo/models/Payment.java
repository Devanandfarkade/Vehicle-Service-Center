package com.sunbeaminfo.models;

import java.time.LocalDate;

public class Payment {
	private int id;
	private double amount;
	private LocalDate tx_date;
	private int service_request_id;
	
	public Payment() {
	}
	
	public Payment(int id, double amount, LocalDate tx_date, int service_request_id) {
		this.id = id;
		this.amount = amount;
		this.tx_date = tx_date;
		this.service_request_id = service_request_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getTx_date() {
		return tx_date;
	}

	public void setTx_date(LocalDate tx_date) {
		this.tx_date = tx_date;
	}

	public int getService_request_id() {
		return service_request_id;
	}

	public void setService_request_id(int service_request_id) {
		this.service_request_id = service_request_id;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", tx_date=" + tx_date + ", service_request_id="
				+ service_request_id + "]";
	}
	
	
	
}

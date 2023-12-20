package com.sunbeaminfo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.sunbeaminfo.dao.PaymentDao;
import com.sunbeaminfo.models.Payment;
import com.sunbeaminfo.models.ServiceRequest;

public class PaymentService {

	public static void payBill() {
		ServiceRequest serviceRequest = ServiceRequestService.getExistingService();
		if (serviceRequest != null) {
			System.out.print("Enter the amount of cash paid - ");
			double paid_amount = new Scanner(System.in).nextDouble();
			try (PaymentDao paymentDao = new PaymentDao()) {
				paymentDao.insertPayment(paid_amount, serviceRequest.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void getDateWiseTotalPayment(LocalDate date) {
		try (PaymentDao paymentDao = new PaymentDao()) {
			List<Payment> paymentsList = paymentDao.getDateWisePayment(date);
			double totalPayments = 0;
			if (paymentsList != null)
				for (Payment payment : paymentsList) {
					System.out.println(payment);
					totalPayments = totalPayments + payment.getAmount();
				}
			System.out.println("Total Business on "+date+" - "+totalPayments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getTodaysPayments() {
		getDateWiseTotalPayment(LocalDate.now());
	}

	public static void getSpecificDatePayments() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the day - ");
		int day = sc.nextInt();
		System.out.print("Enter the month - ");
		int month = sc.nextInt();
		System.out.print("Enter the year - ");
		int year = sc.nextInt();
		getDateWiseTotalPayment(LocalDate.of(year, month, day));
	}

}

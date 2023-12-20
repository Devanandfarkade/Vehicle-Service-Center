package com.sunbeaminfo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.models.Payment;
import com.sunbeaminfo.utils.DBUtil;

public class PaymentDao implements AutoCloseable {
	private Connection connection;

	public PaymentDao() throws SQLException {
		this.connection = DBUtil.getConnection();
	}

	public void insertPayment(double paid_amount, int id) throws SQLException {
		String sql = "INSERT INTO payments (service_request_id,paid_amount) VALUES (?,?)";
		try (PreparedStatement insertPayment = this.connection.prepareStatement(sql)) {
			insertPayment.setInt(1, id);
			insertPayment.setDouble(2, paid_amount);
			insertPayment.executeUpdate();
		}
	}

	public List<Payment> getDateWisePayment(LocalDate date) throws SQLException {
		String sql = "SELECT id,DATE(tx_date),paid_amount,service_request_id FROM payments WHERE DATE(tx_date) = DATE(?)";
		try (PreparedStatement fetchPayment = this.connection.prepareStatement(sql)) {
			fetchPayment.setDate(1, Date.valueOf(date));
			ResultSet resultSet = fetchPayment.executeQuery();
			List<Payment> paymentList = new ArrayList<Payment>();
			while (resultSet.next()) {
				paymentList.add(new Payment(resultSet.getInt(1), resultSet.getDouble(3),
						resultSet.getDate(2).toLocalDate(), resultSet.getInt(4)));
			}
			return paymentList;
		}

	}

	@Override
	public void close() throws Exception {
		if (this.connection != null)
			this.connection.close();

	}

}

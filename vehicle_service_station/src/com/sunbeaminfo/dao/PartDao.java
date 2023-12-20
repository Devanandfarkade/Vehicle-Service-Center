package com.sunbeaminfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.models.Part;
import com.sunbeaminfo.utils.DBUtil;

public class PartDao implements AutoCloseable {
	private Connection connection;

	public PartDao() throws SQLException {
		this.connection = DBUtil.getConnection();
	}


	public void insertPart(Part part) throws SQLException {
		String sql = "INSERT INTO parts (name,description,price) VALUES(?,?,?)";
		try (PreparedStatement insertPart = this.connection.prepareStatement(sql)) {
			insertPart.setString(1, part.getName());
			insertPart.setString(2, part.getDescription());
			insertPart.setDouble(3, part.getPrice());
			insertPart.executeUpdate();
		}
	}

	public List<Part> fetchAllParts() throws SQLException {
		String sql = "SELECT id,name,description,price FROM parts";
		try (PreparedStatement fetchAllParts = this.connection.prepareStatement(sql)) {
			ResultSet resultSet =  fetchAllParts.executeQuery();
			List<Part> partsList = new ArrayList<Part>();
			while (resultSet.next())
				partsList.add(new Part(resultSet.getInt(1),resultSet.getString(2),
						resultSet.getString(3),resultSet.getDouble(4)));
			return partsList;
		}
	}

	public boolean editPart(int id, double price)throws SQLException {
		String sql = "UPDATE parts SET price = ? WHERE id = ?";
		try (PreparedStatement editPart = this.connection.prepareStatement(sql)) {
			editPart.setDouble(1, price);
			editPart.setInt(2, id);
			return editPart.executeUpdate() == 1 ? true : false;
		}
	}

	public boolean deletePart(int id)throws SQLException {
		String sql = "DELETE FORM parts WHERE id = ?";
		try (PreparedStatement deletePart = this.connection.prepareStatement(sql)) {
			deletePart.setInt(1, id);
			return deletePart.executeUpdate() == 1 ? true : false;
		}
	}

	@Override
	public void close() throws SQLException {
		this.connection.close();
	}

}

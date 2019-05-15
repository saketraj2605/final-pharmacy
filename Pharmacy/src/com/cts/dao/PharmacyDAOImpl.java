package com.cts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.model.Login;
import com.cts.model.Medicine;

public class PharmacyDAOImpl implements PharmacyDAO {
	
	private String jdbcDriver;	
	private String jdbcPassword;
	 private String jdbcURL;
	 private String jdbcUsername;
	 Connection jdbcConnection = null;

	public PharmacyDAOImpl() {
		super();
		
	
	}
	
	
	public PharmacyDAOImpl(String jdbcDriver, String jdbcUsername, String jdbcPassword, String jdbcURL) {
		
		this.jdbcDriver = jdbcDriver;
		this.jdbcPassword = jdbcPassword;
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
	}
	
	
	@Override
	public void connect() throws SQLException {
		try {
	
		if(jdbcConnection == null || jdbcConnection.isClosed()){
				Class.forName("com.mysql.jdbc.Driver");
				jdbcConnection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy","root","root");
				
			}
		}  catch (ClassNotFoundException e) {
				System.out.println(" Driver not");
				e.printStackTrace();
			}
	}

	@Override
	public boolean validate(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			String uname = (String)request.getParameter("uname");
			String pswd =  (String)request.getParameter("psw");
			System.out.println(uname);
			System.out.println(pswd);

			connect();
			String getLoginQuery = "select * from login";
			PreparedStatement ps= jdbcConnection.prepareStatement(getLoginQuery);
			ResultSet rs = ps.executeQuery();
			
			
			
			while(rs.next()) {
				Login login = new Login();
				login.setUserId(rs.getInt("user_id"));
				login.setUserName(rs.getString("user_name"));
				login.setPassword(rs.getString("user_password"));
				login.setRole(rs.getString("user_role"));
				System.out.println(login.getUserName());
				System.out.println(login.getPassword());

				if(login.getUserName().equals(uname) && login.getPassword().equals(pswd)){
					request.setAttribute("user", login);
					System.out.println("inside if");
					return true;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public ArrayList<Medicine> listAllMedicine() throws SQLException {
		ArrayList<Medicine> medicineList= new ArrayList<>();
		try{
				connect();
				String getBookQuery = "select * from medicine";
				PreparedStatement ps= jdbcConnection.prepareStatement(getBookQuery);
				ResultSet rs = ps.executeQuery();
				System.out.println(jdbcConnection);
				
				while(rs.next()) {
					Medicine medicine = new Medicine();
					medicine.setId(rs.getInt("medicine_id"));
					medicine.setName(rs.getString("medicine_name"));
					medicine.setType(rs.getString("medicine_type"));
					medicine.setUnit(rs.getString("medicine_unit"));
					medicine.setUnitValue(rs.getString("medicine_unit_value"));
					medicine.setUnitPrice(rs.getInt("medicine_unit_price"));
					medicine.setExpDate(rs.getDate("medicine_expiry"));
					medicine.setStockStatus(rs.getString("medicine_stock"));
					medicineList.add(medicine);
				}
		} catch (SQLException e){
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return medicineList;
	
	}


	@Override
	public Medicine getById(HttpServletRequest request,
			HttpServletResponse response) {
		Medicine medicine = new Medicine();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			
			connect();
		String getMedicineByIdQuery = "select * from medicine where medicine_id=?";
		java.sql.PreparedStatement ps = jdbcConnection.prepareStatement(getMedicineByIdQuery);
		ps.setInt(1,id);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			medicine.setId(Integer.parseInt(rs.getString("medicine_id")));
			medicine.setName(rs.getString("medicine_name"));
			medicine.setType(rs.getString("medicine_type"));
			medicine.setUnit(rs.getString("medicine_unit"));
			medicine.setUnitValue(rs.getString("medicine_unit_value"));
			medicine.setUnitPrice(rs.getInt("medicine_unit_price"));
			medicine.setExpDate(rs.getDate("medicine_expiry"));
			medicine.setStockStatus(rs.getString("medicine_stock"));
			
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medicine;
	}
}











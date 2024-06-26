package com.devansh.cnp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.devansh.cnp.model.Coupon;
import com.devansh.cnp.util.ConnectionUtil;

public class CouponDAO {

	public void save(Coupon coupon) {
		Connection connection = ConnectionUtil.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("insert into coupon (code,discount,exp_date) values (?,?,?)");
			statement.setString(1,coupon.getCode());
			statement.setBigDecimal(2, coupon.getDiscount());
			statement.setString(3, coupon.getExpDate());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public Coupon findByCode(String code) {
		Coupon coupon = new Coupon();
		Connection connection = ConnectionUtil.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from coupon where code = ?");
			statement.setString(1,code);
;
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				coupon.setId(resultSet.getInt(1));
				coupon.setCode(resultSet.getString(2));
				coupon.setDiscount(resultSet.getBigDecimal(3));
				coupon.setExpDate(resultSet.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return coupon;
	}
	
	public ArrayList<Coupon> displayCoupons() {
		
		ArrayList<Coupon> coup= new ArrayList<Coupon>();
		Connection connection = ConnectionUtil.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from coupon");
			ResultSet resultSet = statement.executeQuery();
			Coupon coupon = null;
			while(resultSet.next()) {
				coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCode(resultSet.getString(2));
				coupon.setDiscount(resultSet.getBigDecimal(3));
				coupon.setExpDate(resultSet.getString(4));
				coup.add(coupon);
//				System.out.println(coupon.getCode());
				
			}
			for(Coupon coupons : coup) {
				System.out.println(coupons.getCode()+"abc");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return coup;
		
	}
	
}

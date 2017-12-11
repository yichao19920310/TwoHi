package com.yichao.dao;

import java.sql.SQLException;

import com.yichao.bean.Admin;
import com.yichao.bean.Car;

public interface AdminDao {

	public abstract Admin getAdminByName(String adminName) throws SQLException;
	public abstract boolean insertCar(Car car) throws SQLException;
	public abstract boolean updateLendPrice(int carId, double lendprice) throws SQLException;
	public abstract boolean updateCarStatus(int carId, int carStatus) throws SQLException;
	
	
	
	
	
	
}

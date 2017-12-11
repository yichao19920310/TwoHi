package com.yichao.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.yichao.bean.Car;
import com.yichao.bean.LendRecord;
import com.yichao.bean.OrderRecord;
import com.yichao.bean.User;

public interface UserDao {
	public abstract User getUserByName(String userName) throws SQLException;
	public abstract boolean insertUser(String userName, String userPwd) throws SQLException;
	
	public abstract boolean lendCar(int carId, int lendDays) throws SQLException;
	public abstract boolean orderCar(int carId) throws SQLException;
	public abstract boolean returnCar(int carId) throws SQLException;
	
	
	
	
	 
	public abstract boolean lendCarByOrder(int carId, int lendDays, int orId) throws SQLException;
}

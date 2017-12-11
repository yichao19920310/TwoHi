package com.yichao.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.yichao.bean.Car;

public interface CarDao {

	public abstract ArrayList<Car> getCarList() throws SQLException;
	public abstract Car getCarById(int carId) throws SQLException;
	public abstract ArrayList<Car> getCarByName(String carName) throws SQLException;
}

package com.yichao.biz;

import com.yichao.bean.Car;
import com.yichao.log4j.Log4j;

public interface AdminBiz extends Log4j{

	public abstract boolean adminLogin(String adminName, String adminPwd);
	
	public abstract boolean addCar(Car car);
	public abstract boolean updateCarStatus(int carId, int carStatus);
	public abstract boolean updateCarLendPrice(int carId, double carLendPrice);
}

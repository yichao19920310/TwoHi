package com.yichao.biz;

import com.yichao.bean.Car;

public interface AdminBiz {

	public abstract boolean adminLogin(String adminName, String adminPwd);
	
	public abstract boolean addCar(Car car);
	public abstract boolean updateCarStatus(int carId, int carStatus);
	public abstract boolean updateCarLendPrice(int carId, double carLendPrice);
}

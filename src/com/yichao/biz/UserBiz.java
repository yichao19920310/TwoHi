package com.yichao.biz;

import com.yichao.bean.Car;

public interface UserBiz {
	public abstract boolean userLogin(String userName, String userPwd);
	public abstract boolean userRegist(String userName, String userPwd);
	public abstract boolean adminLogin(String userName, String userPwd);
	public abstract void showAllCar(int userStatus);
	public abstract void showAllCar(int userStatus, int searchType, int searchId);
	public abstract void sortCar(int userStatus, int sortType);
	public abstract void showCarById(int userStatus, int carId);
	public abstract void showCarByName(int userStatus, String carName);
	public abstract void showMyLendRecord();
	public abstract void showMyOrderRecord();
	public abstract boolean lendCar(int carId, int lendDays);
	public abstract boolean returnCar(int carId);
	public abstract void showAllLendRecord();
	public abstract void showLendRecordByUser(int userId);
	public abstract void showLendRecordByCar(int carId);
	public abstract boolean addCar(Car car);
	public abstract boolean updateCarStatus(int carId, int carStatus);
	public abstract boolean updateCarLendPrice(int carId, double carLendPrice);
	public abstract boolean orderCar(int carId);
	public abstract boolean lendOrderCar(int carId);
	public abstract void showAllOrderRecord();
	public abstract void showOrderRecordByUser(int userId);
	public abstract void showOrderRecordByCar(int carId);
}

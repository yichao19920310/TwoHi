package com.yichao.biz;

import com.yichao.log4j.Log4j;

public interface UserBiz extends Log4j{
	public abstract boolean userLogin(String userName, String userPwd);
	public abstract boolean userRegist(String userName, String userPwd);
	
	
	
	public abstract boolean lendCar(int carId, int lendDays);
	public abstract boolean returnCar(int carId);
	
	public abstract boolean orderCar(int carId,int orderDays);
	public abstract boolean lendOrderCar(int carId,int lendDays);
	public abstract void showLendCar(int carId);
	public abstract void showOrderCar(int carId);
	public abstract void showReturnCar(int carId);
	
}

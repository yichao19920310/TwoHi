package com.yichao.biz;

public interface UserBiz {
	public abstract boolean userLogin(String userName, String userPwd);
	public abstract boolean userRegist(String userName, String userPwd);
	
	
	
	public abstract boolean lendCar(int carId, int lendDays);
	public abstract boolean returnCar(int carId);
	
	public abstract boolean orderCar(int carId);
	public abstract boolean lendOrderCar(int carId);
	
}

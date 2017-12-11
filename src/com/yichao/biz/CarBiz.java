package com.yichao.biz;

public interface CarBiz {

	public abstract void showAllCar();
	public abstract void showAllCar(int searchType, int searchId);
	public abstract void sortCar(int sortType);
	public abstract void showCarById(int carId);
	public abstract void showCarByName(String carName);
}

package com.yichao.biz;

import com.yichao.log4j.Log4j;

public interface CarBiz extends Log4j{

	public abstract void showAllCar();
	public abstract void showAllCar(int searchType, int searchId);
	public abstract void sortCar(int sortType);
	public abstract void showCarById(int carId);
	public abstract void showCarByName(String carName);
}

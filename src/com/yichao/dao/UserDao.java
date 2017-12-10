package com.yichao.dao;

import java.util.ArrayList;

import com.yichao.bean.Car;
import com.yichao.bean.LendRecord;
import com.yichao.bean.OrderRecord;
import com.yichao.bean.User;

public interface UserDao {
	public abstract User getUserByName(String userName);
	public abstract boolean insertUser(String userName, String userPwd);
	public abstract ArrayList<Car> getCarList();
	public abstract Car getCarById(int carId);
	public abstract ArrayList<Car> getCarByName(String carName);
	public abstract boolean lendCar(int carId, int lendDays);
	public abstract boolean orderCar(int carId);
	public abstract boolean returnCar(int carId);
	public abstract boolean insertCar(Car car);
	public abstract boolean updateLendPrice(int carId, double lendprice);
	public abstract boolean updateCarStatus(int carId, int carStatus);
	public abstract ArrayList<LendRecord> getLrList();
	public abstract ArrayList<OrderRecord> getOrList();
	public abstract ArrayList<LendRecord> getLrListByUser(int userId);
	public abstract ArrayList<OrderRecord> getOrListByUser(int userId);
	public abstract ArrayList<LendRecord> getLrListByCar(int carId);
	public abstract ArrayList<OrderRecord> getOrListByCar(int carId);
	/**  
	 * @Title: lendCarByOrder  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param carId
	 * @param @param lendDays
	 * @param @param orId    参数  
	 * @return void    返回类型  
	 * @throws  
	 */  
	public abstract void lendCarByOrder(int carId, int lendDays, int orId);
}

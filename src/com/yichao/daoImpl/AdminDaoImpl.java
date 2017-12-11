package com.yichao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yichao.bean.Admin;
import com.yichao.bean.Car;
import com.yichao.bean.LendRecord;
import com.yichao.bean.OrderRecord;
import com.yichao.dao.AdminDao;
import com.yichao.dao.CarDao;
import com.yichao.dao.LendRecordDao;
import com.yichao.dao.OrderRecordDao;
import com.yichao.tools.DbHelper;

public class AdminDaoImpl implements AdminDao,CarDao,LendRecordDao,OrderRecordDao{

	private Connection mConnection;
	private PreparedStatement mStatement;
	private ResultSet rSet;
	private int rNum;
	
	private DbHelper mDB;
	public AdminDaoImpl() {
		mDB = new DbHelper();
		mConnection = mDB.getConnection();
	}
	@Override
	public ArrayList<OrderRecord> getOrList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<OrderRecord> getOrListByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<OrderRecord> getOrListByCar(int carId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<LendRecord> getLrList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<LendRecord> getLrListByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<LendRecord> getLrListByCar(int carId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Car> getCarList() throws SQLException {
		String sql = "select * from carlist";
		ArrayList<Car> carList = new ArrayList<>();		
		mStatement = mConnection.prepareStatement(sql);
		rSet = mStatement.executeQuery();
		while(rSet.next()) {
			Car car = new Car();
			car.setCarId(rSet.getInt("CARID"));
			car.setCarName(rSet.getString("CARNAME"));
			car.setCarBrand(rSet.getString("CARBRAND"));
			car.setCarBrandId(rSet.getInt("CARBRANDID"));
			car.setCarType(rSet.getString("CARTYPE"));
			car.setCarTypeId(rSet.getInt("CARTYPEID"));
			car.setCarRemark(rSet.getString("CARREMARK"));
			car.setCarPrice(rSet.getDouble("CARPRICE"));
			car.setCarLendPrice(rSet.getDouble("CARLENDPRICE"));
			car.setCarStatus(rSet.getInt("CARSTATUS"));
			car.setCarLendStatus(rSet.getInt("CARLENDSTATUS"));
			car.setCarOrderStatus(rSet.getInt("CARORDERSTATUS"));
			carList.add(car);
			}
		
		
		return carList;
	}

	@Override
	public Car getCarById(int carId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Car> getCarByName(String carName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin getAdminByName(String adminName) throws SQLException {
		String sql = "select * from adminlist where adminname = ?";
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setString(1, adminName);
		rSet = mStatement.executeQuery();
		Admin a = new Admin();
		if(rSet.next()) {
			a.setAdminId(rSet.getInt("ADMINID"));
			a.setAdminName(rSet.getString("ADMINNAME"));
			a.setAdminPwd(rSet.getString("ADMINPWD"));
			return a;
		}
		return null;
	}

	@Override
	public boolean insertCar(Car car) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLendPrice(int carId, double lendprice) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCarStatus(int carId, int carStatus) {
		// TODO Auto-generated method stub
		return false;
	}

	
}

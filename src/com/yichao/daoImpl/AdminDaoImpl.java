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
	public ArrayList<OrderRecord> getOrList() throws SQLException {
		String sql = "select * from orderrecordlist";
		mStatement = mConnection.prepareStatement(sql);		
		rSet = mStatement.executeQuery();
		ArrayList<OrderRecord> orList = new ArrayList<>();
		while(rSet.next()) {
			OrderRecord or = new OrderRecord();
			or.setOrId(rSet.getInt("ORID"));
			or.setOrNumber(rSet.getString("ORNUMBER"));
			or.setCarId(rSet.getInt("CARID"));
			or.setCarName(rSet.getString("CARNAME"));
			or.setUserId(rSet.getInt("USERID"));
			or.setUserName(rSet.getString("USERNAME"));
			or.setExpLendDate(rSet.getDate("EXPLENDDATE"));
			or.setActLendDate(rSet.getDate("ACTLENDDATE"));
			or.setOrStatus(rSet.getInt("ORSTATUS"));
			orList.add(or);			
		}
		
		return orList;
	}

	@Override
	public ArrayList<OrderRecord> getOrListByUser(int userId) throws SQLException {
		String sql = "select * from orderrecordlist where userid = ?";
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setInt(1, userId);
		rSet = mStatement.executeQuery();
		ArrayList<OrderRecord> orList = new ArrayList<>();
		while(rSet.next()) {
			OrderRecord or = new OrderRecord();
			or.setOrId(rSet.getInt("ORID"));
			or.setOrNumber(rSet.getString("ORNUMBER"));
			or.setCarId(rSet.getInt("CARID"));
			or.setCarName(rSet.getString("CARNAME"));
			or.setUserId(rSet.getInt("USERID"));
			or.setUserName(rSet.getString("USERNAME"));
			or.setExpLendDate(rSet.getDate("EXPLENDDATE"));
			or.setActLendDate(rSet.getDate("ACTLENDDATE"));
			or.setOrStatus(rSet.getInt("ORSTATUS"));
			orList.add(or);			
		}
		
		return orList;
	}

	@Override
	public ArrayList<OrderRecord> getOrListByCar(int carId) throws SQLException {
		String sql = "select * from orderrecordlist where carid = ?";
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setInt(1, carId);
		rSet = mStatement.executeQuery();
		ArrayList<OrderRecord> orList = new ArrayList<>();
		while(rSet.next()) {
			OrderRecord or = new OrderRecord();
			or.setOrId(rSet.getInt("ORID"));
			or.setOrNumber(rSet.getString("ORNUMBER"));
			or.setCarId(rSet.getInt("CARID"));
			or.setCarName(rSet.getString("CARNAME"));
			or.setUserId(rSet.getInt("USERID"));
			or.setUserName(rSet.getString("USERNAME"));
			or.setExpLendDate(rSet.getDate("EXPLENDDATE"));
			or.setActLendDate(rSet.getDate("ACTLENDDATE"));
			or.setOrStatus(rSet.getInt("ORSTATUS"));
			orList.add(or);			
		}
		
		return orList;
	}

	@Override
	public ArrayList<LendRecord> getLrList() throws SQLException {
		String sql = "select * from lendrecordlist";
		mStatement = mConnection.prepareStatement(sql);
		rSet = mStatement.executeQuery();
		ArrayList<LendRecord> lrList = new ArrayList<>();
		while(rSet.next()) {
			LendRecord lr = new LendRecord();
			lr.setLrId(rSet.getInt("LRID"));
			lr.setLrNumber(rSet.getString("LRNUMBER"));
			lr.setCarId(rSet.getInt("CARID"));
			lr.setCarName(rSet.getString("CARNAME"));
			lr.setUserId(rSet.getInt("USERID"));
			lr.setUserName(rSet.getString("USERNAME"));
			lr.setLendDate(rSet.getDate("LENDDATE"));
			lr.setExpRetuDate(rSet.getDate("EXPRETUDATE"));
			lr.setActRetuDate(rSet.getDate("ACTRETUDATE"));
			lr.setCarLendPrice(rSet.getDouble("CARLENDPRICE"));
			lr.setLateFee(rSet.getDouble("LATEFEE"));
			lr.setTotalFee(rSet.getDouble("TOTALFEE"));
			lr.setLrStatus(rSet.getInt("LRSTATUS"));
			
			lrList.add(lr);			
		}
		
		return lrList;
	}

	@Override
	public ArrayList<LendRecord> getLrListByUser(int userId) throws SQLException {
		String sql = "select * from lendrecordlist where userid = ?";
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setInt(1, userId);
		rSet = mStatement.executeQuery();
		ArrayList<LendRecord> lrList = new ArrayList<>();
		while(rSet.next()) {
			LendRecord lr = new LendRecord();
			lr.setLrId(rSet.getInt("LRID"));
			lr.setLrNumber(rSet.getString("LRNUMBER"));
			lr.setCarId(rSet.getInt("CARID"));
			lr.setCarName(rSet.getString("CARNAME"));
			lr.setUserId(rSet.getInt("USERID"));
			lr.setUserName(rSet.getString("USERNAME"));
			lr.setLendDate(rSet.getDate("LENDDATE"));
			lr.setExpRetuDate(rSet.getDate("EXPRETUDATE"));
			lr.setActRetuDate(rSet.getDate("ACTRETUDATE"));
			lr.setCarLendPrice(rSet.getDouble("CARLENDPRICE"));
			lr.setLateFee(rSet.getDouble("LATEFEE"));
			lr.setTotalFee(rSet.getDouble("TOTALFEE"));
			lr.setLrStatus(rSet.getInt("LRSTATUS"));
			
			lrList.add(lr);			
		}
		
		return lrList;
	}

	@Override
	public ArrayList<LendRecord> getLrListByCar(int carId) throws SQLException {
		String sql = "select * from lendrecordlist where carid = ?";
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setInt(1, carId);
		rSet = mStatement.executeQuery();
		ArrayList<LendRecord> lrList = new ArrayList<>();
		while(rSet.next()) {
			LendRecord lr = new LendRecord();
			lr.setLrId(rSet.getInt("LRID"));
			lr.setLrNumber(rSet.getString("LRNUMBER"));
			lr.setCarId(rSet.getInt("CARID"));
			lr.setCarName(rSet.getString("CARNAME"));
			lr.setUserId(rSet.getInt("USERID"));
			lr.setUserName(rSet.getString("USERNAME"));
			lr.setLendDate(rSet.getDate("LENDDATE"));
			lr.setExpRetuDate(rSet.getDate("EXPRETUDATE"));
			lr.setActRetuDate(rSet.getDate("ACTRETUDATE"));
			lr.setCarLendPrice(rSet.getDouble("CARLENDPRICE"));
			lr.setLateFee(rSet.getDouble("LATEFEE"));
			lr.setTotalFee(rSet.getDouble("TOTALFEE"));
			lr.setLrStatus(rSet.getInt("LRSTATUS"));
			
			lrList.add(lr);			
		}
		
		return lrList;
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
	public ArrayList<Car> getCarById(int carId) throws SQLException {
		String sql = "select * from carlist where carid = ?";
		ArrayList<Car> carList = new ArrayList<>();		
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setInt(1, carId);
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
	public ArrayList<Car> getCarByName(String carName) throws SQLException {
		String sql = "select * from carlist where carname like '%'||?||'%'";
		ArrayList<Car> carList = new ArrayList<>();		
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setString(1, carName);
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

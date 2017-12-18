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
	/**
	 * 
	  * <p>Title: getOrList</p>   
	  * <p>Description: 从数据库获取预约记录</p>   
	  * @return 预约记录集合
	  * @throws SQLException   
	  * @see com.yichao.dao.OrderRecordDao#getOrList()
	 */
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
			or.setOrderDate(rSet.getDate("ORDERDATE"));
			orList.add(or);			
		}
		
		return orList;
	}

	/**
	 * 
	  * <p>Title: getOrListByUser</p>   
	  * <p>Description: 从数据库获取指定用户预约记录</p>   
	  * @param userId
	  * @return 预约记录集合
	  * @throws SQLException   
	  * @see com.yichao.dao.OrderRecordDao#getOrListByUser(int)
	 */
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
			or.setOrderDate(rSet.getDate("ORDERDATE"));
			orList.add(or);			
		}
		
		return orList;
	}

	/**
	 * 
	  * <p>Title: getOrListByCar</p>   
	  * <p>Description: 从数据库获取指定汽车预约记录</p>   
	  * @param carId
	  * @return 预约记录集合
	  * @throws SQLException   
	  * @see com.yichao.dao.OrderRecordDao#getOrListByCar(int)
	 */
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
			or.setOrderDate(rSet.getDate("ORDERDATE"));
			orList.add(or);			
		}
		
		return orList;
	}

	/**
	 * 
	  * <p>Title: getLrList</p>   
	  * <p>Description: 从数据库获取所有租赁记录</p>   
	  * @return 租赁记录集合
	  * @throws SQLException   
	  * @see com.yichao.dao.LendRecordDao#getLrList()
	 */
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

	/**
	 * 
	  * <p>Title: getLrListByUser</p>   
	  * <p>Description: 从数据库获取指定用户租赁记录</p>   
	  * @param userId
	  * @return 租赁记录集合
	  * @throws SQLException   
	  * @see com.yichao.dao.LendRecordDao#getLrListByUser(int)
	 */
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

	/**
	 * 
	  * <p>Title: getCarList</p>   
	  * <p>Description: 从数据库获取所有汽车</p>   
	  * @return 汽车集合
	  * @throws SQLException   
	  * @see com.yichao.dao.CarDao#getCarList()
	 */
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

	/**
	 * 
	  * <p>Title: getCarById</p>   
	  * <p>Description: 从数据库获取指定汽车</p>   
	  * @param carId
	  * @return 汽车集合
	  * @throws SQLException   
	  * @see com.yichao.dao.CarDao#getCarById(int)
	 */
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

	/**
	 * 
	  * <p>Title: getCarByName</p>   
	  * <p>Description: 通过汽车名称从数据库获取汽车,可模糊查询</p>   
	  * @param carName
	  * @return 汽车集合
	  * @throws SQLException   
	  * @see com.yichao.dao.CarDao#getCarByName(java.lang.String)
	 */
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

	/**
	 * 
	  * <p>Title: getAdminByName</p>   
	  * <p>Description: 通过管理员用户名从数据库获取管理员对象</p>   
	  * @param adminName
	  * @return 管理员对象
	  * @throws SQLException   
	  * @see com.yichao.dao.AdminDao#getAdminByName(java.lang.String)
	 */
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

	/**
	 * 
	  * <p>Title: insertCar</p>   
	  * <p>Description: 向数据库插入汽车</p>   
	  * @param car
	  * @return 
	  * @throws SQLException   
	  * @see com.yichao.dao.AdminDao#insertCar(com.yichao.bean.Car)
	 */
	@Override
	public boolean insertCar(Car car) throws SQLException {
		String sql = "insert into carlist values(carid_seq.nextval,?,?,?,?,?,?,?,?,?,default,default)";
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setString(1, car.getCarName());
		mStatement.setString(2, car.getCarRemark());
		mStatement.setString(3, car.getCarBrand());
		mStatement.setInt(4, car.getCarBrandId());
		mStatement.setString(5, car.getCarType());
		mStatement.setInt(6, car.getCarTypeId());
		mStatement.setDouble(7, car.getCarPrice());
		mStatement.setDouble(8, car.getCarLendPrice());
		mStatement.setInt(9, car.getCarStatus());
		rNum = mStatement.executeUpdate();
		if(rNum > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	  * <p>Title: updateLendPrice</p>   
	  * <p>Description: 更改汽车租赁价格</p>   
	  * @param carId
	  * @param lendprice
	  * @return
	  * @throws SQLException   
	  * @see com.yichao.dao.AdminDao#updateLendPrice(int, double)
	 */
	@Override
	public boolean updateLendPrice(int carId, double lendprice) throws SQLException {
		String sql = "update carlist set carlendprice = ? where carid = ?";
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setDouble(1, lendprice);
		mStatement.setInt(2, carId);
		rNum = mStatement.executeUpdate();
		if(rNum>0) {
			mConnection.commit();
			return true;
		}
		return false;
	}

	/**
	 * 
	  * <p>Title: updateCarStatus</p>   
	  * <p>Description: 更改汽车上下架</p>   
	  * @param carId
	  * @param carStatus
	  * @return
	  * @throws SQLException   
	  * @see com.yichao.dao.AdminDao#updateCarStatus(int, int)
	 */
	@Override
	public boolean updateCarStatus(int carId, int carStatus) throws SQLException {
		String sql = "update carlist set carstatus = ? where carid = ?";
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setInt(1, carStatus);
		mStatement.setInt(2, carId);
		rNum = mStatement.executeUpdate();
		if(rNum>0) {
			mConnection.commit();
			return true;
		}
		return false;
	}

	
}

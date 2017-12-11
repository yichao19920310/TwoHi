package com.yichao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.yichao.bean.Car;
import com.yichao.bean.LendRecord;
import com.yichao.bean.OrderRecord;
import com.yichao.bean.User;
import com.yichao.dao.CarDao;
import com.yichao.dao.LendRecordDao;
import com.yichao.dao.OrderRecordDao;
import com.yichao.dao.UserDao;
import com.yichao.tools.DbHelper;

public class UserDaoImpl implements UserDao,CarDao,LendRecordDao,OrderRecordDao {

	private Connection mConnection;
	private PreparedStatement mStatement;
	private ResultSet rSet;
	private int rNum;
	
	private DbHelper mDB;
	public UserDaoImpl() {
		mDB = new DbHelper();
		mConnection = mDB.getConnection();
	}
	/* (非 Javadoc)  
	 * <p>Title: getUserByName</p>  
	 * <p>Description: </p>  
	 * @param userName
	 * @return  
	 * @see com.yichao.dao.UserDao#getUserByName(java.lang.String)  
	 */  
	@Override
	public User getUserByName(String userName) throws SQLException {
		String sql = "select * from UserList where userName = ?";
		User u = new User();	
		//--通过连接获取PreparedStatement对象
		
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setString(1, userName);
		rSet = mStatement.executeQuery();
			
		if (rSet.next()) {
			u = new User();				
			u.setUserId(rSet.getInt("USERID"));	
			u.setUserName(rSet.getString("USERNAME"));
			u.setUserPwd(rSet.getString("USERPWD"));							
		}else {
			u = null;
		}
		
		
		
			
		return u;	
	}

	/* (非 Javadoc)  
	 * <p>Title: insertUser</p>  
	 * <p>Description: </p>  
	 * @param userName
	 * @param userPwd
	 * @return  
	 * @see com.yichao.dao.UserDao#insertUser(java.lang.String, java.lang.String)  
	 */  
	@Override
	public boolean insertUser(String userName, String userPwd) {
		String sql = "insert into userlist values (userid_seq.nextval,?,?)";		
		try {
			mStatement = mConnection.prepareStatement(sql);
			mStatement.setString(1, userName);
			mStatement.setString(2, userPwd);
			rNum = mStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rNum > 0) {
			return true;
		}
	
		return false;
	}

	/* (非 Javadoc)  
	 * <p>Title: getCarList</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.dao.UserDao#getCarList()  
	 */  
	@Override
	public ArrayList<Car> getCarList() throws SQLException {
		String sql = "select * from carlist where carstatus = 1";
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

	/* (非 Javadoc)  
	 * <p>Title: getCarById</p>  
	 * <p>Description: </p>  
	 * @param carId
	 * @return  
	 * @see com.yichao.dao.UserDao#getCarById(int)  
	 */  
	@Override
	public Car getCarById(int carId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)  
	 * <p>Title: getCarByName</p>  
	 * <p>Description: </p>  
	 * @param carName
	 * @return  
	 * @see com.yichao.dao.UserDao#getCarByName(java.lang.String)  
	 */  
	@Override
	public ArrayList<Car> getCarByName(String carName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)  
	 * <p>Title: lendCar</p>  
	 * <p>Description: </p>  
	 * @param carId
	 * @param lendDays
	 * @return  
	 * @see com.yichao.dao.UserDao#lendCar(int, int)  
	 */  
	@Override
	public boolean lendCar(int carId, int lendDays) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (非 Javadoc)  
	 * <p>Title: orderCar</p>  
	 * <p>Description: </p>  
	 * @param carId
	 * @return  
	 * @see com.yichao.dao.UserDao#orderCar(int)  
	 */  
	@Override
	public boolean orderCar(int carId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (非 Javadoc)  
	 * <p>Title: returnCar</p>  
	 * <p>Description: </p>  
	 * @param carId
	 * @return  
	 * @see com.yichao.dao.UserDao#returnCar(int)  
	 */  
	@Override
	public boolean returnCar(int carId) {
		// TODO Auto-generated method stub
		return false;
	}

	

	/* (非 Javadoc)  
	 * <p>Title: getLrList</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.dao.UserDao#getLrList()  
	 */  
	@Override
	public ArrayList<LendRecord> getLrList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)  
	 * <p>Title: getOrList</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.dao.UserDao#getOrList()  
	 */  
	@Override
	public ArrayList<OrderRecord> getOrList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)  
	 * <p>Title: getLrListByUser</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @return  
	 * @see com.yichao.dao.UserDao#getLrListByUser(int)  
	 */  
	@Override
	public ArrayList<LendRecord> getLrListByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)  
	 * <p>Title: getOrListByUser</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @return  
	 * @see com.yichao.dao.UserDao#getOrListByUser(int)  
	 */  
	@Override
	public ArrayList<OrderRecord> getOrListByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)  
	 * <p>Title: getLrListByCar</p>  
	 * <p>Description: </p>  
	 * @param carId
	 * @return  
	 * @see com.yichao.dao.UserDao#getLrListByCar(int)  
	 */  
	@Override
	public ArrayList<LendRecord> getLrListByCar(int carId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)  
	 * <p>Title: getOrListByCar</p>  
	 * <p>Description: </p>  
	 * @param carId
	 * @return  
	 * @see com.yichao.dao.UserDao#getOrListByCar(int)  
	 */  
	@Override
	public ArrayList<OrderRecord> getOrListByCar(int carId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (非 Javadoc)  
	 * <p>Title: lendCarByOrder</p>  
	 * <p>Description: </p>  
	 * @param carId
	 * @param lendDays
	 * @param orId  
	 * @see com.yichao.dao.UserDao#lendCarByOrder(int, int, int)  
	 */  
	@Override
	public boolean lendCarByOrder(int carId, int lendDays, int orId) {
		return false;
		// TODO Auto-generated method stub
		
	}
	
}

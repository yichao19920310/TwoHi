package com.yichao.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.yichao.bean.Car;
import com.yichao.bean.LendRecord;
import com.yichao.bean.OrderRecord;
import com.yichao.bean.User;
import com.yichao.bizImpl.UserBizImpl;
import com.yichao.dao.CarDao;
import com.yichao.dao.LendRecordDao;
import com.yichao.dao.OrderRecordDao;
import com.yichao.dao.UserDao;
import com.yichao.tools.DbHelper;
import com.yichao.views.View;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class UserDaoImpl implements UserDao,CarDao,LendRecordDao,OrderRecordDao {

	private Connection mConnection;
	private PreparedStatement mStatement;
	private CallableStatement mCall;
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
	public boolean insertUser(String userName, String userPwd) throws SQLException {
		mConnection.setAutoCommit(false);
		String sql = "insert into userlist values (userid_seq.nextval,?,?)";		
		
		mStatement = mConnection.prepareStatement(sql);
		mStatement.setString(1, userName);
		mStatement.setString(2, userPwd);
		rNum = mStatement.executeUpdate();
		mConnection.commit();
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
	public ArrayList<Car> getCarById(int carId) throws SQLException {
		String sql = "select * from carlist where carid = ? and carstatus = 1";
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

	/* (非 Javadoc)  
	 * <p>Title: getCarByName</p>  
	 * <p>Description: </p>  
	 * @param carName
	 * @return  
	 * @see com.yichao.dao.UserDao#getCarByName(java.lang.String)  
	 */  
	@Override
	public ArrayList<Car> getCarByName(String carName) throws SQLException {
		String sql = "select * from carlist where carname like '%'||?||'%' and carstatus = 1";
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

	/* (非 Javadoc)  
	 * <p>Title: lendCar</p>  
	 * <p>Description: </p>  
	 * @param carId
	 * @param lendDays
	 * @return  
	 * @see com.yichao.dao.UserDao#lendCar(int, int)  
	 */  
	@SuppressWarnings("finally")
	@Override
	public LendRecord lendCar(int carId, int lendDays) throws SQLException{
	
		LendRecord lr = null;
		try {
			mConnection.setAutoCommit(false);
			mConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);  
			String sql1 = "call lendcar(?,?,?,?)";
			mCall = mConnection.prepareCall(sql1);
			mCall.setInt(1, carId);
			mCall.setInt(2, UserBizImpl.mUser.getUserId());
			mCall.setInt(3, lendDays);
			mCall.registerOutParameter(4, OracleTypes.NUMBER);
			mCall.execute();
			mConnection.commit();
			int lrId = mCall.getInt(4);
			//System.out.println(lrId);
			if(lrId==0) {
				return lr;
			}
			String sql2 = "select * from lendrecordlist where lrid = ?";
			mStatement = mConnection.prepareStatement(sql2);
			mStatement.setInt(1, lrId);
			rSet = mStatement.executeQuery();
			
			if(rSet.next()) {
				lr = new LendRecord();
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
				
			}
			//System.out.println(lr);
			
		} catch (SQLException e) {
			View.ub.logError(e, "命令数据库执行借车事务");
			mConnection.rollback();
			e.printStackTrace();
		}finally {
			return lr;
		}
		
		
	}

	/* (非 Javadoc)  
	 * <p>Title: orderCar</p>  
	 * <p>Description: </p>  
	 * @param carId
	 * @return  
	 * @see com.yichao.dao.UserDao#orderCar(int)  
	 */  
	@Override
	public OrderRecord orderCar(int carId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)  
	 * <p>Title: returnCar</p>  
	 * <p>Description: </p>  
	 * @param carId
	 * @return  
	 * @see com.yichao.dao.UserDao#returnCar(int)  
	 */  
	@SuppressWarnings("finally")
	@Override
	public LendRecord returnCar(int carId,int lrId) {
		LendRecord lr = null;
		try {
			mConnection.setAutoCommit(false);
			mConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);  
			String sql1 = "call returncar(?,?,?,?)";
			mCall = mConnection.prepareCall(sql1);
			mCall.setInt(1, carId);
			mCall.setInt(2, UserBizImpl.mUser.getUserId());
			mCall.setInt(3, lrId);
			mCall.registerOutParameter(4, OracleTypes.NUMBER);
			mCall.execute();
			mConnection.commit();
			int state = mCall.getInt(4);
			System.out.println("信息代码:"+state);
			if(state != 5) {
				return lr;
			}
			
			String sql2 = "select * from lendrecordlist where lrid = ?";
			mStatement = mConnection.prepareStatement(sql2);
			mStatement.setInt(1, lrId);
			rSet = mStatement.executeQuery();
			
			if(rSet.next()) {
				System.out.println("检查点1");
				lr = new LendRecord();
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
				
			}
			//System.out.println(lr);
			
		} catch (SQLException e) {
			View.ub.logError(e, "命令数据库执行还车事务");
			mConnection.rollback();
			e.printStackTrace();
		}finally {
			return lr;
		}		
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

	/* (非 Javadoc)  
	 * <p>Title: getOrListByUser</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @return  
	 * @see com.yichao.dao.UserDao#getOrListByUser(int)  
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
			orList.add(or);			
		}
		
		return orList;
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
	public LendRecord lendCarByOrder(int carId, int lendDays, int orId) {
		return null ;
		// TODO Auto-generated method stub
		
	}
	
}

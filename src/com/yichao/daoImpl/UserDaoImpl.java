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
	/**
	 * 
	  * <p>Title: getUserByName</p>   
	  * <p>Description: 通过用户名从数据库获取用户对象</p>   
	  * @param userName
	  * @return 查到的用户
	  * @throws SQLException   
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

	/**
	 * 
	  * <p>Title: insertUser</p>   
	  * <p>Description: 向数据库插入用户</p>   
	  * @param userName
	  * @param userPwd
	  * @return 
	  * @throws SQLException   
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

	/**
	 * 
	  * <p>Title: getCarList</p>   
	  * <p>Description: 从数据库获取所有上架汽车</p>   
	  * @return 汽车集合
	  * @throws SQLException   
	  * @see com.yichao.dao.CarDao#getCarList()
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

	/**
	 * 
	  * <p>Title: getCarById</p>   
	  * <p>Description: 通过id从数据库获取汽车</p>   
	  * @param carId
	  * @return 汽车集合(理论size<=1)
	  * @throws SQLException   
	  * @see com.yichao.dao.CarDao#getCarById(int)
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

	/**
	 * 
	  * <p>Title: getCarByName</p>   
	  * <p>Description: 通过汽车名从数据库获取汽车</p>   
	  * @param carName
	  * @return 汽车集合
	  * @throws SQLException   
	  * @see com.yichao.dao.CarDao#getCarByName(java.lang.String)
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

	/**
	 * 
	  * <p>Title: lendCar</p>   
	  * <p>Description: 执行借车事务</p>   
	  * @param carId
	  * @param lendDays
	  * @return 租赁记录
	  * @throws SQLException   
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

	/**
	 * 
	  * <p>Title: orderCar</p>   
	  * <p>Description: 执行预约事务</p>   
	  * @param carId
	  * @param orderDays
	  * @return 预约记录
	  * @throws SQLException   
	  * @see com.yichao.dao.UserDao#orderCar(int, int)
	 */
	@Override
	public OrderRecord orderCar(int carId, int orderDays) throws SQLException  {
		mConnection.setAutoCommit(false);
		String sql1 = "update carlist set carorderstatus = 0 where carid = ?";
		mStatement = mConnection.prepareStatement(sql1);
		mStatement.setInt(1, carId);
		rNum = mStatement.executeUpdate();
		if(rNum == 0) {
			mConnection.rollback();
			return null;
		}
		String sql2 = "insert into orderrecordlist values(orid_seq.nextval,ornum(orid_seq.currval),?,getcarnamebyid(?),?,getusernamebyid(?),sysdate,sysdate+?,null,default)";
		mStatement = mConnection.prepareStatement(sql2);
		mStatement.setInt(1, carId);
		mStatement.setInt(2, carId);
		mStatement.setInt(3, UserBizImpl.mUser.getUserId());
		mStatement.setInt(4, UserBizImpl.mUser.getUserId());
		mStatement.setInt(5, orderDays);
		rNum = mStatement.executeUpdate();
		if(rNum == 0) {
			mConnection.rollback();
			return null;
		}else {
			mConnection.commit();
		}
		String sql3 = "select * from orderrecordlist where carid = ? and userid = ? and orstatus = 1";
		mStatement = mConnection.prepareStatement(sql3);
		mStatement.setInt(1, carId);
		mStatement.setInt(2, UserBizImpl.mUser.getUserId());
		rSet = mStatement.executeQuery();
		OrderRecord or = null;
		if(rSet.next()) {
			or = new OrderRecord();
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
				
		}
		return or;
	}

	/**
	 * 
	  * <p>Title: returnCar</p>   
	  * <p>Description: 执行还车事务</p>   
	  * @param carId
	  * @param lrId
	  * @return   借出记录
	  * @see com.yichao.dao.UserDao#returnCar(int, int)
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
			//System.out.println("信息代码:"+state);
			if(state != 5) {
				return lr;
			}
			
			String sql2 = "select * from lendrecordlist where lrid = ?";
			mStatement = mConnection.prepareStatement(sql2);
			mStatement.setInt(1, lrId);
			rSet = mStatement.executeQuery();
			
			if(rSet.next()) {
				//System.out.println("检查点1");
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

	

	@Override
	public ArrayList<LendRecord> getLrList() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public ArrayList<OrderRecord> getOrList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	  * <p>Title: getLrListByUser</p>   
	  * <p>Description: 获取指定用户租赁记录</p>   
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

	/**
	 * 
	  * <p>Title: getOrListByUser</p>   
	  * <p>Description: 获取指定用户预约记录</p>   
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

	
	@Override
	public ArrayList<LendRecord> getLrListByCar(int carId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public ArrayList<OrderRecord> getOrListByCar(int carId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	  * <p>Title: lendCarByOrder</p>   
	  * <p>Description: 执行租赁预约的汽车事务</p>   
	  * @param carId
	  * @param lendDays
	  * @param orId
	  * @return   生成的租赁记录
	  * @see com.yichao.dao.UserDao#lendCarByOrder(int, int, int)
	 */
	@SuppressWarnings("finally")
	@Override
	public LendRecord lendCarByOrder(int carId, int lendDays, int orId) {
		LendRecord lr = null;
		try {
			mConnection.setAutoCommit(false);
			mConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);  
			String sql1 = "call lendordercar(?,?,?,?,?)";
			mCall = mConnection.prepareCall(sql1);
			mCall.setInt(1, carId);
			mCall.setInt(2, UserBizImpl.mUser.getUserId());
			mCall.setInt(3, lendDays);
			mCall.setInt(4, orId);
			mCall.registerOutParameter(5, OracleTypes.NUMBER);
			mCall.execute();
			mConnection.commit();
			int lrId = mCall.getInt(5);
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
			View.ub.logError(e, "命令数据库执行通过预约借车事务");
			mConnection.rollback();
			e.printStackTrace();
		}finally {
			return lr;
		}
		
		
		
	}
	
}

package com.yichao.bizImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.yichao.bean.Car;
import com.yichao.bean.LendRecord;
import com.yichao.bean.OrderRecord;
import com.yichao.bean.User;
import com.yichao.biz.CarBiz;
import com.yichao.biz.LendRecordBiz;
import com.yichao.biz.OrderRecordBiz;
import com.yichao.biz.UserBiz;
import com.yichao.daoImpl.UserDaoImpl;

/**
  * 
  * @ClassName:  UserBizImpl   
  * @Description:用户业务   
  * @author: Dovahkiin  
  * @date:   2017年12月18日 上午10:05:15   
  *
  */
public class UserBizImpl implements UserBiz,CarBiz,LendRecordBiz,OrderRecordBiz {
	/**
	 * 静态成员,用于保存当前登录的用户信息
	 */
	public static User mUser;
	public static ArrayList<Car> mCarList;
	public static ArrayList<LendRecord> mLendRecordList;
	public static ArrayList<OrderRecord> mOrderRecordList;
	public static LendRecord cLr;
	public static OrderRecord cOr;
	public static Car cCar;
	public static UserDaoImpl ud = new UserDaoImpl();
	
	final int ONLINE_CAR = 1;
	final int OFFLINE_CAR = 0;
	final int LENDABLE = 1;
	final int UN_LENDABLE = 0;
	final int ORDERABLE = 1;
	final int UN_ORDERABLE = 0;
	final int ORDER_ACT = 1;
	final int ORDER_FIN = 0;
	final int LEND_ACT = 1;
	final int LEND_FIN = 0;
	final int SEARCH_BRAND = 1;
	final int SEARCH_TYPE = 2;
	/**
	 * 
	  * <p>Title: userLogin</p>   
	  * <p>Description: 登录</p>   
	  * @param userName 用户名
	  * @param userPwd 密码
	  * @return  true:成功 false:失败 
	  * @see com.yichao.biz.UserBiz#userLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean userLogin(String userName, String userPwd) {
		
		User u = null;
		try {
			u = ud.getUserByName(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logError(e,"登录从数据库通过用户名调取用户");
		}
		if(u == null) {
			System.out.println("用户名不存在!");
			logInfo("用户名不存在,登录失败");
			return false;
		}
		if(userPwd.equals(u.getUserPwd())) {
			mUser = u;
			logInfo("登录成功");
			return true;
		}
		System.out.println("密码不匹配!");
		logInfo("密码不匹配,登陆失败");
		return false;
	}
	/**
	 * 
	  * <p>Title: userRegist</p>   
	  * <p>Description: 注册</p>   
	  * @param userName
	  * @param userPwd
	  * @return   true:注册成功 false:注册失败
	  * @see com.yichao.biz.UserBiz#userRegist(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean userRegist(String userName, String userPwd) {
		Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z0-9_.]{5,15}");
		Matcher m1 = p.matcher(userName);
		Matcher m2 = p.matcher(userPwd);
		User u = null;
		try {
			u = ud.getUserByName(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logError(e,"注册从数据库通过用户名获取用户");
		}
		if(u != null) {
			System.out.println("用户名已存在!");
			return false;
		}
		if(m1.matches() && m2.matches()) {
			boolean isSuccess = false;
			try {
				isSuccess = ud.insertUser(userName, userPwd);
			} catch (SQLException e) {
				logError(e,"插入用户进数据库");
				e.printStackTrace();
			}
			
			return isSuccess;			
		}
		System.out.println("用户名或密码格式不正确!");
		return false;
	}
	
	/**
	 * 
	  * <p>Title: showAllCar</p>   
	  * <p>Description: 显示所有汽车</p>      
	  * @see com.yichao.biz.CarBiz#showAllCar()
	 */
	@Override
	public void showAllCar() {
		logInfo("查看所有汽车");
		try {
			mCarList = ud.getCarList();
		} catch (SQLException e) {
			logError(e,"从数据库获取所有汽车");
			e.printStackTrace();
			
		}
		
		System.out.println("=================================================================================");
		System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否可预约");
		for (Car car : mCarList) {
			
			System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
					+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
					+")\t"+car.getCarLendPrice()+"/天\t"+((LENDABLE == car.getCarLendStatus())?"是":"否")
					+"\t"+((ORDERABLE == car.getCarOrderStatus())?"是":"否"));
			
		}		
	}
	/**
	 * 
	  * <p>Title: lendCar</p>   
	  * <p>Description: 借车</p>   
	  * @param carId
	  * @param lendDays
	  * @return   true:成功 false:失败
	  * @see com.yichao.biz.UserBiz#lendCar(int, int)
	 */
	@Override
	public boolean lendCar(int carId, int lendDays) {
		logInfo("租车");		
		boolean isSuccess = false;
		try {
			mOrderRecordList = ud.getOrListByUser(mUser.getUserId());
			mCarList = ud.getCarList();
		} catch (SQLException e) {
			logError(e,"从数据库获取所有上架汽车列表和自己的预约单列表");
			e.printStackTrace();
		}
		
		for (Car car : mCarList) {
			if(carId == car.getCarId() && 
					LENDABLE == car.getCarLendStatus()&& ORDERABLE == car.getCarOrderStatus()) {
				try {
					cLr = ud.lendCar(carId, lendDays);
				} catch (SQLException e) {
					logError(e,"命令数据库执行借车事务异常执行回滚");
					e.printStackTrace();
				}
				if(null == cLr) {
					isSuccess = false;
					
					
				}else {
					isSuccess = true;
				
					
				}
				break;
			}else if(carId == car.getCarId() && 
					LENDABLE == car.getCarLendStatus()&& UN_ORDERABLE == car.getCarOrderStatus()) {
				for (OrderRecord or : mOrderRecordList) {
					if(or.getCarId() == carId && ORDER_ACT == or.getOrStatus()){
						cLr = ud.lendCarByOrder(carId, lendDays,or.getOrId());
						if(null == cLr) {
							isSuccess = false;
							
							
						}else {
							isSuccess = true;
							
							
						}
					}
					break;
				}
				break;
			}
		}
		
		return isSuccess;
		
	}
	
	@Override
	public void showAllOrderRecord() {
		
		
	}
	/**
	 * 
	  * <p>Title: showOrderRecordByUser</p>   
	  * <p>Description: 查看自己预约记录</p>   
	  * @param userId   
	  * @see com.yichao.biz.OrderRecordBiz#showOrderRecordByUser(int)
	 */
	@Override
	public void showOrderRecordByUser(int userId) {
		logInfo("查看自己预约记录");
		try {
			mOrderRecordList = ud.getOrListByUser(userId);
		} catch (SQLException e) {
			logError(e,"通过用户从数据库获取预约记录集合");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("编号\t汽车编号\t汽车名称\t预期借车时间\t实际借车时间\t尚未借车");
		for (OrderRecord or : mOrderRecordList) {
			System.out.println(or.getOrId()+"\t"+or.getCarId()+"\t"+or.getCarName()+"\t"
					+or.getExpLendDate()+"\t"+or.getActLendDate()+"\t"
					+((ORDER_ACT == or.getOrStatus())?"是":"否"));
		}
	}
	@Override
	public void showOrderRecordByCar(int carId) {
		
		
	}
	@Override
	public void showAllLendRecord() {
		
		
	}
	/**
	 * 
	  * <p>Title: showLendRecordByUser</p>   
	  * <p>Description: 查看自己租赁记录</p>   
	  * @param userId   
	  * @see com.yichao.biz.LendRecordBiz#showLendRecordByUser(int)
	 */
	@Override
	public void showLendRecordByUser(int userId) {
		logInfo("查看自己的借出记录");
		try {
			mLendRecordList = ud.getLrListByUser(userId);
		} catch (SQLException e) {
			logError(e,"通过用户从数据库获取借出记录集合");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("编号\t汽车编号\t汽车名称\t租金总额\t租借汽车日期\t最后还车期限\t实际还车时间\t尚未还车");
		for (LendRecord lr : mLendRecordList) {
			System.out.println(lr.getLrId()+"\t"+lr.getCarId()+"\t"+lr.getCarName()+"\t"
					+lr.getTotalFee()+"\t"+lr.getLendDate()+"\t"+lr.getExpRetuDate()+"\t"
					+lr.getActRetuDate()+"\t"+((LEND_ACT == lr.getLrStatus())?"是":"否"));
		}
		
	}
	@Override
	public void showLendRecordByCar(int carId) {
		
		
	}
	/**
	 * 
	  * <p>Title: showAllCar</p>   
	  * <p>Description: 按品牌或类型搜索汽车</p>   
	  * @param searchType 1代表按品牌 2代表按类型
	  * @param searchId   
	  * @see com.yichao.biz.CarBiz#showAllCar(int, int)
	 */
	@Override
	public void showAllCar(int searchType, int searchId) {
		logInfo("按类型品牌查看汽车");
		if(SEARCH_BRAND == searchType) {
			logInfo("按品牌查车");
			System.out.println("=================================================================================");
			System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否可预约");
			for (Car car : mCarList) {
				if(car.getCarBrandId()==searchId) {
					System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
							+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
							+")\t"+car.getCarLendPrice()+"/天\t"+((LENDABLE == car.getCarLendStatus())?"是":"否")
							+"\t"+((ORDERABLE == car.getCarOrderStatus())?"是":"否"));
				}
			}
		}else if(SEARCH_TYPE == searchType) {
			logInfo("按类型查车");
			System.out.println("=================================================================================");
			System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否可预约");
			for (Car car : mCarList) {
				if(car.getCarTypeId()==searchId) {
					System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
							+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
							+")\t"+car.getCarLendPrice()+"/天\t"+((LENDABLE == car.getCarLendStatus())?"是":"否")
							+"\t"+((ORDERABLE == car.getCarOrderStatus())?"是":"否"));
				}
			}
		}
		
	}
	/**
	 * 
	  * <p>Title: sortCar</p>   
	  * <p>Description: 显示排序后的汽车</p>   
	  * @param sortType  1代表按价格降序 2代表按价格升序 
	  * @see com.yichao.biz.CarBiz#sortCar(int)
	 */
	@Override
	public void sortCar(int sortType) {
		logInfo("排序汽车");
		mCarList.sort(new Comparator<Car>() {

			@Override
			public int compare(Car o1, Car o2) {
				if(o1.getCarLendPrice()<o2.getCarLendPrice()) {
					return (sortType==1)?1:-1;
				}else if(o1.getCarLendPrice()==o2.getCarLendPrice()) {
					return 0;
				}else {
					return (sortType==1)?-1:1;
				}
				
			}
			
		});
		System.out.println("=================================================================================");
		System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否可预约");
		for (Car car : mCarList) {
			System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
					+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
					+")\t"+car.getCarLendPrice()+"/天\t"+((LENDABLE == car.getCarLendStatus())?"是":"否")
					+"\t"+((ORDERABLE == car.getCarOrderStatus())?"是":"否"));
		}
		
	}
	@Override
	public void showCarById(int carId) {
		
		
	}
	/**
	 * 
	  * <p>Title: showCarByName</p>   
	  * <p>Description: 通过汽车名车模糊查找汽车</p>   
	  * @param carName   
	  * @see com.yichao.biz.CarBiz#showCarByName(java.lang.String)
	 */
	@Override
	public void showCarByName(String carName) {
		logInfo("通过汽车名称查找汽车");
		ArrayList<Car> carList = new ArrayList<>();
		try {
			carList = ud.getCarByName(carName);
		} catch (SQLException e) {
			logError(e,"通过汽车名称从数据库获取汽车列表");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否可预约");
		for (Car car : carList) {
			System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
					+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
					+")\t"+car.getCarLendPrice()+"/天\t"+((LENDABLE == car.getCarLendStatus())?"是":"否")
					+"\t"+((ORDERABLE == car.getCarOrderStatus())?"是":"否"));
		}
	}
	/**
	 * 
	  * <p>Title: returnCar</p>   
	  * <p>Description: 还车</p>   
	  * @param carId
	  * @return  true:成功 false:失败 
	  * @see com.yichao.biz.UserBiz#returnCar(int)
	 */
	@Override
	public boolean returnCar(int carId) {
		logInfo("还车");
		cLr = null;
		try {
			mLendRecordList = ud.getLrListByUser(mUser.getUserId());
		} catch (SQLException e) {
			logError(e,"从数据库获取指定用户租赁记录");
			e.printStackTrace();
		}
		for (LendRecord lr : mLendRecordList) {
			if(carId == lr.getCarId() && LEND_ACT == lr.getLrStatus()) {
				cLr = ud.returnCar(carId,lr.getLrId());
				break;
			}
		}
		if(cLr == null) {
			System.out.println("您未租赁此车");
			return false;
		}
		return true;
	}
	/**
	 * 
	  * <p>Title: orderCar</p>   
	  * <p>Description: 预约车</p>   
	  * @param carId
	  * @param orderDays
	  * @return true:成功 false:失败  
	  * @see com.yichao.biz.UserBiz#orderCar(int, int)
	 */
	@Override
	public boolean orderCar(int carId, int orderDays) {
		logInfo("预约车");
		boolean isSuccess = false;
		try {			
			mCarList = ud.getCarList();
		} catch (SQLException e) {
			logError(e,"从数据库获取所有上架汽车列表");
			e.printStackTrace();
		}
		for (Car car : mCarList) {
			if(carId == car.getCarId() && LENDABLE == car.getCarLendStatus() && ORDERABLE == car.getCarOrderStatus()) {
				try {
					cOr = ud.orderCar(carId, orderDays);
				} catch (SQLException e) {
					logError(e,"数据库执行预约汽车事物");
					e.printStackTrace();
				}
				if(cOr == null) {
					isSuccess = false;
				}else {
					isSuccess = true;
				}
				break;
			}
		}
		
		return isSuccess;
	}
	/**
	 * 
	  * <p>Title: lendOrderCar</p>   
	  * <p>Description: 租赁已预约的汽车</p>   
	  * @param carId
	  * @param lendDays
	  * @return true:成功 false:失败  
	  * @see com.yichao.biz.UserBiz#lendOrderCar(int, int)
	 */
	@Override
	public boolean lendOrderCar(int carId,int lendDays) {
		logInfo("租赁已预约的汽车");
		boolean isSuccess = false;
		try {
			mOrderRecordList = ud.getOrListByUser(mUser.getUserId());
			mCarList = ud.getCarList();
		} catch (SQLException e) {
			logError(e,"从数据库获取所有上架汽车列表和自己的预约单列表");
			e.printStackTrace();
		}
		for (OrderRecord or : mOrderRecordList) {
			if(carId == or.getCarId() && ORDER_ACT == or.getOrStatus()) {
				for (Car car : mCarList) {
					if(carId == car.getCarId() && LENDABLE == car.getCarLendStatus() && 
							UN_ORDERABLE == car.getCarOrderStatus()) {
						cLr = ud.lendCarByOrder(carId, lendDays, or.getOrId());
						if(null == cLr) {
							isSuccess = false;		
						}else {
							isSuccess = true;									
						}
						break;
					}
				}
				break;
			}
		}
		return isSuccess;
	}
	/**
	 * 
	  * <p>Title: logError</p>   
	  * <p>Description: 错误日志</p>   
	  * @param e 异常对象
	  * @param info  日志内容 
	  * @see com.yichao.log4j.Log4j#logError(java.lang.Throwable, java.lang.String)
	 */
	@Override
	public void logError(Throwable e,String info) {
		Logger logger = Logger.getLogger("UserErrorLog");
		if(mUser != null) {
			logger.error("用户:"+mUser.getUserName()+info+"时出错,错误信息为:"+e.getMessage());
		}else {
			logger.error(info+"时出错:"+e.getMessage());
		}
		
	}
	/**
	 * 
	  * <p>Title: logInfo</p>   
	  * <p>Description: 操作日志</p>   
	  * @param info  日志内容 
	  * @see com.yichao.log4j.Log4j#logInfo(java.lang.String)
	 */
	@Override
	public void logInfo(String info) {
		Logger logger = Logger.getLogger("UserInfoLog");
		if(mUser != null) {
			logger.info("用户:"+mUser.getUserName()+info);
		}else {
			logger.info("未登录用户"+info);
		}
	}
	/**
	 * 
	  * <p>Title: showLendCar</p>   
	  * <p>Description: 显示刚租赁的信息</p>   
	  * @param carId   
	  * @see com.yichao.biz.UserBiz#showLendCar(int)
	 */
	@Override
	public void showLendCar(int carId) {
		try {
			cCar = ud.getCarById(carId).get(0);
		} catch (SQLException e) {
			logError(e,"通过id从数据库获取指定汽车");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("编号\t租借单据流水号\t汽车名称\t备注\t品牌\t类型\t每日租金\t借车日期");
		System.out.println(cLr.getLrId()+"\t"+cLr.getLrNumber()+"\t"+cLr.getCarName()+"\t"+cCar.getCarRemark()
			+"\t"+cCar.getCarBrand()+"\t"+cCar.getCarType()+"\t"+cLr.getCarLendPrice()+"\t"+cLr.getLendDate());
		
		
	}
	/**
	 * 
	  * <p>Title: showOrderCar</p>   
	  * <p>Description: 显示刚预约的信息</p>   
	  * @param carId   
	  * @see com.yichao.biz.UserBiz#showOrderCar(int)
	 */
	@Override
	public void showOrderCar(int carId) {
		try {
			cCar = ud.getCarById(carId).get(0);
		} catch (SQLException e) {
			logError(e,"通过id从数据库获取指定汽车");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("编号\t预约单据流水号\t汽车名称\t备注\t品牌\t类型\t每日租金\t预计借车日期\t预约日期");
		System.out.println(cOr.getOrId()+"\t"+cOr.getOrNumber()+"\t"+cOr.getCarName()+"\t"+cCar.getCarRemark()
			+"\t"+cCar.getCarBrand()+"\t"+cCar.getCarType()+"\t"+cCar.getCarLendPrice()+"\t"+cOr.getExpLendDate()
			+"\t"+cOr.getOrderDate());
		
	}
	/**
	 * 
	  * <p>Title: showReturnCar</p>   
	  * <p>Description: 显示刚还车的信息</p>   
	  * @param carId   
	  * @see com.yichao.biz.UserBiz#showReturnCar(int)
	 */
	@Override
	public void showReturnCar(int carId) {
		try {
			cCar = ud.getCarById(carId).get(0);
		} catch (SQLException e) {
			logError(e,"通过id从数据库获取指定汽车");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("编号\t租借单据流水号\t汽车名称\t每日租金\t滞纳金\t租金总额\t预期还车日期\t实际还车日期");
		System.out.println(cLr.getLrId()+"\t"+cLr.getLrNumber()+"\t"+cLr.getCarName()+"\t"
				+cLr.getCarLendPrice()+"\t"+cLr.getLateFee()+"\t"+cLr.getTotalFee()+"\t"
				+cLr.getExpRetuDate()+"\t"+cLr.getActRetuDate());
	}
	

	
}

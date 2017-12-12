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

public class UserBizImpl implements UserBiz,CarBiz,LendRecordBiz,OrderRecordBiz {
	public static User mUser;
	public ArrayList<Car> mCarList;
	public ArrayList<LendRecord> mLendRecordList;
	public ArrayList<OrderRecord> mOrderRecordList;
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
			logInfo("用户名不存在,登录失败");
			return false;
		}
		if(userPwd.equals(u.getUserPwd())) {
			mUser = u;
			logInfo("登录成功");
			return true;
		}
		logInfo("密码不匹配,登陆失败");
		return false;
	}
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
		if(m1.matches() && m2.matches() && u == null) {
			boolean isSuccess = false;
			try {
				isSuccess = ud.insertUser(userName, userPwd);
			} catch (SQLException e) {
				logError(e,"插入用户进数据库");
				e.printStackTrace();
			}
			
			return isSuccess;			
		}		
		return false;
	}
	
	
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
	
	@Override
	public boolean lendCar(int carId, int lendDays) {
		try {
			mOrderRecordList = ud.getOrListByUser(mUser.getUserId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean isSuccess = false;
		for (Car car : mCarList) {
			if(carId == car.getCarId() && 
					LENDABLE == car.getCarLendStatus()&& ORDERABLE == car.getCarOrderStatus()) {
				isSuccess = ud.lendCar(carId, lendDays);
				break;
			}else if(carId == car.getCarId() && 
					LENDABLE == car.getCarLendStatus()&& UN_ORDERABLE == car.getCarOrderStatus()) {
				for (OrderRecord or : mOrderRecordList) {
					if(or.getCarId() == car.getCarId() && ORDER_ACT == or.getOrStatus()){
						isSuccess = ud.lendCarByOrder(carId, lendDays,or.getOrId());
						break;
					}
				}
			}
		}
		return isSuccess;
	}
	@Override
	public void showAllOrderRecord() {
		// TODO Auto-generated method stub
		
	}
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showAllLendRecord() {
		// TODO Auto-generated method stub
		
	}
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
		System.out.println("编号\t汽车编号\t汽车名称\t租金总额\t借车时间\t最后还车期限\t实际还车时间\t尚未还车");
		for (LendRecord lr : mLendRecordList) {
			System.out.println(lr.getLrId()+"\t"+lr.getCarId()+"\t"+lr.getCarName()+"\t"
					+lr.getTotalFee()+"\t"+lr.getLendDate()+"\t"+lr.getExpRetuDate()+"\t"
					+lr.getActRetuDate()+"\t"+((LEND_ACT == lr.getLrStatus())?"是":"否"));
		}
		
	}
	@Override
	public void showLendRecordByCar(int carId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showAllCar(int searchType, int searchId) {
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
		// TODO Auto-generated method stub
		
	}
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
	@Override
	public boolean returnCar(int carId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean orderCar(int carId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean lendOrderCar(int carId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void logError(Throwable e,String info) {
		Logger logger = Logger.getLogger("UserErrorLog");
		if(mUser != null) {
			logger.error("用户:"+mUser.getUserName()+info+"时出错,错误信息为:"+e.getMessage());
		}else {
			logger.error(info+"时出错:"+e.getMessage());
		}
		
	}
	@Override
	public void logInfo(String info) {
		Logger logger = Logger.getLogger("UserInfoLog");
		if(mUser != null) {
			logger.info("用户:"+mUser.getUserName()+info);
		}else {
			logger.info("用户"+info);
		}
	}
	
	
}

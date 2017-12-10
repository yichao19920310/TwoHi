package com.yichao.bizImpl;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yichao.bean.Car;
import com.yichao.bean.LendRecord;
import com.yichao.bean.OrderRecord;
import com.yichao.bean.User;
import com.yichao.biz.UserBiz;
import com.yichao.dao.UserDao;
import com.yichao.daoImpl.UserDaoImpl;

public class UserBizImpl implements UserBiz {
	public static User mUser;
	public ArrayList<Car> mCarList;
	public ArrayList<LendRecord> mLendRecordList;
	public ArrayList<OrderRecord> mOrderRecordList;
	public static UserDao ud = new UserDaoImpl();
	final int ADMIN_USER = 0;
	final int GENERAL_USER = 1;
	final int ONLINE_CAR = 1;
	final int OFFLINE_CAR = 0;
	final int LENDABLE = 1;
	final int UN_LENDABLE = 0;
	final int ORDERABLE = 1;
	final int UN_ORDERABLE = 0;
	final int ORDER_ACT = 1;
	final int ORDER_FIN = 0;
	@Override
	public boolean userLogin(String userName, String userPwd) {
		
		User u = ud.getUserByName(userName);
		if(u == null) {
			return false;
		}else if(GENERAL_USER != u.getUserStatus()) {
			return false;
		}
		if(userPwd.equals(u.getUserPwd())) {
			mUser = u;
			return true;
		}
		return false;
	}
	@Override
	public boolean userRegist(String userName, String userPwd) {
		Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z0-9_.]{5,15}");
		Matcher m1 = p.matcher(userName);
		Matcher m2 = p.matcher(userPwd);
		if(m1.matches() && m2.matches() && ud.getUserByName(userName)==null) {
			boolean isSuccess = ud.insertUser(userName, userPwd);
			return isSuccess;			
		}
		
		return false;
	}
	
	@Override
	public boolean adminLogin(String userName, String userPwd) {
		
		User u = ud.getUserByName(userName);
		if(u == null) {
			return false;
		}else if(ADMIN_USER != u.getUserStatus()) {
			return false;
		}
		if(userPwd.equals(u.getUserPwd())) {
			mUser = u;
			return true;
		}
		return false;
	}
	@Override
	public void showAllCar(int userStatus) {
		mCarList = ud.getCarList();
		if(GENERAL_USER == userStatus) {
			System.out.println("=====================================================");
			System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否可预约");
			for (Car car : mCarList) {
				if(ONLINE_CAR == car.getCarStatus()) {
					System.out.println(""+car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
							+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
							+")\t"+car.getCarLendPrice()+"/天\t"+((LENDABLE == car.getCarLendStatus())?"是":"否")
							+"\t"+((ORDERABLE == car.getCarOrderStatus())?"是":"否"));
				}
			}
		}else if (ADMIN_USER == userStatus) {
			System.out.println("=====================================================");
			System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否可预约\t是否上架");
			for (Car car : mCarList) {				
				System.out.println(""+car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
						+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
						+")\t"+car.getCarLendPrice()+"/天\t"+((LENDABLE == car.getCarLendStatus())?"是":"否")
						+"\t"+((ORDERABLE == car.getCarOrderStatus())?"是":"否")+"\t"
						+((ONLINE_CAR == car.getCarStatus())?"是":"否"));			
			}
		}
	}
	@Override
	public void showAllCar(int userStatus, int searchType, int searchId) {
	}
	@Override
	public void sortCar(int userStatus, int sortType) {
	}
	@Override
	public void showCarById(int userStatus, int carId) {
	}
	@Override
	public void showCarByName(int userStatus, String carName) {
	}
	@Override
	public void showMyLendRecord() {
	}
	@Override
	public void showMyOrderRecord() {
	}
	@Override
	public boolean lendCar(int carId, int lendDays) {
		mOrderRecordList = ud.getOrListByUser(mUser.getUserId());
		for (Car car : mCarList) {
			if(ONLINE_CAR == car.getCarStatus() && carId == car.getCarId() && 
					LENDABLE == car.getCarLendStatus()&& ORDERABLE == car.getCarOrderStatus()) {
				ud.lendCar(carId, lendDays);
				break;
			}else if(ONLINE_CAR == car.getCarStatus() && carId == car.getCarId() && 
					LENDABLE == car.getCarLendStatus()&& UN_ORDERABLE == car.getCarOrderStatus()) {
				for (OrderRecord or : mOrderRecordList) {
					if(or.getCarId() == car.getCarId() && ORDER_ACT == or.getOrStatus()){
						ud.lendCarByOrder(carId, lendDays,or.getOrId());
						break;
					}
				}
			}
		}
		return false;
	}
	@Override
	public boolean returnCar(int carId) {
		return false;
	}
	@Override
	public void showAllLendRecord() {
	}
	@Override
	public void showLendRecordByUser(int userId) {
	}
	@Override
	public void showLendRecordByCar(int carId) {
	}
	@Override
	public boolean addCar(Car car) {
		return false;
	}
	@Override
	public boolean updateCarStatus(int carId, int carStatus) {
		return false;
	}
	@Override
	public boolean updateCarLendPrice(int carId, double carLendPrice) {
		return false;
	}
	@Override
	public boolean orderCar(int carId) {
		return false;
	}
	@Override
	public boolean lendOrderCar(int carId) {
		return false;
	}
	@Override
	public void showAllOrderRecord() {
	}
	@Override
	public void showOrderRecordByUser(int userId) {
	}
	@Override
	public void showOrderRecordByCar(int carId) {
	}
	
}

package com.yichao.bizImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	@Override
	public boolean userLogin(String userName, String userPwd) {
		
		User u = null;
		try {
			u = ud.getUserByName(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(u == null) {
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
		User u = null;
		try {
			u = ud.getUserByName(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(m1.matches() && m2.matches() && u == null) {
			boolean isSuccess = ud.insertUser(userName, userPwd);
			return isSuccess;			
		}
		
		return false;
	}
	
	
	@Override
	public void showAllCar() {
		try {
			mCarList = ud.getCarList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		mOrderRecordList = ud.getOrListByUser(mUser.getUserId());
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showLendRecordByCar(int carId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showAllCar(int searchType, int searchId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sortCar(int sortType) {
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
		// TODO Auto-generated method stub
		
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
	
	
}

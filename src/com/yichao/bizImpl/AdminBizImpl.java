package com.yichao.bizImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.log4j.Logger;

import com.yichao.bean.Admin;
import com.yichao.bean.Car;
import com.yichao.biz.AdminBiz;
import com.yichao.biz.CarBiz;
import com.yichao.biz.LendRecordBiz;
import com.yichao.biz.OrderRecordBiz;
import com.yichao.daoImpl.AdminDaoImpl;

public class AdminBizImpl implements AdminBiz,CarBiz,LendRecordBiz,OrderRecordBiz{

	public static Admin mAdmin;
	public ArrayList<Car> mCarList;
	private static AdminDaoImpl ad = new AdminDaoImpl();
	
	final int ONLINE_CAR = 1;
	final int OFFLINE_CAR = 0;
	final int LENDABLE = 1;
	final int UN_LENDABLE = 0;
	final int ORDERABLE = 1;
	final int UN_ORDERABLE = 0;
	final int ORDER_ACT = 1;
	final int ORDER_FIN = 0;
	final int SEARCH_BRAND = 1;
	final int SEARCH_TYPE = 2;
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
	public void showAllCar() {
		logInfo("查看所有汽车");
		try {
			mCarList = ad.getCarList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logError(e,"从数据库获取所有汽车");
		}
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

	@Override
	public void showAllCar(int searchType, int searchId) {
		if(SEARCH_BRAND == searchType) {
			System.out.println("=====================================================");
			System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否可预约\t是否上架");
			for (Car car : mCarList) {
				if(car.getCarBrandId()==searchId) {
					System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
							+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
							+")\t"+car.getCarLendPrice()+"/天\t"+((LENDABLE == car.getCarLendStatus())?"是":"否")
							+"\t"+((ORDERABLE == car.getCarOrderStatus())?"是":"否")+"\t"
									+((ONLINE_CAR == car.getCarStatus())?"是":"否"));
				}
			}
		}else if(SEARCH_TYPE == searchType) {
			System.out.println("=====================================================");
			System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否可预约\t是否上架");
			for (Car car : mCarList) {
				if(car.getCarTypeId()==searchId) {
					System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
							+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
							+")\t"+car.getCarLendPrice()+"/天\t"+((LENDABLE == car.getCarLendStatus())?"是":"否")
							+"\t"+((ORDERABLE == car.getCarOrderStatus())?"是":"否")+"\t"
									+((ONLINE_CAR == car.getCarStatus())?"是":"否"));
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

	@Override
	public void showCarById(int carId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showCarByName(String carName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean adminLogin(String adminName, String adminPwd) {
		
			
			Admin a = null;
			try {
				a = ad.getAdminByName(adminName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logError(e,"从数据库通过管理员用户名获取管理员");
			}
			if(a == null) {
				logInfo("用户名不存在,登陆失败");
				return false;
				
			}
			if(adminPwd.equals(a.getAdminPwd())) {
				mAdmin = a;
				logInfo("登录成功");
				return true;
			}
			logInfo("密码不匹配,登陆失败");
			return false;
			
		
	}

	@Override
	public boolean addCar(Car car) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCarStatus(int carId, int carStatus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCarLendPrice(int carId, double carLendPrice) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void logError(Throwable e,String info) {
		Logger logger = Logger.getLogger("AdminErrorLog");
		if(mAdmin != null) {
			logger.error("管理员:"+mAdmin.getAdminName()+info+"时出错,错误信息为:"+e.getMessage());
		}else {
			logger.error(info+"时出错:"+e.getMessage());
		}
	}

	@Override
	public void logInfo(String info) {
		Logger logger = Logger.getLogger("AdminInfoLog");
		
		if(mAdmin != null) {
			logger.info("管理员:"+mAdmin.getAdminName()+info);
		}else {
			logger.info("管理员"+info);
		}
	}

	
}

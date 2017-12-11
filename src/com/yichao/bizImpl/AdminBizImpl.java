package com.yichao.bizImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

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
		try {
			mCarList = ad.getCarList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			}
			if(a == null) {
				return false;
			}
			if(adminPwd.equals(a.getAdminPwd())) {
				mAdmin = a;
				return true;
			}
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

	
}

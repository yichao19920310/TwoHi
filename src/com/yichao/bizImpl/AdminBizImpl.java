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
		logInfo("�鿴��������");
		try {
			mCarList = ad.getCarList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logError(e,"�����ݿ��ȡ��������");
		}
		System.out.println("=====================================================");
		System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t�Ƿ����\t�Ƿ��ԤԼ\t�Ƿ��ϼ�");
		for (Car car : mCarList) {				
			System.out.println(""+car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
					+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
					+")\t"+car.getCarLendPrice()+"/��\t"+((LENDABLE == car.getCarLendStatus())?"��":"��")
					+"\t"+((ORDERABLE == car.getCarOrderStatus())?"��":"��")+"\t"
					+((ONLINE_CAR == car.getCarStatus())?"��":"��"));			
		}
		
	}

	@Override
	public void showAllCar(int searchType, int searchId) {
		if(SEARCH_BRAND == searchType) {
			System.out.println("=====================================================");
			System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t�Ƿ����\t�Ƿ��ԤԼ\t�Ƿ��ϼ�");
			for (Car car : mCarList) {
				if(car.getCarBrandId()==searchId) {
					System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
							+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
							+")\t"+car.getCarLendPrice()+"/��\t"+((LENDABLE == car.getCarLendStatus())?"��":"��")
							+"\t"+((ORDERABLE == car.getCarOrderStatus())?"��":"��")+"\t"
									+((ONLINE_CAR == car.getCarStatus())?"��":"��"));
				}
			}
		}else if(SEARCH_TYPE == searchType) {
			System.out.println("=====================================================");
			System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t�Ƿ����\t�Ƿ��ԤԼ\t�Ƿ��ϼ�");
			for (Car car : mCarList) {
				if(car.getCarTypeId()==searchId) {
					System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
							+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
							+")\t"+car.getCarLendPrice()+"/��\t"+((LENDABLE == car.getCarLendStatus())?"��":"��")
							+"\t"+((ORDERABLE == car.getCarOrderStatus())?"��":"��")+"\t"
									+((ONLINE_CAR == car.getCarStatus())?"��":"��"));
				}
			}
		}
		
		
	}

	@Override
	public void sortCar(int sortType) {
		logInfo("��������");
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
		System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t�Ƿ����\t�Ƿ��ԤԼ\t�Ƿ��ϼ�");
		for (Car car : mCarList) {				
			System.out.println(""+car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
					+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
					+")\t"+car.getCarLendPrice()+"/��\t"+((LENDABLE == car.getCarLendStatus())?"��":"��")
					+"\t"+((ORDERABLE == car.getCarOrderStatus())?"��":"��")+"\t"
					+((ONLINE_CAR == car.getCarStatus())?"��":"��"));			
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
				logError(e,"�����ݿ�ͨ������Ա�û�����ȡ����Ա");
			}
			if(a == null) {
				logInfo("�û���������,��½ʧ��");
				return false;
				
			}
			if(adminPwd.equals(a.getAdminPwd())) {
				mAdmin = a;
				logInfo("��¼�ɹ�");
				return true;
			}
			logInfo("���벻ƥ��,��½ʧ��");
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
			logger.error("����Ա:"+mAdmin.getAdminName()+info+"ʱ����,������ϢΪ:"+e.getMessage());
		}else {
			logger.error(info+"ʱ����:"+e.getMessage());
		}
	}

	@Override
	public void logInfo(String info) {
		Logger logger = Logger.getLogger("AdminInfoLog");
		
		if(mAdmin != null) {
			logger.info("����Ա:"+mAdmin.getAdminName()+info);
		}else {
			logger.info("����Ա"+info);
		}
	}

	
}

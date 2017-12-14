package com.yichao.bizImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.log4j.Logger;

import com.yichao.bean.Admin;
import com.yichao.bean.Car;
import com.yichao.bean.LendRecord;
import com.yichao.bean.OrderRecord;
import com.yichao.biz.AdminBiz;
import com.yichao.biz.CarBiz;
import com.yichao.biz.LendRecordBiz;
import com.yichao.biz.OrderRecordBiz;
import com.yichao.daoImpl.AdminDaoImpl;

public class AdminBizImpl implements AdminBiz,CarBiz,LendRecordBiz,OrderRecordBiz{

	public static Admin mAdmin;
	public ArrayList<Car> mCarList;
	private static AdminDaoImpl ad = new AdminDaoImpl();
	public static Car cCar;
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
		ArrayList<OrderRecord> orList = new ArrayList<>();
		try {
			orList = ad.getOrList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("���\t�������\t��������\t�û����\t�û���\tԤ  Լ   ��  ��\tԤ����������\tʵ����������");
		for (OrderRecord or : orList) {
			System.out.println(or.getOrId()+"\t"+or.getCarId()+"\t"+or.getCarName()+"\t"
					+or.getUserId()+"\t"+or.getUserName()+"\t"+or.getOrderDate()+"\t"
					+or.getExpLendDate()+"\t"+or.getActLendDate());
		}
	}

	@Override
	public void showOrderRecordByUser(int userId) {
		ArrayList<OrderRecord> orList = new ArrayList<>();
		try {
			orList = ad.getOrListByUser(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("���\t�������\t��������\t�û����\t�û���\tԤ  Լ   ��  ��\tԤ����������\tʵ����������");
		for (OrderRecord or : orList) {
			System.out.println(or.getOrId()+"\t"+or.getCarId()+"\t"+or.getCarName()+"\t"
					+or.getUserId()+"\t"+or.getUserName()+"\t"+or.getOrderDate()+"\t"
					+or.getExpLendDate()+"\t"+or.getActLendDate());
		}
		
	}

	@Override
	public void showOrderRecordByCar(int carId) {
		ArrayList<OrderRecord> orList = new ArrayList<>();
		try {
			orList = ad.getOrListByCar(carId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("���\t�������\t��������\t�û����\t�û���\tԤ  Լ   ��  ��\tԤ����������\tʵ����������");
		for (OrderRecord or : orList) {
			System.out.println(or.getOrId()+"\t"+or.getCarId()+"\t"+or.getCarName()+"\t"
					+or.getUserId()+"\t"+or.getUserName()+"\t"+or.getOrderDate()+"\t"
					+or.getExpLendDate()+"\t"+or.getActLendDate());
		}
	}

	@Override
	public void showAllLendRecord() {
		ArrayList<LendRecord> lrList = new ArrayList<>();
		try {
			lrList = ad.getLrList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("���\t�������\t��������\t�û����\t�û���\tÿ�����\t����ܶ�\t���ɽ�\t��  ��  ��  ��\tԤ�ڹ黹����\tʵ�ʹ黹����");
		for (LendRecord lr : lrList) {
			System.out.println(lr.getLrId()+"\t"+lr.getCarId()+"\t"+lr.getCarName()+"\t"
					+lr.getUserId()+"\t"+lr.getUserName()+"\t"+lr.getCarLendPrice()+"\t"
					+lr.getTotalFee()+"\t"+lr.getLateFee()+"\t"+lr.getLendDate()+"\t"
					+lr.getExpRetuDate()+"\t"+lr.getActRetuDate());
		}
	}

	@Override
	public void showLendRecordByUser(int userId) {
		ArrayList<LendRecord> lrList = new ArrayList<>();
		try {
			lrList = ad.getLrListByUser(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("���\t�������\t��������\t�û����\t�û���\tÿ�����\t����ܶ�\t���ɽ�\t��  ��  ��  ��\tԤ�ڹ黹����\tʵ�ʹ黹����");
		for (LendRecord lr : lrList) {
			System.out.println(lr.getLrId()+"\t"+lr.getCarId()+"\t"+lr.getCarName()+"\t"
					+lr.getUserId()+"\t"+lr.getUserName()+"\t"+lr.getCarLendPrice()+"\t"
					+lr.getTotalFee()+"\t"+lr.getLateFee()+"\t"+lr.getLendDate()+"\t"
					+lr.getExpRetuDate()+"\t"+lr.getActRetuDate());
		}
		
	}

	@Override
	public void showLendRecordByCar(int carId) {
		ArrayList<LendRecord> lrList = new ArrayList<>();
		try {
			lrList = ad.getLrListByCar(carId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("���\t�������\t��������\t�û����\t�û���\tÿ�����\t����ܶ�\t���ɽ�\t��  ��  ��  ��\tԤ�ڹ黹����\tʵ�ʹ黹����");
		for (LendRecord lr : lrList) {
			System.out.println(lr.getLrId()+"\t"+lr.getCarId()+"\t"+lr.getCarName()+"\t"
					+lr.getUserId()+"\t"+lr.getUserName()+"\t"+lr.getCarLendPrice()+"\t"
					+lr.getTotalFee()+"\t"+lr.getLateFee()+"\t"+lr.getLendDate()+"\t"
					+lr.getExpRetuDate()+"\t"+lr.getActRetuDate());
		}
		
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
		
		cCar = null;
		try {
			cCar = ad.getCarById(carId).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t�Ƿ����\t�Ƿ��ԤԼ\t�Ƿ��ϼ�");
		System.out.println(""+cCar.getCarId()+"\t"+cCar.getCarName()+"\t"+cCar.getCarRemark()+"\t"
				+cCar.getCarBrand()+"("+cCar.getCarBrandId()+")\t"+cCar.getCarType()+"("+cCar.getCarTypeId()
				+")\t"+cCar.getCarLendPrice()+"/��\t"+((LENDABLE == cCar.getCarLendStatus())?"��":"��")
				+"\t"+((ORDERABLE == cCar.getCarOrderStatus())?"��":"��")+"\t"
				+((ONLINE_CAR == cCar.getCarStatus())?"��":"��"));		
	}

	@Override
	public void showCarByName(String carName) {
		logInfo("ͨ���������Ʋ�������");
		ArrayList<Car> carList = new ArrayList<>();
		try {
			carList = ad.getCarByName(carName);
		} catch (SQLException e) {
			logError(e,"ͨ���������ƴ����ݿ��ȡ�����б�");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t�Ƿ����\t�Ƿ��ԤԼ\t�Ƿ��ϼ�");
		for (Car car : carList) {
			System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
					+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
					+")\t"+car.getCarLendPrice()+"/��\t"+((LENDABLE == car.getCarLendStatus())?"��":"��")
					+"\t"+((ORDERABLE == car.getCarOrderStatus())?"��":"��")+"\t"
					+((ONLINE_CAR == car.getCarStatus())?"��":"��"));
		}
		
	}

	@Override
	public boolean adminLogin(String adminName, String adminPwd) {
		
			
			Admin a = null;
			try {
				a = ad.getAdminByName(adminName);
			} catch (SQLException e) {
				
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
		boolean isSuccess = false;
		try {
			isSuccess = ad.insertCar(car);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public boolean updateCarStatus(int carId, int carStatus) {
		boolean isSuccess = false;
		try {
			isSuccess = ad.updateCarStatus(carId, carStatus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public boolean updateCarLendPrice(int carId, double carLendPrice) {
		boolean isSuccess = false;
		try {
			isSuccess = ad.updateLendPrice(carId, carLendPrice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
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

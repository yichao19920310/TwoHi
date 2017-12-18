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

/**
  * 
  * @ClassName:  AdminBizImpl   
  * @Description:����Աҵ��   
  * @author: Dovahkiin  
  * @date:   2017��12��18�� ����10:06:08   
  *
  */
public class AdminBizImpl implements AdminBiz,CarBiz,LendRecordBiz,OrderRecordBiz{

	/**
	 * ��̬��Ա,���ڱ��浱ǰ��¼�Ĺ���Ա��Ϣ
	 */
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
	/**
	 * 
	  * <p>Title: showAllOrderRecord</p>   
	  * <p>Description: ��ʾ����ԤԼ��¼</p>      
	  * @see com.yichao.biz.OrderRecordBiz#showAllOrderRecord()
	 */
	@Override
	public void showAllOrderRecord() {
		logInfo("�鿴����ԤԼ��¼");
		ArrayList<OrderRecord> orList = new ArrayList<>();
		try {
			orList = ad.getOrList();
		} catch (SQLException e) {
			logError(e,"�����ݿ��ȡ����ԤԼ��¼");
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

	/**
	 * 
	  * <p>Title: showOrderRecordByUser</p>   
	  * <p>Description: ��ʾָ���û�ԤԼ��¼</p>   
	  * @param userId   
	  * @see com.yichao.biz.OrderRecordBiz#showOrderRecordByUser(int)
	 */	
	@Override
	public void showOrderRecordByUser(int userId) {
		logInfo("�鿴ָ���û�ԤԼ��¼");
		ArrayList<OrderRecord> orList = new ArrayList<>();
		try {
			orList = ad.getOrListByUser(userId);
		} catch (SQLException e) {
			logError(e,"�����ݿ��ȡָ���û�ԤԼ��¼");
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

	/**
	 * 
	  * <p>Title: showOrderRecordByCar</p>   
	  * <p>Description: ��ʾָ������ԤԼ��¼</p>   
	  * @param carId   
	  * @see com.yichao.biz.OrderRecordBiz#showOrderRecordByCar(int)
	 */
	@Override
	public void showOrderRecordByCar(int carId) {
		logInfo("�鿴ָ������ԤԼ��¼");
		ArrayList<OrderRecord> orList = new ArrayList<>();
		try {
			orList = ad.getOrListByCar(carId);
		} catch (SQLException e) {
			logError(e,"�����ݿ��ȡָ������ԤԼ��¼");
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

	/**
	 * 
	  * <p>Title: showAllLendRecord</p>   
	  * <p>Description: ��ʾ�������޼�¼</p>      
	  * @see com.yichao.biz.LendRecordBiz#showAllLendRecord()
	 */
	@Override
	public void showAllLendRecord() {
		logInfo("�鿴�������޼�¼");
		ArrayList<LendRecord> lrList = new ArrayList<>();
		try {
			lrList = ad.getLrList();
		} catch (SQLException e) {
			logError(e,"�����ݿ��ȡ�������޼�¼");
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

	/**
	 * 
	  * <p>Title: showLendRecordByUser</p>   
	  * <p>Description: ��ʾָ���û����޼�¼</p>   
	  * @param userId   
	  * @see com.yichao.biz.LendRecordBiz#showLendRecordByUser(int)
	 */
	@Override
	public void showLendRecordByUser(int userId) {
		logInfo("�鿴ָ���û����޼�¼");
		ArrayList<LendRecord> lrList = new ArrayList<>();
		try {
			lrList = ad.getLrListByUser(userId);
		} catch (SQLException e) {
			logError(e,"�����ݿ��ȡָ���û����޼�¼");
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

	/**
	 * 
	  * <p>Title: showLendRecordByCar</p>   
	  * <p>Description: ��ʾָ���������޼�¼</p>   
	  * @param carId   
	  * @see com.yichao.biz.LendRecordBiz#showLendRecordByCar(int)
	 */
	@Override
	public void showLendRecordByCar(int carId) {
		logInfo("�鿴ָ���������޼�¼");
		ArrayList<LendRecord> lrList = new ArrayList<>();
		try {
			lrList = ad.getLrListByCar(carId);
		} catch (SQLException e) {
			logError(e,"�����ݿ��ȡָ���������޼�¼");
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

	/**
	 * 
	  * <p>Title: showAllCar</p>   
	  * <p>Description: ��ʾ��������</p>      
	  * @see com.yichao.biz.CarBiz#showAllCar()
	 */
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

	/**
	 * 
	  * <p>Title: showAllCar</p>   
	  * <p>Description: ��Ʒ�ƻ�������ʾ����</p>   
	  * @param searchType 1����Ʒ�� 2��������
	  * @param searchId   
	  * @see com.yichao.biz.CarBiz#showAllCar(int, int)
	 */
	@Override
	public void showAllCar(int searchType, int searchId) {
		logInfo("������Ʒ�Ʋ鿴����");
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

	/**
	 * 
	  * <p>Title: sortCar</p>   
	  * <p>Description: ��ʾ���������</p>   
	  * @param sortType  1�����۸��� 2�����۸����� 
	  * @see com.yichao.biz.CarBiz#sortCar(int)
	 */
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

	/**
	 * 
	  * <p>Title: showCarById</p>   
	  * <p>Description: ��ʾָ��id����</p>   
	  * @param carId   
	  * @see com.yichao.biz.CarBiz#showCarById(int)
	 */
	@Override
	public void showCarById(int carId) {
		logInfo("ͨ������id��������");
		cCar = null;
		try {
			cCar = ad.getCarById(carId).get(0);
		} catch (SQLException e) {
			logError(e,"ͨ��id�����ݿ��ȡָ������");
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

	/**
	 * 
	  * <p>Title: showCarByName</p>   
	  * <p>Description: ����������ģ����������</p>   
	  * @param carName   
	  * @see com.yichao.biz.CarBiz#showCarByName(java.lang.String)
	 */
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

	/**
	 * 
	  * <p>Title: adminLogin</p>   
	  * <p>Description: ����Ա��¼</p>   
	  * @param adminName
	  * @param adminPwd
	  * @return  true:�ɹ� false:ʧ�� 
	  * @see com.yichao.biz.AdminBiz#adminLogin(java.lang.String, java.lang.String)
	 */
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

	/**
	 * 
	  * <p>Title: addCar</p>   
	  * <p>Description: �������</p>   
	  * @param car
	  * @return  true:�ɹ� false:ʧ�� 
	  * @see com.yichao.biz.AdminBiz#addCar(com.yichao.bean.Car)
	 */
	@Override
	public boolean addCar(Car car) {
		logInfo("�������");
		boolean isSuccess = false;
		try {
			isSuccess = ad.insertCar(car);
		} catch (SQLException e) {
			logError(e,"�����ݿ��������");
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 
	  * <p>Title: updateCarStatus</p>   
	  * <p>Description: �����������¼�</p>   
	  * @param carId
	  * @param carStatus
	  * @return  true:�ɹ� false:ʧ�� 
	  * @see com.yichao.biz.AdminBiz#updateCarStatus(int, int)
	 */
	@Override
	public boolean updateCarStatus(int carId, int carStatus) {
		logInfo("�����������¼�");
		boolean isSuccess = false;
		try {
			isSuccess = ad.updateCarStatus(carId, carStatus);
		} catch (SQLException e) {
			logError(e,"�������ݿ�����״̬");
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 
	  * <p>Title: updateCarLendPrice</p>   
	  * <p>Description: �����������޼۸�</p>   
	  * @param carId
	  * @param carLendPrice
	  * @return  true:�ɹ� false:ʧ�� 
	  * @see com.yichao.biz.AdminBiz#updateCarLendPrice(int, double)
	 */
	@Override
	public boolean updateCarLendPrice(int carId, double carLendPrice) {
		logInfo("�����������޼۸�");
		boolean isSuccess = false;
		try {
			isSuccess = ad.updateLendPrice(carId, carLendPrice);
		} catch (SQLException e) {
			logError(e,"�������ݿ��������޼۸�");
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 
	  * <p>Title: logError</p>   
	  * <p>Description: ������־</p>   
	  * @param e �쳣����
	  * @param info   ��־����
	  * @see com.yichao.log4j.Log4j#logError(java.lang.Throwable, java.lang.String)
	 */
	@Override
	public void logError(Throwable e,String info) {
		Logger logger = Logger.getLogger("AdminErrorLog");
		if(mAdmin != null) {
			logger.error("����Ա:"+mAdmin.getAdminName()+info+"ʱ����,������ϢΪ:"+e.getMessage());
		}else {
			logger.error(info+"ʱ����:"+e.getMessage());
		}
	}

	/**
	 * 
	  * <p>Title: logInfo</p>   
	  * <p>Description: ������־</p>   
	  * @param info  ��־���� 
	  * @see com.yichao.log4j.Log4j#logInfo(java.lang.String)
	 */
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

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
			logError(e,"��¼�����ݿ�ͨ���û�����ȡ�û�");
		}
		if(u == null) {
			logInfo("�û���������,��¼ʧ��");
			return false;
		}
		if(userPwd.equals(u.getUserPwd())) {
			mUser = u;
			logInfo("��¼�ɹ�");
			return true;
		}
		logInfo("���벻ƥ��,��½ʧ��");
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
			logError(e,"ע������ݿ�ͨ���û�����ȡ�û�");
		}
		if(m1.matches() && m2.matches() && u == null) {
			boolean isSuccess = false;
			try {
				isSuccess = ud.insertUser(userName, userPwd);
			} catch (SQLException e) {
				logError(e,"�����û������ݿ�");
				e.printStackTrace();
			}
			
			return isSuccess;			
		}		
		return false;
	}
	
	
	@Override
	public void showAllCar() {
		logInfo("�鿴��������");
		try {
			mCarList = ud.getCarList();
		} catch (SQLException e) {
			logError(e,"�����ݿ��ȡ��������");
			e.printStackTrace();
			
		}
		
		System.out.println("=================================================================================");
		System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t�Ƿ����\t�Ƿ��ԤԼ");
		for (Car car : mCarList) {
			
			System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
					+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
					+")\t"+car.getCarLendPrice()+"/��\t"+((LENDABLE == car.getCarLendStatus())?"��":"��")
					+"\t"+((ORDERABLE == car.getCarOrderStatus())?"��":"��"));
			
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
		logInfo("�鿴�Լ�ԤԼ��¼");
		try {
			mOrderRecordList = ud.getOrListByUser(userId);
		} catch (SQLException e) {
			logError(e,"ͨ���û������ݿ��ȡԤԼ��¼����");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("���\t�������\t��������\tԤ�ڽ賵ʱ��\tʵ�ʽ賵ʱ��\t��δ�賵");
		for (OrderRecord or : mOrderRecordList) {
			System.out.println(or.getOrId()+"\t"+or.getCarId()+"\t"+or.getCarName()+"\t"
					+or.getExpLendDate()+"\t"+or.getActLendDate()+"\t"
					+((ORDER_ACT == or.getOrStatus())?"��":"��"));
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
		logInfo("�鿴�Լ��Ľ����¼");
		try {
			mLendRecordList = ud.getLrListByUser(userId);
		} catch (SQLException e) {
			logError(e,"ͨ���û������ݿ��ȡ�����¼����");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("���\t�������\t��������\t����ܶ�\t�賵ʱ��\t��󻹳�����\tʵ�ʻ���ʱ��\t��δ����");
		for (LendRecord lr : mLendRecordList) {
			System.out.println(lr.getLrId()+"\t"+lr.getCarId()+"\t"+lr.getCarName()+"\t"
					+lr.getTotalFee()+"\t"+lr.getLendDate()+"\t"+lr.getExpRetuDate()+"\t"
					+lr.getActRetuDate()+"\t"+((LEND_ACT == lr.getLrStatus())?"��":"��"));
		}
		
	}
	@Override
	public void showLendRecordByCar(int carId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showAllCar(int searchType, int searchId) {
		if(SEARCH_BRAND == searchType) {
			logInfo("��Ʒ�Ʋ鳵");
			System.out.println("=================================================================================");
			System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t�Ƿ����\t�Ƿ��ԤԼ");
			for (Car car : mCarList) {
				if(car.getCarBrandId()==searchId) {
					System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
							+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
							+")\t"+car.getCarLendPrice()+"/��\t"+((LENDABLE == car.getCarLendStatus())?"��":"��")
							+"\t"+((ORDERABLE == car.getCarOrderStatus())?"��":"��"));
				}
			}
		}else if(SEARCH_TYPE == searchType) {
			logInfo("�����Ͳ鳵");
			System.out.println("=================================================================================");
			System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t�Ƿ����\t�Ƿ��ԤԼ");
			for (Car car : mCarList) {
				if(car.getCarTypeId()==searchId) {
					System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
							+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
							+")\t"+car.getCarLendPrice()+"/��\t"+((LENDABLE == car.getCarLendStatus())?"��":"��")
							+"\t"+((ORDERABLE == car.getCarOrderStatus())?"��":"��"));
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
		System.out.println("=================================================================================");
		System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t�Ƿ����\t�Ƿ��ԤԼ");
		for (Car car : mCarList) {
			System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
					+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
					+")\t"+car.getCarLendPrice()+"/��\t"+((LENDABLE == car.getCarLendStatus())?"��":"��")
					+"\t"+((ORDERABLE == car.getCarOrderStatus())?"��":"��"));
		}
		
	}
	@Override
	public void showCarById(int carId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showCarByName(String carName) {
		logInfo("ͨ���������Ʋ�������");
		ArrayList<Car> carList = new ArrayList<>();
		try {
			carList = ud.getCarByName(carName);
		} catch (SQLException e) {
			logError(e,"ͨ���������ƴ����ݿ��ȡ�����б�");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t�Ƿ����\t�Ƿ��ԤԼ");
		for (Car car : carList) {
			System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
					+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
					+")\t"+car.getCarLendPrice()+"/��\t"+((LENDABLE == car.getCarLendStatus())?"��":"��")
					+"\t"+((ORDERABLE == car.getCarOrderStatus())?"��":"��"));
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
			logger.error("�û�:"+mUser.getUserName()+info+"ʱ����,������ϢΪ:"+e.getMessage());
		}else {
			logger.error(info+"ʱ����:"+e.getMessage());
		}
		
	}
	@Override
	public void logInfo(String info) {
		Logger logger = Logger.getLogger("UserInfoLog");
		if(mUser != null) {
			logger.info("�û�:"+mUser.getUserName()+info);
		}else {
			logger.info("�û�"+info);
		}
	}
	
	
}

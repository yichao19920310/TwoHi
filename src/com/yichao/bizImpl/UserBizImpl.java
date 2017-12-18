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
  * @Description:�û�ҵ��   
  * @author: Dovahkiin  
  * @date:   2017��12��18�� ����10:05:15   
  *
  */
public class UserBizImpl implements UserBiz,CarBiz,LendRecordBiz,OrderRecordBiz {
	/**
	 * ��̬��Ա,���ڱ��浱ǰ��¼���û���Ϣ
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
	  * <p>Description: ��¼</p>   
	  * @param userName �û���
	  * @param userPwd ����
	  * @return  true:�ɹ� false:ʧ�� 
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
			logError(e,"��¼�����ݿ�ͨ���û�����ȡ�û�");
		}
		if(u == null) {
			System.out.println("�û���������!");
			logInfo("�û���������,��¼ʧ��");
			return false;
		}
		if(userPwd.equals(u.getUserPwd())) {
			mUser = u;
			logInfo("��¼�ɹ�");
			return true;
		}
		System.out.println("���벻ƥ��!");
		logInfo("���벻ƥ��,��½ʧ��");
		return false;
	}
	/**
	 * 
	  * <p>Title: userRegist</p>   
	  * <p>Description: ע��</p>   
	  * @param userName
	  * @param userPwd
	  * @return   true:ע��ɹ� false:ע��ʧ��
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
			logError(e,"ע������ݿ�ͨ���û�����ȡ�û�");
		}
		if(u != null) {
			System.out.println("�û����Ѵ���!");
			return false;
		}
		if(m1.matches() && m2.matches()) {
			boolean isSuccess = false;
			try {
				isSuccess = ud.insertUser(userName, userPwd);
			} catch (SQLException e) {
				logError(e,"�����û������ݿ�");
				e.printStackTrace();
			}
			
			return isSuccess;			
		}
		System.out.println("�û����������ʽ����ȷ!");
		return false;
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
	/**
	 * 
	  * <p>Title: lendCar</p>   
	  * <p>Description: �賵</p>   
	  * @param carId
	  * @param lendDays
	  * @return   true:�ɹ� false:ʧ��
	  * @see com.yichao.biz.UserBiz#lendCar(int, int)
	 */
	@Override
	public boolean lendCar(int carId, int lendDays) {
		logInfo("�⳵");		
		boolean isSuccess = false;
		try {
			mOrderRecordList = ud.getOrListByUser(mUser.getUserId());
			mCarList = ud.getCarList();
		} catch (SQLException e) {
			logError(e,"�����ݿ��ȡ�����ϼ������б���Լ���ԤԼ���б�");
			e.printStackTrace();
		}
		
		for (Car car : mCarList) {
			if(carId == car.getCarId() && 
					LENDABLE == car.getCarLendStatus()&& ORDERABLE == car.getCarOrderStatus()) {
				try {
					cLr = ud.lendCar(carId, lendDays);
				} catch (SQLException e) {
					logError(e,"�������ݿ�ִ�н賵�����쳣ִ�лع�");
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
	  * <p>Description: �鿴�Լ�ԤԼ��¼</p>   
	  * @param userId   
	  * @see com.yichao.biz.OrderRecordBiz#showOrderRecordByUser(int)
	 */
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
		
		
	}
	@Override
	public void showAllLendRecord() {
		
		
	}
	/**
	 * 
	  * <p>Title: showLendRecordByUser</p>   
	  * <p>Description: �鿴�Լ����޼�¼</p>   
	  * @param userId   
	  * @see com.yichao.biz.LendRecordBiz#showLendRecordByUser(int)
	 */
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
		System.out.println("���\t�������\t��������\t����ܶ�\t�����������\t��󻹳�����\tʵ�ʻ���ʱ��\t��δ����");
		for (LendRecord lr : mLendRecordList) {
			System.out.println(lr.getLrId()+"\t"+lr.getCarId()+"\t"+lr.getCarName()+"\t"
					+lr.getTotalFee()+"\t"+lr.getLendDate()+"\t"+lr.getExpRetuDate()+"\t"
					+lr.getActRetuDate()+"\t"+((LEND_ACT == lr.getLrStatus())?"��":"��"));
		}
		
	}
	@Override
	public void showLendRecordByCar(int carId) {
		
		
	}
	/**
	 * 
	  * <p>Title: showAllCar</p>   
	  * <p>Description: ��Ʒ�ƻ�������������</p>   
	  * @param searchType 1����Ʒ�� 2��������
	  * @param searchId   
	  * @see com.yichao.biz.CarBiz#showAllCar(int, int)
	 */
	@Override
	public void showAllCar(int searchType, int searchId) {
		logInfo("������Ʒ�Ʋ鿴����");
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
	/**
	 * 
	  * <p>Title: sortCar</p>   
	  * <p>Description: ��ʾ����������</p>   
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
		
		
	}
	/**
	 * 
	  * <p>Title: showCarByName</p>   
	  * <p>Description: ͨ����������ģ����������</p>   
	  * @param carName   
	  * @see com.yichao.biz.CarBiz#showCarByName(java.lang.String)
	 */
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
	/**
	 * 
	  * <p>Title: returnCar</p>   
	  * <p>Description: ����</p>   
	  * @param carId
	  * @return  true:�ɹ� false:ʧ�� 
	  * @see com.yichao.biz.UserBiz#returnCar(int)
	 */
	@Override
	public boolean returnCar(int carId) {
		logInfo("����");
		cLr = null;
		try {
			mLendRecordList = ud.getLrListByUser(mUser.getUserId());
		} catch (SQLException e) {
			logError(e,"�����ݿ��ȡָ���û����޼�¼");
			e.printStackTrace();
		}
		for (LendRecord lr : mLendRecordList) {
			if(carId == lr.getCarId() && LEND_ACT == lr.getLrStatus()) {
				cLr = ud.returnCar(carId,lr.getLrId());
				break;
			}
		}
		if(cLr == null) {
			System.out.println("��δ���޴˳�");
			return false;
		}
		return true;
	}
	/**
	 * 
	  * <p>Title: orderCar</p>   
	  * <p>Description: ԤԼ��</p>   
	  * @param carId
	  * @param orderDays
	  * @return true:�ɹ� false:ʧ��  
	  * @see com.yichao.biz.UserBiz#orderCar(int, int)
	 */
	@Override
	public boolean orderCar(int carId, int orderDays) {
		logInfo("ԤԼ��");
		boolean isSuccess = false;
		try {			
			mCarList = ud.getCarList();
		} catch (SQLException e) {
			logError(e,"�����ݿ��ȡ�����ϼ������б�");
			e.printStackTrace();
		}
		for (Car car : mCarList) {
			if(carId == car.getCarId() && LENDABLE == car.getCarLendStatus() && ORDERABLE == car.getCarOrderStatus()) {
				try {
					cOr = ud.orderCar(carId, orderDays);
				} catch (SQLException e) {
					logError(e,"���ݿ�ִ��ԤԼ��������");
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
	  * <p>Description: ������ԤԼ������</p>   
	  * @param carId
	  * @param lendDays
	  * @return true:�ɹ� false:ʧ��  
	  * @see com.yichao.biz.UserBiz#lendOrderCar(int, int)
	 */
	@Override
	public boolean lendOrderCar(int carId,int lendDays) {
		logInfo("������ԤԼ������");
		boolean isSuccess = false;
		try {
			mOrderRecordList = ud.getOrListByUser(mUser.getUserId());
			mCarList = ud.getCarList();
		} catch (SQLException e) {
			logError(e,"�����ݿ��ȡ�����ϼ������б���Լ���ԤԼ���б�");
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
	  * <p>Description: ������־</p>   
	  * @param e �쳣����
	  * @param info  ��־���� 
	  * @see com.yichao.log4j.Log4j#logError(java.lang.Throwable, java.lang.String)
	 */
	@Override
	public void logError(Throwable e,String info) {
		Logger logger = Logger.getLogger("UserErrorLog");
		if(mUser != null) {
			logger.error("�û�:"+mUser.getUserName()+info+"ʱ����,������ϢΪ:"+e.getMessage());
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
		Logger logger = Logger.getLogger("UserInfoLog");
		if(mUser != null) {
			logger.info("�û�:"+mUser.getUserName()+info);
		}else {
			logger.info("δ��¼�û�"+info);
		}
	}
	/**
	 * 
	  * <p>Title: showLendCar</p>   
	  * <p>Description: ��ʾ�����޵���Ϣ</p>   
	  * @param carId   
	  * @see com.yichao.biz.UserBiz#showLendCar(int)
	 */
	@Override
	public void showLendCar(int carId) {
		try {
			cCar = ud.getCarById(carId).get(0);
		} catch (SQLException e) {
			logError(e,"ͨ��id�����ݿ��ȡָ������");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("���\t��赥����ˮ��\t��������\t��ע\tƷ��\t����\tÿ�����\t�賵����");
		System.out.println(cLr.getLrId()+"\t"+cLr.getLrNumber()+"\t"+cLr.getCarName()+"\t"+cCar.getCarRemark()
			+"\t"+cCar.getCarBrand()+"\t"+cCar.getCarType()+"\t"+cLr.getCarLendPrice()+"\t"+cLr.getLendDate());
		
		
	}
	/**
	 * 
	  * <p>Title: showOrderCar</p>   
	  * <p>Description: ��ʾ��ԤԼ����Ϣ</p>   
	  * @param carId   
	  * @see com.yichao.biz.UserBiz#showOrderCar(int)
	 */
	@Override
	public void showOrderCar(int carId) {
		try {
			cCar = ud.getCarById(carId).get(0);
		} catch (SQLException e) {
			logError(e,"ͨ��id�����ݿ��ȡָ������");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("���\tԤԼ������ˮ��\t��������\t��ע\tƷ��\t����\tÿ�����\tԤ�ƽ賵����\tԤԼ����");
		System.out.println(cOr.getOrId()+"\t"+cOr.getOrNumber()+"\t"+cOr.getCarName()+"\t"+cCar.getCarRemark()
			+"\t"+cCar.getCarBrand()+"\t"+cCar.getCarType()+"\t"+cCar.getCarLendPrice()+"\t"+cOr.getExpLendDate()
			+"\t"+cOr.getOrderDate());
		
	}
	/**
	 * 
	  * <p>Title: showReturnCar</p>   
	  * <p>Description: ��ʾ�ջ�������Ϣ</p>   
	  * @param carId   
	  * @see com.yichao.biz.UserBiz#showReturnCar(int)
	 */
	@Override
	public void showReturnCar(int carId) {
		try {
			cCar = ud.getCarById(carId).get(0);
		} catch (SQLException e) {
			logError(e,"ͨ��id�����ݿ��ȡָ������");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("���\t��赥����ˮ��\t��������\tÿ�����\t���ɽ�\t����ܶ�\tԤ�ڻ�������\tʵ�ʻ�������");
		System.out.println(cLr.getLrId()+"\t"+cLr.getLrNumber()+"\t"+cLr.getCarName()+"\t"
				+cLr.getCarLendPrice()+"\t"+cLr.getLateFee()+"\t"+cLr.getTotalFee()+"\t"
				+cLr.getExpRetuDate()+"\t"+cLr.getActRetuDate());
	}
	

	
}

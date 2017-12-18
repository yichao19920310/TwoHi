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
  * @Description:管理员业务   
  * @author: Dovahkiin  
  * @date:   2017年12月18日 上午10:06:08   
  *
  */
public class AdminBizImpl implements AdminBiz,CarBiz,LendRecordBiz,OrderRecordBiz{

	/**
	 * 静态成员,用于保存当前登录的管理员信息
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
	  * <p>Description: 显示所有预约记录</p>      
	  * @see com.yichao.biz.OrderRecordBiz#showAllOrderRecord()
	 */
	@Override
	public void showAllOrderRecord() {
		logInfo("查看所有预约记录");
		ArrayList<OrderRecord> orList = new ArrayList<>();
		try {
			orList = ad.getOrList();
		} catch (SQLException e) {
			logError(e,"从数据库获取所有预约记录");
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("编号\t汽车编号\t汽车名称\t用户编号\t用户名\t预  约   日  期\t预期租赁日期\t实际租赁日期");
		for (OrderRecord or : orList) {
			System.out.println(or.getOrId()+"\t"+or.getCarId()+"\t"+or.getCarName()+"\t"
					+or.getUserId()+"\t"+or.getUserName()+"\t"+or.getOrderDate()+"\t"
					+or.getExpLendDate()+"\t"+or.getActLendDate());
		}
	}

	/**
	 * 
	  * <p>Title: showOrderRecordByUser</p>   
	  * <p>Description: 显示指定用户预约记录</p>   
	  * @param userId   
	  * @see com.yichao.biz.OrderRecordBiz#showOrderRecordByUser(int)
	 */	
	@Override
	public void showOrderRecordByUser(int userId) {
		logInfo("查看指定用户预约记录");
		ArrayList<OrderRecord> orList = new ArrayList<>();
		try {
			orList = ad.getOrListByUser(userId);
		} catch (SQLException e) {
			logError(e,"从数据库获取指定用户预约记录");
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("编号\t汽车编号\t汽车名称\t用户编号\t用户名\t预  约   日  期\t预期租赁日期\t实际租赁日期");
		for (OrderRecord or : orList) {
			System.out.println(or.getOrId()+"\t"+or.getCarId()+"\t"+or.getCarName()+"\t"
					+or.getUserId()+"\t"+or.getUserName()+"\t"+or.getOrderDate()+"\t"
					+or.getExpLendDate()+"\t"+or.getActLendDate());
		}
		
	}

	/**
	 * 
	  * <p>Title: showOrderRecordByCar</p>   
	  * <p>Description: 显示指定汽车预约记录</p>   
	  * @param carId   
	  * @see com.yichao.biz.OrderRecordBiz#showOrderRecordByCar(int)
	 */
	@Override
	public void showOrderRecordByCar(int carId) {
		logInfo("查看指定汽车预约记录");
		ArrayList<OrderRecord> orList = new ArrayList<>();
		try {
			orList = ad.getOrListByCar(carId);
		} catch (SQLException e) {
			logError(e,"从数据库获取指定汽车预约记录");
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("编号\t汽车编号\t汽车名称\t用户编号\t用户名\t预  约   日  期\t预期租赁日期\t实际租赁日期");
		for (OrderRecord or : orList) {
			System.out.println(or.getOrId()+"\t"+or.getCarId()+"\t"+or.getCarName()+"\t"
					+or.getUserId()+"\t"+or.getUserName()+"\t"+or.getOrderDate()+"\t"
					+or.getExpLendDate()+"\t"+or.getActLendDate());
		}
	}

	/**
	 * 
	  * <p>Title: showAllLendRecord</p>   
	  * <p>Description: 显示所有租赁记录</p>      
	  * @see com.yichao.biz.LendRecordBiz#showAllLendRecord()
	 */
	@Override
	public void showAllLendRecord() {
		logInfo("查看所有租赁记录");
		ArrayList<LendRecord> lrList = new ArrayList<>();
		try {
			lrList = ad.getLrList();
		} catch (SQLException e) {
			logError(e,"从数据库获取所有租赁记录");
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("编号\t汽车编号\t汽车名称\t用户编号\t用户名\t每日租金\t租金总额\t滞纳金\t借  出  日  期\t预期归还日期\t实际归还日期");
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
	  * <p>Description: 显示指定用户租赁记录</p>   
	  * @param userId   
	  * @see com.yichao.biz.LendRecordBiz#showLendRecordByUser(int)
	 */
	@Override
	public void showLendRecordByUser(int userId) {
		logInfo("查看指定用户租赁记录");
		ArrayList<LendRecord> lrList = new ArrayList<>();
		try {
			lrList = ad.getLrListByUser(userId);
		} catch (SQLException e) {
			logError(e,"从数据库获取指定用户租赁记录");
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("编号\t汽车编号\t汽车名称\t用户编号\t用户名\t每日租金\t租金总额\t滞纳金\t借  出  日  期\t预期归还日期\t实际归还日期");
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
	  * <p>Description: 显示指定汽车租赁记录</p>   
	  * @param carId   
	  * @see com.yichao.biz.LendRecordBiz#showLendRecordByCar(int)
	 */
	@Override
	public void showLendRecordByCar(int carId) {
		logInfo("查看指定汽车租赁记录");
		ArrayList<LendRecord> lrList = new ArrayList<>();
		try {
			lrList = ad.getLrListByCar(carId);
		} catch (SQLException e) {
			logError(e,"从数据库获取指定汽车租赁记录");
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("编号\t汽车编号\t汽车名称\t用户编号\t用户名\t每日租金\t租金总额\t滞纳金\t借  出  日  期\t预期归还日期\t实际归还日期");
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
	  * <p>Description: 显示所有汽车</p>      
	  * @see com.yichao.biz.CarBiz#showAllCar()
	 */
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

	/**
	 * 
	  * <p>Title: showAllCar</p>   
	  * <p>Description: 按品牌或类型显示汽车</p>   
	  * @param searchType 1代表按品牌 2代表按类型
	  * @param searchId   
	  * @see com.yichao.biz.CarBiz#showAllCar(int, int)
	 */
	@Override
	public void showAllCar(int searchType, int searchId) {
		logInfo("按类型品牌查看汽车");
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

	/**
	 * 
	  * <p>Title: sortCar</p>   
	  * <p>Description: 显示排序后汽车</p>   
	  * @param sortType  1代表按价格降序 2代表按价格升序 
	  * @see com.yichao.biz.CarBiz#sortCar(int)
	 */
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

	/**
	 * 
	  * <p>Title: showCarById</p>   
	  * <p>Description: 显示指定id汽车</p>   
	  * @param carId   
	  * @see com.yichao.biz.CarBiz#showCarById(int)
	 */
	@Override
	public void showCarById(int carId) {
		logInfo("通过汽车id查找汽车");
		cCar = null;
		try {
			cCar = ad.getCarById(carId).get(0);
		} catch (SQLException e) {
			logError(e,"通过id从数据库获取指定汽车");
			e.printStackTrace();
		}
		System.out.println("=====================================================");
		System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否可预约\t是否上架");
		System.out.println(""+cCar.getCarId()+"\t"+cCar.getCarName()+"\t"+cCar.getCarRemark()+"\t"
				+cCar.getCarBrand()+"("+cCar.getCarBrandId()+")\t"+cCar.getCarType()+"("+cCar.getCarTypeId()
				+")\t"+cCar.getCarLendPrice()+"/天\t"+((LENDABLE == cCar.getCarLendStatus())?"是":"否")
				+"\t"+((ORDERABLE == cCar.getCarOrderStatus())?"是":"否")+"\t"
				+((ONLINE_CAR == cCar.getCarStatus())?"是":"否"));		
	}

	/**
	 * 
	  * <p>Title: showCarByName</p>   
	  * <p>Description: 按汽车名称模糊查找汽车</p>   
	  * @param carName   
	  * @see com.yichao.biz.CarBiz#showCarByName(java.lang.String)
	 */
	@Override
	public void showCarByName(String carName) {
		logInfo("通过汽车名称查找汽车");
		ArrayList<Car> carList = new ArrayList<>();
		try {
			carList = ad.getCarByName(carName);
		} catch (SQLException e) {
			logError(e,"通过汽车名称从数据库获取汽车列表");
			e.printStackTrace();
		}
		System.out.println("=================================================================================");
		System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否可预约\t是否上架");
		for (Car car : carList) {
			System.out.println(car.getCarId()+"\t"+car.getCarName()+"\t"+car.getCarRemark()+"\t"
					+car.getCarBrand()+"("+car.getCarBrandId()+")\t"+car.getCarType()+"("+car.getCarTypeId()
					+")\t"+car.getCarLendPrice()+"/天\t"+((LENDABLE == car.getCarLendStatus())?"是":"否")
					+"\t"+((ORDERABLE == car.getCarOrderStatus())?"是":"否")+"\t"
					+((ONLINE_CAR == car.getCarStatus())?"是":"否"));
		}
		
	}

	/**
	 * 
	  * <p>Title: adminLogin</p>   
	  * <p>Description: 管理员登录</p>   
	  * @param adminName
	  * @param adminPwd
	  * @return  true:成功 false:失败 
	  * @see com.yichao.biz.AdminBiz#adminLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean adminLogin(String adminName, String adminPwd) {
		
			
			Admin a = null;
			try {
				a = ad.getAdminByName(adminName);
			} catch (SQLException e) {
				
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

	/**
	 * 
	  * <p>Title: addCar</p>   
	  * <p>Description: 添加汽车</p>   
	  * @param car
	  * @return  true:成功 false:失败 
	  * @see com.yichao.biz.AdminBiz#addCar(com.yichao.bean.Car)
	 */
	@Override
	public boolean addCar(Car car) {
		logInfo("添加汽车");
		boolean isSuccess = false;
		try {
			isSuccess = ad.insertCar(car);
		} catch (SQLException e) {
			logError(e,"向数据库插入汽车");
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 
	  * <p>Title: updateCarStatus</p>   
	  * <p>Description: 更改汽车上下架</p>   
	  * @param carId
	  * @param carStatus
	  * @return  true:成功 false:失败 
	  * @see com.yichao.biz.AdminBiz#updateCarStatus(int, int)
	 */
	@Override
	public boolean updateCarStatus(int carId, int carStatus) {
		logInfo("更改汽车上下架");
		boolean isSuccess = false;
		try {
			isSuccess = ad.updateCarStatus(carId, carStatus);
		} catch (SQLException e) {
			logError(e,"更改数据库汽车状态");
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 
	  * <p>Title: updateCarLendPrice</p>   
	  * <p>Description: 更改汽车租赁价格</p>   
	  * @param carId
	  * @param carLendPrice
	  * @return  true:成功 false:失败 
	  * @see com.yichao.biz.AdminBiz#updateCarLendPrice(int, double)
	 */
	@Override
	public boolean updateCarLendPrice(int carId, double carLendPrice) {
		logInfo("更改汽车租赁价格");
		boolean isSuccess = false;
		try {
			isSuccess = ad.updateLendPrice(carId, carLendPrice);
		} catch (SQLException e) {
			logError(e,"更改数据库汽车租赁价格");
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 
	  * <p>Title: logError</p>   
	  * <p>Description: 错误日志</p>   
	  * @param e 异常对象
	  * @param info   日志内容
	  * @see com.yichao.log4j.Log4j#logError(java.lang.Throwable, java.lang.String)
	 */
	@Override
	public void logError(Throwable e,String info) {
		Logger logger = Logger.getLogger("AdminErrorLog");
		if(mAdmin != null) {
			logger.error("管理员:"+mAdmin.getAdminName()+info+"时出错,错误信息为:"+e.getMessage());
		}else {
			logger.error(info+"时出错:"+e.getMessage());
		}
	}

	/**
	 * 
	  * <p>Title: logInfo</p>   
	  * <p>Description: 操作日志</p>   
	  * @param info  日志内容 
	  * @see com.yichao.log4j.Log4j#logInfo(java.lang.String)
	 */
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

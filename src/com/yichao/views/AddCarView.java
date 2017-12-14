/**  
 * @Title: AddCarView.java  
 * @Package com.yichao.views  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author Administrator  
 * @date 2017年12月14日  
 * @version V1.0  
 */  
package com.yichao.views;

import com.yichao.bean.Car;

/**  
 * @ClassName: AddCarView  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author Administrator  
 * @date 2017年12月14日  
 *    
 */
public class AddCarView extends View {

	private static int step;
	/* (非 Javadoc)  
	 * <p>Title: showView</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.views.View#showView()  
	 */
	@Override
	public View showView() {
		System.out.println("=====================================");
		System.out.println("请分别输入以下信息:");
		step = 1;
		int carBrandId = 0;
		int carTypeId = 0;
		String carBrand = "";
		String carType = "";
		String carName = "";
		String carRemark = "";
		Double carPrice = 0.0;
		Double carLendPrice = 0.0;
		int carStatus = 0;
		while(step != 0) {
			switch(step) {
			case 1:
				step1();
				carBrandId = iT.getInt();
				if(carBrandId > 5 || carBrandId <= 0) {
					step = 1;
					System.out.println("无此编号!");
				}else {
					step = 2;
					carBrand = getCarBrand(carBrandId);
				}
				break;
			case 2:
				step2();
				carTypeId = iT.getInt();
				if(carTypeId > 4 || carTypeId <= 0) {
					step = 2;
					System.out.println("无此编号!");
				}else {
					step = 3;
					carType = getCarType(carTypeId);
				}
				break;
			case 3:
				System.out.println("-------------------------------------");
				System.out.print("汽车名称:");
				carName = iT.getString();
				System.out.println();
				step = 4;
				break;
			case 4:
				System.out.println("-------------------------------------");
				System.out.print("概要:");
				carRemark = iT.getString();
				System.out.println();
				step = 5;
				break;
			case 5:
				System.out.println("-------------------------------------");
				System.out.print("汽车价格:");
				carPrice = iT.getDouble();
				System.out.println();
				if(carPrice < 0) {
					System.out.println("价格不能为负数!");
					step = 5;
				}else {
					step = 6;
				}
				break;
			case 6:
				System.out.println("-------------------------------------");
				System.out.print("每日租金:");
				carLendPrice = iT.getDouble();
				System.out.println();
				if(carLendPrice < 0) {
					System.out.println("价格不能为负数!");
					step = 6;
				}else {
					step = 7;
				}
				break;
			case 7:
				System.out.println("-------------------------------------");
				System.out.print("是否上架(1:上架,0:下架):");
				carStatus = iT.getInt();
				System.out.println();
				if(carStatus < 0 || carStatus > 1) {
					System.out.println("请输入1或0!");
					step = 7;
				}else {
					step = 0;
				}
			default:
				break;
			}
		}
		Car car = new Car();
		car.setCarBrandId(carBrandId);
		car.setCarBrand(carBrand);
		car.setCarTypeId(carTypeId);
		car.setCarType(carType);
		car.setCarName(carName);
		car.setCarPrice(carPrice);
		car.setCarLendPrice(carLendPrice);
		car.setCarRemark(carRemark);
		car.setCarStatus(carStatus);
		boolean isSuccess = ab.addCar(car);
		if(isSuccess) {
			System.out.println("添加成功!");
		}else {
			System.out.println("添加失败!");
		}
				
				
		
		return mView = new AdminMenuView();
	}

	private String getCarType(int carTypeId) {
		String carType = "";
		switch(carTypeId) {
		case 1:
			carType = "紧凑型";
			break;
		case 2:
			carType = "舒适型";
			break;
		case 3:
			carType = "SUV";
			break;
		case 4:
			carType = "精英型";
			break;		
		default:
			break;
		}
		return carType;
	}

	private void step2() {
		int carTypeId;
		System.out.println("-------------------------------------");
		System.out.println("类型如下:");
		System.out.println("类型编号\t类型名");
		System.out.println("1\t紧凑型");
		System.out.println("2\t舒适型");
		System.out.println("3\tSUV");
		System.out.println("4\t精英型");
		System.out.print("请选择类型编号:");
		carTypeId = iT.getInt();
		System.out.println();
	}

	private String getCarBrand(int carBrandId) {
		String carBrand = "";
		switch(carBrandId) {
		case 1:
			carBrand = "标致";
			break;
		case 2:
			carBrand = "大众";
			break;
		case 3:
			carBrand = "奥迪";
			break;
		case 4:
			carBrand = "奔驰";
			break;
		case 5:
			carBrand = "宝马";
			break;
		default:
			break;
		}
		return carBrand;
	}

	private void step1() {
		System.out.println("-------------------------------------");
		System.out.println("品牌如下:");
		System.out.println("品牌编号\t品牌名");
		System.out.println("1\t标致");
		System.out.println("2\t大众");
		System.out.println("3\t奥迪");
		System.out.println("4\t奔驰");
		System.out.println("5\t宝马");
		System.out.print("请选择品牌编号:");
		int carBrandId = iT.getInt();
		System.out.println();
	}

}

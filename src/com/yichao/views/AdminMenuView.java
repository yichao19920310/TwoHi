package com.yichao.views;

import com.yichao.bizImpl.AdminBizImpl;

public class AdminMenuView extends View {

	private final String EXIT = "0";
	private final String SEARCH_BY_ID = "1";
	private final String SORT = "2";	
	private final String SEARCH_BY_BRAND = "3";
	private final String SEARCH_BY_TYPE = "4";
	private final String SEARCH_BY_NAME = "5";
	private final String ADD_CAR = "6";
	private final String UPDATE_CAR = "7";
	private final String SHOW_LRLIST = "8";
	private final String SHOW_ORLIST = "9";
	
	@Override
	public View showView() {
		ab.showAllCar();
		showMenu();
		String command = iT.getString();
		String command1 = "";
		String command2 = "";
		int index = command.indexOf("+");
		if(command.length()>2 && index == 1) {
			command1 =  command.substring(0,index);
			command2 = command.substring(index + 1);
			if(iT.isInt(command2)) {
				
			}else {
				System.out.println("指令有误!");
				return mView = new AdminMenuView();
			}
		}else if(command.length()==1 && index == -1) {
			command1 = command;
		}else {
			System.out.println("指令有误!");
			return mView = new AdminMenuView();
		}
		switch(command1) {
		case EXIT:
			mView = null;
			ab.logInfo("退出登录");
			System.out.println("再见!");
			break;
		case SEARCH_BY_ID:			
			int carId = Integer.parseInt(command2);	
			ab.showCarById(carId);
			mView = new AdminMenuView();
			break;
		case SORT:
			int sortType = Integer.parseInt(command2);
			ab.sortCar(sortType);
			mView = new AdminMenuView();
			break;
		case SEARCH_BY_BRAND:
			int carBrandId = Integer.parseInt(command2);
			ab.showAllCar(1, carBrandId);
			mView = new UserMenuView();
			break;
		case SEARCH_BY_TYPE:
			int carTypeId = Integer.parseInt(command2);
			ab.showAllCar(2,carTypeId);
			mView = new AdminMenuView();
			break;
		case SEARCH_BY_NAME:
			mView = new SearchByNameView();
			break;
		case ADD_CAR:
			mView = new AddCarView();
			break;
		case UPDATE_CAR:
			int carId1 = Integer.parseInt(command2);
			ab.showCarById(carId1);
			if(AdminBizImpl.cCar.getCarLendStatus() == 0 || AdminBizImpl.cCar.getCarOrderStatus() == 0) {
				System.out.println("该车已被租赁或预约,暂时不可修改!");
				mView = new AdminMenuView();
			}else {
				updateCar();
			}			
			mView = new AdminMenuView();
			break;
		case SHOW_LRLIST:
			mView = new ShowLrListView();
			break;
		case SHOW_ORLIST:
			mView = new ShowOrListView();
			break;
		default:
			System.out.println("指令有误!");
			mView = new AdminMenuView();
			break;
		}
		return mView;
	
		
	}

	private void updateCar() {
		System.out.println("请输入要更改的内容编号:");
		System.out.println("1:租赁价格 2:上架下架");
		int choose = iT.getInt();
		switch(choose) {
		case 1:
			int lendPrice = 0;
			while(true) {
				System.out.println("请输入新的租赁价格:");
				lendPrice = iT.getInt();
				if(lendPrice < 0) {
					System.out.println("价格不能为负数");
					continue;
				}else {
					break;
				}				
			}
			if(ab.updateCarLendPrice(AdminBizImpl.cCar.getCarId(), lendPrice)) {
				System.out.println("修改成功!");
				ab.showCarById(AdminBizImpl.cCar.getCarId());
			}else {
				System.out.println("修改失败!");
			}
			break;
		case 2:
			int carStatus = 0;
			while(true) {
				System.out.println("请输入1:上架 0:下架");
				carStatus = iT.getInt();
				if(carStatus>1 || carStatus<0) {
					System.out.println("请输入1或0!");
					continue;
				}else {
					break;
				}
			}
			if(ab.updateCarStatus(AdminBizImpl.cCar.getCarId(), carStatus)) {
				System.out.println("修改成功!");
				ab.showCarById(AdminBizImpl.cCar.getCarId());
			}else {
				System.out.println("修改失败!");
			}
			break;
		}
	}

	private void showMenu() {
		System.out.println();
		System.out.println("输入0:退出");
		System.out.println("输入1+汽车编号:查看指定汽车");
	
		System.out.println("输入2+1:价格降序排列;2+2:价格升序排列");		
		System.out.println("输入3+品牌编号:按品牌搜索");
		System.out.println("输入4+类型编号:按类型搜索");
		System.out.println("输入5:按汽车名称搜索");
		System.out.println("输入6:添加汽车");
		System.out.println("输入7+汽车编号:修改汽车信息");
		System.out.println("输入8:查看租借记录");		
		System.out.println("输入9:查看预约记录");		
	}

}

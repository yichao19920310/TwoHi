package com.yichao.views;

public class AdminMenuView extends View {

	final String EXIT = "0";
	final String SEARCH_BY_ID = "1";
	final String SORT = "2";	
	final String SEARCH_BY_BRAND = "3";
	final String SEARCH_BY_TYPE = "4";
	final String SHOW_ALL_CARS = "5";
	final String ADD_CAR = "6";
	final String UPDATE_CAR = "7";
	final String SHOW_LRLIST = "8";
	final String SHOW_ORLIST = "9";
	
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
				return mView = new UserMenuView();
			}
		}else if(command.length()==1 && index == -1) {
			command1 = command;
		}else {
			System.out.println("指令有误!");
			return mView = new UserMenuView();
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
			break;
		case SORT:
			int sortType = Integer.parseInt(command2);
			ab.sortCar(sortType);
			mView = new UserMenuView();
			break;
		case SEARCH_BY_BRAND:
			
			break;
		case SEARCH_BY_TYPE:
			break;
		case SHOW_ALL_CARS:
			break;
		case ADD_CAR:
			break;
		case UPDATE_CAR:
			break;
		case SHOW_LRLIST:
			break;
		case SHOW_ORLIST:
			break;
		default:
			break;
		}
		return mView;
	
		
	}

	private void showMenu() {
		System.out.println();
		System.out.println("输入0:退出");
		System.out.println("输入1+汽车编号:查看指定汽车");
	
		System.out.println("输入2+1:价格降序排列;2+2:价格升序排列");		
		System.out.println("输入3+品牌编号:按品牌搜索");
		System.out.println("输入4+类型编号:按类型搜索");
		System.out.println("输入5:查看全部汽车");
		System.out.println("输入6:添加汽车");
		System.out.println("输入7+汽车编号:修改汽车信息");
		System.out.println("输入8:查看租借记录");		
		System.out.println("输入9:查看预约记录");		
	}

}

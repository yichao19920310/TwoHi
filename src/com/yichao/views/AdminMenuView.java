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
				System.out.println("ָ������!");
				return mView = new UserMenuView();
			}
		}else if(command.length()==1 && index == -1) {
			command1 = command;
		}else {
			System.out.println("ָ������!");
			return mView = new UserMenuView();
		}
		switch(command1) {
		case EXIT:
			mView = null;
			ab.logInfo("�˳���¼");
			System.out.println("�ټ�!");
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
		System.out.println("����0:�˳�");
		System.out.println("����1+�������:�鿴ָ������");
	
		System.out.println("����2+1:�۸�������;2+2:�۸���������");		
		System.out.println("����3+Ʒ�Ʊ��:��Ʒ������");
		System.out.println("����4+���ͱ��:����������");
		System.out.println("����5:�鿴ȫ������");
		System.out.println("����6:�������");
		System.out.println("����7+�������:�޸�������Ϣ");
		System.out.println("����8:�鿴����¼");		
		System.out.println("����9:�鿴ԤԼ��¼");		
	}

}

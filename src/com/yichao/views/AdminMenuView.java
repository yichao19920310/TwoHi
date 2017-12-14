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
				System.out.println("ָ������!");
				return mView = new AdminMenuView();
			}
		}else if(command.length()==1 && index == -1) {
			command1 = command;
		}else {
			System.out.println("ָ������!");
			return mView = new AdminMenuView();
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
				System.out.println("�ó��ѱ����޻�ԤԼ,��ʱ�����޸�!");
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
			System.out.println("ָ������!");
			mView = new AdminMenuView();
			break;
		}
		return mView;
	
		
	}

	private void updateCar() {
		System.out.println("������Ҫ���ĵ����ݱ��:");
		System.out.println("1:���޼۸� 2:�ϼ��¼�");
		int choose = iT.getInt();
		switch(choose) {
		case 1:
			int lendPrice = 0;
			while(true) {
				System.out.println("�������µ����޼۸�:");
				lendPrice = iT.getInt();
				if(lendPrice < 0) {
					System.out.println("�۸���Ϊ����");
					continue;
				}else {
					break;
				}				
			}
			if(ab.updateCarLendPrice(AdminBizImpl.cCar.getCarId(), lendPrice)) {
				System.out.println("�޸ĳɹ�!");
				ab.showCarById(AdminBizImpl.cCar.getCarId());
			}else {
				System.out.println("�޸�ʧ��!");
			}
			break;
		case 2:
			int carStatus = 0;
			while(true) {
				System.out.println("������1:�ϼ� 0:�¼�");
				carStatus = iT.getInt();
				if(carStatus>1 || carStatus<0) {
					System.out.println("������1��0!");
					continue;
				}else {
					break;
				}
			}
			if(ab.updateCarStatus(AdminBizImpl.cCar.getCarId(), carStatus)) {
				System.out.println("�޸ĳɹ�!");
				ab.showCarById(AdminBizImpl.cCar.getCarId());
			}else {
				System.out.println("�޸�ʧ��!");
			}
			break;
		}
	}

	private void showMenu() {
		System.out.println();
		System.out.println("����0:�˳�");
		System.out.println("����1+�������:�鿴ָ������");
	
		System.out.println("����2+1:�۸�������;2+2:�۸���������");		
		System.out.println("����3+Ʒ�Ʊ��:��Ʒ������");
		System.out.println("����4+���ͱ��:����������");
		System.out.println("����5:��������������");
		System.out.println("����6:�������");
		System.out.println("����7+�������:�޸�������Ϣ");
		System.out.println("����8:�鿴����¼");		
		System.out.println("����9:�鿴ԤԼ��¼");		
	}

}

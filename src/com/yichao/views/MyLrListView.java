package com.yichao.views;

import com.yichao.bizImpl.UserBizImpl;

public class MyLrListView extends View {

	private final String BACK = "0";
	private final String RETURN_CAR = "9";
	@Override
	public View showView() {
		ub.showLendRecordByUser(UserBizImpl.mUser.getUserId());
		showMenu();
		String command = iT.getString();
		String command1 = "";
		String command2 = "";
		int index = command.indexOf("+");
		if(command.length() == 1 && index == -1) {
			command1 = command;
		}else if(command.length() > 2 && index == 1) {
			command1 = command.substring(0, index);
			command2 = command.substring(index+1);
			if(iT.isInt(command2)) {
				
			}else {
				System.out.println("指令有误!");
				return mView = new MyLrListView();
			}
		}else {
			System.out.println("指令有误!");
			return mView = new MyLrListView();
		}
		switch(command1) {
		case BACK:
			mView = new UserMenuView();
			break;
		case RETURN_CAR:
			int carId = Integer.parseInt(command2);
			ub.returnCar(carId);
			mView = new MyLrListView();
			break;
		default:
			System.out.println("指令有误!");
			mView = new MyLrListView();			
			break;
		}
		return mView;
	}
	public void showMenu() {
		System.out.println();
		System.out.println("输入0:返回");
		System.out.println("输入9+汽车编号:还车");
	}

}

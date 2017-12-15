package com.yichao.views;

import com.yichao.bizImpl.UserBizImpl;

public class MyOrListView extends View {

	private final String BACK = "0";
	private final String LEND_CAR_BY_ORDER ="9";
	@Override
	public View showView() {
		ub.showOrderRecordByUser(UserBizImpl.mUser.getUserId());
		showMenu();
		String command = iT.getString();
		String command1 = "";
		String command2 = "";
		String command3 = "";
		int index1 = command.indexOf("+");
		int index2 = command.lastIndexOf("+");
		if(command.length() > 2 && index1 == 1 && index2 != 1) {
			command1 = command.substring(0,index1);
			command2 = command.substring(index1+1, index2);
			command3 = command.substring(index2+1);
			if(iT.isInt(command2) && iT.isInt(command3)) {
				
			}else {
				System.out.println("指令有误!");
				return mView = new MyOrListView();
			}
		}else if(command.length() > 2 && index1 == 1 && index2 == 1) {
			command1 = command.substring(0,index1);
			command2 = command.substring(index1+1);
			if(iT.isInt(command2)) {
				
			}else {
				System.out.println("指令有误!");
				return mView = new MyOrListView();
			}
		}else if(command.length() == 1 && index1 == -1) {
			command1 = command;
		}else {
			System.out.println("指令有误!");
			return mView = new MyOrListView();
		}
		switch(command1) {
		case BACK:
			mView = new UserMenuView();
			break;
		case LEND_CAR_BY_ORDER:
			int carId = Integer.parseInt(command2);
			int lendDays =Integer.parseInt(command3);
			if(ub.lendOrderCar(carId,lendDays)) {
				System.out.println("通过预约完成租车!信息如下:");
				ub.showLendCar(carId);
			}else {
				System.out.println("操作失败,您未预约该车或未到预约时间!");
			}
			break;
		default:
			System.out.println("指令有误!");
			mView = new MyOrListView();			
			break;
		}
		return mView;		
	}
	/**  
	 * @Title: showMenu  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param     参数  
	 * @return void    返回类型  
	 * @throws  
	 */  
	private void showMenu() {
		System.out.println();
		System.out.println("输入0:返回");
		System.out.println("输入9+汽车编号+天数:通过预约租车");
		
	}

}

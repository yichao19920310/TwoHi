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
				System.out.println("ָ������!");
				return mView = new MyOrListView();
			}
		}else if(command.length() > 2 && index1 == 1 && index2 == 1) {
			command1 = command.substring(0,index1);
			command2 = command.substring(index1+1);
			if(iT.isInt(command2)) {
				
			}else {
				System.out.println("ָ������!");
				return mView = new MyOrListView();
			}
		}else if(command.length() == 1 && index1 == -1) {
			command1 = command;
		}else {
			System.out.println("ָ������!");
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
				System.out.println("ͨ��ԤԼ����⳵!��Ϣ����:");
				ub.showLendCar(carId);
			}else {
				System.out.println("����ʧ��,��δԤԼ�ó���δ��ԤԼʱ��!");
			}
			break;
		default:
			System.out.println("ָ������!");
			mView = new MyOrListView();			
			break;
		}
		return mView;		
	}
	/**  
	 * @Title: showMenu  
	 * @Description: TODO(������һ�仰�����������������)  
	 * @param     ����  
	 * @return void    ��������  
	 * @throws  
	 */  
	private void showMenu() {
		System.out.println();
		System.out.println("����0:����");
		System.out.println("����9+�������+����:ͨ��ԤԼ�⳵");
		
	}

}

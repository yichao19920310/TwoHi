/**  
 * @Title: UserMenuView.java  
 * @Package com.yichao.test  
 * @Description: TODO(��һ�仰�������ļ���ʲô)  
 * @author Administrator  
 * @date 2017��12��10��  
 * @version V1.0  
 */  
package com.yichao.views;

/**  
 * @ClassName: UserMenuView  
 * @Description: TODO(������һ�仰��������������)  
 * @author Administrator  
 * @date 2017��12��10��  
 *    
 */
public class UserMenuView extends View {

	private final String EXIT = "0";
	private final String LEND_CAR = "1";
	private final String ORDER_CAR = "2";
	private final String SORT = "3";
	private final String SEARCH_BY_BRAND = "4";
	private final String SEARCH_BY_TYPE = "5";
	private final String SEARCH_BY_NAME = "6";
	private final String SHOW_MY_LRLIST = "7";
	private final String SHOW_MY_ORLIST = "8";
	private final String RETURN_CAR = "9";
 	/* (�� Javadoc)  
	 * <p>Title: showView</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.views.View#showView()  
	 */
	@Override
	public View showView() {
		ub.showAllCar();		
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
				return mView = new UserMenuView();
			}
		}else if(command.length() > 2 && index1 == 1 && index2 == 1) {
			command1 = command.substring(0,index1);
			command2 = command.substring(index1+1);
			if(iT.isInt(command2)) {
				
			}else {
				System.out.println("ָ������!");
				return mView = new UserMenuView();
			}
		}else if(command.length() == 1 && index1 == -1) {
			command1 = command;
		}else {
			System.out.println("ָ������!");
			return mView = new UserMenuView();
		}
		switch(command1) {
		case EXIT:
			mView = null;
			ub.logInfo("�˳���¼");
			System.out.println("�ټ�!");
			break;
		case LEND_CAR:			
			int carId = Integer.parseInt(command2);
			int lendDays = Integer.parseInt(command3);			
			if(ub.lendCar(carId, lendDays)) {
				System.out.println("�⳵�ɹ�!��Ϣ����:");
				ub.logInfo("�⳵�ɹ�");
				ub.showLendCar(carId);
			}else {
				System.out.println("�⳵ʧ��!");
				ub.logInfo("�⳵ʧ��");
			}
			mView = new UserMenuView();					
			break;
		case ORDER_CAR:
			break;
		case SORT:
			int sortType = Integer.parseInt(command2);
			ub.sortCar(sortType);
			mView = new UserMenuView();
			break;
		case SEARCH_BY_BRAND:
			int carBrandId = Integer.parseInt(command2);
			ub.showAllCar(1, carBrandId);
			mView = new UserMenuView();
			break;
		case SEARCH_BY_TYPE:
			int carTypeId = Integer.parseInt(command2);
			ub.showAllCar(2,carTypeId);
			mView = new UserMenuView();
			break;
		case SEARCH_BY_NAME:
			mView = new SearchCarByNameView();
			break;
		case SHOW_MY_LRLIST:
			mView = new MyLrListView();
			break;
		case SHOW_MY_ORLIST:
			mView = new MyOrListView();
			break;
		case RETURN_CAR:
			int carId1 = Integer.parseInt(command2);
			if(ub.returnCar(carId1)) {
				System.out.println("�����ɹ�!��Ϣ����:");
				ub.logInfo("�����ɹ�");
				ub.showReturnCar(carId1);
			}else {
				System.out.println("����ʧ��!");
				ub.logInfo("����ʧ��");
			}
			mView = new UserMenuView();
			
			break;
		default:
			System.out.println("ָ������!");
			mView = new UserMenuView();
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
		System.out.println("����0:�˳�");
		System.out.println("����1+�������+�������:�⳵");
		System.out.println("����2+�������:ԤԼ��");
		System.out.println("����3+1:�۸�������;3+2:�۸���������");		
		System.out.println("����4+Ʒ�Ʊ��:��Ʒ������");
		System.out.println("����5+���ͱ��:����������");
		System.out.println("����6:��������������");
		System.out.println("����7:�鿴�ҵ�����¼");
		System.out.println("����8:�鿴�ҵ�ԤԼ��¼");
		System.out.println("����9+�������:����");		
	}

}

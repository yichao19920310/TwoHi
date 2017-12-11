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

	final String EXIT = "0";
	final String LEND_CAR = "1";
	final String ORDER_CAR = "2";
	final String SORT = "3";
	final String SEARCH_BY_BRAND = "4";
	final String SEARCH_BY_TYPE = "5";
	final String SHOW_ALL_CARS = "6";
	final String SHOW_MY_LRLIST = "7";
	final String SHOW_MY_ORLIST = "8";
	final String RETURN_CAR = "9";
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
		}
		switch(command1) {
		case EXIT:
			mView = null;
			System.out.println("�ټ�!");
			break;
		case LEND_CAR:			
			int carId = Integer.parseInt(command2);
			int lendDays = Integer.parseInt(command3);			
			if(ub.lendCar(carId, lendDays)) {
				System.out.println("�⳵�ɹ�!��Ϣ����:");
			}else {
				System.out.println("�⳵ʧ��!");
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
			break;
		case SEARCH_BY_TYPE:
			break;
		case SHOW_ALL_CARS:
			break;
		case SHOW_MY_LRLIST:
			break;
		case SHOW_MY_ORLIST:
			break;
		case RETURN_CAR:
			break;
		default:
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
		System.out.println("����6:�鿴ȫ������");
		System.out.println("����7:�鿴�ҵ�����¼");
		System.out.println("����8:�鿴�ҵ�ԤԼ��¼");
		System.out.println("����9+�������:����");		
	}

}

/**  
 * @Title: UserMenuView.java  
 * @Package com.yichao.test  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author Administrator  
 * @date 2017年12月10日  
 * @version V1.0  
 */  
package com.yichao.views;

/**  
 * @ClassName: UserMenuView  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author Administrator  
 * @date 2017年12月10日  
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
 	/* (非 Javadoc)  
	 * <p>Title: showView</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.views.View#showView()  
	 */
	@Override
	public View showView() {
		ub.showAllCar(1);		
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
		}else if(command.length() > 2 && index1 == 1 && index2 == 1) {
			command1 = command.substring(0,index1);
			command2 = command.substring(index1+1);
		}else if(command.length() == 1 && index1 == -1) {
			command1 = command;
		}
		switch(command1) {
		case EXIT:
			mView = null;
			System.out.println("再见!");
			break;
		case LEND_CAR:
			int carId = Integer.parseInt(command2);
			int lendDays = Integer.parseInt(command3);
			boolean isSuccess = ub.lendCar(carId, lendDays);
			break;
		case ORDER_CAR:
			break;
		case SORT:
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
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param     参数  
	 * @return void    返回类型  
	 * @throws  
	 */  
	private void showMenu() {
		System.out.println();
		System.out.println("输入0:退出");
		System.out.println("输入1+汽车编号+租借天数:租车");
		System.out.println("输入2+汽车编号:预约车");
		System.out.println("输入3+1:价格降序排列;2+2:价格升序排列");		
		System.out.println("输入4+品牌编号:按品牌搜索");
		System.out.println("输入5+类型编号:按类型搜索");
		System.out.println("输入6:查看全部汽车");
		System.out.println("输入7:查看我的租借记录");
		System.out.println("输入8:查看我的预约记录");
		System.out.println("输入9+汽车编号:还车");		
	}

}

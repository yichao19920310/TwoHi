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
 	/* (非 Javadoc)  
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
				System.out.println("指令有误!");
				return mView = new UserMenuView();
			}
		}else if(command.length() > 2 && index1 == 1 && index2 == 1) {
			command1 = command.substring(0,index1);
			command2 = command.substring(index1+1);
			if(iT.isInt(command2)) {
				
			}else {
				System.out.println("指令有误!");
				return mView = new UserMenuView();
			}
		}else if(command.length() == 1 && index1 == -1) {
			command1 = command;
		}else {
			System.out.println("指令有误!");
			return mView = new UserMenuView();
		}
		switch(command1) {
		case EXIT:
			mView = null;
			ub.logInfo("退出登录");
			System.out.println("再见!");
			break;
		case LEND_CAR:			
			int carId = Integer.parseInt(command2);
			int lendDays = Integer.parseInt(command3);			
			if(ub.lendCar(carId, lendDays)) {
				System.out.println("租车成功!信息如下:");
				ub.logInfo("租车成功");
				ub.showLendCar(carId);
			}else {
				System.out.println("租车失败!");
				ub.logInfo("租车失败");
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
				System.out.println("还车成功!信息如下:");
				ub.logInfo("还车成功");
				ub.showReturnCar(carId1);
			}else {
				System.out.println("还车失败!");
				ub.logInfo("还车失败");
			}
			mView = new UserMenuView();
			
			break;
		default:
			System.out.println("指令有误!");
			mView = new UserMenuView();
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
		System.out.println("输入3+1:价格降序排列;3+2:价格升序排列");		
		System.out.println("输入4+品牌编号:按品牌搜索");
		System.out.println("输入5+类型编号:按类型搜索");
		System.out.println("输入6:按汽车名称搜索");
		System.out.println("输入7:查看我的租借记录");
		System.out.println("输入8:查看我的预约记录");
		System.out.println("输入9+汽车编号:还车");		
	}

}

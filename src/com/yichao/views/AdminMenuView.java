package com.yichao.views;

public class AdminMenuView extends View {

	@Override
	public View showView() {
		ab.showAllCar();
		showMenu();
		String command = iT.getString();
		String command1 = "";
		String command2 = "";
		int index = command.indexOf("+");
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

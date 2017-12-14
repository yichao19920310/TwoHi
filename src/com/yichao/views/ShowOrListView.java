/**  
 * @Title: ShowOrListView.java  
 * @Package com.yichao.views  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author Administrator  
 * @date 2017年12月14日  
 * @version V1.0  
 */  
package com.yichao.views;

/**  
 * @ClassName: ShowOrListView  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author Administrator  
 * @date 2017年12月14日  
 *    
 */
public class ShowOrListView extends View {

	/* (非 Javadoc)  
	 * <p>Title: showView</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.views.View#showView()  
	 */
	@Override
	public View showView() {
		ab.showAllOrderRecord();
		showMenu();
		boolean b = true;
		while(b) {
			String command = iT.getString();
			String command1 = "";
			String command2 = "";
			int index = command.indexOf("+");
			if(command.length()>2 && index == 1) {
				command1 =  command.substring(0,index);
				command2 = command.substring(index + 1);
				if(iT.isInt(command2)) {
					
				}else {
					System.out.println("指令有误!");
					return mView = new AdminMenuView();
				}
			}else if(command.length()==1 && index == -1) {
				command1 = command;
			}else {
				System.out.println("指令有误!");
				return mView = new AdminMenuView();
			}
			switch(command1) {
			case "0":
				b = false;
				break;
			case "1":
				ab.showAllOrderRecord();
				break;
			case "2":
				int userId = Integer.parseInt(command2);
				ab.showOrderRecordByUser(userId);
				break;
			case "3":
				int carId = Integer.parseInt(command2);
				ab.showOrderRecordByCar(carId);
				break;
			default:
				System.out.println("指令有误!");
				break;
			}
		}
		
		return mView = new AdminMenuView();
	}

	private void showMenu() {
		System.out.println();
		System.out.println("输入0:返回");
		System.out.println("输入1:查看所有预约记录");
		System.out.println("输入2+用户编号:查看用户预约记录");
		System.out.println("输入3+汽车编号:查看汽车预约记录");
		
	}

}

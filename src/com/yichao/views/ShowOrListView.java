/**  
 * @Title: ShowOrListView.java  
 * @Package com.yichao.views  
 * @Description: TODO(��һ�仰�������ļ���ʲô)  
 * @author Administrator  
 * @date 2017��12��14��  
 * @version V1.0  
 */  
package com.yichao.views;

/**  
 * @ClassName: ShowOrListView  
 * @Description: TODO(������һ�仰��������������)  
 * @author Administrator  
 * @date 2017��12��14��  
 *    
 */
public class ShowOrListView extends View {

	/* (�� Javadoc)  
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
				System.out.println("ָ������!");
				break;
			}
		}
		
		return mView = new AdminMenuView();
	}

	private void showMenu() {
		System.out.println();
		System.out.println("����0:����");
		System.out.println("����1:�鿴����ԤԼ��¼");
		System.out.println("����2+�û����:�鿴�û�ԤԼ��¼");
		System.out.println("����3+�������:�鿴����ԤԼ��¼");
		
	}

}

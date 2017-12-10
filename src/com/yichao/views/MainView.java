/**  
 * @Title: MainView.java  
 * @Package com.yichao.test  
 * @Description: TODO(��һ�仰�������ļ���ʲô)  
 * @author Administrator  
 * @date 2017��12��10��  
 * @version V1.0  
 */  
package com.yichao.views;

/**  
 * @ClassName: MainView  
 * @Description: TODO(������һ�仰��������������)  
 * @author Administrator  
 * @date 2017��12��10��  
 *    
 */
public class MainView extends View {

	private final int USER_LOGIN = 1;
	private final int USER_REGIST = 2;
	private final int ADMIN_LOGIN = 3;
	private final int EXIT = 0;
	@Override
	public View showView() {
		showWelcome();
		showMenu();
		return mView;
	}
	public void showWelcome() {
		for (int i = 0; i <= 100; i++) {
			if(i==50) {
				System.out.println("\n");
				System.out.println("\t\t��ӭ���ʶ����⳵!");
				System.out.println("\n");
			}else {
				System.out.print("*");
			}			
		}
		System.out.println();
	}
	public void showMenu() {
		System.out.println("1.�û���¼\t2.�û�ע��\t3.����Ա��¼\t0.�˳�");
		int choose = iT.getInt();
		switch(choose) {
		case USER_LOGIN:
			mView = new UserLoginView();
			break;
		case USER_REGIST:
			mView = new UserRegistView();
			break;
		case ADMIN_LOGIN:
			mView = new AdminLoginView();
			break;
		case EXIT:
			mView = null;
			System.out.println("�ټ�!");
			break;
		default:
			mView = new MainView();
			break;
		}
	}

	
}

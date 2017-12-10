/**  
 * @Title: MainView.java  
 * @Package com.yichao.test  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author Administrator  
 * @date 2017年12月10日  
 * @version V1.0  
 */  
package com.yichao.views;

/**  
 * @ClassName: MainView  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author Administrator  
 * @date 2017年12月10日  
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
				System.out.println("\t\t欢迎访问二嗨租车!");
				System.out.println("\n");
			}else {
				System.out.print("*");
			}			
		}
		System.out.println();
	}
	public void showMenu() {
		System.out.println("1.用户登录\t2.用户注册\t3.管理员登录\t0.退出");
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
			System.out.println("再见!");
			break;
		default:
			mView = new MainView();
			break;
		}
	}

	
}

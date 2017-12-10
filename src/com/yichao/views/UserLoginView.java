/**  
 * @Title: UserLoginView.java  
 * @Package com.yichao.test  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author Administrator  
 * @date 2017年12月10日  
 * @version V1.0  
 */  
package com.yichao.views;

import com.yichao.bizImpl.UserBizImpl;

/**  
 * @ClassName: UserLoginView  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author Administrator  
 * @date 2017年12月10日  
 *    
 */
public class UserLoginView extends View {

	public static int loginCount = 0;
	@Override
	public View showView() {
		System.out.println("=====>>>用户登录");
		System.out.println("请输入用户名:");
		String userName = iT.getString();
		System.out.println("请输入密码:");
		String userPwd = iT.getString();
		boolean isSuccess = ub.userLogin(userName, userPwd);
		if(isSuccess) {
			System.out.println("登录成功!");
			System.out.println("欢迎" + UserBizImpl.mUser.getUserName() + "!");
			mView = new UserMenuView();
		}else {
			System.out.println("登录失败!");
			loginCount++;
			if(loginCount == 3) {
				System.out.println("登录失败3次,自动前往注册界面!");
				loginCount = 0;
				mView = new UserRegistView();
			}else {
				mView = new MainView();
			}
			
		}
		return mView;
	}

	
}

/**  
 * @Title: UserRegistView.java  
 * @Package com.yichao.test  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author Administrator  
 * @date 2017年12月10日  
 * @version V1.0  
 */  
package com.yichao.views;

/**  
 * @ClassName: UserRegistView  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author Administrator  
 * @date 2017年12月10日  
 *    
 */
public class UserRegistView extends View {

	/* (非 Javadoc)  
	 * <p>Title: showView</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.views.View#showView()  
	 */  
	@Override
	public View showView() {
		System.out.println("=====>>>用户注册");
		System.out.println("请输入用户名:");
		String userName = iT.getString();
		System.out.println("请输入密码:");
		String userPwd = iT.getString();
		boolean isSuccess = ub.userRegist(userName, userPwd);
		if(isSuccess) {
			System.out.println("注册成功!");
			
		}else {
			System.out.println("注册失败!");
		}
		mView = new MainView();
		return mView;
	}

}

/**  
 * @Title: AdminLoginView.java  
 * @Package com.yichao.test  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author Administrator  
 * @date 2017年12月10日  
 * @version V1.0  
 */  
package com.yichao.views;

import com.yichao.bizImpl.AdminBizImpl;

/**  
 * @ClassName: AdminLoginView  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author Administrator  
 * @date 2017年12月10日  
 *    
 */
public class AdminLoginView extends View {

	/* (非 Javadoc)  
	 * <p>Title: showView</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.views.View#showView()  
	 */  
	@Override
	public View showView() {
		System.out.println("=====>>>管理员登录");
		System.out.println("请输入用户名:");
		String adminName = iT.getString();
		System.out.println("请输入密码:");
		String adminPwd = iT.getString();
		boolean isSuccess = ab.adminLogin(adminName, adminPwd);
		if(isSuccess) {
			System.out.println("登录成功!");
			System.out.println("欢迎" + AdminBizImpl.mAdmin.getAdminName() + "!");
			mView = new AdminMenuView();
		}else {
			System.out.println("登录失败!");			
			
			
			mView = new MainView();
			
			
		}
		return mView;
	}

}

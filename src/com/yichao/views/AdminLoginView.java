/**  
 * @Title: AdminLoginView.java  
 * @Package com.yichao.test  
 * @Description: TODO(��һ�仰�������ļ���ʲô)  
 * @author Administrator  
 * @date 2017��12��10��  
 * @version V1.0  
 */  
package com.yichao.views;

import com.yichao.bizImpl.AdminBizImpl;

/**  
 * @ClassName: AdminLoginView  
 * @Description: TODO(������һ�仰��������������)  
 * @author Administrator  
 * @date 2017��12��10��  
 *    
 */
public class AdminLoginView extends View {

	/* (�� Javadoc)  
	 * <p>Title: showView</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.views.View#showView()  
	 */  
	@Override
	public View showView() {
		System.out.println("=====>>>����Ա��¼");
		System.out.println("�������û���:");
		String adminName = iT.getString();
		System.out.println("����������:");
		String adminPwd = iT.getString();
		boolean isSuccess = ab.adminLogin(adminName, adminPwd);
		if(isSuccess) {
			System.out.println("��¼�ɹ�!");
			System.out.println("��ӭ" + AdminBizImpl.mAdmin.getAdminName() + "!");
			mView = new AdminMenuView();
		}else {
			System.out.println("��¼ʧ��!");			
			
			
			mView = new MainView();
			
			
		}
		return mView;
	}

}

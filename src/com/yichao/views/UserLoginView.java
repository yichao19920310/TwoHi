/**  
 * @Title: UserLoginView.java  
 * @Package com.yichao.test  
 * @Description: TODO(��һ�仰�������ļ���ʲô)  
 * @author Administrator  
 * @date 2017��12��10��  
 * @version V1.0  
 */  
package com.yichao.views;

import com.yichao.bizImpl.UserBizImpl;

/**  
 * @ClassName: UserLoginView  
 * @Description: TODO(������һ�仰��������������)  
 * @author Administrator  
 * @date 2017��12��10��  
 *    
 */
public class UserLoginView extends View {

	public static int loginCount = 0;
	@Override
	public View showView() {
		System.out.println("=====>>>�û���¼");
		System.out.println("�������û���:");
		String userName = iT.getString();
		System.out.println("����������:");
		String userPwd = iT.getString();
		boolean isSuccess = ub.userLogin(userName, userPwd);
		if(isSuccess) {
			System.out.println("��¼�ɹ�!");
			System.out.println("��ӭ" + UserBizImpl.mUser.getUserName() + "!");
			mView = new UserMenuView();
		}else {
			System.out.println("��¼ʧ��!");
			loginCount++;
			if(loginCount == 3) {
				System.out.println("��¼ʧ��3��,�Զ�ǰ��ע�����!");
				loginCount = 0;
				mView = new UserRegistView();
			}else {
				mView = new MainView();
			}
			
		}
		return mView;
	}

	
}

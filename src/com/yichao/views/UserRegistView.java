/**  
 * @Title: UserRegistView.java  
 * @Package com.yichao.test  
 * @Description: TODO(��һ�仰�������ļ���ʲô)  
 * @author Administrator  
 * @date 2017��12��10��  
 * @version V1.0  
 */  
package com.yichao.views;

/**  
 * @ClassName: UserRegistView  
 * @Description: TODO(������һ�仰��������������)  
 * @author Administrator  
 * @date 2017��12��10��  
 *    
 */
public class UserRegistView extends View {

	/* (�� Javadoc)  
	 * <p>Title: showView</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.views.View#showView()  
	 */  
	@Override
	public View showView() {
		System.out.println("=====>>>�û�ע��");
		System.out.println("�������û���:");
		String userName = iT.getString();
		System.out.println("����������:");
		String userPwd = iT.getString();
		boolean isSuccess = ub.userRegist(userName, userPwd);
		if(isSuccess) {
			System.out.println("ע��ɹ�!");
			
		}else {
			System.out.println("ע��ʧ��!");
		}
		mView = new MainView();
		return mView;
	}

}

/**  
 * @Title: Test.java  
 * @Package com.yichao.test  
 * @Description: TODO(��һ�仰�������ļ���ʲô)  
 * @author Administrator  
 * @date 2017��12��10��  
 * @version V1.0  
 */  
package com.yichao.test;

import com.yichao.views.MainView;
import com.yichao.views.View;

/**  
 * @ClassName: Test  
 * @Description: TODO(������һ�仰��������������)  
 * @author Administrator  
 * @date 2017��12��10��  
 *    
 */
public class Test {

	/**  
	 * @Title: main  
	 * @Description: TODO(������һ�仰�����������������)  
	 * @param @param args    ����  
	 * @return void    ��������  
	 * @throws  
	 */
	public static void main(String[] args) {
		View view = new MainView();
		while(view != null) {
			view = view.showView();
		}

	}

}

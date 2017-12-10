/**  
 * @Title: Test.java  
 * @Package com.yichao.test  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author Administrator  
 * @date 2017年12月10日  
 * @version V1.0  
 */  
package com.yichao.test;

import com.yichao.views.MainView;
import com.yichao.views.View;

/**  
 * @ClassName: Test  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author Administrator  
 * @date 2017年12月10日  
 *    
 */
public class Test {

	/**  
	 * @Title: main  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param args    参数  
	 * @return void    返回类型  
	 * @throws  
	 */
	public static void main(String[] args) {
		View view = new MainView();
		while(view != null) {
			view = view.showView();
		}

	}

}

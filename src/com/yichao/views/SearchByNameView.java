/**  
 * @Title: SearchByNameView.java  
 * @Package com.yichao.views  
 * @Description: TODO(��һ�仰�������ļ���ʲô)  
 * @author Administrator  
 * @date 2017��12��12��  
 * @version V1.0  
 */  
package com.yichao.views;

/**  
 * @ClassName: SearchByNameView  
 * @Description: TODO(������һ�仰��������������)  
 * @author Administrator  
 * @date 2017��12��12��  
 *    
 */
public class SearchByNameView extends View {

	/* (�� Javadoc)  
	 * <p>Title: showView</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.views.View#showView()  
	 */
	@Override
	public View showView() {
		System.out.println("��������������(������һ����):");
		String carName = iT.getString();
		ab.showCarByName(carName);
		return mView = new AdminMenuView();
	}

}

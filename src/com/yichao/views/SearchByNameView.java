/**  
 * @Title: SearchByNameView.java  
 * @Package com.yichao.views  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author Administrator  
 * @date 2017年12月12日  
 * @version V1.0  
 */  
package com.yichao.views;

/**  
 * @ClassName: SearchByNameView  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author Administrator  
 * @date 2017年12月12日  
 *    
 */
public class SearchByNameView extends View {

	/* (非 Javadoc)  
	 * <p>Title: showView</p>  
	 * <p>Description: </p>  
	 * @return  
	 * @see com.yichao.views.View#showView()  
	 */
	@Override
	public View showView() {
		System.out.println("请输入汽车名称(可输入一部分):");
		String carName = iT.getString();
		ab.showCarByName(carName);
		return mView = new AdminMenuView();
	}

}

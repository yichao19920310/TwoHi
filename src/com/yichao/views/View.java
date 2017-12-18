package com.yichao.views;

import com.yichao.bizImpl.AdminBizImpl;
import com.yichao.bizImpl.UserBizImpl;
import com.yichao.tools.InputTool;
/**
 * 
  * @ClassName:  View   
  * @Description:视图类,显示界面   
  * @author: Dovahkiin  
  * @date:   2017年12月18日 上午9:59:46   
  *
 */
public abstract class View {
	public View mView;
	public InputTool iT = InputTool.getInstance();
	public static UserBizImpl ub = new UserBizImpl();
	public static AdminBizImpl ab = new AdminBizImpl();
	public abstract View showView();
}

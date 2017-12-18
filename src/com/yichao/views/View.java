package com.yichao.views;

import com.yichao.bizImpl.AdminBizImpl;
import com.yichao.bizImpl.UserBizImpl;
import com.yichao.tools.InputTool;
/**
 * 
  * @ClassName:  View   
  * @Description:��ͼ��,��ʾ����   
  * @author: Dovahkiin  
  * @date:   2017��12��18�� ����9:59:46   
  *
 */
public abstract class View {
	public View mView;
	public InputTool iT = InputTool.getInstance();
	public static UserBizImpl ub = new UserBizImpl();
	public static AdminBizImpl ab = new AdminBizImpl();
	public abstract View showView();
}

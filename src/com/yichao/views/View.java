package com.yichao.views;

import com.yichao.biz.UserBiz;
import com.yichao.bizImpl.AdminBizImpl;
import com.yichao.bizImpl.UserBizImpl;
import com.yichao.tools.InputTool;

public abstract class View {
	public View mView;
	public InputTool iT = InputTool.getInstance();
	public static UserBizImpl ub = new UserBizImpl();
	public static AdminBizImpl ab = new AdminBizImpl();
	public abstract View showView();
}

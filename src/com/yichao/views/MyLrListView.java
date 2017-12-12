package com.yichao.views;

import com.yichao.bizImpl.UserBizImpl;

public class MyLrListView extends View {

	@Override
	public View showView() {
		ub.showLendRecordByUser(UserBizImpl.mUser.getUserId());
		showMenu();
		return mView;
	}
	public void showMenu() {
		
	}

}

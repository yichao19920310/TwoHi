package com.yichao.views;

import com.yichao.bizImpl.UserBizImpl;

public class MyOrListView extends View {

	@Override
	public View showView() {
		ub.showOrderRecordByUser(UserBizImpl.mUser.getUserId());
		return mView;
	}

}

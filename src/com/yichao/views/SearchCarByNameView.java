package com.yichao.views;

public class SearchCarByNameView extends View {

	@Override
	public View showView() {
		System.out.println("请输入汽车名车(可输入部分):");
		String carName = iT.getString();
		ub.showCarByName(carName);
		return mView = new UserMenuView();
	}

}

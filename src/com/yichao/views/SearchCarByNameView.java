package com.yichao.views;

public class SearchCarByNameView extends View {

	@Override
	public View showView() {
		System.out.println("��������������(�����벿��):");
		String carName = iT.getString();
		ub.showCarByName(carName);
		return mView = new UserMenuView();
	}

}
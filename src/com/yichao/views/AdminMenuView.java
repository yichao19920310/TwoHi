package com.yichao.views;

public class AdminMenuView extends View {

	@Override
	public View showView() {
		ab.showAllCar();
		showMenu();
		String command = iT.getString();
		String command1 = "";
		String command2 = "";
		int index = command.indexOf("+");
		return mView;
	}

	private void showMenu() {
		System.out.println();
		System.out.println("����0:�˳�");
		System.out.println("����1+�������:�鿴ָ������");
	
		System.out.println("����2+1:�۸�������;2+2:�۸���������");		
		System.out.println("����3+Ʒ�Ʊ��:��Ʒ������");
		System.out.println("����4+���ͱ��:����������");
		System.out.println("����5:�鿴ȫ������");
		System.out.println("����6:�������");
		System.out.println("����7+�������:�޸�������Ϣ");
		System.out.println("����8:�鿴����¼");		
		System.out.println("����9:�鿴ԤԼ��¼");		
	}

}

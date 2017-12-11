package com.yichao.biz;

public interface OrderRecordBiz {

	public abstract void showAllOrderRecord();
	public abstract void showOrderRecordByUser(int userId);
	public abstract void showOrderRecordByCar(int carId);
}

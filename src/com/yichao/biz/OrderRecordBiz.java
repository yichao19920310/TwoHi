package com.yichao.biz;

import com.yichao.log4j.Log4j;

public interface OrderRecordBiz extends Log4j{

	public abstract void showAllOrderRecord();
	public abstract void showOrderRecordByUser(int userId);
	public abstract void showOrderRecordByCar(int carId);
}

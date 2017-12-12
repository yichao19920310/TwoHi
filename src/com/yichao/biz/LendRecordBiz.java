package com.yichao.biz;

import com.yichao.log4j.Log4j;

public interface LendRecordBiz extends Log4j{

	public abstract void showAllLendRecord();
	public abstract void showLendRecordByUser(int userId);
	public abstract void showLendRecordByCar(int carId);
}

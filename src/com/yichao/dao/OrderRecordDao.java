package com.yichao.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.yichao.bean.OrderRecord;

public interface OrderRecordDao {

	public abstract ArrayList<OrderRecord> getOrList() throws SQLException;
	
	public abstract ArrayList<OrderRecord> getOrListByUser(int userId) throws SQLException;
	
	public abstract ArrayList<OrderRecord> getOrListByCar(int carId) throws SQLException;
}

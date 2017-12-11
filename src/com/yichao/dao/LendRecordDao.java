package com.yichao.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.yichao.bean.LendRecord;

public interface LendRecordDao {

	public abstract ArrayList<LendRecord> getLrList() throws SQLException;
	public abstract ArrayList<LendRecord> getLrListByUser(int userId) throws SQLException;
	public abstract ArrayList<LendRecord> getLrListByCar(int carId) throws SQLException;
}

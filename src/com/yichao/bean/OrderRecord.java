package com.yichao.bean;

import java.io.Serializable;
import java.sql.Date;

public class OrderRecord implements Serializable {
	
	/**  
	 * @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
	 */  
	private static final long serialVersionUID = -4152934087508142954L;
	private int orId;
	private String orNumber;
	private int carId;
	private String carName;
	private int userId;
	private String userName;
	private Date orderDate;
	private Date expLendDate;
	private Date actLendDate;
	private int orStatus;
	public int getOrId() {
		return orId;
	}
	public void setOrId(int orId) {
		this.orId = orId;
	}
	public String getOrNumber() {
		return orNumber;
	}
	public void setOrNumber(String orNumber) {
		this.orNumber = orNumber;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getExpLendDate() {
		return expLendDate;
	}
	public void setExpLendDate(Date expLendDate) {
		this.expLendDate = expLendDate;
	}
	public Date getActLendDate() {
		return actLendDate;
	}
	public void setActLendDate(Date actLendDate) {
		this.actLendDate = actLendDate;
	}
	public int getOrStatus() {
		return orStatus;
	}
	public void setOrStatus(int orStatus) {
		this.orStatus = orStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

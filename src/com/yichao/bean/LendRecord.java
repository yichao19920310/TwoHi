package com.yichao.bean;

import java.io.Serializable;
import java.sql.Date;

public class LendRecord implements Serializable {
	
	/**  
	 * @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
	 */  
	private static final long serialVersionUID = 7567147760133606158L;
	
	private int lrId;
	private String lrNumber;
	private int carId;
	private String carName;
	private int userId;
	private String userName;
	private double carLendPrice;
	private double lateFee;
	private double totalFee;
	private Date lendDate;
	private Date expRetuDate;
	private Date actRetuDate;
	private int lrStatus;
	public int getLrId() {
		return lrId;
	}
	public void setLrId(int lrId) {
		this.lrId = lrId;
	}
	public String getLrNumber() {
		return lrNumber;
	}
	public void setLrNumber(String lrNumber) {
		this.lrNumber = lrNumber;
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
	public double getCarLendPrice() {
		return carLendPrice;
	}
	public void setCarLendPrice(double carLendPrice) {
		this.carLendPrice = carLendPrice;
	}
	public double getLateFee() {
		return lateFee;
	}
	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}
	public double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	public Date getLendDate() {
		return lendDate;
	}
	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}
	public Date getExpRetuDate() {
		return expRetuDate;
	}
	public void setExpRetuDate(Date expRetuDate) {
		this.expRetuDate = expRetuDate;
	}
	public Date getActRetuDate() {
		return actRetuDate;
	}
	public void setActRetuDate(Date actRetuDate) {
		this.actRetuDate = actRetuDate;
	}
	public int getLrStatus() {
		return lrStatus;
	}
	public void setLrStatus(int lrStatus) {
		this.lrStatus = lrStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "LendRecord [lrId=" + lrId + ", lrNumber=" + lrNumber + ", carId=" + carId + ", carName=" + carName
				+ ", userId=" + userId + ", userName=" + userName + ", carLendPrice=" + carLendPrice + ", lateFee="
				+ lateFee + ", totalFee=" + totalFee + ", lendDate=" + lendDate + ", expRetuDate=" + expRetuDate
				+ ", actRetuDate=" + actRetuDate + ", lrStatus=" + lrStatus + "]";
	}
}

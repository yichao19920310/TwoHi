package com.yichao.bean;

import java.io.Serializable;

public class Car implements Serializable {
	
	/**  
	 * @Fields field:field:{todo}(��һ�仰�������������ʾʲô)  
	 */  
	private static final long serialVersionUID = -3526046204843235256L;
	private int carId;
	private String carName;
	private String carRemark;
	private String carBrand;
	private int carBrandId;
	private String carType;
	private int carTypeId;
	private double carPrice;
	private double carLendPrice;
	//1���� 0������
	private int carLendStatus;
	//1δԤԼ 0��ԤԼ
	private int carOrderStatus;
	//1�ϼ� 0�¼�
	private int carStatus;
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
	public String getCarRemark() {
		return carRemark;
	}
	public void setCarRemark(String carRemark) {
		this.carRemark = carRemark;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public int getCarBrandId() {
		return carBrandId;
	}
	public void setCarBrandId(int carBrandId) {
		this.carBrandId = carBrandId;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public int getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(int carTypeId) {
		this.carTypeId = carTypeId;
	}
	public double getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}
	public double getCarLendPrice() {
		return carLendPrice;
	}
	public void setCarLendPrice(double carLendPrice) {
		this.carLendPrice = carLendPrice;
	}
	public int getCarLendStatus() {
		return carLendStatus;
	}
	public void setCarLendStatus(int carLendStatus) {
		this.carLendStatus = carLendStatus;
	}
	public int getCarOrderStatus() {
		return carOrderStatus;
	}
	public void setCarOrderStatus(int carOrderStatus) {
		this.carOrderStatus = carOrderStatus;
	}
	public int getCarStatus() {
		return carStatus;
	}
	public void setCarStatus(int carStatus) {
		this.carStatus = carStatus;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Car [carId=" + carId + ", carName=" + carName + ", carRemark=" + carRemark + ", carBrand=" + carBrand
				+ ", carBrandId=" + carBrandId + ", carType=" + carType + ", carTypeId=" + carTypeId + ", carPrice="
				+ carPrice + ", carLendPrice=" + carLendPrice + ", carLendStatus=" + carLendStatus + ", carOrderStatus="
				+ carOrderStatus + ", carStatus=" + carStatus + "]";
	}
	
}

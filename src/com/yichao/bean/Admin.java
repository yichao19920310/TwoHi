package com.yichao.bean;

import java.io.Serializable;

public class Admin implements Serializable{

	
	/** 
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	private static final long serialVersionUID = -3006110702744399937L;
	private int adminId;
	private String adminName;
	private String adminPwd;
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

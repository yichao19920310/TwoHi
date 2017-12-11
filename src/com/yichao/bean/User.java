package com.yichao.bean;

import java.io.Serializable;

public class User implements Serializable{
	
	/**  
	 * @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
	 */  
	private static final long serialVersionUID = 6192198099763607799L;
	private int userId;
	private String userName;
	private String userPwd;
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
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

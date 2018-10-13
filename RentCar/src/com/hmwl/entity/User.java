package com.hmwl.entity;

public class User {
	/***
	 * 表users的实体类
	 */
	private Integer userid;
	private String username;
	private String password;
	private String address;
	private String phone;
	private Integer isadmin;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}
	public User(Integer userid, String username, String password, String address, String phone, Integer isadmin) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.isadmin = isadmin;
	}
	public User() {
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", address=" + address
				+ ", phone=" + phone + ", isadmin=" + isadmin + "]";
	}
	
	
}

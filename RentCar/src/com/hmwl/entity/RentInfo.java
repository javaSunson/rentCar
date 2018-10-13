package com.hmwl.entity;

import java.util.Date;

public class RentInfo {
	private Integer rent_id;
	private Integer user_id;
	private Integer car_id;
	private Date start_date;
	private Date end_date;
	private Integer money;
	public Integer getRent_id() {
		return rent_id;
	}
	public void setRent_id(Integer rent_id) {
		this.rent_id = rent_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getCar_id() {
		return car_id;
	}
	public void setCar_id(Integer car_id) {
		this.car_id = car_id;
	}
	public Date getstart_date() {
		return start_date;
	}
	public void setstart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public RentInfo(Integer rent_id, Integer user_id, Integer car_id, Date start_date, Date end_date, Integer money) {
		super();
		this.rent_id = rent_id;
		this.user_id = user_id;
		this.car_id = car_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.money = money;
	}
	public RentInfo() {
		super();
	}
	@Override
	public String toString() {
		return "    "+rent_id + "    " + user_id + "      " + car_id + "    "
				+ start_date + "    " + end_date + "    " + money ;
	}
	
}

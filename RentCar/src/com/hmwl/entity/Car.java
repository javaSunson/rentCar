package com.hmwl.entity;

public class Car {
	private Integer car_id;
	private String car_brand;
	private String car_model;
	private String car_number;
	private double money_day;
	private double car_price;
	private String status;
	public Integer getCar_id() {
		return car_id;
	}
	public void setCar_id(Integer car_id) {
		this.car_id = car_id;
	}
	public String getCar_brand() {
		return car_brand;
	}
	public void setCar_brand(String car_brand) {
		this.car_brand = car_brand;
	}
	public String getCar_model() {
		return car_model;
	}
	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}
	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}
	public double getMoney_day() {
		return money_day;
	}
	public void setMoney_day(double money_day) {
		this.money_day = money_day;
	}
	public double getCar_price() {
		return car_price;
	}
	public void setCar_price(double car_price) {
		this.car_price = car_price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Car(Integer car_id, String car_brand, String car_model, String car_number, double money_day,
			double car_price, String status) {
		super();
		this.car_id = car_id;
		this.car_brand = car_brand;
		this.car_model = car_model;
		this.car_number = car_number;
		this.money_day = money_day;
		this.car_price = car_price;
		this.status = status;
	}
	public Car() {
	}
	@Override
	public String toString() {
		return "" + car_id + "    " + car_brand + "    " + car_model + 
				"    "+ car_number + "    " + money_day + "    " + car_price 
				+ "    " + status;
//		return "编号:" + car_id + "\t品牌:" + car_brand + "\t型号" + car_model + 
//				"\t车牌号:"+ car_number + "\t日租金:" + money_day + "\t价值:" + car_price 
//				+ "\t租借状态:" + status;
	}
	
	
}

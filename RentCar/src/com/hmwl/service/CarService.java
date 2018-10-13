package com.hmwl.service;

import java.util.List;

import com.hmwl.dao.CommonDao;
import com.hmwl.entity.Car;
import com.hmwl.entity.RentInfo;

public class CarService {
	CommonDao cd = new CommonDao();
	/**查看所有汽车的源代码**/
	public List<Car> queryCars(){
		CommonDao cd = new CommonDao();
		String sql = "select * from car";
		List<Car> carlist = cd .commonQuery(new Car(), sql);
		return carlist;
	}
	/**根据id进行汽车查询的代码*/
	public List<Car> queryCarsByID(String car_id){
		CommonDao cd = new CommonDao();
		String sql = "select * from car where car_id = ?";
		List<Car> carlist = cd .commonQuery(new Car(), sql, car_id);
		return carlist;
	}
	/**实现根据价格进行查询**/
	public List<Car> sortPrice(String way){
		CommonDao cd = new CommonDao();
		String sql = "select * from car order by car_price "+way;
		List<Car> carlist = cd .commonQuery(new Car(), sql);
		return carlist;
	}
	/**实现根据品牌进行排序,但是好像不符合需求中根据品牌筛选,待优化**/
	public List<Car> sortBrand(String way){
		CommonDao cd = new CommonDao();
		String sql = "select * from car order by car_brand "+way;
		List<Car> carlist = cd .commonQuery(new Car(), sql);
		return carlist;
	}
	/**管理员可用,根据输入的数据进行车辆插入操作
	 * 1.考虑是不是已经存在同类型的了
	 * 2.如果数量为0,自动将可租性设置为不可租
	 * 
	 * */
	//ui_car_brand.getText(), ui_car_model.getText(), 
	//ui_car_num.getText(), ui_car_price.getText(), ui_day_money.getText(), ui_car_isrent.getText()
	public int addCar(String car_brand,String car_model,String car_number,String car_price
			,String money_day,String status) {
		CommonDao cd = new CommonDao();
		//如果插入的车辆型号是存在的,是不是直接插入到数量比较合适
		//cd.commonQuery(new Car, sql, param)
		String sql = "insert into car values(car_next.nextval,?,?,?,?,?,?)";
		int result = cd.commonUpdate(sql, car_brand, car_model,car_number,car_price,money_day,status);
		return result;
	}
	/**更改车的源代码*/
	public int  changeCar(String car_brand,String car_model,String car_number,String car_price
			,String money_day,String status) {
		/**在GUI可视化的情况下,修改好像不是那么容易了????**/
		String sql = "select * from car where car_brand = ? and car_model = ?" ;     //校验数据库中是不是有这个记录
		String upsql = "update cat set car_brand =?"
				+ ",car_model =?,car_number = ?, car_price = ?,money_day = ? ,status = ?";     //如果存在,允许修改,并且提交
		
		CommonDao cd = new CommonDao();
		List<Car> list = cd.commonQuery(new Car(), sql, car_brand,car_model);
		if(list.size() == 0) {
			return  -1;  //如果数据中不存在该车品牌和型号,就不让修改
		}else {
			/*思考:如果为传过来的数据为空,那么,就有污染数据的风险*/
			
			/***
			 * 解决方按:传过来的值,用户提交的时候,如果从界面上获取的值是空的,就使用查询到的值
			 * */
			if(car_brand == null) {
				car_brand = list.get(0).getCar_brand();
			}
			if(car_model == null) {
				car_model = list.get(0).getCar_model();
			}
			if(car_number == null) {
				car_number = list.get(0).getCar_number();
			}
			if(car_price == null) {
				car_price = String.valueOf(list.get(0).getCar_price());
			}
			if(money_day == null) {
				money_day = String.valueOf(list.get(0).getMoney_day());
			}
			if(status == null) {
				car_number = list.get(0).getStatus();
			}
			
			cd.commonUpdate(upsql,car_brand,car_model,
					car_number,car_price,money_day,status);
		}
		return 1;
	}
	/**该方法用来校验数量是不是大于0*/
	public void checkCanRent() {
		
	}
	/**这里写租借成功之后,处理逻辑*/
	/**车的数量-1*/
	public void minusCar(String car_id) {
		
		String sqlque = "select car_number from car where car_id = ?";
		String sql = "update car set car_number = ? where car_id = ? ";
		Integer car_number = Integer.parseInt(cd.commonQuery(new Car(), sqlque, car_id).get(0).getCar_number())-1;
		int result = cd.commonUpdate(sql, car_number.toString(),car_id);
	}
	/**查看当前自己租了什么车**/
	public void showRentInfoByUser() {
		
	}
	/**还车**/
	public boolean rebackCar() {
		/**先查询当前登录账户租借的车*/
		return false;
	}
	public List<Car> getRecordByCarBrand(String car_brand){
		String sql = "select * from car where car_brand = ?";
		List<Car> list = cd.commonQuery(new Car(), sql, car_brand);
		return list;
		
	}
	
}

package com.hmwl.service;

import java.util.List;

import com.hmwl.dao.CommonDao;
import com.hmwl.entity.Car;
import com.hmwl.entity.RentInfo;

public class CarService {
	CommonDao cd = new CommonDao();
	/**�鿴����������Դ����**/
	public List<Car> queryCars(){
		CommonDao cd = new CommonDao();
		String sql = "select * from car";
		List<Car> carlist = cd .commonQuery(new Car(), sql);
		return carlist;
	}
	/**����id����������ѯ�Ĵ���*/
	public List<Car> queryCarsByID(String car_id){
		CommonDao cd = new CommonDao();
		String sql = "select * from car where car_id = ?";
		List<Car> carlist = cd .commonQuery(new Car(), sql, car_id);
		return carlist;
	}
	/**ʵ�ָ��ݼ۸���в�ѯ**/
	public List<Car> sortPrice(String way){
		CommonDao cd = new CommonDao();
		String sql = "select * from car order by car_price "+way;
		List<Car> carlist = cd .commonQuery(new Car(), sql);
		return carlist;
	}
	/**ʵ�ָ���Ʒ�ƽ�������,���Ǻ��񲻷��������и���Ʒ��ɸѡ,���Ż�**/
	public List<Car> sortBrand(String way){
		CommonDao cd = new CommonDao();
		String sql = "select * from car order by car_brand "+way;
		List<Car> carlist = cd .commonQuery(new Car(), sql);
		return carlist;
	}
	/**����Ա����,������������ݽ��г����������
	 * 1.�����ǲ����Ѿ�����ͬ���͵���
	 * 2.�������Ϊ0,�Զ�������������Ϊ������
	 * 
	 * */
	//ui_car_brand.getText(), ui_car_model.getText(), 
	//ui_car_num.getText(), ui_car_price.getText(), ui_day_money.getText(), ui_car_isrent.getText()
	public int addCar(String car_brand,String car_model,String car_number,String car_price
			,String money_day,String status) {
		CommonDao cd = new CommonDao();
		//�������ĳ����ͺ��Ǵ��ڵ�,�ǲ���ֱ�Ӳ��뵽�����ȽϺ���
		//cd.commonQuery(new Car, sql, param)
		String sql = "insert into car values(car_next.nextval,?,?,?,?,?,?)";
		int result = cd.commonUpdate(sql, car_brand, car_model,car_number,car_price,money_day,status);
		return result;
	}
	/**���ĳ���Դ����*/
	public int  changeCar(String car_brand,String car_model,String car_number,String car_price
			,String money_day,String status) {
		/**��GUI���ӻ��������,�޸ĺ�������ô������????**/
		String sql = "select * from car where car_brand = ? and car_model = ?" ;     //У�����ݿ����ǲ����������¼
		String upsql = "update cat set car_brand =?"
				+ ",car_model =?,car_number = ?, car_price = ?,money_day = ? ,status = ?";     //�������,�����޸�,�����ύ
		
		CommonDao cd = new CommonDao();
		List<Car> list = cd.commonQuery(new Car(), sql, car_brand,car_model);
		if(list.size() == 0) {
			return  -1;  //��������в����ڸó�Ʒ�ƺ��ͺ�,�Ͳ����޸�
		}else {
			/*˼��:���Ϊ������������Ϊ��,��ô,������Ⱦ���ݵķ���*/
			
			/***
			 * �������:��������ֵ,�û��ύ��ʱ��,����ӽ����ϻ�ȡ��ֵ�ǿյ�,��ʹ�ò�ѯ����ֵ
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
	/**�÷�������У�������ǲ��Ǵ���0*/
	public void checkCanRent() {
		
	}
	/**����д���ɹ�֮��,�����߼�*/
	/**��������-1*/
	public void minusCar(String car_id) {
		
		String sqlque = "select car_number from car where car_id = ?";
		String sql = "update car set car_number = ? where car_id = ? ";
		Integer car_number = Integer.parseInt(cd.commonQuery(new Car(), sqlque, car_id).get(0).getCar_number())-1;
		int result = cd.commonUpdate(sql, car_number.toString(),car_id);
	}
	/**�鿴��ǰ�Լ�����ʲô��**/
	public void showRentInfoByUser() {
		
	}
	/**����**/
	public boolean rebackCar() {
		/**�Ȳ�ѯ��ǰ��¼�˻����ĳ�*/
		return false;
	}
	public List<Car> getRecordByCarBrand(String car_brand){
		String sql = "select * from car where car_brand = ?";
		List<Car> list = cd.commonQuery(new Car(), sql, car_brand);
		return list;
		
	}
	
}

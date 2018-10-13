package com.hmwl.service;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.hmwl.dao.CommonDao;
import com.hmwl.entity.Car;
import com.hmwl.entity.RentInfo;
import com.hmwl.entity.User;
import com.ibm.icu.text.AlphabeticIndex.Record;


public class RentService {
	/**�����յ����,�Թ�����*/
	CommonDao cd = new CommonDao();
	/*�����븴��**/
//	public void setTable(Table table ,List<Object> list) {
//		TableItem item = null; 
//		for (int row = 0; row < list.size() ;row++) { 
//			item = new TableItem(table, SWT.NONE); 
//				  item.setText(0, list.get(row).getCar_id().toString());
//				  item.setText(1, list.get(row).getCar_brand());
//				  item.setText(2, list.get(row).getCar_model());
//				  item.setText(3, String.valueOf(list.get(row).getMoney_day()));
//				  item.setText(4, String.valueOf(list.get(row).getCar_price()));
//				  item.setText(5, list.get(row).getStatus());
//		}
//	}
	
	public double rentPrice(String car_id) {
		String sql = "select money_day from car where car_id = ?";
		CommonDao cd = new CommonDao();
		List<Car> cqy = cd.commonQuery(new Car(), sql, car_id);
		if(cqy.size() == 0) {
			return -1;
		}else {
			return cqy.get(0).getMoney_day();
		}
	}
	public boolean rentCar(String car_id,String start,String end,String money) {
		//���ղ���
		String sql = "update  rentinfo set car_id = ? , "
				+ "start_date = ?, end_date = ? ,money = ? where car_id is null";
		CommonDao cd = new CommonDao();
		int row = cd.commonUpdate(sql,car_id,start,end,money);
		if(row > 0) {
			return true;
		}
		return false;
	}
	/**�⳵����д��CarService����....*/
	
	/**��ѯ������Ϣ**/
	public List<User> queryUsersByName(String username){
		/**�����û���,��ѯ���û�id**/
		CommonDao cd = new CommonDao();
		UserService user = new UserService();
		int user_id = user.isExistUser(username);
		String sql = "select * from users where username = ?";
		List<User> carlist = cd .commonQuery(new User(), sql, username);
		return carlist;
	}
	/**�����û�id��ѯ�����û����޵ĳ���**/
	public List<RentInfo> queryRentList(Integer user_id){
		/**�����û���,��ѯ���û�id**/
	
		String sql = "select * from rentinfo where user_id = ?";
		List<RentInfo> rentlist = cd.commonQuery(new RentInfo(), sql, user_id);
		return rentlist;
	}
	/**�黹����**/
	public void rebackCar(String username) {
		List<User> list = queryUsersByName(username);
		Integer userid = list.get(0).getUserid();
		if(list.size() != 0) {
			/**����¼���**/
			
			/**����������+1**/
			String sql = "update car set car_number = (car_number+1) "
					+ "where car_id = (select car_id from rentinfo where user_id = ?)";
			int row = cd.commonUpdate(sql, userid);
			System.out.println(row>0?"�ɹ�!":"ʧ��!");
		}
	}
	/**����Ա����   ��������ѯ�������޼�¼*/
	public List<RentInfo> showAllRencentRecord() {
		String sql = "select * from rentinfo ";
		List<RentInfo> list = cd.commonQuery(new RentInfo(), sql);
		
		return list;
		
	}
	/**����Ա���� �����û�������ѯ���޼�¼**/
	public List<RentInfo> getRecordByUserName(String username){
		String sql = "select * from rentinfo where user_id = (select userid from users where username = ?) ";
		List<RentInfo> list = cd.commonQuery(new RentInfo(), sql, username);
		return list;
	}
	/**����Ա���� �����û�Id��ѯ���޼�¼**/
	public List<RentInfo> getRecordByUserId(Integer user_id){
		String sql = "select * from rentinfo where user_id = ? ";
		List<RentInfo> list = cd.commonQuery(new RentInfo(), sql, user_id);
		return list;
		
	}
	/**����Ա���� ���ݳ���ID��ѯ���޼�¼**/
	public List<RentInfo> getRecordByCarId(Integer car_id){
		String sql = "select * from rentinfo where car_id = ? ";
		List<RentInfo> list = cd.commonQuery(new RentInfo(), sql, car_id);
		return list;
		
	}
	/**����Ա���� ���ݳ���Ʒ�Ʋ�ѯ���޼�¼**/
	public List<RentInfo> getRecordByCarBrand(String car_brand){
		String sql = "select * from rentinfo where car_id = (select car_id from car where car_brand = ?)";
		List<RentInfo> list = cd.commonQuery(new RentInfo(), sql, car_brand);
		return list;
		
	}
}

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
	/**处理单日的租金,以供调用*/
	CommonDao cd = new CommonDao();
	/*表格代码复用**/
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
		//风险操作
		String sql = "update  rentinfo set car_id = ? , "
				+ "start_date = ?, end_date = ? ,money = ? where car_id is null";
		CommonDao cd = new CommonDao();
		int row = cd.commonUpdate(sql,car_id,start,end,money);
		if(row > 0) {
			return true;
		}
		return false;
	}
	/**租车代码写到CarService里了....*/
	
	/**查询租赁信息**/
	public List<User> queryUsersByName(String username){
		/**根据用户名,查询到用户id**/
		CommonDao cd = new CommonDao();
		UserService user = new UserService();
		int user_id = user.isExistUser(username);
		String sql = "select * from users where username = ?";
		List<User> carlist = cd .commonQuery(new User(), sql, username);
		return carlist;
	}
	/**根据用户id查询到该用户租赁的车辆**/
	public List<RentInfo> queryRentList(Integer user_id){
		/**根据用户名,查询到用户id**/
	
		String sql = "select * from rentinfo where user_id = ?";
		List<RentInfo> rentlist = cd.commonQuery(new RentInfo(), sql, user_id);
		return rentlist;
	}
	/**归还汽车**/
	public void rebackCar(String username) {
		List<User> list = queryUsersByName(username);
		Integer userid = list.get(0).getUserid();
		if(list.size() != 0) {
			/**将记录表的**/
			
			/**将车的数量+1**/
			String sql = "update car set car_number = (car_number+1) "
					+ "where car_id = (select car_id from rentinfo where user_id = ?)";
			int row = cd.commonUpdate(sql, userid);
			System.out.println(row>0?"成功!":"失败!");
		}
	}
	/**管理员方法   无条件查询所有租赁记录*/
	public List<RentInfo> showAllRencentRecord() {
		String sql = "select * from rentinfo ";
		List<RentInfo> list = cd.commonQuery(new RentInfo(), sql);
		
		return list;
		
	}
	/**管理员可用 根据用户姓名查询租赁记录**/
	public List<RentInfo> getRecordByUserName(String username){
		String sql = "select * from rentinfo where user_id = (select userid from users where username = ?) ";
		List<RentInfo> list = cd.commonQuery(new RentInfo(), sql, username);
		return list;
	}
	/**管理员可用 根据用户Id查询租赁记录**/
	public List<RentInfo> getRecordByUserId(Integer user_id){
		String sql = "select * from rentinfo where user_id = ? ";
		List<RentInfo> list = cd.commonQuery(new RentInfo(), sql, user_id);
		return list;
		
	}
	/**管理员可用 根据车辆ID查询租赁记录**/
	public List<RentInfo> getRecordByCarId(Integer car_id){
		String sql = "select * from rentinfo where car_id = ? ";
		List<RentInfo> list = cd.commonQuery(new RentInfo(), sql, car_id);
		return list;
		
	}
	/**管理员可用 根据车辆品牌查询租赁记录**/
	public List<RentInfo> getRecordByCarBrand(String car_brand){
		String sql = "select * from rentinfo where car_id = (select car_id from car where car_brand = ?)";
		List<RentInfo> list = cd.commonQuery(new RentInfo(), sql, car_brand);
		return list;
		
	}
}

package com.hmwl.service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.hmwl.dao.CommonDao;
import com.hmwl.entity.User;
import com.hmwl.utils.Md5Tools;

public class UserService {
	CommonDao cd = new CommonDao();
	/***用户登录**/
	public int login(String username,String password) {
		String sql = "select * from users where username = ?";
		CommonDao cd = new CommonDao();
		List<User> res = cd.commonQuery(new User(), sql, username);
		if(res.size() == 0) {
			//登陆失败
			return -1;
		}else {
	
			if(res.get(0).getPassword().equals(password)) {
				Integer uid = res.get(0).getUserid();
				if(res.get(0).getIsadmin()!=1) {
					//不是管理员
					System.out.println(uid);
					markUse(uid.toString());
				}
				return res.get(0).getIsadmin();
				
			}
		}
		return -1;
	}
	/***用户注册***/
	public boolean register(String username,String password,String address,String phone) {
		String sql = "select * from users where username = ?";
		String sqlin = "insert into users values(auto_id.nextval,?,?,?,?,0)";
		List<User> userlist = cd.commonQuery(new User(), sql, username);
		if(userlist.size() == 0) {
			//不存在该数据，可以注册martin
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String res = Md5Tools.stringToMD5(password);
			System.out.println(res);
			int commonUpdate = cd.commonUpdate(sqlin,username,res,address,phone);
			return commonUpdate>0?true:false;
		}else {
			//用户名存在,提示用户已存在!
			return false;
		}
		
	}
	/**登陆成功之后就在租赁记录里添加该用户id以备后用
	 * 如果最后没租车,就将该记录清空
	 * */
	public void markUse(String user_id) {
		String sql = "insert into rentinfo(rent_id,user_id) values(next_cord.nextval,?)";
		
		cd.commonUpdate(sql, user_id);
	}
	public boolean checkSec() {
		
		return false;
	}
	public int isExistUser(String username) {
		String sql = "select * from users where username = ?";
		List<User> list = cd.commonQuery(new User(), sql, username);
		if(list.size() == 0) {
			return 0;
		}else {
			return list.get(0).getUserid();//返回userid
		}
	}
}

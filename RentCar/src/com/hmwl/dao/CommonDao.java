package com.hmwl.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hmwl.utils.ConnectionPool;

/**
 * 通用的更新方法和通用的查询方法
 * */
public class CommonDao {
	//通用的查询方法
	public <T> List<T> commonQuery(T t,String sql,Object...param){
		List<T> list = new ArrayList<>();
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement pstm= null;
		ResultSet rs=null;
		try {
			pstm = conn.prepareStatement(sql);
			for(int i=0;i<param.length;i++) {
				pstm.setObject(i+1, param[i]);
			}
			rs = pstm.executeQuery();
			// 结果集中有一个方法，可以获取到结果集的元数据
			// 包括结果有多少列，每列的列名
			ResultSetMetaData metaData = rs.getMetaData();
			//先获取结果集列的个数
			int count = metaData.getColumnCount();
			while(rs.next()) {
				//Student类的Class对象
				Object stu = t.getClass().newInstance();
				//遍历每个列的列名.
				for(int i=1;i<=count;i++) {
					String label = metaData.getColumnLabel(i).toLowerCase();
					//如果java类的属性名，与列名完全一致，
					//我们可以通过反射  使用列名来获取对应的
					//java属性名 ,并且给属性赋值
					Field field = t.getClass().getDeclaredField(label);
					Object value = rs.getObject(label);//当前列的值
					if(field.getType()==int.class || field.getType()==Integer.class) {
						value= Integer.parseInt(value+"");
					}
					if(field.getType()==double.class || field.getType()==Double.class) {
						value= Double.parseDouble(value+"");
					}
					if(field.getType()==float.class || field.getType()==Float.class) {
						value= Float.parseFloat(value+"");
					}
					field.setAccessible(true);
					field.set(stu, value);
				}
				list.add((T) stu);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		ConnectionPool.closeResources(rs, pstm, conn);
		return list;
	}
	
	//通用的更新       返回值为int，表示受影响的行数
	public int commonUpdate(String sql,Object...param) {
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement pstm=null;
		int res=-1; 
		try {
			pstm = conn.prepareStatement(sql);
			//给占位符赋值
			for(int i=0;i<param.length;i++) {
				pstm.setObject(i+1, param[i]);
			}
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionPool.closeResources(null, pstm, conn);
		return res;
	}
	//通用的更新       返回值为int，表示受影响的行数
	public int noCommitCommonUpdate(Connection conn,String sql,Object...param) {
		PreparedStatement pstm=null;
		int res=-1; 
		try {
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql);
			for(int i=0;i<param.length;i++) {
				pstm.setObject(i+1, param[i]);
			}
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionPool.closeResources(null, pstm, conn);
		return res;
	}
	
	
}

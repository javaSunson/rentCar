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
 * ͨ�õĸ��·�����ͨ�õĲ�ѯ����
 * */
public class CommonDao {
	//ͨ�õĲ�ѯ����
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
			// ���������һ�����������Ի�ȡ���������Ԫ����
			// ��������ж����У�ÿ�е�����
			ResultSetMetaData metaData = rs.getMetaData();
			//�Ȼ�ȡ������еĸ���
			int count = metaData.getColumnCount();
			while(rs.next()) {
				//Student���Class����
				Object stu = t.getClass().newInstance();
				//����ÿ���е�����.
				for(int i=1;i<=count;i++) {
					String label = metaData.getColumnLabel(i).toLowerCase();
					//���java�������������������ȫһ�£�
					//���ǿ���ͨ������  ʹ����������ȡ��Ӧ��
					//java������ ,���Ҹ����Ը�ֵ
					Field field = t.getClass().getDeclaredField(label);
					Object value = rs.getObject(label);//��ǰ�е�ֵ
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
	
	//ͨ�õĸ���       ����ֵΪint����ʾ��Ӱ�������
	public int commonUpdate(String sql,Object...param) {
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement pstm=null;
		int res=-1; 
		try {
			pstm = conn.prepareStatement(sql);
			//��ռλ����ֵ
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
	//ͨ�õĸ���       ����ֵΪint����ʾ��Ӱ�������
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

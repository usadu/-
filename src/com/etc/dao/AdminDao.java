package com.etc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.etc.entity.Admin;
import com.etc.util.DBUtil;

/**
 * GoodsDaoִ����Ʒ�����ݿ�ķ���
 * @author Administrator
 *
 */

public class AdminDao{	
	   /**
	    * ��ѯ������Ʒ
	    */
	   public Admin queryAll(String tf,String pwd ){
		  // List<Goods> list=new ArrayList<>();
		   //String sql="select * from goods";
		   //����util��ɲ�ѯ,���������󷵻ظ�������
		   String sql="select * from admin where  admin_admin=? and admin_pwd=?";
		   CachedRowSet crs=DBUtil.execQuery(sql,tf,pwd);
		   Admin admin=null;
		   try {
			while(crs.next()) {
				  int admin_Id =crs.getInt(1);
				  String admin_admin =crs.getString(2);
				  String  admin_pwd =crs.getString(3);
				  String admin_name=crs.getString(4);
				  //ʵ��������
				   admin=new Admin(admin_Id, admin_admin,admin_pwd, admin_name);
				  //��Ӷ��󵽼�����
				  //list.add(g);
				  
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		   return admin;
	  }
	   



}
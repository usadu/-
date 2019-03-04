package com.etc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.etc.entity.Admin;
import com.etc.util.DBUtil;

/**
 * GoodsDao执行商品的数据库的访问
 * @author Administrator
 *
 */

public class AdminDao{	
	   /**
	    * 查询所有商品
	    */
	   public Admin queryAll(String tf,String pwd ){
		  // List<Goods> list=new ArrayList<>();
		   //String sql="select * from goods";
		   //调用util完成查询,将结果处理后返回给调用者
		   String sql="select * from admin where  admin_admin=? and admin_pwd=?";
		   CachedRowSet crs=DBUtil.execQuery(sql,tf,pwd);
		   Admin admin=null;
		   try {
			while(crs.next()) {
				  int admin_Id =crs.getInt(1);
				  String admin_admin =crs.getString(2);
				  String  admin_pwd =crs.getString(3);
				  String admin_name=crs.getString(4);
				  //实例化对象
				   admin=new Admin(admin_Id, admin_admin,admin_pwd, admin_name);
				  //添加对象到集合中
				  //list.add(g);
				  
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		   return admin;
	  }
	   



}
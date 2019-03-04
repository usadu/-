package com.etc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.etc.entity.User;
import com.etc.util.DBUtil;

public class UserDao {
/**
 * �����û�
 */
	public static boolean addUser(User user) {
		if(null!=user) {
			return DBUtil.execUpdate("insert into user values(null,?,?,?,?,?,?)",user.getUser_pwd(),user.getUser_name(),user.getUser_age(),user.getUser_creat_time(),user.getUser_sex(),user.getUser_tell());
		}
		return false;
	}
/**
 * ɾ���û�
 */
	public static boolean deleteUser(int user_id) {
		if (user_id>0) {
			return DBUtil.execUpdate("delete from user where user_id=?",user_id);
			
		}
		return false;
	}
/**
 * �޸��û�
 */
	public static boolean updateUser(User user) {
		if (null!=user) {
			return DBUtil.execUpdate("update user set user_pwd=?,user_name=?,user_age=?,user_creat_time=?,user_sex=?,user_tell=?",user.getUser_pwd(),user.getUser_name(),user.getUser_age(),user.getUser_creat_time(),user.getUser_sex(),user.getUser_tell());
			
		}
		return false;
	}
/**
 * ��ѯ�����û�
 */
	public static List<User> queryAll() {
		List<User> list=new ArrayList<>();
		CachedRowSet crs=DBUtil.execQuery("select * from user");
		User u=null;
		try {
			while(crs.next()) {
				int user_id=crs.getInt(1);
				String user_pwd=crs.getString(2);
				String user_name=crs.getString(3);
				String user_age=crs.getString(4);
				String user_creat_time=crs.getString(5);
				String user_sex=crs.getString(6);
				int user_tell=crs.getInt(7);
				u=new User(user_id,user_pwd,user_name,user_age,user_creat_time,user_sex,user_tell);
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
/**
 * �����û�����ģ����ѯ
 */
	public static List<User> queryByUserName(String name) {
		List<User> list=new ArrayList<>();
		CachedRowSet crs=DBUtil.execQuery("select * from user where user_name like ?","%"+name+"%");
		User u=null;
		try {
			while(crs.next()) {
				int user_id=crs.getInt(1);
				String user_pwd=crs.getString(2);
				String user_name=crs.getString(3);
				String user_age=crs.getString(4);
				String user_creat_time=crs.getString(5);
				String user_sex=crs.getString(6);
				int user_tell=crs.getInt(7);
				u=new User(user_id,user_pwd,user_name,user_age,user_creat_time,user_sex,user_tell);
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
/**
 * �����û�ID��ѯ�û�
 */
	public static User queryByUserId(int userid) {
		//List<User> list=new ArrayList<>();
		CachedRowSet crs=DBUtil.execQuery("select * from user where user_id=?",userid);
		User u=null;
		try {
			while(crs.next()) {
				int user_id=crs.getInt(1);
				String user_pwd=crs.getString(2);
				String user_name=crs.getString(3);
				String user_age=crs.getString(4);
				String user_creat_time=crs.getString(5);
				String user_sex=crs.getString(6);
				int user_tell=crs.getInt(7);
				u=new User(user_id,user_pwd,user_name,user_age,user_creat_time,user_sex,user_tell);
				//list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	/**
	 * �����û�����ѯ�û�ID
	 * 
	 */
	public static int queryIDByUserName(String username) {
		//List<User> list=new ArrayList<>();
		CachedRowSet crs=DBUtil.execQuery("select * from user where user_name=?",username);
		int uid=0;
		try {
			while(crs.next()) {
				int user_id=crs.getInt(1);
				uid=user_id;
				//list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uid;
	}
	/**
	 * ��¼����
	 *
	 */
	public static User login(String userPwd,String userName) {
		CachedRowSet crs=DBUtil.execQuery("select*from user where user_pwd=? and user_name=?", userPwd,userName);
		User user=null;
		try {
			while(crs.next()) {
				int user_id=crs.getInt(1);
				String user_pwd=crs.getString(2);
				String user_name=crs.getString(3);
				String user_age=crs.getString(4);
				String user_creat_time=crs.getString(5);
				String user_sex=crs.getString(6);
				int user_tell=crs.getInt(7);
				user=new User(user_id,user_pwd,user_name,user_age,user_creat_time,user_sex,user_tell);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	/**
	 * �����û�����ģ����ѯ
	 */
		public static User queryByUserName2(String name) {
			//List<User> list=new ArrayList<>();
			CachedRowSet crs=DBUtil.execQuery("select * from user where user_name=?",name);
			User u=null;
			try {
				while(crs.next()) {
					int user_id=crs.getInt(1);
					String user_pwd=crs.getString(2);
					String user_name=crs.getString(3);
					String user_age=crs.getString(4);
					String user_creat_time=crs.getString(5);
					String user_sex=crs.getString(6);
					int user_tell=crs.getInt(7);
					u=new User(user_id,user_pwd,user_name,user_age,user_creat_time,user_sex,user_tell);
					//list.add(u);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return u;
		}
}

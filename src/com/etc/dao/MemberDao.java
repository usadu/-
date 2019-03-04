package com.etc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.etc.entity.Member;
import com.etc.util.DBUtil;

public class MemberDao {
	/**
	 * 增加会员
	 */
	public static boolean addMember(Member member) {
		if (null!=member) {
			return DBUtil.execUpdate("insert into member values(null,?,?)", member.getUser_id(),member.getMember_status());
		}
		return false;
	}
	/**
	 * 删除会员,根据会员ID
	 */
	public static boolean deleteMember(int member_id) {
		if (member_id>0) {
			return DBUtil.execUpdate("delete from member where member_id=?", member_id);
		}
		return false;
	}
	/**
	 * 根据用户id(user_id)删除会员
	 * 
	 */
	public static boolean deleteMemberByUser_id(int user_id) {
		if (user_id>0) {
			return DBUtil.execUpdate("delete from member where user_id=?", user_id);
		}
		return false;
	}
	/**
	 * 修改会员
	 */
	public static boolean updateMember(Member member) {
		if (null!=member) {
			return DBUtil.execUpdate("update member set user_id=?,member_status=?", member.getUser_id(),member.getMember_status());
		}
		return false;
	}
	/**
	 * 查找所有的会员列表
	 */
	public static List<Member> queryAll(){
		List<Member> list=new ArrayList<>();
		CachedRowSet crs=DBUtil.execQuery("select * from member");
		Member m=null;
		try {
			while(crs.next()) {
				int member_id=crs.getInt(1);
				int user_id=crs.getInt(2);
				int member_status=crs.getInt(3);
				m=new Member(member_id,user_id,member_status);
				list.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据User_id查找会员
	 */
	public static Member queryByUser_id(int userid){
		//List<Member> list=new ArrayList<>();
		CachedRowSet crs=DBUtil.execQuery("select * from member where user_id=?",userid);
		Member m=null;
		try {
			while(crs.next()) {
				int member_id=crs.getInt(1);
				int user_id=crs.getInt(2);
				int member_status=crs.getInt(3);
				m=new Member(member_id,user_id,member_status);
				//list.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	/**
	 * 根据memberid查找会员
	 */
	public static Member queryByMember_id(int memberid){
		//List<Member> list=new ArrayList<>();
		CachedRowSet crs=DBUtil.execQuery("select * from member where member_id=?",memberid);
		Member m=null;
		try {
			while(crs.next()) {
				int member_id=crs.getInt(1);
				int user_id=crs.getInt(2);
				int member_status=crs.getInt(3);
				m=new Member(member_id,user_id,member_status);
				//list.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
}

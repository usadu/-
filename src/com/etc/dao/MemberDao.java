package com.etc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.etc.entity.Member;
import com.etc.util.DBUtil;

public class MemberDao {
	/**
	 * ���ӻ�Ա
	 */
	public static boolean addMember(Member member) {
		if (null!=member) {
			return DBUtil.execUpdate("insert into member values(null,?,?)", member.getUser_id(),member.getMember_status());
		}
		return false;
	}
	/**
	 * ɾ����Ա,���ݻ�ԱID
	 */
	public static boolean deleteMember(int member_id) {
		if (member_id>0) {
			return DBUtil.execUpdate("delete from member where member_id=?", member_id);
		}
		return false;
	}
	/**
	 * �����û�id(user_id)ɾ����Ա
	 * 
	 */
	public static boolean deleteMemberByUser_id(int user_id) {
		if (user_id>0) {
			return DBUtil.execUpdate("delete from member where user_id=?", user_id);
		}
		return false;
	}
	/**
	 * �޸Ļ�Ա
	 */
	public static boolean updateMember(Member member) {
		if (null!=member) {
			return DBUtil.execUpdate("update member set user_id=?,member_status=?", member.getUser_id(),member.getMember_status());
		}
		return false;
	}
	/**
	 * �������еĻ�Ա�б�
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
	 * ����User_id���һ�Ա
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
	 * ����memberid���һ�Ա
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

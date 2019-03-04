package com.etc.entity;

public class Member {
	private int member_id;
	private int user_id;
	private int member_status;
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getMember_status() {
		return member_status;
	}
	public void setMember_status(int member_status) {
		this.member_status = member_status;
	}
	public Member(int member_id, int user_id, int member_status) {
		super();
		this.member_id = member_id;
		this.user_id = user_id;
		this.member_status = member_status;
	}
	public Member(int user_id, int member_status) {
		super();
		this.user_id = user_id;
		this.member_status = member_status;
	}
	public Member() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", user_id=" + user_id + ", member_status=" + member_status + "]";
	}
	
}

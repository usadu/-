package com.etc.entity;

public class User {
	private int user_id;
	private String user_pwd;
	private String user_name;
	private String user_age;
	private String user_creat_time;
	private String user_sex;
	private int user_tell;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_age() {
		return user_age;
	}
	public void setUser_age(String user_age) {
		this.user_age = user_age;
	}
	public String getUser_creat_time() {
		return user_creat_time;
	}
	public void setUser_creat_time(String user_creat_time) {
		this.user_creat_time = user_creat_time;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public int getUser_tell() {
		return user_tell;
	}
	public void setUser_tell(int user_tell) {
		this.user_tell = user_tell;
	}
	public User(int user_id, String user_pwd, String user_name, String user_age, String user_creat_time,
			String user_sex, int user_tell) {
		super();
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_name = user_name;
		this.user_age = user_age;
		this.user_creat_time = user_creat_time;
		this.user_sex = user_sex;
		this.user_tell = user_tell;
	}
	public User(String user_pwd, String user_name, String user_age, String user_creat_time, String user_sex,
			int user_tell) {
		super();
		this.user_pwd = user_pwd;
		this.user_name = user_name;
		this.user_age = user_age;
		this.user_creat_time = user_creat_time;
		this.user_sex = user_sex;
		this.user_tell = user_tell;
	}
	
	public User(int user_id, String user_name, String user_age, String user_creat_time, String user_sex,
			int user_tell) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_age = user_age;
		this.user_creat_time = user_creat_time;
		this.user_sex = user_sex;
		this.user_tell = user_tell;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_pwd=" + user_pwd + ", user_name=" + user_name + ", user_age="
				+ user_age + ", user_creat_time=" + user_creat_time + ", user_sex=" + user_sex + ", user_tell="
				+ user_tell + "]";
	}
	
}

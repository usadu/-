package com.etc.entity;

public class Admin {

	private int admin_id;
	private String admin_admin;
	private String admin_pwd;
	private String admin_name;
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_admin() {
		return admin_admin;
	}
	public void setAdmin_admin(String admin_admin) {
		this.admin_admin = admin_admin;
	}
	public String getAdmin_pwd() {
		return admin_pwd;
	}
	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public Admin(String admin_admin, String admin_pwd, String admin_name) {
		super();
		this.admin_admin = admin_admin;
		this.admin_pwd = admin_pwd;
		this.admin_name = admin_name;
	}
	public Admin(int admin_id, String admin_admin, String admin_pwd, String admin_name) {
		super();
		this.admin_id = admin_id;
		this.admin_admin = admin_admin;
		this.admin_pwd = admin_pwd;
		this.admin_name = admin_name;
	}
	
	public Admin(String admin_admin, String admin_pwd) {
		super();
		this.admin_admin = admin_admin;
		this.admin_pwd = admin_pwd;
	}
	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", admin_admin=" + admin_admin + ", admin_pwd=" + admin_pwd
				+ ", admin_name=" + admin_name + "]";
	}
	
	
}

package com.etc.entity;
/**
 * music实体类
 * @author Administrator
 *
 */
public class Music {
	private int music_id;//音乐编号
	private String music_name;//歌名
	private String music_singer;//歌手
	private int type_id;
	private String type_name;//类型
	private String music_time;//上传时间
	private String music_path;//音乐路径
	private String music_photo;//图片路径
	private int music_status;//是否可以下载,状态: 1正常 2是否禁止下载3条件下载->付费
	/**
	 * @return the music_id
	 */
	public int getMusic_id() {
		return music_id;
	}
	/**
	 * @param music_id the music_id to set
	 */
	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}
	/**
	 * @return the music_name
	 */
	public String getMusic_name() {
		return music_name;
	}
	/**
	 * @param music_name the music_name to set
	 */
	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
	/**
	 * @return the music_singer
	 */
	public String getMusic_singer() {
		return music_singer;
	}
	/**
	 * @param music_singer the music_singer to set
	 */
	public void setMusic_singer(String music_singer) {
		this.music_singer = music_singer;
	}
	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}
	/**
	 * @param type_id the type_id to set
	 */
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	/**
	 * @return the type_name
	 */
	public String getType_name() {
		return type_name;
	}
	/**
	 * @param type_name the type_name to set
	 */
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	/**
	 * @return the music_time
	 */
	public String getMusic_time() {
		return music_time;
	}
	/**
	 * @param music_time the music_time to set
	 */
	public void setMusic_time(String music_time) {
		this.music_time = music_time;
	}
	/**
	 * @return the music_path
	 */
	public String getMusic_path() {
		return music_path;
	}
	/**
	 * @param music_path the music_path to set
	 */
	public void setMusic_path(String music_path) {
		this.music_path = music_path;
	}
	/**
	 * @return the music_photo
	 */
	public String getMusic_photo() {
		return music_photo;
	}
	/**
	 * @param music_photo the music_photo to set
	 */
	public void setMusic_photo(String music_photo) {
		this.music_photo = music_photo;
	}
	/**
	 * @return the music_status
	 */
	public int getMusic_status() {
		return music_status;
	}
	/**
	 * @param music_status the music_status to set
	 */
	public void setMusic_status(int music_status) {
		this.music_status = music_status;
	}
	public Music(int music_id, String music_name, String music_singer, String type_name, String music_time,
			String music_path,String music_photo,int music_status) {
		super();
		this.music_id = music_id;
		this.music_name = music_name;
		this.music_singer = music_singer;
		this.type_name = type_name;
		this.music_time = music_time;
		this.music_path = music_path;
		this.music_photo = music_photo;
		this.music_status = music_status;
	}
	
	public Music(String music_name, String music_singer, int type_id, String music_time, String music_path,
			String music_photo, int music_status) {
		super();
		this.music_name = music_name;
		this.music_singer = music_singer;
		this.type_id = type_id;
		this.music_time = music_time;
		this.music_path = music_path;
		this.music_photo = music_photo;
		this.music_status = music_status;
	}
	public Music(int music_id, String music_name, String music_singer, int type_id, String music_time,
			String music_path, String music_photo, int music_status) {
		super();
		this.music_id = music_id;
		this.music_name = music_name;
		this.music_singer = music_singer;
		this.type_id = type_id;
		this.music_time = music_time;
		this.music_path = music_path;
		this.music_photo = music_photo;
		this.music_status = music_status;
	}
	public Music(int music_id, String music_name, String music_singer, String type_name, String music_time,
			int music_status) {
		super();
		this.music_id = music_id;
		this.music_name = music_name;
		this.music_singer = music_singer;
		this.type_name = type_name;
		this.music_time = music_time;
		this.music_status = music_status;
	}
	public Music(String music_name, String music_singer, int type_id, String type_name, String music_time,
			String music_path, String music_photo, int music_status) {
		super();
		this.music_name = music_name;
		this.music_singer = music_singer;
		this.type_id = type_id;
		this.type_name = type_name;
		this.music_time = music_time;
		this.music_path = music_path;
		this.music_photo = music_photo;
		this.music_status = music_status;
	}
	
	public Music(int music_id, String music_name, String music_singer, String type_name, String music_path,
			String music_photo, int music_status) {
		super();
		this.music_id = music_id;
		this.music_name = music_name;
		this.music_singer = music_singer;
		this.type_name = type_name;
		this.music_path = music_path;
		this.music_photo = music_photo;
		this.music_status = music_status;
	}
	public Music(String music_name, String music_singer, String type_name, String music_time, String music_path,
			int music_status) {
		super();
		this.music_name = music_name;
		this.music_singer = music_singer;
		this.type_name = type_name;
		this.music_time = music_time;
		this.music_path = music_path;
		this.music_status = music_status;
	}
	

	public Music(String music_name, String music_singer, String music_path) {
		super();
		this.music_name = music_name;
		this.music_singer = music_singer;
		this.music_path = music_path;
	}
	public Music() {
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Music [music_id=" + music_id + ", music_name=" + music_name + ", music_singer=" + music_singer
				+ ", type_id=" + type_id + ", type_name=" + type_name + ", music_time=" + music_time + ", music_path="
				+ music_path + ", music_photo=" + music_photo + ", music_status=" + music_status + "]";
	}
	
	
}

package com.etc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.etc.entity.Collection;
import com.etc.entity.Music;

import com.etc.entity.User;
import com.etc.util.DBUtil;

public class CollectionDao extends Thread {

/***
 * 
 * 模糊音乐
 * */
public ArrayList<Music> querylike(String name) {
	String sql="select music_id,music_name,music_singer, type_name,music_time,music_path,music_status from music inner join type  on music.type_id =type.type_id where music_name like ? or music_singer like ?";
	CachedRowSet crs=DBUtil.execQuery(sql, "%"+name+"%","%"+name+"%");
	ArrayList<Music> list=new ArrayList<Music>();
	Music music=null;
	try {
		while(crs.next()) {
		int music_id=crs.getInt("music_id");
		String music_name=crs.getString("music_name");
		String music_singer=crs.getString("music_singer");
		String type_name=crs.getString("type_name");
		String music_time=crs.getString("music_time");
		String music_path=crs.getString("music_path");
		int music_status=crs.getInt("music_status");
		music=new Music(music_id,music_name, music_singer, type_name, music_time, music_path,  music_status);
		list.add(music);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}
/**
 * 打开收藏库
 */
public ArrayList<Collection> queryAllCollection() {
	String sql="select*from collection";
	CachedRowSet crs=DBUtil.execQuery(sql);
	ArrayList<Collection>  list=new ArrayList<Collection>();
	try {
		while(crs.next()){
			int collectionid=crs.getInt("collection_id");
			int musicid=crs.getInt("music_id");
			int userid=crs.getInt("user_id");
			Collection ct=new Collection(collectionid,musicid,userid);
			list.add(ct);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
	
}




/***
 * 
 * 打开收藏的全部音乐
 * */
public ArrayList<Music> queryAll(int id) {
	CachedRowSet crs=DBUtil.execQuery("select music_id from collection where user_id=?",id);
	ArrayList<Music> list=new ArrayList<Music>();
	try {
		while(crs.next()){
			int n=crs.getInt("music_id");System.out.println(n);
			CachedRowSet crs1=DBUtil.execQuery("select music_id,music_name, music_singer, type_name, music_time, music_path, music_photo, music_status from music  inner join type on music.type_id=type.type_id where music_id=?",n);
			while(crs1.next()){
				int music_id=crs1.getInt("music_id");
				String music_name=crs1.getString("music_name");
				String music_singer=crs1.getString("music_singer");
				String type_name=crs1.getString("type_name");
				String music_time=crs1.getString("music_time");
				String music_path=crs1.getString("music_path");
				String music_photo=crs1.getString("music_photo");
				int music_status=crs1.getInt("music_status");
			Music music=new Music(music_id,music_name, music_singer, type_name, music_time, music_path,music_photo,  music_status);
			list.add(music);
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}	
/***
 * 
 * 打开音乐库
 * */
public ArrayList<Music> queryAllmusic() {
	String sql="select music_id,music_name,music_singer, type_name,music_time,music_path,music_photo,music_status from music inner join type on music.type_id =type.type_id";
	CachedRowSet crs=DBUtil.execQuery(sql);
	ArrayList<Music> list=new ArrayList<Music>();
	  Music music=null;
	try {
		while(crs.next()) {
			int music_id=crs.getInt("music_id");
			String music_name=crs.getString("music_name");
			String music_singer=crs.getString("music_singer");
			String type_name=crs.getString("type_name");
			String music_time = crs.getString("music_time");
			String music_path=crs.getString("music_path");
			String music_photo=crs.getString("music_photo");
			int music_status=crs.getInt("music_status");
			music=new Music(music_id, music_name, music_singer, type_name, music_time, music_path, music_photo,music_status);
			list.add(music);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     return list;
}	


/***
 * 
 * 添加音乐到收藏
 * */
public boolean addcollection(Collection collection)	{
	 return DBUtil.execUpdate("insert into collection values(null,?,?)",collection.getMusic_id(),collection.getUser_id());
}
//添加音乐时候用来获取音乐路径
public Music querymusic(String name) {
		CachedRowSet  crs=DBUtil.execQuery("select * from music where music_name=?", name);
		Music music=null;
		try {
			while(crs.next()) {
				String music_name=crs.getString("music_name");
				String music_singer=crs.getString("music_singer");
				String music_path=crs.getString("music_path");
				music=new Music( music_name, music_singer,  music_path);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return music;
	}
/***
 * 
 * 删除收藏列表音乐(collection_id 删除)
 * */
public boolean delecollection(int id,int userid)	{
	 return DBUtil.execUpdate("delete from collection where music_id=? and user_id=?", id,userid);
}

//歌单查询音乐类型	
public ArrayList<Music> queryType(int i) {
	String sql="select music_id,music_name,music_singer, type_name,music_time,music_path,music_photo,music_status from music inner join type  on music.type_id =type.type_id where music.type_id=?";
	CachedRowSet crs=DBUtil.execQuery(sql, i);
	ArrayList<Music> list=new ArrayList<Music>();
	Music music=null;
	try {
		while(crs.next()) {
		int music_id=crs.getInt("music_id");
		String music_name=crs.getString("music_name");
		String music_singer=crs.getString("music_singer");
		String type_name=crs.getString("type_name");
		String music_time=crs.getString("music_time");
		String music_path=crs.getString("music_path");
		int music_status=crs.getInt("music_status");
		music=new Music(music_id, music_name, music_singer, type_name, music_time, music_path, music_status);
		list.add(music);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}

//获取用户再通过名字获取id
public User getId(String name) {
	 CachedRowSet crs=DBUtil.execQuery("select user_id from users where user_name=?",name);
	 User user=null;
	 try {
		while(crs.next()) {
			int user_id=crs.getInt("user_id");
			String user_pwd=crs.getString("user_pwd");
			String user_name=crs.getString("user_name");
			String user_age=crs.getString("user_age");
			String user_creat_time=crs.getString("user_creat_time");
			String user_sex=crs.getString("user_sex");
			int user_tell=crs.getInt("user_tell");
			user=new User(user_id, user_name, user_age, user_creat_time, user_sex, user_tell);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return user;
}

}

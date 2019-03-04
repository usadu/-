package com.etc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.etc.entity.Music;
import com.etc.util.DBUtil;

public class MusicDao {
	/**
	 * 增加音乐
	 */
	public static boolean addMusic(Music music) {
		if(null!=music) {
		return DBUtil.execUpdate("insert into music values(null,?,?,?,?,?,?,?)",music.getMusic_name(),music.getMusic_singer(),music.getType_id(),music.getMusic_time(),music.getMusic_path(),music.getMusic_photo(),music.getMusic_status() );
	}
		return false;
   }
	/**		删除歌曲	
	    * 根据歌的Id删除歌曲
	    */
	   public static boolean deleteMusic(int music_id){
		   if(music_id>0) {
			   String sql="delete from music where music_id=?";

			   return DBUtil.execUpdate(sql, music_id);
		   }
		 
		 return false;
		}
	   /**修改歌曲信息
	    * 根据歌曲的ID
	    * 
	    */
	   public static boolean updateMusic(Music music) {
		   if(null!=music) {
			   String sql="update music set music_name=?,music_singer=?,type_id=?,music_time=?,music_path=?,music_photo=?,music_status=? where music_id=?";
			   return DBUtil.execUpdate(sql, music.getMusic_name(),music.getMusic_singer(),music.getType_id(),music.getMusic_time(),music.getMusic_path(),music.getMusic_photo(),music.getMusic_status(),music.getMusic_id());
		   }
		   return false;
	   }
	   /**
	    * 查询所有歌曲
	    */
	   public static List<Music> queryAll() {
		   List<Music> list=new ArrayList<>();
		   CachedRowSet crs=DBUtil.execQuery("select * from music");
		   Music m=null;
		   try {
			while(crs.next()) {
				int music_id=crs.getInt(1);
				String music_name=crs.getString(2);
				String music_singer=crs.getString(3);
				int type_id=crs.getInt(4);
				String music_time=crs.getString(5);
				String music_path=crs.getString(6);
				String music_photo=crs.getString(7);
				int music_status=crs.getInt("music_status");
				m=new Music(music_id,music_name,music_singer,type_id,music_time,music_path,music_photo,music_status);
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return list;
	   }
	   /**
	    * 根据名字模糊查询
	    */
	   public static List<Music> queryByMusic_name(String name) {
		   List<Music> list=new ArrayList<>();
		   //String sql="select * from music where music_name like ?";
		   CachedRowSet crs=DBUtil.execQuery("select * from music where music_name like ?","%"+name+"%");
		   Music m=null;
		   try {
			while(crs.next()) {
				int music_id=crs.getInt("music_id");
				String music_name=crs.getString("music_name");
				String music_singer=crs.getString("music_singer");
				int type_id=crs.getInt("type_id");
				String music_time=crs.getString("music_time");
				String music_path=crs.getString("music_path");
				String music_photo=crs.getString("music_photo");
				int music_status=crs.getInt("music_status");
				m=new Music(music_id,music_name,music_singer,type_id,music_time,music_path,music_photo,music_status);
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return list;
	   }
	   /**
	    * 根据歌曲Id查询歌曲
	    */
	   public static Music queryByMusic_id(int musicid) {
		  // List<Music> list=new ArrayList<>();
		   String sql="select * from music where music_id =?";
		   CachedRowSet crs=DBUtil.execQuery(sql,musicid);
		   Music m=null;
		   try {
			   	while(crs.next()) {
				int music_id=crs.getInt("music_id");
				String music_name=crs.getString("music_name");
				String music_singer=crs.getString("music_singer");
				int type_id=crs.getInt("type_id");
				String music_time=crs.getString("music_time");
				String music_path=crs.getString("music_path");
				String music_photo=crs.getString("music_photo");
				int music_status=crs.getInt("music_status");
				m=new Music(music_id,music_name,music_singer,type_id,music_time,music_path,music_photo,music_status);
				/*list.add(m);*/
			   	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return m;
	   }
	 /**
	  * 根据音乐名和歌手查音乐ID
	  */
	   public static int queryMusicid(String mname,String msinger) {
			  // List<Music> list=new ArrayList<>();
			   String sql="select * from music where music_name =? and music_singer=?";
			   CachedRowSet crs=DBUtil.execQuery(sql,mname,msinger);
			   int mid=0;
			   try {
				   	while(crs.next()) {
					int music_id=crs.getInt("music_id");
					
					mid=music_id;
					/*list.add(m);*/
				   	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   return mid;
		   }
}

package com.etc.entity;

public class Collection {
	private int collection_id;
	private int user_id;
	private int music_id;

	public int getCollection_id() {
		return collection_id;
	}

	public void setCollection_id(int collection_id) {
		this.collection_id = collection_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getMusic_id() {
		return music_id;
	}

	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}

	public Collection(int collection_id,  int music_id,int user_id) {
		super();
		this.collection_id = collection_id;
		this.user_id = user_id;
		this.music_id = music_id;
	}

	public Collection(int music_id,int user_id ) {
		super();
		this.user_id = user_id;
		this.music_id = music_id;
	}

	@Override
	public String toString() {
		return "Collection [collection_id=" + collection_id + ", user_id=" + user_id + ", music_id=" + music_id + "]";
	}

}

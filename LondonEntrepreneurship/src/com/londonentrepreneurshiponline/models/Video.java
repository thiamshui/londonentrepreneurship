/**
 * 
 */
package com.londonentrepreneurshiponline.models;

import com.londonentrepreneurshiponline.R;

/**
 * @author thiamshui
 *
 */
public class Video {
	
	private int id;
	private String uri;
	private String title;
	private String desc;
	private int likes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}

}

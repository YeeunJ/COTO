package com.walab.coding.model;

public class RecomCountDTO {

	private int id;
	private int userID;
	private int recomID;
	private int recommend;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getRecomID() {
		return recomID;
	}
	public void setRecomID(int recomID) {
		this.recomID = recomID;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
}

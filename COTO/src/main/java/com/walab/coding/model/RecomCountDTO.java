package com.walab.coding.model;

public class RecomCountDTO {

	private int id;
	private int userID;
	private int recomID;
	private boolean recommendYN;
	private int recommendCount;
	
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
	public boolean getRecommendYN() {
		return recommendYN;
	}
	public void setRecommendYN(boolean recommendYN) {
		this.recommendYN = recommendYN;
	}
	public int getRecommendCount() {
		return recommendCount;
	}
	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}
	
}

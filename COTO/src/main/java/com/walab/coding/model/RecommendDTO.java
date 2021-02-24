package com.walab.coding.model;

import java.util.Date;

public class RecommendDTO {

	private int id;
	private int userID;
	private String nickname;
	private String title;
	private int difficulty;
	private String content;
	private int recomCount;
	private int recomCommentCount;
	private int totalProbCnt;
	private int userProbCnt;
	private int userCart;
	private Date deleteDate;
	private Date regDate;
	
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRecomCount() {
		return recomCount;
	}
	public void setRecomCount(int recomCount) {
		this.recomCount = recomCount;
	}
	public int getRecomCommentCount() {
		return recomCommentCount;
	}
	public void setRecomCommentCount(int recomCommentCount) {
		this.recomCommentCount = recomCommentCount;
	}
	public int getTotalProbCnt() {
		return totalProbCnt;
	}
	public void setTotalProbCnt(int totalProbCnt) {
		this.totalProbCnt = totalProbCnt;
	}
	public int getUserProbCnt() {
		return userProbCnt;
	}
	public void setUserProbCnt(int userProbCnt) {
		this.userProbCnt = userProbCnt;
	}
	public int getUserCart() {
		return userCart;
	}
	public void setUserCart(int userCart) {
		this.userCart = userCart;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "RecommendDTO [id=" + id + ", userID=" + userID + ", nickname=" + nickname + ", title=" + title
				+ ", difficulty=" + difficulty + ", content=" + content + ", recomCount=" + recomCount
				+ ", recomCommentCount=" + recomCommentCount + ", deleteDate=" + deleteDate + ", regDate=" + regDate
				+ "]";
	}
}

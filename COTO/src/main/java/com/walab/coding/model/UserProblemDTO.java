package com.walab.coding.model;

import java.sql.Date;

public class UserProblemDTO {
	private int id;
	private int userID;
	private int problemID;
	private int siteID;
	private String problem;
	private String site;
	private String siteUrl;
	private String link;
	private String difficulty;
	private String memo;
	private Date regDate;
	private int countSolvedP;
	
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
	public int getProblemID() {
		return problemID;
	}
	public void setProblemID(int problemID) {
		this.problemID = problemID;
	}
	public int getSiteID() {
		return siteID;
	}
	public void setSiteID(int siteID) {
		this.siteID = siteID;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getCountSolvedP() {
		return countSolvedP;
	}
	public void setCountSolvedP(int countSolvedP) {
		this.countSolvedP = countSolvedP;
	}
	
	@Override
	public String toString() {
		return "UserProblemDTO [id=" + id + ", userID=" + userID + ", problemID=" + problemID + ", siteID=" + siteID
				+ ", problem=" + problem + ", site=" + site + ", siteUrl=" + siteUrl + ", link=" + link
				+ ", difficulty=" + difficulty + ", memo=" + memo + ", regDate=" + regDate + "]";
	}
	
}

package com.walab.coding.model;

import java.util.Date;

public class GroupDTO {
	private int id;
	private String groupName;
	private String groupGoal;
	private String goal;
	private String groupDesc;
	private int adminID;
	private int isAdmin;
	private String name;
	private String nickName;	
	private Date startDate;
	private Date endDate;
	private Date regdate;

	private int userCnt;
	private double rate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupGoal() {
		return groupGoal;
	}
	public void setGroupGoal(String groupGoal) {
		this.groupGoal = groupGoal;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getGroupDesc(String groupDesc) {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
	public int getUserCnt() {
		return userCnt;
	}
	public void setUserCnt(int userCnt) {
		this.userCnt = userCnt;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}

	
	@Override
	public String toString() {
		return "GroupDTO [id=" + id + ", groupName=" + groupName + ", groupGoal=" + groupGoal + ", goal=" + goal
				+ ", groupDesc=" + groupDesc + ", adminID=" + adminID + ", isAdmin=" + isAdmin + ", name=" + name
				+ ", nickName=" + nickName + ", startDate=" + startDate + ", endDate=" + endDate + ", regdate="
				+ regdate + "]";
	}
}

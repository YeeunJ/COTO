package com.walab.coding.model;

import java.util.Date;

public class GroupInfoDTO {
	
	int id;
	int userID;
	String groupName;
	String goal;
	String groupDesc;
	String startDate;
	String endDate;
	Date regdate;
	
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "GroupInfoDTO [id=" + id + ", userID=" + userID + ", groupName=" + groupName + ", goal=" + goal
				+ ", groupDesc=" + groupDesc + ", startDate=" + startDate + ", endDate=" + endDate + ", regdate="
				+ regdate + "]";
	}
}

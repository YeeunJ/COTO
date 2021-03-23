package com.walab.coding.model;

import java.util.Date;

public class GroupDTO {
	int id;
	String groupName;
	String groupGoal;
	String goal;
	String groupDesc;
	int adminID;
	int isAdmin;
	String name;
	String nickName;	
	Date startDate;
	Date endDate;
	Date regdate;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName(String nickName) {
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

	
	@Override
	public String toString() {
		return "GroupDTO [id=" + id + ", groupName=" + groupName + ", groupGoal=" + groupGoal + ", goal=" + goal
				+ ", groupDesc=" + groupDesc + ", adminID=" + adminID + ", isAdmin=" + isAdmin + ", name=" + name
				+ ", nickName=" + nickName + ", startDate=" + startDate + ", endDate=" + endDate + ", regdate="
				+ regdate + "]";
	}
}

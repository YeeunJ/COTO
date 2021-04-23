package com.walab.coding.model;

import java.sql.Date;

public class GroupGoalDTO {
	int id;
	Date startDate;
	Date endDate;
	int groupID;
	
	int probCount;
	int progress;
	
	
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public int getProbCount() {
		return probCount;
	}
	public void setProbCount(int probCount) {
		this.probCount = probCount;
	}
	
}

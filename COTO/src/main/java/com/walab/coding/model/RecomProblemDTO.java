package com.walab.coding.model;

import java.sql.Date;

public class RecomProblemDTO {

	private int id;
	private int recomID;
	private int siteID;
	private int problemID;
	private Date date;
	private String name;
	private String siteName;
	private String link;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRecomID() {
		return recomID;
	}
	public void setRecomID(int recomID) {
		this.recomID = recomID;
	}
	public int getSiteID() {
		return siteID;
	}
	public void setSiteID(int siteID) {
		this.siteID = siteID;
	}
	public int getProblemID() {
		return problemID;
	}
	public void setProblemID(int problemID) {
		this.problemID = problemID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "RecomProblemDTO [id=" + id + ", recomID=" + recomID + ", siteID=" + siteID + ", problemID=" + problemID
				+ ", date=" + date + ", name=" + name + ", siteName=" + siteName + ", link=" + link + "]";
	}
}

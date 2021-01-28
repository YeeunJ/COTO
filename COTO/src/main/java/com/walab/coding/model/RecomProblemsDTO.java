package com.walab.coding.model;

public class RecomProblemsDTO {

	private int id;
	private int recomID;
	private int site;
	private String problem;
	
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
	public int getSite() {
		return site;
	}
	public void setSite(int site) {
		this.site = site;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
}

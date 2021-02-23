package com.walab.coding.model;

public class RankDTO {
	private int cnt;
	private String nickName;
	private int id;

	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "RankDTO [cnt=" + cnt + ", nickName=" + nickName + "]";
	}
	
}

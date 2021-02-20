package com.walab.coding.repository;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.RecomCommentDTO;

public interface RecomCommentDAO {
	
	public void createComment(RecomCommentDTO r);
	public List<Map<String, Object>> readAll(Map<String, Object> param);
	public List<Map<Integer, Integer>> readCommentCount();
	public int readRecomCommentCount(int recomID);
	public int deleteRecomComment(int recomID);
}

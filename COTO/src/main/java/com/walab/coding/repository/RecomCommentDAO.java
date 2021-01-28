package com.walab.coding.repository;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.RecomCommentDTO;

public interface RecomCommentDAO {
	
	public List<Map<String, String>> readAll(Map<String, Object> param);
	public List<Map<Integer, Integer>> readCommentCount();
	public void createComment(RecomCommentDTO r);
	
}

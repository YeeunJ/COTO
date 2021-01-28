package com.walab.coding.service;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.RecomCommentDTO;

public interface RecomCommentService {
	List<Map<String, String>> read(int recomID);
	List<Map<Integer,Integer>> readCount();
	void createComment(RecomCommentDTO r);
}

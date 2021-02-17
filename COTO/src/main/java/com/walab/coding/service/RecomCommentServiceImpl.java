package com.walab.coding.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomCommentDTO;
import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.repository.RecomCommentDAO;

@Service
public class RecomCommentServiceImpl implements RecomCommentService {
	
	@Autowired
	RecomCommentDAO recomCommentDAO;
	
	/**
	 * RecommendController
	 * 
	 */
	@Override
	public List<Map<String, Object>> read(int recomID) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("recomID", recomID);
		
		List<Map<String, Object>> list = recomCommentDAO.readAll(param);
		
		return list;
	}

	/**
	 * RecommendController
	 * 
	 */
	@Override
	public List<Map<Integer, Integer>> readCount() {
		
		List<Map<Integer, Integer>> countList = recomCommentDAO.readCommentCount();
		
		return countList;
		
	}

	/**
	 * RecommendController
	 * 
	 */
	@Override
	public void createComment(RecomCommentDTO r) {
		
		recomCommentDAO.createComment(r);
		
	}
	
	/**
	 * 지금은 안쓰지만 댓글 삭제에 그대로 적용하면 될듯!!
	 * 
	 */
	@Override
	public int deleteRecomComment(int recomID) {
		return recomCommentDAO.deleteRecomComment(recomID);
	}

}

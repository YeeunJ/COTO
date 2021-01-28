package com.walab.coding.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomCommentDTO;
import com.walab.coding.repository.RecomCommentDAO;

@Service
public class RecomCommentServiceImpl implements RecomCommentService {
	
	@Autowired
	RecomCommentDAO recomCommentDAO;
	
	@Override
	public List<Map<String, String>> read(int recomID) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("recomID", recomID);
		
		List<Map<String, String>> list = recomCommentDAO.readAll(param);
		
		return list;
	}

	@Override
	public List<Map<Integer, Integer>> readCount() {
		
		List<Map<Integer, Integer>> countList = recomCommentDAO.readCommentCount();
		
		return countList;
		
	}

	@Override
	public void createComment(RecomCommentDTO r) {
		
		recomCommentDAO.createComment(r);
		
	}

}

package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomProblemsDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.repository.RecommendDAO;

@Service
public class RecommendServiceImpl implements RecommendService {

	@Autowired
	RecommendDAO recommendDAO;
	
	@Override
	public List<RecommendDTO> readRecom() {
		List<RecommendDTO> recoms = recommendDAO.readRecom();
		
		return recoms;
	}
	
	@Override
	public List<RecomProblemsDTO> readRecomProblems(int recomID) {
		List<RecomProblemsDTO> recomProblems = recommendDAO.readRecomProblems(recomID);
		
		return recomProblems;
	}
	
	@Override
	public int createRecomProblem(RecommendDTO recommend) {
		int recomID = recommendDAO.createRecomProblem(recommend);
		
		return recomID;
	}
}

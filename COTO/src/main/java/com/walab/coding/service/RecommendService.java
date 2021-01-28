package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.RecomProblemsDTO;
import com.walab.coding.model.RecommendDTO;

public interface RecommendService {

	List<RecommendDTO> readRecom();
	
	List<RecomProblemsDTO> readRecomProblems(int recomID);
	
	int createRecomProblem(RecommendDTO recommend);
}

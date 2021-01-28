package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.RecomProblemsDTO;
import com.walab.coding.model.RecommendDTO;

public interface RecommendDAO {

	public List<RecommendDTO> readRecom();
	
	public void readRecomCount(RecommendDTO recommend, int index);
	
	public List<RecomProblemsDTO> readRecomProblems(int recomID);
	
	public int createRecomProblem(RecommendDTO recommend);
}

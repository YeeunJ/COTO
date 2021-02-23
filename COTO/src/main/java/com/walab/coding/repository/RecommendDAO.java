package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;

public interface RecommendDAO {
	
	public int createRecomProblem(RecommendDTO recommend);
	
	public List<RecommendDTO> readRecommendList();

	public List<RecommendDTO> readRecentRecommendList();
	
	public RecommendDTO readRecommend(int recomID);
	
	public List<RecommendDTO> readRecomByPage(String searchValue, String orderValue, int s_point, int list);
	
	public int readRecomListCnt();
	
	public int updateRecommend(RecommendDTO r);
	
	public int deleteRecom(int recomID);

	

	
	
	public List<RecomProblemDTO> readRecomProblems(int recomID);
	
	public List<RecommendDTO> searchProblemByContents(String searchValue, String orderValue);
}

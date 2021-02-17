package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;

public interface RecommendService {
	
	int createRecomProblem(RecommendDTO recommend);

	List<RecommendDTO> readRecom();
	
	List<RecomProblemDTO> readRecomProblems(int recomID);
	
	/**
	 * read the recommend list that uploaded recently
	 * usage: HomeController
	 */
	public List<RecommendDTO> readRecentRecommendList();
	
	public RecommendDTO readRecommend(int recomID);

	public List<RecommendDTO> readRecomByPage(String searchValue, String orderValue, int s_point, int list);

	public int readRecomListCnt();
		
	public int deleteRecom(int recomID);
	
	public int updateRecommend(RecommendDTO r);
	
	public List<RecommendDTO> search(String searchValue, String orderValue);

}

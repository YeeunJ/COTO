package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;

public interface RecommendService {
	
	int createRecomProblem(RecommendDTO recommend);

	List<RecommendDTO> readRecommendList();
	
	//List<RecomProblemDTO> readRecomProblems(int recomID);
	
	/**
	 * read the recommend list that user picked
	 * usage: MyproblemController
	 */
	public List<RecommendDTO> readCartRecommendList();
	
	/**
	 * read the recommend list that uploaded recently
	 * usage: HomeController
	 */
	public List<RecommendDTO> readRecentRecommendList();
	
	public RecommendDTO readRecommend(int recomID);

	public List<RecommendDTO> readRecomByPage(String searchValue, String orderValue, int s_point, int list);

	public int readRecomListCnt();
	
	public int updateRecommend(RecommendDTO r);
		
	public int deleteRecom(int recomID);

}

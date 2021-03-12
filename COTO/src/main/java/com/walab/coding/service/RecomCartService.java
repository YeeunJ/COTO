package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.RecomCartDTO;
import com.walab.coding.model.RecommendDTO;

public interface RecomCartService {
	void createRecomCart(RecomCartDTO cart);
	/**
	 * read the recommend list that user picked
	 * usage: MyproblemController
	 */
	List<RecommendDTO> readCartByRecommend(String searchValue, String orderValue, List<String> tagValue, int s_point, int list, int userID);
	public int readCartByID(int recomID, int userID);
	//List<RecommendDTO> readCartByRecommend(int userID);
	void deleteRecomCart(RecomCartDTO cart);
	List<RecommendDTO> readCartRecommendList(int userID);
}

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
	public List<RecommendDTO> readCartRecommendList(int userID);
	int readCartByID(int recomID, int userID);
	List<RecommendDTO> readCartByRecommend(int recomID);
	void deleteRecomCart(RecomCartDTO cart);
}

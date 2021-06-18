package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.RecomCartDTO;
import com.walab.coding.model.RecommendDTO;

public interface RecomCartDAO {
	void createRecomCart(RecomCartDTO cart);
	public List<RecommendDTO> readCartRecommendList(int userID);
	int readCartByID(int recomID, int userID);
	void deleteRecomCart(RecomCartDTO cart);
	int readTotalProbCnt(int recomID);
	int readUserProbCnt(int userID,int recomID);
	int readCartByRecommend(int recomID, int userID);
	public void deleteRecomProblem(int recomID);
}

package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.RecomCartDTO;
import com.walab.coding.model.RecommendDTO;

public interface RecomCartDAO {
	void createRecomCart(RecomCartDTO cart);
	public List<RecommendDTO> readCartRecommendList(int userID);
	int readCartByID(int recomID, int userID);
	void deleteRecomCart(int id);
}

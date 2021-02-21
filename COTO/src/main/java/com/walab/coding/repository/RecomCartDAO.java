package com.walab.coding.repository;

import com.walab.coding.model.RecomCartDTO;

public interface RecomCartDAO {
	void createRecomCart(RecomCartDTO cart);
	int readCartByID(int recomID, int userID);
	void deleteRecomCart(RecomCartDTO cart);
}

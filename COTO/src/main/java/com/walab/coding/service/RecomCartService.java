package com.walab.coding.service;

import com.walab.coding.model.RecomCartDTO;

public interface RecomCartService {
	void createRecomCart(RecomCartDTO cart);
	int readCartByID(int recomID, int userID);
	void deleteRecomCart(RecomCartDTO cart);
}

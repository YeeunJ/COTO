package com.walab.coding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomCartDTO;
import com.walab.coding.repository.RecomCartDAO;

@Service
public class RecomCartServiceImpl implements RecomCartService {
	
	@Autowired
	RecomCartDAO recomCartDAO;
	
	@Override
	public void createRecomCart(RecomCartDTO cart) {
		
		recomCartDAO.createRecomCart(cart);
		
	}
	
	@Override
	public int readCartByID(int recomID, int userID) {
		return recomCartDAO.readCartByID(recomID, userID);
	}

	@Override
	public void deleteRecomCart(int id) {
		
		recomCartDAO.deleteRecomCart(id);
		
	}


}

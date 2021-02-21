package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomCartDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.repository.RecomCartDAO;

@Service
public class RecomCartServiceImpl implements RecomCartService {
	
	@Autowired
	RecomCartDAO recomCartDAO;
	
	@Override
	public void createRecomCart(RecomCartDTO cart) {
		
		recomCartDAO.createRecomCart(cart);
		
	}
	
	public List<RecommendDTO> readCartRecommendList(int userID){
		List<RecommendDTO> result = recomCartDAO.readCartRecommendList(userID);	
		return result;
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

package com.walab.coding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.walab.coding.repository.RecomCountDAO;

import com.walab.coding.model.RecomCountDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.repository.RecomCountDAO;

@Service
public class RecomCountServiceImpl implements RecomCountService {
	
	@Autowired
	RecomCountDAO recomCountDAO;
	
	public int createRecomCount(RecomCountDTO rcd) {
		return recomCountDAO.createRecomCount(rcd);
	}
	
	public RecomCountDTO readRecomCount(int recomID, int userID) {
		RecomCountDTO rcd = new RecomCountDTO();
		rcd.setRecommendCount(recomCountDAO.readRecomCount(recomID));
		if(recomCountDAO.readRecomCountYNbyUserID(recomID, userID) > 0) {
			rcd.setRecommendYN(true);
		}else {
			rcd.setRecommendYN(false);
		}
		
		return rcd;
	}
	
	@Override
	public int deleteRecomCount(int recomID) {
		return recomCountDAO.deleteRecomCount(recomID);
	}
}

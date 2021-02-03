package com.walab.coding.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.walab.coding.repository.RecomCountDAO;

public class RecomCountServiceImpl implements RecomCountService {

	@Autowired
	RecomCountDAO recomCountDAO;
	
	@Override
	public int deleteRecomCount(int recomID) {
		return recomCountDAO.deleteRecomCount(recomID);
	}
}

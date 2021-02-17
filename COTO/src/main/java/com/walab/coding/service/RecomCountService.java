package com.walab.coding.service;

import com.walab.coding.model.RecomCountDTO;

public interface RecomCountService {
	
	public int createRecomCount(RecomCountDTO rcd);
	
	public RecomCountDTO readRecomCount(int recomID, int userID);
	
	public int deleteRecomCount(RecomCountDTO rcd);
	
	public int deleteRecomCount(int recomID);
}

package com.walab.coding.repository;

import com.walab.coding.model.RecomCountDTO;
import com.walab.coding.model.RecommendDTO;

public interface RecomCountDAO {

	public int createRecomCount(RecomCountDTO rcd);
	
	public int readRecomCount(int recomID);
	
	public int readRecomCountYNbyUserID(int recomID, int userID);	
	
	public int deleteRecomCount(RecomCountDTO rcd);

	public int deleteRecomCount(int recomID);
}

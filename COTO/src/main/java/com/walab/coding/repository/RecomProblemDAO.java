package com.walab.coding.repository;

import com.walab.coding.model.RecomProblemDTO;

public interface RecomProblemDAO {

	void createRecomProblem(RecomProblemDTO rp);
	
	public int readProblemID(int siteID, String problem);
}

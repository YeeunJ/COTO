package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.RecomProblemDTO;

public interface RecomProblemDAO {

	void createRecomProblem(RecomProblemDTO rp);
	
	public int readProblemID(int siteID, String problem);
	
	public List<RecomProblemDTO> readProblem();
}

package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.RecomProblemDTO;

public interface RecomProblemDAO {

	void createRecomProblem(RecomProblemDTO rp);
	
	public List<RecomProblemDTO> readProblemList();
	
	List<RecomProblemDTO> readProblemByID(int recomID);
	
	public RecomProblemDTO readEachProblem(int rpID, int userID);
	
	public int deleteRecomProblem(int recomID);
	
	public int readProblemID(int siteID, String problem);
}

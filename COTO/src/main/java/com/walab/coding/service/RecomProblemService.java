package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.RecomProblemDTO;

public interface RecomProblemService {

	public void createRecomProblem(List<RecomProblemDTO> recomprobs);
	
	public int readProblemID(int siteID, String problem);
	
	public List<RecomProblemDTO> readProblem();
	
	public int deleteRecomProblem(int recomID);
	
	public List<RecomProblemDTO> readProblemByID(int recomID);
	
	
}

package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.ProblemDTO;

public interface ProblemDAO {
	
	public int readProblemID(int siteID, String problem);
	
	public List<ProblemDTO> readProblem();
	

}

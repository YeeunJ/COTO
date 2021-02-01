package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.PaginationDTO;
import com.walab.coding.model.ProblemDTO;

public interface ProblemDAO {
	
	public int readProblemID(int siteID, String problem);
	
	public List<ProblemDTO> readProblem();
	
	public int readProblemListCnt();
	
	public List<ProblemDTO> readProblemByPage(PaginationDTO page);

}

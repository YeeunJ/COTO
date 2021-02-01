package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.PaginationDTO;
import com.walab.coding.model.ProblemDTO;

public interface ProblemService {
	public List<ProblemDTO> readProblems();	
	
	public int readProblemListCnt();
	
	public List<ProblemDTO> readProblemByPage(PaginationDTO page);
}

package com.walab.coding.service;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.PaginationDTO;
import com.walab.coding.model.ProblemDTO;

public interface ProblemService {
	public List<ProblemDTO> readProblems();	
	
	public int readProblemListCnt();
	
	public List<ProblemDTO> readProblemByPage(PaginationDTO page);
	
	List<ProblemDTO> readRecentProblem();
	
	public List<Map<String, Object>> readRatioBySiteid();

	public List<Map<String, Object>> makeRatioBySiteid(List<Map<String, Object>> ratioBySite, List<CodingSiteDTO> codingSite);


}

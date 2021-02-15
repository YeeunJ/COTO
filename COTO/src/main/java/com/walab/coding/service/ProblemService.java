package com.walab.coding.service;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.ProblemDTO;

public interface ProblemService {
	public List<ProblemDTO> readProblems();	
	
	public int readProblemListCnt(String searchValue, String orderValue, String siteValue);
	
	public List<ProblemDTO> readProblemByPage(int s_point, int list);
	
	// Home Controller
	List<ProblemDTO> readRecentProblem();
	
	// Problemlist Controller
	public List<Map<String, Object>> readRatioBySiteid();
	
	public List<ProblemDTO> search(int s_point, int list, String searchValue, String orderValue, String siteValue);
	
	// Problemlist Controller
	public List<Map<String, Object>> makeRatioBySiteid(List<Map<String, Object>> ratioBySite, List<CodingSiteDTO> codingSite);


}

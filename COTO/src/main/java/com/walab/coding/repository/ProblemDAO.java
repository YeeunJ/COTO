package com.walab.coding.repository;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.ProblemDTO;

public interface ProblemDAO {
	
	public int readProblemListCnt(String searchValue, String orderValue, String siteValue);
	
	public List<ProblemDTO> readRecentProblem();
	
	public List<ProblemDTO> searchProblemByContents(int s_point, int list, String searchValue, String orderValue, String siteValue);

	public List<Map<String, Object>> readRatioBySiteid();
	
	public ProblemDTO readProblembyID(int siteID, String problem);

	public List<ProblemDTO> readProblem();
	
	public List<ProblemDTO> readOtherUserProblemName(int userID);	
}

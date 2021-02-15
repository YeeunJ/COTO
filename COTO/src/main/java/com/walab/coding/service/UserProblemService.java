package com.walab.coding.service;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.UserProblemDTO;

public interface UserProblemService {
	// Home Controller
	void createUserProblem(List<UserProblemDTO> p);
	
	public void createUserProblembyID(UserProblemDTO p);
	
	List<UserProblemDTO> read(int userID);
	
	int readSolvedP(int userID);
	
	// HomeController
	public List<UserProblemDTO> readProblemList();
	
	// HomeController
	public List<RankDTO> readTotalRankList();

	// HomeController
	public List<RankDTO> readTodayRankList();
	
	List<UserProblemDTO> readProblemByPage(int userID, int s_point, int list);
	
	int readProblemCnt(int userID);
	
	public List<UserProblemDTO> readProblemActivities(int userID, int goalID);

	public List<Map<String, Object>> readAvgForaWeek();

	public int update(UserProblemDTO upd);
	
	public int delete(int userProblemID);
	
	public List<UserProblemDTO> countSolvedProblemEachDay(int userID);

	
	List<UserProblemDTO> search(int userID, String searchValue);
}

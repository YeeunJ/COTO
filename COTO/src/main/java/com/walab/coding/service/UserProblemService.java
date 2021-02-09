package com.walab.coding.service;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.UserProblemDTO;

public interface UserProblemService {
	List<UserProblemDTO> read(int userID);
	
	int readSolvedP(int userID);
	
	public List<RankDTO> readTotalRankList();
	
	public List<RankDTO> readTodayRankList();

	public int update(UserProblemDTO upd);
	
	public int delete(int userProblemID);
	
	void createUserProblem(List<UserProblemDTO> p);
	
	public void createUserProblembyID(UserProblemDTO p);
	
	List<UserProblemDTO> search(int userID, String searchValue);
	
	public List<UserProblemDTO> readProblemList();
	
	public List<UserProblemDTO> countSolvedProblemEachDay(int userID);
	
	public List<UserProblemDTO> readProblemActivities(int userID, int goalID);

	public List<Map<String, Object>> readAvgForaWeek();
	
	List<UserProblemDTO> readProblemByPage(int userID, int s_point, int list);
	
	int readProblemCnt(int userID);
}

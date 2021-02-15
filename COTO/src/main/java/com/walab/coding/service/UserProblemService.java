package com.walab.coding.service;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.UserProblemDTO;

public interface UserProblemService {
	// HomeController, MyproblemsController
	void createUserProblem(List<UserProblemDTO> p);
	
	// RecommendController
	public void createUserProblembyID(UserProblemDTO p);
	
	// MyproblemsController
	List<UserProblemDTO> read(int userID);
	
	// MyproblemsController
	int readSolvedP(int userID);
	
	// HomeController
	public List<UserProblemDTO> readProblemList();
	
	// HomeController
	public List<RankDTO> readTotalRankList();

	// HomeController
	public List<RankDTO> readTodayRankList();
	
	// MyproblemsController
	List<UserProblemDTO> readProblemByPage(int userID, int s_point, int list);
	
	// MyproblemsController
	int readProblemCnt(int userID);
	
	// MyactivitiesController
	public List<UserProblemDTO> readProblemActivities(int userID, int goalID);

	// ProblemlistController
	public List<Map<String, Object>> readAvgForaWeek();

	// MyproblemsController
	public int update(UserProblemDTO upd);
	
	// RecommendController
	public int delete(int userProblemID);
	
	// MyproblemsController
	public List<UserProblemDTO> countSolvedProblemEachDay(int userID);

	// MyproblemsController
	List<UserProblemDTO> search(int userID, String searchValue);
}

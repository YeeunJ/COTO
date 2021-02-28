package com.walab.coding.repository;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.UserProblemDTO;


public interface UserProblemDAO {
	// MyproblemsController
	public List<UserProblemDTO> readAll(int userID);
	
	// MyproblemsController
	public int updateProblem(UserProblemDTO upd);
	
	// RecommendController
	public int deleteProblem(int userProblemID);
	
	public int deleteUserProblemByProblemID(int userProblemID);
	
	// MyproblemsController
	public int readSolvedP(int userID);
	
	// HomeController
	public List<RankDTO> readTotalRankList();
	
	// HomeController
	public List<RankDTO> readTodayRankList();
	
	// HomeController, MyproblemsController
	public void createUserProblem(UserProblemDTO p);
	
	// MyproblemsController
	public List<UserProblemDTO> searchProblemByContent(int userID, String searchValue);
	
	// HomeController
	public List<UserProblemDTO> readProblemOrderByCount();
	
	// MyproblemsController
	public List<UserProblemDTO> countSolvedProblemEachDay(int userID);
	
	// MyactivitiesController
	public List<UserProblemDTO> readProblemActivities(int userID, int goalID);
	
	// ProblemlistController
	public List<Map<String,Object>> readAvgForaWeek();
	
	// ProblemlistController
	public int readUserCountToday();
		
	// MyproblemsController
	public List<UserProblemDTO> readProblemByPage(int userID, int s_point, int list);
	
	// MyproblemsController
	public int readProblemCnt(int userID);
	
	// RecommendController
	public void createUserProblembyID(UserProblemDTO p);
	
	// UserPageController
	public List<UserProblemDTO> readOtherUserPage(int userID);
	
	// UserPageController
	public int countOtherUserProblem(int userID);
}

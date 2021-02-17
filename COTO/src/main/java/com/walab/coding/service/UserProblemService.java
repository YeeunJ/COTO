package com.walab.coding.service;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.UserProblemDTO;

public interface UserProblemService {
	
	/**
	 * insert user problems that user solved
	 * usage: HomeController, MyproblemsController
	 */
	void createUserProblem(List<UserProblemDTO> p);
	
	// RecommendController
	public void createUserProblembyID(UserProblemDTO p);
	
	/**
	 * insert read problems each site
	 * usage: MyproblemsController
	 */
	List<UserProblemDTO> read(int userID);
	
	/**
	 * count user solvedProblem List
	 * usage: MyproblemsController
	 */
	int readSolvedP(int userID);
	
	/**
	 * read user problems that user solved
	 * usage: HomeController
	 */
	public List<UserProblemDTO> readProblemList();
	
	/**
	 * read accumulated rank
	 * usage: HomeController
	 */
	public List<RankDTO> readTotalRankList();

	/**
	 * read today's rank
	 * usage: HomeController
	 */
	public List<RankDTO> readTodayRankList();
	
	/**
	 * For pagination
	 * usage: MyproblemsController
	 */
	List<UserProblemDTO> readProblemByPage(int userID, int s_point, int list);
	
	/**
	 * For pagination
	 * usage: MyproblemsController
	 */
	int readProblemCnt(int userID);
	
	// MyactivitiesController
	public List<UserProblemDTO> readProblemActivities(int userID, int goalID);

	// ProblemlistController
	public List<Map<String, Object>> readAvgForaWeek();

	/**
	 * update user solvedProblem List
	 * usage: MyproblemsController
	 */
	public int update(UserProblemDTO upd);
	
	// RecommendController
	public int delete(int userProblemID);
	
	/**
	 * For chart, count user solvedProblem Each day
	 * usage: MyproblemsController
	 */
	public List<UserProblemDTO> countSolvedProblemEachDay(int userID);

	/**
	 * search user solvedProblem List
	 * usage: MyproblemsController
	 */
	List<UserProblemDTO> search(int userID, String searchValue);
}

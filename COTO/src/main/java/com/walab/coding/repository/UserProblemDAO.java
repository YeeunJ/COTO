package com.walab.coding.repository;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.UserProblemDTO;


public interface UserProblemDAO {
	public List<UserProblemDTO> readAll(int userID);
	public int updateProblem(UserProblemDTO upd);
	public int deleteProblem(int userProblemID);
	public int readSolvedP(int userID);
	public List<RankDTO> readTotalRankList();
	public List<RankDTO> readTodayRankList();
	public void createUserProblem(UserProblemDTO p);
	public List<UserProblemDTO> searchProblemByContent(int userID, String searchValue);
	public List<UserProblemDTO> readProblemOrderByCount();
	public List<UserProblemDTO> countSolvedProblemEachDay(int userID);
	public List<UserProblemDTO> readProblemActivities(int userID, int goalID);
	public List<Map<String,Object>> readAvgForaWeek();
}

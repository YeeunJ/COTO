package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.UserProblemDTO;


public interface UserProblemDAO {
	public List<UserProblemDTO> readAll(int userID);
	public int updateProblem(UserProblemDTO upd);
	public int deleteProblem(int userProblemID);
	public int readSolvedP(int userID);
	public List<RankDTO> readRankList();
	public void createUserProblem(UserProblemDTO p);
	public List<UserProblemDTO> searchProblemByContent(int userID, String searchValue);
}

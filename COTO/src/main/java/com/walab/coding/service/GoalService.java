package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserProblemDTO;

public interface GoalService {
	List<GoalDTO> readGoal(int userID);
	List<GoalDTO> readGoalAll(int userID, int s_point, int list);
//	List<GoalDTO> countGoal(int userID);
//	public int updateGoal(GoalDTO goalDTO);
	int updateGoal(GoalDTO goalDTO);
	int readGoalCnt(int userID);
}

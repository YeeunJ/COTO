package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GoalDTO;

public interface GoalService {
	List<GoalDTO> readGoal(int userID);
	List<GoalDTO> readGoalAll(int userID);
//	List<GoalDTO> countGoal(int userID);
//	public int updateGoal(GoalDTO goalDTO);
	int updateGoal(GoalDTO goalDTO);

}

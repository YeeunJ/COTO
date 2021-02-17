package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserProblemDTO;

public interface GoalService {
	/**
	 * Read the goal information of a user with a specific ID
	 * usage: MyinformationController	 
	 */
	List<GoalDTO> readGoal(int userID);
	/**
	 * Read the goal that registered
	 * usage: 	 
	 */
	List<GoalDTO> readGoalAll(int userID, int s_point, int list);
	/**
	 * Read the number of the goals
	 * usage: 	 
	 */
	int readGoalCnt(int userID);
	/**
	 * Update a user's goals
	 * usage: MyinformationController	 
	 */	
	int updateGoal(GoalDTO goalDTO);
}

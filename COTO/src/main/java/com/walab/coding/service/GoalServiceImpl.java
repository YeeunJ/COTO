package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.repository.GoalDAO;


@Service
public class GoalServiceImpl implements GoalService{
	
	@Autowired
	GoalDAO goalDAO ;
	
	/**
	 * MyinformationController
	 * Read the goal information of a user with a specific ID
	 */
	@Override
	public List<GoalDTO> readGoal(int userID){
		List<GoalDTO> goal = goalDAO.readGoal(userID);
		return goal;
	}
	
	@Override
	public List<GoalDTO> readGoalAll(int userID, int s_point, int list){
		List<GoalDTO> goalList = goalDAO.readGoalAll(userID, s_point, list);
		return goalList;
	}

	
	/**
	 * MyinformationController
	 * Update a user's goals
	 */
	@Override
	public int updateGoal(GoalDTO goalDTO) {
		// TODO Auto-generated method stub
		return goalDAO.updateGoal(goalDTO);
	}

	@Override
	public int readGoalCnt(int userID) {
		return goalDAO.readGoalCnt(userID);
	}
}

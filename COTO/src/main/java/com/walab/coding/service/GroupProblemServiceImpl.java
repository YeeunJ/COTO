package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.GroupProblemDTO;
import com.walab.coding.repository.GroupProblemDAO;

@Service
public class GroupProblemServiceImpl implements GroupProblemService {

	@Autowired
	GroupProblemDAO groupProblemDAO;
	
	@Override
	public List<GroupProblemDTO> readProblemsByGoalId(int goalID) {
		return groupProblemDAO.readProblemsByGoalId(goalID);
	}
	
	@Override
	public int readProbCountByGoalIDAndUserID(int id, int userID) {
		return groupProblemDAO.readProbCountByGoalIDAndUserID(id, userID);
	}
	
	@Override
	public void deleteProblemByGoalID(int id) {
		groupProblemDAO.deleteProblemByGoalID(id);
	}
}

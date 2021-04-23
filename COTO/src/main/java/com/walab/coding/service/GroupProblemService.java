package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GroupProblemDTO;

public interface GroupProblemService {

	List<GroupProblemDTO> readProblemsByGoalId(int id);

	int readProbCountByGoalIDAndUserID(int id, int userID);

}

package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.GroupProblemDTO;

public interface GroupProblemDAO {

	List<GroupProblemDTO> readProblemsByGoalId(int goalID);

	int readProbCountByGoalIDAndUserID(int id, int userID);

	void deleteProblemByGoalID(int id);

}

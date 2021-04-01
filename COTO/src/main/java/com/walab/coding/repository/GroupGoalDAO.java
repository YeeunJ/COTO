package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.GroupGoalDTO;
import com.walab.coding.model.ProblemDTO;

public interface GroupGoalDAO {

	void createGoal(String startDate, String endDate, int groupID);

	int readGoalID();

	void createGoalProblem(int goalID, ProblemDTO p);

	List<GroupGoalDTO> readGoalListByGroupId(int groupID);

}

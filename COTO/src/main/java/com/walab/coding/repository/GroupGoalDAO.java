package com.walab.coding.repository;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.GroupGoalDTO;
import com.walab.coding.model.GroupUserDTO;
import com.walab.coding.model.ProblemDTO;

public interface GroupGoalDAO {

	void createGoal(String startDate, String endDate, int groupID);

	int readGoalID();

	void createGoalProblem(int goalID, ProblemDTO p);

	List<GroupGoalDTO> readGoalListByGroupId(int groupID);

	List<GroupUserDTO> progressByUser(int groupID);

	GroupGoalDTO readGoalByGroupIdAndGoalId(int groupID, int goalID);

}

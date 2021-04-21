package com.walab.coding.service;

import java.util.List;
import java.util.Map;

import com.walab.coding.model.GroupGoalDTO;

public interface GroupGoalService {

	void createGoal(String startDate, String endDate, int groupID);
	int readGoalID();
	void createGoalProblems(int goalID, List<String> problem, List<String> siteId, List<String> link);
	List<GroupGoalDTO> readGoalListByGroupId(int groupID);
	List<Map<String, Object>> progressByUser(int groupID);
}

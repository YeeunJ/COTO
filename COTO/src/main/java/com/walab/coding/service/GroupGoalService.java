package com.walab.coding.service;

import java.util.List;

public interface GroupGoalService {

	void createGoal(String startDate, String endDate, int groupID);
	int readGoalID();
	void createGoalProblems(int goalID, List<String> problem, List<String> siteId, List<String> link);
}

package com.walab.coding.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.ProblemDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.repository.GroupGoalDAO;

@Service
public class GroupGoalServiceImpl implements GroupGoalService {
	
	@Autowired
	GroupGoalDAO groupGoalDAO;
	
	@Override
	public void createGoal(String startDate, String endDate, int groupID) {
		groupGoalDAO.createGoal(startDate, endDate, groupID);
	}

	@Override
	public int readGoalID() {
		return groupGoalDAO.readGoalID();
	}

	@Override
	public void createGoalProblems(int goalID, List<String> problem, List<String> siteId, List<String> link) {
		
		List<ProblemDTO> problems = new ArrayList<ProblemDTO>();
		
		for(int i=0 ; i<siteId.size() ; i++) {
			ProblemDTO p = new ProblemDTO();
			
			if(Integer.parseInt(siteId.get(i)) != 0) {
				p.setSiteID(Integer.parseInt(siteId.get(i)));
			}
			p.setName(problem.get(i));
			if(link.get(i) == null) {
				p.setLink(null); 
			} else {
				p.setLink(link.get(i));
			}
			problems.add(p);
			
			System.out.println(p);
			
			groupGoalDAO.createGoalProblem(goalID, p);
		}     
	}

}

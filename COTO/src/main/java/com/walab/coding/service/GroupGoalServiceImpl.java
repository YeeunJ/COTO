package com.walab.coding.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.GroupGoalDTO;
import com.walab.coding.model.GroupUserDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.repository.GroupGoalDAO;
import com.walab.coding.repository.UserDAO;

@Service
public class GroupGoalServiceImpl implements GroupGoalService {
	
	@Autowired
	GroupGoalDAO groupGoalDAO;
	@Autowired
	UserDAO userDAO;
	
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
	
	@Override
	public List<GroupGoalDTO> readGoalListByGroupId(int groupID) {
		return groupGoalDAO.readGoalListByGroupId(groupID);
	}

	@Override
	public List<Map<String, Object>> progressByUser(int groupID) {
		List <GroupUserDTO> count = groupGoalDAO.progressByUser(groupID);
		List<GroupUserDTO> groupUsers = userDAO.readUsersByGroup(groupID);
		List<Map<String,Object>> progress = new ArrayList<Map<String,Object>>();
//		for(int i=0 ; i<groupUsers.size() ; i++) {
//			System.out.println(groupUsers.get(i).getUserID());
//		}
		for(int i=0 ; i<count.size() ; i++) {
			System.out.println(count.toString());
		}
//		
		
		for(int i=0 ; i<groupUsers.size() ; i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			int userID = groupUsers.get(i).getUserID();
			String name = groupUsers.get(i).getName();
			String email = groupUsers.get(i).getEmail();
			String nickName = groupUsers.get(i).getNickName();
			
			
			map.put("userID", userID);
			map.put("count", 0);
			map.put("name",name);
			map.put("email", email);
			map.put("nickName", nickName);
			
			for(int j=0 ; j<count.size(); j++) {
				if(userID == count.get(j).getUserID()) {
					System.out.println("in true!!");
					map.put("count", count.get(j).getCount());
				}
			}
			progress.add(map);
			System.out.println(map);
		}
		return progress;
	}
	
	@Override
	public GroupGoalDTO readGoalByGroupIdAndGoalId(int groupID, int goalID) {
		return groupGoalDAO.readGoalByGroupIdAndGoalId(groupID, goalID);
	}
	
	@Override
	public void deleteGoalByGroupId(int groupID) {
		groupGoalDAO.deleteGoalByGroupId(groupID);
	}
	
	@Override
	public void deleteGoalByGoalID(int id) {
		groupGoalDAO.deleteGoalByGoalID(id);
	}

}

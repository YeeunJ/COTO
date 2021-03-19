package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.GroupInfoDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.repository.GroupInfoDAO;
import com.walab.coding.repository.UserDAO;

@Service
public class GroupInfoServiceImpl implements GroupInfoService {
	
	@Autowired
	GroupInfoDAO groupInfoDAO;
	@Autowired
	UserDAO userDAO;
	
	@Override
	public void createGroupInfo(GroupInfoDTO dto) {
		groupInfoDAO.createGroupInfo(dto);
	}

	@Override
	public int readGroupID() {
		return groupInfoDAO.readGroupID();
	}

	@Override
	public void createGroupUsers(List<String> users, int groupID) {
		for(int i=0 ; i<users.size() ; i++) {
			int userID = userDAO.readUserIDByEmail(users.get(i));
			groupInfoDAO.createGroupUser(userID, 0, groupID);
		}
	}

}

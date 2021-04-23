package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.GroupUserDTO;
import com.walab.coding.repository.GroupDAO;
import com.walab.coding.repository.GroupUserDAO;

@Service
public class GroupUserServiceImpl implements GroupUserService {
	
	@Autowired
	GroupUserDAO groupUserDAO;

	@Override
	public List<GroupUserDTO> readUsersByGroup(int groupID) {
		return groupUserDAO.readUsersByGroup(groupID);
	}
	
	@Override
	public int attendanceByGroup(int groupID){
		return groupUserDAO.attendanceByGroup(groupID);	
	}
	
	@Override
	public int totalGroupUser(int groupID) {
		return groupUserDAO.totalGroupUser(groupID);	
	}

}

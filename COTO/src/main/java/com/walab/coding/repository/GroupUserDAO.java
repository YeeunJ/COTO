package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.GroupUserDTO;

public interface GroupUserDAO {
	
	public List<GroupUserDTO> readUsersByGroup(int groupID);
	int attendanceByGroup(int groupID);
	int totalGroupUser(int groupID);

}

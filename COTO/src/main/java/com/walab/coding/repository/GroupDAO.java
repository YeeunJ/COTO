package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.GroupDTO;

public interface GroupDAO {
	
	public List<GroupDTO> readMyGroups(int UserID);

	public List<GroupDTO> readAdminGroups(int userID);

	public List<GroupDTO> readAllGroups();

	public void deleteUser(int userID, int groupID);

	int readAdminofGroup(int groupID);
	
}

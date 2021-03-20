package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GroupDTO;

public interface GroupService {
	
	/**
	 * read the groups that user registered.
	 * usage: MyGroupsController
	 */
	public List<GroupDTO> readMyGroups(int UserID);

	public List<GroupDTO> readAdminGroups(int userID);
	
	}

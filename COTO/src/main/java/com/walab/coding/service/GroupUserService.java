package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GroupUserDTO;

public interface GroupUserService {
	
	public List<GroupUserDTO> readUsersByGroup(int groupID);

}

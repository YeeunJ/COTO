package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.GroupDTO;

public interface GroupDAO {
	
	public List<GroupDTO> readMyGroups(int UserID);
	
}

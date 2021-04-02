package com.walab.coding.repository;

import com.walab.coding.model.GroupInfoDTO;

public interface GroupInfoDAO {
	void createGroupInfo(GroupInfoDTO dto);

	int readGroupID();

	void createGroupUser(int userID, int isAdmin, int groupID);
}

package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GroupInfoDTO;

public interface GroupInfoService {
	void createGroupInfo(GroupInfoDTO dto);

	int readGroupID();

	void createGroupUsers(List<String> users, int groupID);
}

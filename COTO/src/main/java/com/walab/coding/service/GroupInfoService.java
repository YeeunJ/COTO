package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GroupDTO;
import com.walab.coding.model.GroupInfoDTO;

public interface GroupInfoService {
	void createGroupInfo(GroupInfoDTO dto);

	int readGroupID();

	List<GroupInfoDTO> readGroupInfoById(int groupID);

	void createGroupUsers(List<String> users, int groupID);
}

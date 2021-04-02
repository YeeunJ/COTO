package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GroupDTO;
import com.walab.coding.model.GroupInfoDTO;

public interface GroupInfoService {
	void createGroupInfo(GroupInfoDTO dto);

	int readGroupID();

	void createGroupUsers(List<String> users, int groupID);
	
	public int readGroupListCnt(String searchValue, String orderValue);

	List<GroupDTO> search(int s_point, int list, String searchValue, String orderValue);
	
}

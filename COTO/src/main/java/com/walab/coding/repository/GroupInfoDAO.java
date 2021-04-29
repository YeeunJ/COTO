package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.GroupDTO;
import com.walab.coding.model.GroupInfoDTO;

public interface GroupInfoDAO {
	void createGroupInfo(GroupInfoDTO dto);

	int readGroupID();

	void createGroupUser(int userID, int isAdmin, int groupID);

	List<GroupInfoDTO> readGroupInfoById(int groupID);
	
	public int readGroupListCnt(String searchValue, String orderValue);

	List<GroupDTO> search(int s_point, int list, String searchValue, String orderValue);
	
	public int update(GroupInfoDTO gid);

	int readGroupLeaderByGroupID(int groupID);

	void deleteGroupInfoByGroupId(int groupID);
}

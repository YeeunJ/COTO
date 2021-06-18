package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.GroupDTO;
import com.walab.coding.model.GroupInfoDTO;
import com.walab.coding.repository.GroupInfoDAO;
import com.walab.coding.repository.UserDAO;

@Service
public class GroupInfoServiceImpl implements GroupInfoService {
	
	@Autowired
	GroupInfoDAO groupInfoDAO;
	@Autowired
	UserDAO userDAO;
	
	@Override
	public void createGroupInfo(GroupInfoDTO dto) {
		groupInfoDAO.createGroupInfo(dto);
	}

	@Override
	public int readGroupID() {
		return groupInfoDAO.readGroupID();
	}
	
	@Override
	public List<GroupInfoDTO> readGroupInfoById(int groupID){
		return groupInfoDAO.readGroupInfoById(groupID);
	}
	
	@Override
	public void createGroupUsers(List<String> users, int groupID) {
		for(int i=0 ; i<users.size() ; i++) {
			int userID = userDAO.readUserIDByEmail(users.get(i));
			if(i==0)
				groupInfoDAO.createGroupUser(userID, 1, groupID);
			else
				groupInfoDAO.createGroupUser(userID, 0, groupID);
		}
	}
	
	@Override
	public int readGroupListCnt(String searchValue, String orderValue) {
		return groupInfoDAO.readGroupListCnt(searchValue, orderValue);
	}
	
	@Override
	public List<GroupDTO> search(int s_point, int list, String searchValue, String orderValue) {
		searchValue = "%".concat(searchValue).concat("%");
		if(orderValue == null)
			orderValue ="problem.regdate desc";
		
		List<GroupDTO> groups = groupInfoDAO.search(s_point, list, searchValue, orderValue);
		
		return groups;
	}
	
	@Override
	public int update(GroupInfoDTO gid) {
		return groupInfoDAO.update(gid);
	}
	
	@Override
	public int readGroupLeaderByGroupID(int groupID) {
		return groupInfoDAO.readGroupLeaderByGroupID(groupID);
	}
	
	@Override
	public void deleteGroupInfoByGroupId(int groupID) {
		groupInfoDAO.deleteGroupInfoByGroupId(groupID);
	}

}

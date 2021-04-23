package com.walab.coding.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.GroupDTO;
import com.walab.coding.model.GroupInfoDTO;

@Repository
public class GroupInfoDAOImpl implements GroupInfoDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "groupInfo";
	
	@Override
	public void createGroupInfo(GroupInfoDTO dto) {
		sqlSession.insert(namespace+".createGroupInfo", dto);
	}

	@Override
	public int readGroupID() {
		return sqlSession.selectOne(namespace+".readGroupID");
	}
	
	@Override
	public List<GroupInfoDTO> readGroupInfoById(int groupID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("groupID", groupID);
		
		return sqlSession.selectList(namespace+".readGroupInfo", param);
	}

	@Override
	public void createGroupUser(int userID, int isAdmin, int groupID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userID", userID);
		param.put("isAdmin", isAdmin);
		param.put("groupID", groupID);
		
		sqlSession.insert(namespace+".createGroupUser", param);
		
	}
	
	@Override
	public int readGroupListCnt(String searchValue, String orderValue) {
		Map<String, Object> groupListParam = new HashMap<String, Object>();
		groupListParam.put("searchValue", searchValue);
		groupListParam.put("orderValue", orderValue);
		
		return sqlSession.selectOne(namespace+".readGroupListCnt", groupListParam);
	}
	
	@Override
	public List<GroupDTO> search(int s_point, int list, String searchValue, String orderValue) {
		Map<String, Object> groupListParam = new HashMap<String, Object>();
		groupListParam.put("searchValue", searchValue);
		groupListParam.put("orderValue", orderValue);
		groupListParam.put("s_point", s_point);
		groupListParam.put("list", list);
		
		return sqlSession.selectList(namespace+".search", groupListParam);
	}
	
	@Override
	public int update(GroupInfoDTO gid) {
		Map<String, Object> groupListParam = new HashMap<String, Object>();
		groupListParam.put("startDate", gid.getStartDate());
		groupListParam.put("endDate", gid.getEndDate());
		groupListParam.put("groupDesc", gid.getGroupDesc());
		groupListParam.put("groupID", gid.getId());
		
		return sqlSession.update(namespace+".updateGroupInfo", groupListParam);
	}
	
	@Override
	public int readGroupLeaderByGroupID(int groupID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("groupID", groupID);

		return sqlSession.selectOne(namespace+".readGroupLeaderByGroupID", param);
	}

}

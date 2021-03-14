package com.walab.coding.repository;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public void createGroupUser(int userID, int isAdmin, int groupID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userID", userID);
		param.put("isAdmin", isAdmin);
		param.put("groupID", groupID);
		
		sqlSession.insert(namespace+".createGroupUser", param);
		
	}

}

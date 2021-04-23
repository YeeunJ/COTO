package com.walab.coding.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.GroupUserDTO;

@Repository
public class GroupUserDAOImpl implements GroupUserDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "groupUser";

	@Override
	public List<GroupUserDTO> readUsersByGroup(int groupID) {
		return sqlSession.selectList(namespace+".readUsersByGroup", groupID);
	}
	
	@Override
	public int attendanceByGroup(int groupID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("groupID", groupID);
		return sqlSession.selectOne(namespace+".attendanceByGroup", param);
	}
	
	@Override
	public int totalGroupUser(int groupID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("groupID", groupID);
		return sqlSession.selectOne(namespace+".totalGroupUser", param);
	}

}

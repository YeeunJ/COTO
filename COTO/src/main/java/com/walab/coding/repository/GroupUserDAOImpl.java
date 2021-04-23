package com.walab.coding.repository;

import java.util.List;

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

}

package com.walab.coding.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.GroupDTO;

@Repository
public class GroupDAOImpl implements GroupDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "groups";
	
	
	@Override
	public List<GroupDTO> readMyGroups(int UserID){ 
		List<GroupDTO> readMyGroupsList = new ArrayList<GroupDTO>();
		Map<String, Object> groupsList = new HashMap<String, Object>();
		groupsList.put("userID", UserID);
		readMyGroupsList  = sqlSession.selectList(namespace+".readMyGroups", groupsList);
		return readMyGroupsList;
		
	}

}
package com.walab.coding.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.GroupProblemDTO;

@Repository
public class GroupProblemDAOImpl implements GroupProblemDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "groupProblem";
	
	@Override
	public List<GroupProblemDTO> readProblemsByGoalId(int goalID) {
		return sqlSession.selectList(namespace+".readProblemsByGoalId", goalID);
	}
}

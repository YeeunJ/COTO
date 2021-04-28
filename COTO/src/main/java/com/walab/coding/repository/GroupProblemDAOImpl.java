package com.walab.coding.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Override
	public int readProbCountByGoalIDAndUserID(int id, int userID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("userID", userID);
		
		return sqlSession.selectOne(namespace+".readProbCountByGoalIDAndUserID", param);
	}
	
	@Override
	public void deleteProblemByGoalID(int id) {
		sqlSession.delete(namespace+".deleteProblemByGoalID", id);
	}
}

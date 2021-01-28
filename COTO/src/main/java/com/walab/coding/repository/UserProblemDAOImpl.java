package com.walab.coding.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserProblemDTO;

@Repository
public class UserProblemDAOImpl implements UserProblemDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace = "userProblem";
	
	public List<UserProblemDTO> readAll(int userID) {
		
		List<UserProblemDTO> userProblemList = new ArrayList<UserProblemDTO>();
		
		Map<String, Object> userProblemListParam = new HashMap<String, Object>();
		userProblemListParam.put("userID", userID);
		
		return userProblemList = sqlSession.selectList(namespace+".readUserProblemList", userProblemListParam);
	}
	
	public int updateProblem(UserProblemDTO upd) {
		
		Map<String, Object> userProblemListParam = new HashMap<String, Object>();
		userProblemListParam.put("memo", upd.getMemo());
		userProblemListParam.put("difficulty", upd.getDifficulty());
		userProblemListParam.put("problemID", upd.getId());
		
		return sqlSession.update(namespace+".updateUserProblem", userProblemListParam);
	}
	
	public int deleteProblem(int userProblemID) {
		Map<String, Object> userProblemListParam = new HashMap<String, Object>();
		userProblemListParam.put("problemID", userProblemID);
		
		return sqlSession.delete(namespace+".deleteUserProblem", userProblemListParam);
	}
	
	public int readSolvedP(int userID) {
		
		Map<String, Object> userSolvedProblemParam = new HashMap<String, Object>();
		userSolvedProblemParam.put("userID", userID);
		
		return sqlSession.selectOne(namespace+".readSovledUserProblem", userSolvedProblemParam);
	}
	public List<String> readRank(int probs) {
		Map<String, Object> userRankParam = new HashMap<String, Object>();
		//userRankParam.put("username", probs);
		
		return sqlSession.selectList(namespace+".readRank");
	}
}

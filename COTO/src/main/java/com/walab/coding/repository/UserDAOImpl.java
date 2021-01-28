package com.walab.coding.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.UserDTO;
import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserProblemsDTO;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace = "user";
	
	public List<UserDTO> readUserAll(int userID) {
		
		List<UserDTO> userList = new ArrayList<UserDTO>();
		
		Map<String, Object> userListParam = new HashMap<String, Object>();
		userListParam.put("userID", userID);
		
		return userList = sqlSession.selectList(namespace+".userList", userListParam);
	}
	
//	public List<UserDTO> updateUser(int userID){
//		
//		int userList = new ArrayList<UserDTO>();
//
//		Map<String, Object> userListParam = new HashMap<String, Object>();
//		userListParam.put("userID", userID);
//		
//		return userList = sqlSession.update(namespace+".userList", userListParam);
//	}
	
	@Override
	public int createUsergoal(GoalDTO goal) {
		// TODO Auto-generated method stub
		sqlSession.insert("userInfo.createUsergoal", goal);
		return 0;
	}

	@Override
	public int createUserInfo(UserDTO user) {
		sqlSession.insert("userInfo.createUserInfo", user);
		return 0;
	}

	@Override
	public void createUserProblem(UserProblemsDTO p) {
		
		sqlSession.insert("userInfo.createUserProblem", p);
	
	}
	@Override
	public int updateUser(UserDTO updateUser) {
		
		Map<String, Object> userParam = new HashMap<String, Object>();
		userParam.put("name", updateUser.getName());
		userParam.put("nickName", updateUser.getNickName());
		userParam.put("userNumber", updateUser.getUserNumber());
		userParam.put("intro", updateUser.getIntro());
		userParam.put("id", updateUser.getId());
		
		return sqlSession.update("user.updateUser", userParam);
	}
	
	
}


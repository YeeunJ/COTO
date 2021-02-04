package com.walab.coding.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.model.GoalDTO;

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
	
	public int readUserIDByEmail(String email) {
		Map<String, Object> userListParam = new HashMap<String, Object>();
		userListParam.put("email", email);
		int id = 0;
		try {
			id = sqlSession.selectOne(namespace+".readUserIDByEmail", userListParam);
		}catch (NullPointerException e){
			System.out.println(e);
			id = 0;
		}
		return id;
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
		sqlSession.insert("user.createUsergoal", goal);
		return 0;
	}

	@Override
	public int createUserInfo(UserDTO user) {
		sqlSession.insert("user.createUserInfo", user);
		return 0;
	}

	@Override
	public int updateUser(UserDTO updateUser) {
		
		Map<String, Object> userParam = new HashMap<String, Object>();
		userParam.put("name", updateUser.getName());
		userParam.put("nickName", updateUser.getNickName());
		userParam.put("intro", updateUser.getIntro());
		userParam.put("id", updateUser.getId());
		
		return sqlSession.update("user.updateUser", userParam);
	}

	@Override
	public int readUserCountByNickname(String nickname) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("nickName", nickname);
	
		return sqlSession.selectOne("user.readUserCountByNickname", param);
	}

	@Override
	public List<UserDTO> read() {
		List<UserDTO> users = sqlSession.selectList("user.readAllUser");
		
		return users;
	}

	@Override
	public void updateUserAdmin(int isAdmin, int userID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("isAdmin", isAdmin);
		param.put("userID", userID);
	
		sqlSession.update("user.updateUserAdmin", param);
	}
	
	
}


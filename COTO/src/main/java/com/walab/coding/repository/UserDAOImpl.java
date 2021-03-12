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

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	private String namespace = "user";
	
	/**
	 * MyinformationController
	 * Read the information of a user with a specific ID
	 */
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

	/**
	 * RegisterController
	 * Create user's basic information
	 */
	@Override
	public int createUserInfo(UserDTO user) {
		sqlSession.insert("user.createUserInfo", user);
		return 0;
	}
	
	/**
	 * RegisterController
	 * Create user's goal information
	 */
	@Override
	public int createUsergoal(GoalDTO goal) {
		sqlSession.insert("goal.createUsergoal", goal);
		return 0;
	}
	
	/**
	 * MyinformationController
	 * Update a user's basic information
	 */
	@Override
	public int updateUser(UserDTO updateUser) {
		
		Map<String, Object> userParam = new HashMap<String, Object>();
		userParam.put("name", updateUser.getName());
		userParam.put("email", updateUser.getEmail());
		userParam.put("nickName", updateUser.getNickName());
		userParam.put("intro", updateUser.getIntro());
		userParam.put("id", updateUser.getId());
		
		return sqlSession.update("user.updateUser", userParam);
	}
	
	/**
	 * RegisterController, MyinformationController
	 * Return the number of duplicate email
	 */
	@Override
	public int readUserCountByNickname(String nickname) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("nickName", nickname);
	
		return sqlSession.selectOne("user.readUserCountByNickname", param);
	}
	
	/**
	 * UsermanageController
	 * Read all users' information.
	 */
	@Override
	public List<UserDTO> read() {
		List<UserDTO> users = sqlSession.selectList("user.readAllUser");
		
		return users;
	}
	
	/**
	 * UsermanageController
	 * Update user's admin permission from user to admin or admin to user.
	 */
	@Override
	public void updateUserAdmin(int isAdmin, int userID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("isAdmin", isAdmin);
		param.put("userID", userID);
	
		sqlSession.update("user.updateUserAdmin", param);
	}
	
	public int readIsAdminByUserID(int userID) {
		Map<String, Object> userParam = new HashMap<String, Object>();
		userParam.put("userID", userID);
		return sqlSession.selectOne(namespace+".readIsAdminByUserID", userParam);
	}
	
	public int selectedUserTotalProblem(String nickName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("nickName", nickName);
	
		return sqlSession.selectOne("user.selectedUserTotalProblem", param);
	}
	public String selectedUserintro(String nickName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("nickName", nickName);
	
		return sqlSession.selectOne("user.selectedUserintro", param);
	}
	
	@Override
	public int readUserListCnt() {
		return sqlSession.selectOne("user.readUserListCnt");
	}
	
	@Override
	public List<UserDTO> readUserByPage(String searchValue, String orderValue, int s_point, int list) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("s_point", s_point);
		param.put("list", list);
		param.put("searchValue", searchValue);
		param.put("orderValue", orderValue);
		
		return sqlSession.selectList("user.readUserByPage", param);
	}
}


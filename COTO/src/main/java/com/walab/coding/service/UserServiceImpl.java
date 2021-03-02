package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO userDAO;
	
	/**
	 * MyinformationController
	 * Read the information of a user with a specific ID
	 */
	public List<UserDTO> readUser(int userID){
		List<UserDTO> users = userDAO.readUserAll(userID);
		return users;
	}
	
	public int readUserIDByEmail(String email) {
		int userID = userDAO.readUserIDByEmail(email);
		
		return userID;
	}

	public int readIsAdminByUserID(int userID) {
		return userDAO.readIsAdminByUserID(userID);
	}
	
	public void regist(UserDTO ud) {
		
	}
	
	/**
	 * RegisterController
	 * Create user's basic information
	 */
	@Override
	public int createUserinfo(UserDTO u) {
		int result = userDAO.createUserInfo(u);
		return result;
	}
	
	/**
	 * RegisterController
	 * Create user's goal information
	 */
	@Override
	public int createUsergoal(GoalDTO g) {
		int result = userDAO.createUsergoal(g);
		return result;
	}
	
	/**
	 * MyinformationController
	 * Update a user's basic information
	 */
	@Override
	public int updateUser(UserDTO updateUser) {
		return userDAO.updateUser(updateUser);
	}
	
	/**
	 * RegisterController
	 * Return the number of duplicate email
	 */
	@Override
	public int readUserCountByNickname(String Nickname) {
		return userDAO.readUserCountByNickname(Nickname);
	}
	
	/**
	 * UsermanageController
	 * Read all users' information.
	 */
	@Override
	public List<UserDTO> read() {
		return userDAO.read();
	}
	
	/**
	 * UsermanageController
	 * Update user's admin permission from user to admin or admin to user.
	 */
	@Override
	public void updateUserAdmin(int isAdmin, int userID) {
		userDAO.updateUserAdmin(isAdmin, userID);
	}
	
	@Override
	public int selectedUserTotalProblem(String nickName) {
		return userDAO.selectedUserTotalProblem(nickName);
	}
	
	@Override
	public String selectedUserintro(String nickName) {
		return userDAO.selectedUserintro(nickName);
	}
	
	/**
	 * UsermanageController
	 * Read all user count.
	 */
	@Override
	public int readUserListCnt() {
		return userDAO.readUserListCnt();
	}
	
	/**
	 * UsermanageController
	 * Read all users' information by page.
	 */
	@Override
	public List<UserDTO> readUserByPage(String searchValue, String orderValue, int s_point, int list) {
		return userDAO.readUserByPage(searchValue, orderValue, s_point, list);
	}
	
}

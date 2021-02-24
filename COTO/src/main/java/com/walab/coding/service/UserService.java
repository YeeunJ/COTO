package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;

public interface UserService {

	/**
	 * Create user's basic information
	 * usage: RegisterController
	 */
	int createUserinfo(UserDTO u);
	
	/**
	 * Create user's goal information
	 * usage: RegisterController
	 */
	int createUsergoal(GoalDTO g);
	
	public void regist(UserDTO ud);
	
	/**
	 * Read all users' information.
	 * usage: UsermanageController
	 */
	public List<UserDTO> read();
	
	List<UserDTO> readUser(int userID);
	
	public int readUserIDByEmail(String email);
	
	public int readIsAdminByUserID(int userID);
	
	/**
	 * Return the number of duplicate email
	 * usage: RegisterController
	 */
	public int readUserCountByNickname(String Nickname);
	
	/**
	 * Update a user's basic information
	 * usage: MyinformationController
	 */
	public int updateUser(UserDTO updateUser);
	
	/**
	 * Update user's admin permission from user to admin or admin to user.
	 * usage: UsermanageController	 
	 */
	public void updateUserAdmin(int isAdmin, int userID);
	
	public int selectedUserTotalProblem(String nickName);
	
	public String selectedUserintro(String nickName);
}

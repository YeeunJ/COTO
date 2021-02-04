package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;

public interface UserService {

	List<UserDTO> readUser(int userID);
	
	public void regist(UserDTO ud);
	
	public int readUserIDByEmail(String email);
	
	int createUserinfo(UserDTO u);
	
	int createUsergoal(GoalDTO g);
	
	public int updateUser(UserDTO updateUser);
	
	public int readUserCountByNickname(String Nickname);
	
	public List<UserDTO> read();
	
	public void updateUserAdmin(int isAdmin, int userID);
}

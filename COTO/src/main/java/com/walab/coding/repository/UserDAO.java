package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;

public interface UserDAO {
	
	public int createUserInfo(UserDTO u);
	public int createUsergoal(GoalDTO g);
	public int readUserIDByEmail(String email);
	public List<UserDTO> readUserAll(int userID);
//	public List<UserDTO> updateUser(int userID);
	public int updateUser(UserDTO updateUser);
	public int readUserCountByNickname(String nickname);
	public List<UserDTO> read();
	public void updateUserAdmin(int isAdmin, int userID);

}
package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;

public interface UserDAO {
	
	public int createUserInfo(UserDTO u);
	public int createUsergoal(GoalDTO g);

	public List<UserDTO> readUserAll(int userID);
//	public List<UserDTO> updateUser(int userID);
	public int updateUser(UserDTO updateUser);


}
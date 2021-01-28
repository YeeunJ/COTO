package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.model.UserProblemsDTO;

public interface UserDAO {
	
	public int createUserInfo(UserDTO u);
	public int createUsergoal(GoalDTO g);
	public void createUserProblem(UserProblemsDTO p);

	public List<UserDTO> readUserAll(int userID);
//	public List<UserDTO> updateUser(int userID);
	public int updateUser(UserDTO updateUser);


}
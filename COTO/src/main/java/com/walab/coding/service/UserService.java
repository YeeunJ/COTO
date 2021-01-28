package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemsDTO;

public interface UserService {

	List<UserDTO> readUser(int userID);
	public void regist(UserDTO ud);
	
	int registerUserinfo(UserDTO u);
	
	int registerUsergoal(GoalDTO g);
	
	void createUserProblem(List<UserProblemsDTO> p);
	public int updateUser(UserDTO updateUser);


}

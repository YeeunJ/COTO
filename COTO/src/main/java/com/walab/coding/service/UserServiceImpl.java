package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO userDAO;
	
	public List<UserDTO> readUser(int userID){
		List<UserDTO> users = userDAO.readUserAll(userID);
		return users;
	}
	
	public void regist(UserDTO ud) {
		
	}

	@Override
	public int createUsergoal(GoalDTO g) {
		int result = userDAO.createUsergoal(g);
		return result;
	}

	@Override
	public int createUserinfo(UserDTO u) {
		// TODO Auto-generated method stub
		int result = userDAO.createUserInfo(u);
		return result;
	}

	@Override
	public int updateUser(UserDTO updateUser) {
		return userDAO.updateUser(updateUser);
	}
}

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
	
	public int readUserIDByEmail(String email) {
		int userID = userDAO.readUserIDByEmail(email);
		
		return userID;
	}

	public int readIsAdminByUserID(int userID) {
		return userDAO.readIsAdminByUserID(userID);
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
		int result = userDAO.createUserInfo(u);
		return result;
	}

	@Override
	public int updateUser(UserDTO updateUser) {
		return userDAO.updateUser(updateUser);
	}

	@Override
	public int readUserCountByNickname(String Nickname) {
		return userDAO.readUserCountByNickname(Nickname);
	}

	@Override
	public List<UserDTO> read() {
		return userDAO.read();
	}

	@Override
	public void updateUserAdmin(int isAdmin, int userID) {
		userDAO.updateUserAdmin(isAdmin, userID);
	}
}

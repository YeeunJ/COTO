package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;

public interface UserDAO {
	
	public int createUserInfo(UserDTO u);
	public int createUsergoal(GoalDTO g);
	public List<UserDTO> read();
	public List<UserDTO> readUserAll(int userID);
	public int readUserIDByEmail(String email);
	public int readIsAdminByUserID(int userID);
	public int readUserCountByNickname(String nickname);
	public int updateUser(UserDTO updateUser);
	public void updateUserAdmin(int isAdmin, int userID);
	public int selectedUserTotalProblem(String nickName);
	public String selectedUserintro(String nickName);
	public int readUserListCnt();
	public List<UserDTO> readUserByPage(String searchValue, String orderValue, int s_point, int list);
}
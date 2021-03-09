package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.walab.coding.model.GroupDTO;

import com.walab.coding.repository.GroupDAO;


@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDAO groupDAO;

	@Override
	public List<GroupDTO> readMyGroups(int UserID) {
		return groupDAO.readMyGroups(UserID);
	}
	

	
}

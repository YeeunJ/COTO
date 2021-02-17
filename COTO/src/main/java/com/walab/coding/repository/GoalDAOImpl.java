package com.walab.coding.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.GoalDTO;

@Repository("goalDAO")
public class GoalDAOImpl implements GoalDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace = "goal";
	
	/**
	 * MyinformationController
	 * Read the goal information of a user with a specific ID
	 */
	public List<GoalDTO> readGoal(int userID) {
		
		List<GoalDTO> goal = new ArrayList<GoalDTO>();
		
		Map<String, Object> goalParam = new HashMap<String, Object>();
		goalParam.put("userID", userID);
		
		return goal = sqlSession.selectList(namespace+".readGoal", goalParam);
	}
	
	public List<GoalDTO> readGoalAll(int userID, int s_point, int list) {
		
		List<GoalDTO> goalList = new ArrayList<GoalDTO>();
		
		Map<String, Object> goalListParam = new HashMap<String, Object>();
		goalListParam.put("userID", userID);
		goalListParam.put("s_point", s_point);
		goalListParam.put("list", list);
		
		return goalList = sqlSession.selectList(namespace+".readGoalByPage", goalListParam);
		//return goalList = sqlSession.selectList(namespace+".readGoalList", goalListParam);
	}
	
	/**
	 * MyinformationController
	 * Update a user's goals
	 */
	public int updateGoal(GoalDTO updatdGoal) {
		
		Map<String, Object> goalParam = new HashMap<String, Object>();
		goalParam.put("goal", updatdGoal.getGoal());
		goalParam.put("startDate", updatdGoal.getStartDate());
		goalParam.put("endDate", updatdGoal.getEndDate());
		goalParam.put("goalNum", updatdGoal.getGoalNum());		
		goalParam.put("id", updatdGoal.getId());
		
		return sqlSession.update(namespace+".updateGoal", goalParam);
	}

	@Override
	public int readGoalCnt(int userID) {
		Map<String, Object> goalListParam = new HashMap<String, Object>();
		System.out.println("goalDAOImpl>>>>>"+userID);
		goalListParam.put("userID", userID);
		
		return sqlSession.selectOne(namespace+".readGoalCnt", goalListParam);
	}
}


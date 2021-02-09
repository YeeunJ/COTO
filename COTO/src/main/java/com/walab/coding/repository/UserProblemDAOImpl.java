package com.walab.coding.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.model.UserProblemDTO;

@Repository
public class UserProblemDAOImpl implements UserProblemDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace = "userProblem";
	
	public List<UserProblemDTO> readAll(int userID) {
		Map<String, Object> userProblemListParam = new HashMap<String, Object>();
		userProblemListParam.put("userID", userID);
		
		return sqlSession.selectList(namespace+".readUserProblemList", userProblemListParam);
	}
	
	public int updateProblem(UserProblemDTO upd) {
		
		Map<String, Object> userProblemListParam = new HashMap<String, Object>();
		userProblemListParam.put("memo", upd.getMemo());
		userProblemListParam.put("difficulty", upd.getDifficulty());
		userProblemListParam.put("problemID", upd.getId());
		
		return sqlSession.update(namespace+".updateUserProblem", userProblemListParam);
	}
	
	public int deleteProblem(int userProblemID) {
		Map<String, Object> userProblemListParam = new HashMap<String, Object>();
		userProblemListParam.put("problemID", userProblemID);
		
		return sqlSession.delete(namespace+".deleteUserProblem", userProblemListParam);
	}
	
	public int readSolvedP(int userID) {
		
		Map<String, Object> userSolvedProblemParam = new HashMap<String, Object>();
		userSolvedProblemParam.put("userID", userID);
		
		return sqlSession.selectOne(namespace+".readSovledUserProblem", userSolvedProblemParam);
	}
	
	public List<RankDTO> readTotalRankList() {
		List<RankDTO> rankList = new ArrayList<RankDTO>();
		rankList = sqlSession.selectList(namespace+".readTotalRank");

		return rankList;
	}
	
	public List<RankDTO> readTodayRankList() {
		List<RankDTO> rankList = new ArrayList<RankDTO>();
		rankList = sqlSession.selectList(namespace+".readTodayRank");

		return rankList;
	}

	@Override
	public void createUserProblem(UserProblemDTO p) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("siteID", p.getSiteID());
		param.put("name", p.getProblem());
		param.put("link", p.getLink());
		
		List<ProblemDTO> result = sqlSession.selectList("problem.readProblemByUser", param);
		
		int problemID;
		if(result.size() == 0) {
			
			ProblemDTO newProb = new ProblemDTO();
			newProb.setSiteID(p.getSiteID());
			newProb.setName(p.getProblem());
			newProb.setLink(p.getLink());
			
			sqlSession.insert("problem.createProblem", newProb);
			problemID = sqlSession.selectOne("problem.readProblemCnt");
			problemID++;
			
			System.out.println("problem에 없음! 새로운 problem 만들었음");
		}else {
			problemID = result.get(0).getId();
			System.out.println("problem에 있음 : "+problemID);
		}
		
		p.setProblemID(problemID);
		sqlSession.insert(namespace+".createUserProblem", p);
	
	}

	@Override
	public List<UserProblemDTO> searchProblemByContent(int userID, String searchValue) {
		Map<String, Object> userProblemListParam = new HashMap<String, Object>();
		userProblemListParam.put("userID", userID);
		userProblemListParam.put("content1", searchValue);
		userProblemListParam.put("content2", searchValue);
		userProblemListParam.put("content3", searchValue);
		userProblemListParam.put("content4", searchValue);
		System.out.println(searchValue);
		
		return sqlSession.selectList(namespace+".searchUserProblemByContent", userProblemListParam);
	}

	@Override
	public List<UserProblemDTO> readProblemOrderByCount() {
		return sqlSession.selectList(namespace+".searchProblemOrderByCount");
	}
	
	@Override
	public List<UserProblemDTO> countSolvedProblemEachDay(int userID){
		Map<String, Object> countSolvedProblemParam = new HashMap<String, Object>();
		countSolvedProblemParam.put("userID", userID);
		
		return sqlSession.selectList(namespace+".countUserProblemEachDay", countSolvedProblemParam);
	}
	
	@Override
	public List<UserProblemDTO> readProblemActivities(int userID, int goalID){
		Map<String, Object> readProblemListParam = new HashMap<String, Object>();
		readProblemListParam.put("userID", userID);
		readProblemListParam.put("goalID", goalID);
		
		return sqlSession.selectList(namespace+".readProblemActivities", readProblemListParam);
	}

	@Override
	public List<Map<String, Object>> readAvgForaWeek() {
		return sqlSession.selectList(namespace+".avgForaWeek");
	}

	@Override
	public List<UserProblemDTO> readProblemByPage(int userID, int s_point, int list) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userID", userID);
		param.put("s_point", s_point);
		param.put("list", list);
		
		return sqlSession.selectList(namespace+".readProblemByPage", param);

	}

	@Override
	public int readProblemCnt(int userID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userID", userID);
		
		int count = sqlSession.selectOne(namespace+".readProblemCnt", param);
		return count;
	}
}

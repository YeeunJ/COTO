package com.walab.coding.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.ProblemDTO;

@Repository
public class ProblemDAOImpl implements ProblemDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "problem";
	
	
	@Override
	public int readProblemListCnt(String searchValue, String orderValue, String siteValue) {
		Map<String, Object> ProblemListParam = new HashMap<String, Object>();
		ProblemListParam.put("searchValue", searchValue);
		ProblemListParam.put("orderValue", orderValue);
		ProblemListParam.put("siteValue", siteValue);
		
		return sqlSession.selectOne(namespace+".readProblemCnt", ProblemListParam);
	}
	
	public List<ProblemDTO> readRecentProblem(){
		List<ProblemDTO> recentProblemList = new ArrayList<ProblemDTO>();
		recentProblemList  = sqlSession.selectList(namespace+".readRecentProblem");
		return recentProblemList;
	}
	
	public List<ProblemDTO> searchProblemByContents(int s_point, int list, String searchValue, String orderValue, String siteValue){
		Map<String, Object> ProblemListParam = new HashMap<String, Object>();
		ProblemListParam.put("searchValue", searchValue);
		ProblemListParam.put("orderValue", orderValue);
		ProblemListParam.put("siteValue", siteValue);
		ProblemListParam.put("s_point", s_point);
		ProblemListParam.put("list", list);
		
		return sqlSession.selectList(namespace+".searchProblemList", ProblemListParam);
	}
	
	@Override
	public List<Map<String, Object>> readRatioBySiteid() {
		
		int count = sqlSession.selectOne(namespace+".readAllProblemCnt");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("count", count);
		
		return sqlSession.selectList(namespace+".readRatioBySiteid", param);
	}
	
	
	@Override
	public ProblemDTO readProblembyID(int siteID, String problem) {
		Map<String, Object> ProblemIDParam = new HashMap<String, Object>();
		ProblemIDParam.put("siteID", siteID);
		ProblemIDParam.put("problem", problem);
		System.out.println(problem);
		
		
		return sqlSession.selectOne(namespace+".readProblembyID", ProblemIDParam);
	}
	
	@Override	
	public List<ProblemDTO> readProblem(){
		List<ProblemDTO> problemList = new ArrayList<ProblemDTO>();
		problemList  = sqlSession.selectList(namespace+".readProblem");
		return problemList;
	}
	
	@Override
	public List<ProblemDTO> readOtherUserProblemName(int userID){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userID", userID);
		
		return sqlSession.selectList(namespace+".readOtherUserProblemName", param);
	}

	@Override
	public int readTotalProblemCnt() {
		return sqlSession.selectOne(namespace+".totalProblemCnt");
	}

	@Override
	public List<Map<String, Object>> readCountBySite() {
		return sqlSession.selectList(namespace+".countBySite");
	}
	
}
package com.walab.coding.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.PaginationDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;


@Repository
public class ProblemDAOImpl implements ProblemDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "problem";
	
	
	@Override
	public int readProblemID(int siteID, String problem) {
		Map<String, Object> ProblemIDParam = new HashMap<String, Object>();
		ProblemIDParam.put("siteID", siteID);
		ProblemIDParam.put("name", problem);
		System.out.println(problem);
		
		int problemID = sqlSession.selectOne(namespace+".readProblemID", ProblemIDParam);
		
		return problemID;
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

	public List<ProblemDTO> readRecentProblem(){
		List<ProblemDTO> recentProblemList = new ArrayList<ProblemDTO>();
		recentProblemList  = sqlSession.selectList(namespace+".readRecentProblem");
		return recentProblemList;
	}

	@Override
	public int readProblemListCnt(String searchValue, String orderValue, String siteValue) {
		// 총 게시글 개수 확인
		Map<String, Object> ProblemListParam = new HashMap<String, Object>();
		ProblemListParam.put("searchValue", searchValue);
		ProblemListParam.put("orderValue", orderValue);
		ProblemListParam.put("siteValue", siteValue);
		
		return sqlSession.selectOne(namespace+".readProblemCnt", ProblemListParam);
	}

	public List<ProblemDTO> searchProblemByContents(int s_point, int list, String searchValue, String orderValue, String siteValue){
		Map<String, Object> ProblemListParam = new HashMap<String, Object>();
		ProblemListParam.put("searchValue", searchValue);
		ProblemListParam.put("orderValue", orderValue);
		ProblemListParam.put("siteValue", siteValue);
		ProblemListParam.put("s_point", s_point);
		ProblemListParam.put("list", list);
		System.out.println("hoho");
		System.out.println(searchValue);
		System.out.println(orderValue);
		System.out.println("SiteValue" + siteValue);
		
		
		return sqlSession.selectList(namespace+".searchProblemList", ProblemListParam);
	}
	
	@Override
	public List<ProblemDTO> readProblemByPage(int s_point, int list) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("s_point", s_point);
		param.put("list", list);
		
		return sqlSession.selectList(namespace+".readProblemByPage", param);
	}

	@Override
	public List<Map<String, Object>> readRatioBySiteid() {
		
		int count = sqlSession.selectOne(namespace+".readAllProblemCnt");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("count", count);
		
		return sqlSession.selectList(namespace+".readRatioBySiteid", param);
	}
	
}
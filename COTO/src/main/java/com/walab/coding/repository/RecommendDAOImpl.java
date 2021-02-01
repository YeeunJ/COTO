package com.walab.coding.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.RecomCountDTO;
import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;

@Repository
public class RecommendDAOImpl implements RecommendDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "recommend";
	private List<RecommendDTO> recommendList = new ArrayList<RecommendDTO>();
	
	public List<RecommendDTO> readRecom() {
		
		recommendList = sqlSession.selectList(namespace+".readRecommendList");
		
		return recommendList;
	}
	
	public List<RecomProblemDTO> readRecomProblems(int recomID) {
		List<RecomProblemDTO> recommendProblemsList = new ArrayList<RecomProblemDTO>();
		
		Map<String, Object> recommendProblemsListParam = new HashMap<String, Object>();
		recommendProblemsListParam.put("recomID", recomID);
		
		return recommendProblemsList = sqlSession.selectList(namespace+".readRecommendProblemList", recommendProblemsListParam);
	}
	
	@Override
	public int createRecomProblem(RecommendDTO recommend) {	
		sqlSession.insert(namespace+".createRecomProblem", recommend);
		int recomID = recommend.getId();
		
		return recomID;
	}
	
	public List<RecommendDTO> searchProblemByContents(String searchValue, String orderValue){
		Map<String, Object> recomProblemListParam = new HashMap<String, Object>();
		recomProblemListParam.put("searchValue", searchValue);
		recomProblemListParam.put("orderValue", orderValue);
		System.out.println(searchValue);
		System.out.println(orderValue);
		
		return sqlSession.selectList(namespace+".searchRecommendList", recomProblemListParam);
	}

	@Override
	public int deleteRecom(int recomID) {
		Map<String, Object> recomListParam = new HashMap<String, Object>();
		recomListParam.put("recomID", recomID);
		
		return sqlSession.delete(namespace+".deleteRecom", recomListParam);
	}
}

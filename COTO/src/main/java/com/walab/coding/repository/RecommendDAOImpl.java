package com.walab.coding.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.RecomCountDTO;
import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecomTagDTO;
import com.walab.coding.model.RecommendDTO;

@Repository
public class RecommendDAOImpl implements RecommendDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "recommend";
	private List<RecommendDTO> recommendList = new ArrayList<RecommendDTO>();

	@Override
	public int createRecomProblem(RecommendDTO recommend) {	
		sqlSession.insert(namespace+".createRecomProblem", recommend);
		int recomID = recommend.getId();
		
		return recomID;
	}
	
	@Override
	public List<RecommendDTO> readRecommendList() {
		recommendList = sqlSession.selectList(namespace+".readRecommendList");
		
		return recommendList;
	}
	
	@Override
	public List<RecommendDTO> readRecentRecommendList() {
		List<RecommendDTO> rankList = new ArrayList<RecommendDTO>();
		rankList = sqlSession.selectList(namespace+".readRecentRecommendList");

		return rankList;
	}
	
	@Override
	public RecommendDTO readRecommend(int recomID) {
		Map<String, Object> recommendProblemParam = new HashMap<String, Object>();
		recommendProblemParam.put("recomID", recomID);
		
		return sqlSession.selectOne(namespace+".readRecommendProblem", recommendProblemParam);
	}
	
	@Override
	public List<RecommendDTO> readRecomByPage(String searchValue, String orderValue, int s_point, int list) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("s_point", s_point);
		param.put("list", list);
		param.put("searchValue", searchValue);
		param.put("orderValue", orderValue);
		
		
		return sqlSession.selectList(namespace+".readRecomByPage", param);
	}
	
	@Override
	public int readRecomListCnt() {
		return sqlSession.selectOne(namespace+".readRecomListCnt");
	}
	
	@Override
	public int updateRecommend(RecommendDTO r) {
		Map<String, Object> recommendParam = new HashMap<String, Object>();
		recommendParam.put("id", r.getId());
		recommendParam.put("title", r.getTitle());
		recommendParam.put("difficulty", r.getDifficulty());
		recommendParam.put("content", r.getContent());
		
		return sqlSession.update(namespace+".updateRecommend", recommendParam);
	}
	
	@Override
	public int deleteRecom(int recomID) {
		Map<String, Object> recomListParam = new HashMap<String, Object>();
		recomListParam.put("recomID", recomID);
		
		return sqlSession.delete(namespace+".deleteRecom", recomListParam);
	}
	
	
	
	
	
	
	
	public List<RecomProblemDTO> readRecomProblems(int recomID) {
		List<RecomProblemDTO> recommendProblemsList = new ArrayList<RecomProblemDTO>();
		
		Map<String, Object> recommendProblemsListParam = new HashMap<String, Object>();
		recommendProblemsListParam.put("recomID", recomID);
		
		return recommendProblemsList = sqlSession.selectList(namespace+".readRecommendProblemList", recommendProblemsListParam);
	}
	
	public List<RecommendDTO> searchProblemByContents(String searchValue, String orderValue){
		Map<String, Object> recomProblemListParam = new HashMap<String, Object>();
		recomProblemListParam.put("searchValue", searchValue);
		recomProblemListParam.put("orderValue", orderValue);
		System.out.println(searchValue);
		System.out.println(orderValue);
		
		return sqlSession.selectList(namespace+".searchRecommendList", recomProblemListParam);
	}
}

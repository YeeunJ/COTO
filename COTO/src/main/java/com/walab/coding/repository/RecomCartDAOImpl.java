package com.walab.coding.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.RecomCartDTO;
import com.walab.coding.model.RecommendDTO;

@Repository
public class RecomCartDAOImpl implements RecomCartDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	String namespace = "recomCart";

	
	@Override
	public void createRecomCart(RecomCartDTO cart) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("recomID", cart.getRecomID());
		param.put("userID", cart.getUserID());

		sqlSession.insert(namespace+".createCart", param);
	}
	
	@Override
	public List<RecommendDTO> readCartRecommendList(int userID) {
		List<RecommendDTO> cartRecommendList = new ArrayList<RecommendDTO>();
		
		Map<String, Object> cartParam = new HashMap<String, Object>();
		cartParam.put("userID", userID);
		
		cartRecommendList = sqlSession.selectList(namespace+".readRecomCart", cartParam);

		return cartRecommendList;
	}
	
	
	@Override
	public int readCartByID(int recomID, int userID) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("recomID", recomID);
		param.put("userID", userID);
		
		return sqlSession.selectOne(namespace+".readCartByID", param);
	
	}

	@Override
	public void deleteRecomCart(RecomCartDTO cart) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("recomID", cart.getRecomID());
		param.put("userID", cart.getUserID());
		
		sqlSession.delete(namespace+".deleteCart", param);
		
	}
	
	@Override
	public void deleteRecomProblem(int recomID) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("recomID", recomID);
		
		sqlSession.delete(namespace+".deleteCartProblem", param);
		
	}

	@Override
	public int readTotalProbCnt(int recomID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("recomID", recomID);
		return sqlSession.selectOne(namespace+".readTotalProbCnt", param);
	}

	@Override
	public int readUserProbCnt(int userID, int recomID) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userID", userID);
		param.put("recomID", recomID);
		
		return sqlSession.selectOne(namespace+".readUserProbCnt", param);
		
	}
	
	@Override
	public int readCartByRecommend(int recomID, int userID) {
		int result = 0;

		return result;
	}



}

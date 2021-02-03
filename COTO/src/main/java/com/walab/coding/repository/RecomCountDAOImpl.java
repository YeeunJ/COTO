package com.walab.coding.repository;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.RecomCountDTO;
import com.walab.coding.model.RecommendDTO;

@Repository
public class RecomCountDAOImpl implements RecomCountDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int readRecomCount(RecommendDTO recommend, int index) {
		
		Map<String, Object> recommendCountListParam = new HashMap<String, Object>();
		recommendCountListParam.put("recomID", recommend.getId());
		
		return sqlSession.selectOne("recomCount.readRecommendCount", recommendCountListParam);
	}
	
	public int createRecomCount(RecomCountDTO rcd) {
		Map<String, Object> recommendCountParam = new HashMap<String, Object>();
		recommendCountParam.put("recomID", rcd.getRecomID());
		recommendCountParam.put("userID", rcd.getUserID());
		
		return sqlSession.selectOne("recomCount.createRecommendCount", recommendCountParam);
	}
	
	public int readRecomCount(int recomID) {
		
		Map<String, Object> recommendCountListParam = new HashMap<String, Object>();
		recommendCountListParam.put("recomID", recomID);
		
		return sqlSession.selectOne("recomCount.readRecommendCount", recommendCountListParam);
	}
	
	public int readRecomCountYNbyUserID(int recomID, int userID) {
		Map<String, Object> recommendCountListParam = new HashMap<String, Object>();
		recommendCountListParam.put("recomID", recomID);
		recommendCountListParam.put("userID", userID);
		
		return sqlSession.selectOne("recomCount.readRecommendCountYNbyUserID", recommendCountListParam);
	}
	
}

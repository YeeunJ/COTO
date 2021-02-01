package com.walab.coding.repository;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}

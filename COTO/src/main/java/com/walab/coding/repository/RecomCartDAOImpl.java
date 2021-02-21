package com.walab.coding.repository;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.RecomCartDTO;

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

}

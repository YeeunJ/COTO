package com.walab.coding.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.RecomTagDTO;

@Repository
public class RecomTagDAOImpl implements RecomTagDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "tag";
	
	@Override
	public void createTag(RecomTagDTO t) {
		sqlSession.insert(namespace + ".createTag", t);
	}
}

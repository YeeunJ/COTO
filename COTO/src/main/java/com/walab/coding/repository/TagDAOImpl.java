package com.walab.coding.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.TagDTO;

@Repository
public class TagDAOImpl implements TagDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "tag";
	
	@Override
	public void createTag(TagDTO t) {
		sqlSession.insert(namespace + ".createTag", t);
	}
}

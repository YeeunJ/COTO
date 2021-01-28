package com.walab.coding.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.RecomCommentDTO;

@Repository("recomCommentDAO")
public class RecomCommentDAOImpl implements RecomCommentDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public List<Map<String, String>> readAll(Map<String, Object> param) {
		List<Map<String, String>> commentList;
		commentList = sqlSession.selectList("recomComment.readRecomComment", param);

		return commentList;
	}

	@Override
	public List<Map<Integer, Integer>> readCommentCount() {
		
		List<Map<Integer, Integer>> commentCount = sqlSession.selectList("recomComment.readCommentCount");
		return commentCount;
		
	}

	@Override
	public void createComment(RecomCommentDTO r) {
		
		sqlSession.insert("recomComment.createComment", r);
		
	}
	
}

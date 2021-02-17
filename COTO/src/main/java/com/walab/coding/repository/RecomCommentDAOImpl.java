package com.walab.coding.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.RecomCommentDTO;
import com.walab.coding.model.RecommendDTO;

@Repository("recomCommentDAO")
public class RecomCommentDAOImpl implements RecomCommentDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void createComment(RecomCommentDTO r) {
		
		sqlSession.insert("recomComment.createComment", r);
		
	}
	
	@Override
	public List<Map<String, Object>> readAll(Map<String, Object> param) {
		List<Map<String, Object>> commentList;
		commentList = sqlSession.selectList("recomComment.readRecomComment", param);

		return commentList;
	}

	@Override
	public List<Map<Integer, Integer>> readCommentCount() {
		
		List<Map<Integer, Integer>> commentCount = sqlSession.selectList("recomComment.readCommentCount");
		return commentCount;
		
	}
	
	@Override
	public int readRecomCommentCount(int recomID) {
		return sqlSession.selectOne("recomComment.readRecomCommentCount", recomID);
	}

	
	@Override
	public int deleteRecomComment(int recomID) {
		Map<String, Object> recomCommentListParam = new HashMap<String, Object>();
		recomCommentListParam.put("recomID", recomID);
		
		return sqlSession.delete("recomComment.deleteRecomComment", recomCommentListParam);
	}
	
	
}

package com.walab.coding.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.RecomTagDTO;
import com.walab.coding.model.UserProblemDTO;

@Repository
public class RecomTagDAOImpl implements RecomTagDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "tag";
	
	@Override
	public void createTag(RecomTagDTO t) {
		sqlSession.insert(namespace + ".createTag", t);
	}
	
	@Override
	public List<RecomTagDTO> readTagOrderByCount() {
		return sqlSession.selectList(namespace+".searchTagOrderByCount");
	}
	
	@Override
	public List<RecomTagDTO> readProblemTag() {
		return sqlSession.selectList(namespace+".readProblemTagList");
	}
	
	@Override
	public int deleteRecomTag(int recomID) {
		Map<String, Object> recomTagListParam = new HashMap<String, Object>();
		recomTagListParam.put("recomID", recomID);
		
		return sqlSession.delete(namespace+".deleteRecomTag", recomTagListParam);
	}
	
	@Override
	public int updateTag(RecomTagDTO rt) {
		Map<String, Object> recomTagParam = new HashMap<String, Object>();
		recomTagParam.put("recomID", rt.getRecomID());
		recomTagParam.put("tag", rt.getTag());
		
		return sqlSession.update(namespace+".updateRecomTag", recomTagParam);
	}
}

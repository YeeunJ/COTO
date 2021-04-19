package com.walab.coding.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.ProblemDTO;
import com.walab.coding.model.RecomProblemDTO;


@Repository
public class RecomProblemDAOImpl implements RecomProblemDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "recomProblems";
	
	@Override
	public void createRecomProblem(RecomProblemDTO rp) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("siteID", rp.getSiteID());
		param.put("name", rp.getName());
		param.put("link", rp.getLink());
		
		List<ProblemDTO> result = sqlSession.selectList("problem.readProblemByUser", param);
		
		int problemID;
		if(result.size() == 0) {
			
			ProblemDTO newProb = new ProblemDTO();
			newProb.setSiteID(rp.getSiteID());
			newProb.setName(rp.getName());
			newProb.setLink(rp.getLink());
			
			sqlSession.insert("problem.createProblem", newProb);
			problemID = sqlSession.selectOne("problem.readMaxProblem");
		}else {
			problemID = result.get(0).getId();
		}
		
		rp.setProblemID(problemID);
		sqlSession.insert(namespace + ".createRecomProblem", rp);
	}
	
	@Override
	public List<RecomProblemDTO> readProblemList() {
		List<RecomProblemDTO> recommendProblemList = new ArrayList<RecomProblemDTO>();
		recommendProblemList  = sqlSession.selectList(namespace+".readRecommendProblemList");
		
		return recommendProblemList;
	}
	
	@Override
	public List<RecomProblemDTO> readProblemByID(int recomID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("recomID", recomID);
		
		return sqlSession.selectList(namespace+".readProblemByID", param);
	}
	
	public RecomProblemDTO readEachProblem(int rpID, int userID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("rpID", rpID);
		param.put("userID", userID);
		return sqlSession.selectOne(namespace+".readEachProblem", param);
	}
	
	@Override
	public int deleteRecomProblem(int recomID) {
		Map<String, Object> recomProblemListParam = new HashMap<String, Object>();
		recomProblemListParam.put("recomID", recomID);
		
		return sqlSession.delete(namespace+".deleteRecomProblem", recomProblemListParam);
	}
	

	
	
	@Override
	public int readProblemID(int siteID, String problem) {
		Map<String, Object> ProblemIDParam = new HashMap<String, Object>();
		ProblemIDParam.put("siteID", siteID);
		ProblemIDParam.put("name", problem);
		int problemID = sqlSession.selectOne("problem.readProblemID", ProblemIDParam);
		
		return problemID;
	}
}

package com.walab.coding.repository;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.RecomProblemDTO;

@Repository
public class RecomProblemDAOImpl implements RecomProblemDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "recomProblems";
	
	@Override
	public void createRecomProblem(RecomProblemDTO rp) {
		sqlSession.insert(namespace + ".createRecomProblem", rp);
	}
	
	@Override
	public int readProblemID(int siteID, String problem) {
		Map<String, Object> ProblemIDParam = new HashMap<String, Object>();
		ProblemIDParam.put("siteID", siteID);
		ProblemIDParam.put("name", problem);
		System.out.println(problem);
		
		int problemID = sqlSession.selectOne("problem.readProblemID", ProblemIDParam);
		
		return problemID;
	}
}

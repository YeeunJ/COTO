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
public class ProblemDAOImpl implements ProblemDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "problem";
	
	
	@Override
	public int readProblemID(int siteID, String problem) {
		Map<String, Object> ProblemIDParam = new HashMap<String, Object>();
		ProblemIDParam.put("siteID", siteID);
		ProblemIDParam.put("name", problem);
		System.out.println(problem);
		
		int problemID = sqlSession.selectOne(namespace+".readProblemID", ProblemIDParam);
		
		return problemID;
	}
	
	@Override	
	public List<ProblemDTO> readProblem(){
		List<ProblemDTO> problemList = new ArrayList<ProblemDTO>();
		problemList  = sqlSession.selectList(namespace+".readProblem");
		return problemList;
	}
	
}
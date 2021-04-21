package com.walab.coding.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.GroupGoalDTO;
import com.walab.coding.model.GroupUserDTO;
import com.walab.coding.model.ProblemDTO;

@Repository
public class GroupGoalDAOImpl implements GroupGoalDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "groupGoal";
	
	@Override
	public void createGoal(String startDate, String endDate, int groupID) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("groupID", groupID);
		
		sqlSession.insert(namespace+".createGroupGoal", param);
		
	}

	@Override
	public int readGoalID() {
		return sqlSession.selectOne(namespace+".readGroupGoalID");
	}

	@Override
	public void createGoalProblem(int goalID, ProblemDTO p) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("siteID", p.getSiteID());
		param.put("name", p.getName());
		param.put("link", p.getLink());
		
		List<ProblemDTO> result = sqlSession.selectList("problem.readProblemByUser", param);
		
		int problemID;
		System.out.println("problem 안 count: "+result.size());
		
		if(result.size() == 0) {
			
			
			sqlSession.insert("problem.createProblem", p);
			problemID = sqlSession.selectOne("problem.readMyLastInsertProblem");
			
			System.out.println("problem에 없음! 새로운 problem 만들었음");
		}else {
			problemID = result.get(0).getId();
			System.out.println("problem에 있음 : "+problemID);
		}
		
		Map<String, Object> groupP = new HashMap<String, Object>();
		groupP.put("goalID", goalID);
		groupP.put("problemID", problemID);
		
		sqlSession.insert(namespace+".createGroupProblem", groupP);
	
		
	}
	
	@Override
	public List<GroupGoalDTO> readGoalListByGroupId(int groupID) {
		return sqlSession.selectList(namespace+".readGoalListByGroupId", groupID);
	}

	@Override
	public List<GroupUserDTO> progressByUser(int groupID) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("groupID", groupID);
		return sqlSession.selectList(namespace+".progressByUser", param);
	}

}

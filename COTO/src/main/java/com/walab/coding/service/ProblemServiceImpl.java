package com.walab.coding.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.PaginationDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.repository.ProblemDAO;
import com.walab.coding.repository.RecommendDAO;

@Service
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	ProblemDAO problemDAO;

	@Override
	public int readProblemListCnt(String searchValue, String orderValue, String siteValue) {
		return problemDAO.readProblemListCnt(searchValue, orderValue, siteValue);
	}

	@Override
	public List<ProblemDTO> readRecentProblem() {
		return problemDAO.readRecentProblem();
	}
	
	@Override
	public List<ProblemDTO> search(int s_point, int list, String searchValue, String orderValue, String siteValue) {
		searchValue = "%".concat(searchValue).concat("%");
		if(orderValue == null)
			orderValue ="problem.regdate desc";
		
		List<ProblemDTO> problems = problemDAO.searchProblemByContents(s_point, list, searchValue, orderValue, siteValue);
		
		return problems;
	}
	
	
	@Override
	public List<Map<String, Object>> readRatioBySiteid() {
		return problemDAO.readRatioBySiteid();
	}

	@Override
	public List<Map<String, Object>> makeRatioBySiteid(List<Map<String, Object>> ratioBySite,
			List<CodingSiteDTO> codingSite) {
		
		List<Map<String,Object>> ratio = new ArrayList<Map<String,Object>>();
		
		for(int i=0 ; i<codingSite.size() ; i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			Object siteID = (Object)codingSite.get(i).getId();
			Object siteName = (Object)codingSite.get(i).getSiteName();
			map.put("siteID", siteID);
			map.put("siteName", siteName);
			map.put("ratio", 0);
			for(int j=0 ; j<ratioBySite.size(); j++) {
				if(siteID == ratioBySite.get(j).get("siteID")) {
					map.put("ratio", ratioBySite.get(j).get("ratio"));
				}
			}
			ratio.add(map);
		}
		
		return ratio;
	}
	
}

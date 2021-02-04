package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.repository.RecomCommentDAO;
import com.walab.coding.repository.RecomCountDAO;
import com.walab.coding.repository.RecommendDAO;

@Service
public class RecommendServiceImpl implements RecommendService {

	@Autowired
	RecommendDAO recommendDAO;
	@Autowired
	RecomCountDAO recomCountDAO;
	@Autowired
	RecomCommentDAO recomCommentDAO;
	
	@Override
	public List<RecommendDTO> readRecom() {
		List<RecommendDTO> recoms = recommendDAO.readRecom();
		
		for(int i=0;i<recoms.size();i++) {
			recoms.get(i).setRecomCount(recomCountDAO.readRecomCount(recoms.get(i).getId()));
			
			int recomID = recoms.get(i).getId();
			recoms.get(i).setRecomCommentCount(recomCommentDAO.readRecomCommentCount(recomID));
		}
		
		return recoms;
	}
	
	@Override
	public List<RecomProblemDTO> readRecomProblems(int recomID) {
		List<RecomProblemDTO> recomProblems = recommendDAO.readRecomProblems(recomID);
		
		return recomProblems;
	}
	
	@Override
	public int createRecomProblem(RecommendDTO recommend) {
		int recomID = recommendDAO.createRecomProblem(recommend);
		
		return recomID;
	}
	
	@Override
	public List<RecommendDTO> search(String searchValue, String orderValue) {
		searchValue = "%".concat(searchValue).concat("%");
		if(orderValue == null)
			orderValue ="regdate";
		return recommendDAO.searchProblemByContents(searchValue, orderValue);
	}
	
	@Override
	public int deleteRecom(int recomID) {
		return recommendDAO.deleteRecom(recomID);
	}
	
	@Override
	public int updateRecommend(RecommendDTO r) {
		return recommendDAO.updateRecommend(r);
	}
}

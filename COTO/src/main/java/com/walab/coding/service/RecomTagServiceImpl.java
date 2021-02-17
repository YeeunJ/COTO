package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomTagDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.repository.RecomTagDAO;

@Service
public class RecomTagServiceImpl implements RecomTagService {

	@Autowired
	RecomTagDAO tagDAO;

	/**
	 * RecommendController
	 * 
	 */
	@Override
	public void createTag(List<RecomTagDTO> recomTags) {
		for(RecomTagDTO t : recomTags) {
			tagDAO.createTag(t);
		}
	}

	@Override
	public List<RecomTagDTO> readTagList() {
		return tagDAO.readTagOrderByCount();
	}
	
	/**
	 * RecommendController
	 * 
	 */
	@Override
	public List<RecomTagDTO> readProblemTag() {
		return tagDAO.readProblemTag();
	}
	
	/**
	 * RecommendController
	 * 
	 */
	@Override
	public int deleteRecomTag(int recomID) {
		return tagDAO.deleteRecomTag(recomID);
	}
	
	@Override
	public int updateTag(List<RecomTagDTO> rt) {
		for(RecomTagDTO t : rt) {
			int sh = tagDAO.updateTag(t);
			
			if(sh == 0) return 0;
		}
		
		return 1;
	}

	/**
	 * RecommendController
	 * 
	 */
	@Override
	public List<RecomTagDTO> readTagByID(int recomID) {
		return tagDAO.readTagByID(recomID);
	}
}

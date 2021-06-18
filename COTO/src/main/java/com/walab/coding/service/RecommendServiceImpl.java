package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.repository.RecomCartDAO;
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
	@Autowired
	RecomCartDAO recomCartDAO;
	/**
	 * Create recommend zip
	 * usage: RecommendController
	 */
	@Override
	public int createRecomProblem(RecommendDTO recommend) {
		int recomID = recommendDAO.createRecomProblem(recommend);
		
		return recomID;
	}
	
	/**
	 * Read recommend zip list
	 * usage: RecommendController
	 */
	@Override
	public List<RecommendDTO> readRecommendList() {
		List<RecommendDTO> recoms = recommendDAO.readRecommendList();
		
		for(int i=0;i<recoms.size();i++) {
			recoms.get(i).setRecomCount(recomCountDAO.readRecomCount(recoms.get(i).getId()));
			
			int recomID = recoms.get(i).getId();
			recoms.get(i).setRecomCommentCount(recomCommentDAO.readRecomCommentCount(recomID));
		}
		
		return recoms;
	}

	
	//일단 혹시 몰라서 주석 처리 
	/*@Override
	public List<RecomProblemDTO> readRecomProblems(int recomID) {
		List<RecomProblemDTO> recomProblems = recommendDAO.readRecomProblems(recomID);
		
		return recomProblems;
	}*/
	
	/**
	 * read the recommend list that uploaded recently
	 * usage: HomeController
	 */
	@Override
	public List<RecommendDTO> readRecentRecommendList() {
		List<RecommendDTO> result = recommendDAO.readRecentRecommendList();
		return result;
	}
	
	/**
	 * Read recommend zip of specific recomID
	 * usage: RecommendController
	 */
	public RecommendDTO readRecommend(int recomID) {
		RecommendDTO recommend = recommendDAO.readRecommend(recomID);
		
		recommend.setRecomCount(recomCountDAO.readRecomCount(recommend.getId()));
		recommend.setRecomCommentCount(recomCommentDAO.readRecomCommentCount(recomID));
		
		return recommend;
	}
	
	/**
	 * Read recommend zip by page
	 * usage: RecommendController
	 */
	@Override
	public List<RecommendDTO> readRecomByPage(String searchValue, String orderValue, List<String> tagValue, int s_point, int list) {
		List<RecommendDTO> recoms = recommendDAO.readRecomByPage(searchValue, orderValue, tagValue, s_point, list);
		
		for(int i=0;i<recoms.size();i++) {
			recoms.get(i).setRecomCount(recomCountDAO.readRecomCount(recoms.get(i).getId()));
			
			int recomID = recoms.get(i).getId();
			recoms.get(i).setRecomCommentCount(recomCommentDAO.readRecomCommentCount(recomID));
		}
		
		return recoms;
	}
	
	/**
	 * Read recommend zip by page & tag
	 * usage: RecommendController
	 */
	@Override
	public List<RecommendDTO> readRecomByPageTag(String searchValue, String orderValue, List<String> tagValue, int s_point, int list, String tagName) {
		List<RecommendDTO> recoms = recommendDAO.readRecomByPageTag(searchValue, orderValue, tagValue, s_point, list, tagName);

		for(int i=0;i<recoms.size();i++) {
			recoms.get(i).setRecomCount(recomCountDAO.readRecomCount(recoms.get(i).getId()));

			int recomID = recoms.get(i).getId();
			recoms.get(i).setRecomCommentCount(recomCommentDAO.readRecomCommentCount(recomID));
		}

		return recoms;
	}
	
	/**
	 * Read recommend zip count
	 * usage: RecommendController
	 */
	@Override
	public int readRecomListCnt() {
		return recommendDAO.readRecomListCnt();
	}
	
	/**
	 * Update recommend zip of specific recomID
	 * usage: RecommendController
	 */
	@Override
	public int updateRecommend(RecommendDTO r) {
		return recommendDAO.updateRecommend(r);
	}
	
	/**
	 * Delete recommend zip of specific recomID
	 * usage: RecommendController
	 */
	@Override
	public int deleteRecom(int recomID) {
		recomCartDAO.deleteRecomProblem(recomID);
		return recommendDAO.deleteRecom(recomID);
	}
	
}

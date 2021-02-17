package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.repository.RecomProblemDAO;

@Service
public class RecomProblemServiceImpl implements RecomProblemService {

	@Autowired
	RecomProblemDAO recomProblemsDAO;

	/**
	 * Create recommend problems for specific recommend zip
	 * usage: RecommendController
	 */
	@Override
	public void createRecomProblem(List<RecomProblemDTO> recomprobs) {
		for(RecomProblemDTO rp : recomprobs) {
			recomProblemsDAO.createRecomProblem(rp);
		}
	}
	
	//일단 혹시 몰라서 주석 처리 
	/*@Override
	public int readProblemID(int siteID, String problem) {
		return recomProblemsDAO.readProblemID(siteID, problem);
	}*/
	
	/**
	 * Read problem list for all recommend zip
	 * usage: RecommendController
	 */
	@Override
	public List<RecomProblemDTO> readProblemList() {
		List<RecomProblemDTO> recomProblems = recomProblemsDAO.readProblemList();
		
		return recomProblems;
	}
	
	/**
	 * Read problem list of specific recomID
	 * usage: RecommendController
	 */
	@Override
	public List<RecomProblemDTO> readProblemByID(int recomID) {

		return recomProblemsDAO.readProblemByID(recomID);
	}
	
	/**
	 * Read problem solved by the user
	 * usage: RecommendController
	 */
	public RecomProblemDTO readEachProblem(int rpID, int userID) {
		return recomProblemsDAO.readEachProblem(rpID, userID);
	}
	
	/**
	 * Delete problems of specific recomID
	 * usage: RecommendController
	 */
	@Override
	public int deleteRecomProblem(int recomID) {
		return recomProblemsDAO.deleteRecomProblem(recomID);
	}
}

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

	@Override
	public void createRecomProblem(List<RecomProblemDTO> recomprobs) {
		for(RecomProblemDTO rp : recomprobs) {
			recomProblemsDAO.createRecomProblem(rp);
		}
	}
	
	@Override
	public int readProblemID(int siteID, String problem) {
		return recomProblemsDAO.readProblemID(siteID, problem);
	}
	
	@Override
	public List<RecomProblemDTO> readProblem() {
		List<RecomProblemDTO> recomProblems = recomProblemsDAO.readProblem();
		
		return recomProblems;
	}
	
	@Override
	public int deleteRecomProblem(int recomID) {
		return recomProblemsDAO.deleteRecomProblem(recomID);
	}
}

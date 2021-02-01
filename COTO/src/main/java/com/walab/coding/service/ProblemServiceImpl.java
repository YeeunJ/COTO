package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.PaginationDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.repository.ProblemDAO;
import com.walab.coding.repository.RecommendDAO;

@Service
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	ProblemDAO problemDAO;
	
	@Override
	public List<ProblemDTO> readProblems() {
		List<ProblemDTO> problems = problemDAO.readProblem();
		return problems;
	}

	@Override
	public int readProblemListCnt() {
		return problemDAO.readProblemListCnt();
	}

	@Override
	public List<ProblemDTO> readProblemByPage(PaginationDTO page) {
		return problemDAO.readProblemByPage(page);
	}
	
}

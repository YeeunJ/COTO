package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomProblemsDTO;
import com.walab.coding.repository.RecomProblemsDAO;

@Service
public class RecomProblemsServiceImpl implements RecomProblemsService {

	@Autowired
	RecomProblemsDAO recomProblemsDAO;

	@Override
	public void createRecomProblem(List<RecomProblemsDTO> recomprobs) {
		for(RecomProblemsDTO rp : recomprobs) {
			recomProblemsDAO.createRecomProblem(rp);
		}
	}
}

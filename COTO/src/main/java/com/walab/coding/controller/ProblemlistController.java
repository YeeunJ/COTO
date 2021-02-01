package com.walab.coding.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.PaginationDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.service.ProblemService;

/**
 * Handles requests for the application RecommendProblems page.
 */

@Controller
@RequestMapping(value = "/problemList")
public class ProblemlistController {
	

	@Autowired
	ProblemService problemService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewProblems(ModelAndView mv,
			@RequestParam(required=false, defaultValue="1") int page,
			@RequestParam(required=false, defaultValue="1") int range) {
		
		int listCnt = problemService.readProblemListCnt();
		System.out.println(page+"//"+range+"//"+listCnt);
		
		//pagination 객체 생성
		PaginationDTO pagination = new PaginationDTO();
		pagination.pageInfo(page, range, listCnt);
		System.out.println(pagination.toString());
		
		List<ProblemDTO> problemList = problemService.readProblems();
		mv.addObject("pagination", pagination);
		
		mv.addObject("problems", problemList);
		mv.setViewName("problemList");
		
		return mv;
	}

	
	
}
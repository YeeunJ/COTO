package com.walab.coding.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.PaginationDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.ProblemService;
import com.walab.coding.service.UserProblemService;

/**
 * Handles requests for the application RecommendProblems page.
 */

@Controller
@RequestMapping(value = "/problemList")
public class ProblemlistController {
	

	@Autowired
	ProblemService problemService;
	@Autowired
	CodingSiteService codingSiteService;
	@Autowired
	UserProblemService userProblemService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewProblems(ModelAndView mv) {
		
		List<ProblemDTO> problemList = problemService.readProblems();
		List<Map<String,Object>> ratioBySite = problemService.readRatioBySiteid();
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		List<Map<String,Object>> ratio = problemService.makeRatioBySiteid(ratioBySite, codingSite);
		List<Map<String,Object>> average = userProblemService.readAvgForaWeek();
	
		
		mv.addObject("codingSite", codingSite);
		mv.addObject("problems", problemList);
		mv.addObject("ratio", ratio);
		mv.addObject("averageForWeek", average);
		
		mv.setViewName("problemList");
		
		return mv;
	}
	
	@RequestMapping(value = "/getProblemList", method = RequestMethod.GET)
	public ModelAndView getProblemList(ModelAndView mv,
			@RequestParam(required=false, defaultValue="1") int page,
			@RequestParam(required=false, defaultValue="1") int range) {
		
		int listCnt = problemService.readProblemListCnt();
		System.out.println(page+"//"+range+"//"+listCnt);
		
		//pagination 객체 생성
		PaginationDTO pagination = new PaginationDTO();
		pagination.pageInfo(page, range, listCnt);
		System.out.println(pagination.toString());
		
		mv.addObject("pagination", pagination);
		mv.addObject("problems", problemService.readProblemByPage(pagination));
		
		mv.setViewName("ajaxContent/problemListByPageContent");
		
		return mv;
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProblem(HttpServletRequest httpServletRequest) {		
		
//		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
		String searchValue= httpServletRequest.getParameter("searchValue");
		String orderValue= httpServletRequest.getParameter("orderValue");
		String siteValue= httpServletRequest.getParameter("siteValue");
		
		List<ProblemDTO> problems = problemService.search(searchValue, orderValue, siteValue);
		System.out.println(searchValue);
		System.out.println(orderValue);
		System.out.println(siteValue);
		System.out.println("datacnt: " + problems.size());
		ModelAndView mv = new ModelAndView();
		mv.addObject("problems", problems);
		mv.setViewName("ajaxContent/problemListContent");
		
		return mv;
	}

	
	
}
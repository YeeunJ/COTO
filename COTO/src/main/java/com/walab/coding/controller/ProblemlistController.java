package com.walab.coding.controller;


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
import com.walab.coding.model.ProblemDTO;
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
	public ModelAndView viewProblems(ModelAndView mv, 
										@RequestParam(value="page", defaultValue="1") int page) {
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<Map<String,Object>> ratioBySite = problemService.readRatioBySiteid();
		List<Map<String,Object>> ratio = problemService.makeRatioBySiteid(ratioBySite, codingSite);
		List<Map<String,Object>> average = userProblemService.readAvgForaWeek();
	
		mv.addObject("codingSite", codingSite);
		mv.addObject("ratio", ratio);
		mv.addObject("averageForWeek", average);
		
		mv.setViewName("problemList");
		
		return mv;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProblem(HttpServletRequest httpServletRequest,
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="searchValue", defaultValue="") String searchValue,
			@RequestParam(value="orderValue", defaultValue="") String orderValue,
			@RequestParam(value="siteValue", defaultValue="") String siteValue) {		
		
		
		// pagination
		int listCnt = problemService.readProblemListCnt(searchValue, orderValue, siteValue); // 총 문제의 개수
		int list = 10; // 페이지 당 데이터 수
		int block = 10; // 블록 당 페이지 수
		
		System.out.println("listCnt: "+listCnt);
		
		int pageNum = (int) Math.ceil((float)listCnt/list); // 총 페이지
		int nowBlock = (int)Math.ceil((float)page/block); // 현재 페이지가 위치한 블록 번호
		int s_point = (page-1)*list;
		
		int s_page = nowBlock*block - (block-1);
		if (s_page <= 1) {
			s_page = 1;
		}
		int e_page = nowBlock*block;
		if (pageNum <= e_page) {
			e_page = pageNum;
		}
		
		List<ProblemDTO> problems = problemService.search(s_point, list, searchValue, orderValue, siteValue);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		mv.addObject("problems", problems);

		System.out.println("datacnt: " + problems.size());
		
		mv.setViewName("ajaxContent/problemListContent");
		
		return mv;
	}

	
	
}
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
	public ModelAndView viewProblems(ModelAndView mv, 
			@RequestParam(value="page", defaultValue="1") int page) {

		
//		List<ProblemDTO> problemList = problemService.readProblems();
		List<Map<String,Object>> ratioBySite = problemService.readRatioBySiteid();
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		List<Map<String,Object>> ratio = problemService.makeRatioBySiteid(ratioBySite, codingSite);
		List<Map<String,Object>> average = userProblemService.readAvgForaWeek();
		
		// pagination
		int listCnt = problemService.readProblemListCnt(); // 총 문제의 개수
		int list = 15; // 페이지 당 데이터 수
		int block = 10; // 블록 당 페이지 수
		
		int pageNum = (int) Math.ceil((float)listCnt/list); // 총 페이지
		int blockNum = (int)Math.ceil((float)pageNum/block); // 총 블록
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
		
		List<ProblemDTO> problems = problemService.readProblemByPage(s_point, list);
		
		mv.addObject("pagename", "problemList");
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		
		
		mv.addObject("codingSite", codingSite);
		mv.addObject("problems", problems);
		mv.addObject("ratio", ratio);
		mv.addObject("averageForWeek", average);
		
		mv.setViewName("problemList");
		
		return mv;
	}
	
	@RequestMapping(value = "/getProblemList", method = RequestMethod.GET)
	public ModelAndView getProblemList(ModelAndView mv,
			@RequestParam(required=false, defaultValue="1") int page) {

		int listCnt = problemService.readProblemListCnt(); // 총 문제의 개수
		int list = 10; // 페이지 당 데이터 수
		int block = 5; // 블록 당 페이지 수
		
		int pageNum = (int) Math.ceil(listCnt/list); // 총 페이지
		int blockNum = (int)Math.ceil(pageNum/block); // 총 블록
		int nowBlock = (int)Math.ceil(page/block); // 현재 페이지가 위치한 블록 번호
		
		int s_page = nowBlock*block - (block-1);
		if (s_page <= 1) {
			s_page = 1;
		}
		int e_page = nowBlock*block;
			if (pageNum <= e_page) {
				e_page = pageNum;
		}
		
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);

			
		mv.setViewName("ajaxContent/pagenationContent");
		
		return mv;
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProblem(HttpServletRequest httpServletRequest) {		
		
//		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
		String searchValue= httpServletRequest.getParameter("searchValue");
		String orderValue= httpServletRequest.getParameter("orderValue");
		
		List<ProblemDTO> problems = problemService.search(searchValue, orderValue);
		System.out.println(searchValue);
		System.out.println(orderValue);
		System.out.println("datacnt: " + problems.size());
		ModelAndView mv = new ModelAndView();
		mv.addObject("problems", problems);
		mv.setViewName("ajaxContent/problemListContent");
		
		return mv;
	}

	
	
}
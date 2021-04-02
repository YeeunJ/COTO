package com.walab.coding.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.GroupDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.GroupInfoService;
import com.walab.coding.service.GroupService;
import com.walab.coding.service.UserProblemService;

/**
 * Handles requests for the application RecommendProblems page.
 */
@Controller
@RequestMapping(value = "/groupList")
public class GrouplistController {
	
	
	@Autowired
	CodingSiteService codingSiteService;
	
	@Autowired
	UserProblemService userProblemService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	GroupInfoService groupInfoService;
	
	/**
	 * Reads coding sites and shows by page
	 */	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewProblems(ModelAndView mv, 
										@RequestParam(value="page", defaultValue="1") int page) {
		
		List<GroupDTO> groups = groupInfoService.search(0, 10, "", "");
		
		System.out.println(groups.toString());
		
		mv.addObject("groups", groups);
		
		mv.setViewName("groupList");
		
		return mv;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchGroup(HttpServletRequest httpServletRequest,
											@RequestParam(value="page", defaultValue="1") int page,
											@RequestParam(value="searchValue", defaultValue="") String searchValue,
											@RequestParam(value="orderValue", defaultValue="") String orderValue) {				
		
		int listCnt = groupInfoService.readGroupListCnt(searchValue, orderValue); 
		int list = 10; 
		int block = 10; 
		int pageNum = (int) Math.ceil((float)listCnt/list);
		int nowBlock = (int)Math.ceil((float)page/block); 
		int s_point = (page-1)*list;
		
		int s_page = nowBlock*block - (block-1);
		if (s_page <= 1) {
			s_page = 1;
		}
		int e_page = nowBlock*block;
		if (pageNum <= e_page) {
			e_page = pageNum;
		}
		
		List<GroupDTO> groups = groupInfoService.search(s_point, list, searchValue, orderValue);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		mv.addObject("groups", groups);
		
		mv.setViewName("ajaxContent/groupListContent");
		
		return mv;
	}
	
}
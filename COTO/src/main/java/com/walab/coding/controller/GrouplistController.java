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
import com.walab.coding.model.GroupDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.GroupService;
import com.walab.coding.service.ProblemService;
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
	
	/**
	 * Reads coding sites and shows by page
	 */	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewProblems(ModelAndView mv, 
										@RequestParam(value="page", defaultValue="1") int page) {
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSitebyYN();
		List<GroupDTO> myGroups = groupService.readAllGroups();
		
		System.out.println(myGroups.toString());

		mv.addObject("codingSite", codingSite);
		mv.addObject("grouplist", myGroups);
		
		mv.setViewName("groupList");
		
		return mv;
	}
	
	
}
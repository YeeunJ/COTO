package com.walab.coding.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.model.GroupDTO;
import com.walab.coding.model.GroupInfoDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.GroupGoalService;
import com.walab.coding.service.GroupInfoService;
import com.walab.coding.service.GroupService;


/**
 * Handles requests for the application Mypage-groups page.
 */

@Controller
@RequestMapping(value = "/mypage/groups")
public class MyGroupsController {

	@Autowired
	CodingSiteService codingSiteService;
  
	@Autowired
	GroupService groupService;
	
	@Autowired
	GroupInfoService groupInfoService;
	@Autowired
	GroupGoalService groupGoalService;

	/**
	 * Read user goal, solvedProblem, codingSite, solvedProblem List
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewProblems(HttpServletRequest httpServletRequest, ModelAndView mv, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page) {

		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();

		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<GroupDTO> myGroups = groupService.readMyGroups(userID);
		List<GroupDTO> adminGroups = groupService.readAdminGroups(userID);
		
		mv.addObject("adminGroups", adminGroups);
		mv.addObject("userID", userID);
		mv.addObject("CodingSite", codingSite);
		mv.addObject("groups", myGroups);
		
		
		/* pagination 
		int listCnt = userProblemService.readProblemCnt(userID); 
		int list = 10; 
		int block = 10;

		int pageNum = (int) Math.ceil((float) listCnt / list); 
		//int blockNum = (int) Math.ceil((float) pageNum / block);
		int nowBlock = (int) Math.ceil((float) page / block);
		int s_point = (page - 1) * list;

		int s_page = nowBlock * block - (block - 1);
		if (s_page <= 1) {
			s_page = 1;
		}
		int e_page = nowBlock * block;
		if (pageNum <= e_page) {
			e_page = pageNum;
		}


		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		 pagination end */
		

		mv.setViewName("mypage/groups");

		return mv;
	}

	

	@RequestMapping(value = "/createGroup", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView createGroup(HttpServletRequest httpServletRequest, ModelAndView mv,
			@RequestParam(value="groupTitle") String groupTitle,
			@RequestParam(value="groupDesc") String groupDesc,
			@RequestParam(value="groupGoal") String groupGoal,
			@RequestParam(value="startDate") String startDate,
			@RequestParam(value="endDate") String endDate,
			@RequestParam(value="probStartDate") String probStartDate,
			@RequestParam(value="probEndDate") String probEndDate,
			@RequestParam(value="users[]") List<String> users,
			@RequestParam(value="siteId[]") List<String> siteId, 
			@RequestParam(value="problem[]") List<String> problem, 
			@RequestParam(value="link[]") List<String> link
			) throws UnsupportedEncodingException, ParseException {
		
		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();
		GroupInfoDTO info = new GroupInfoDTO();
		info.setGroupName(groupTitle);
		info.setGoal(groupGoal);
		info.setGroupDesc(groupDesc);
		info.setStartDate(startDate);
		info.setEndDate(endDate);
		info.setUserID(userID);

		info.toString();
		groupInfoService.createGroupInfo(info);
		int groupID = groupInfoService.readGroupID();
				
		groupInfoService.createGroupUsers(users, groupID);
		groupGoalService.createGoal(probStartDate, probEndDate, groupID);
		
		int goalID = groupGoalService.readGoalID();
		groupGoalService.createGoalProblems(goalID, problem, siteId, link);
		
		
		List<GroupDTO> adminGroups = groupService.readAdminGroups(userID);
		mv.addObject("adminGroups", adminGroups);
		mv.setViewName("ajaxContent/adminGroupContent");
		
		return mv;
	}
	
	
	@GetMapping("/{groupID}")
	@ResponseBody
	public ModelAndView postDeleteFactory(@PathVariable("groupID") int groupID, ModelAndView mv) {
		System.out.println("groupID: "+groupID);
		mv.setViewName("mypage/oneGroup");
		return mv;
	}
	
	
}

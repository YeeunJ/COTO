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
import org.springframework.web.servlet.view.RedirectView;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.model.GroupDTO;
import com.walab.coding.model.GroupGoalDTO;
import com.walab.coding.model.GroupInfoDTO;
import com.walab.coding.model.GroupProblemDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.model.GroupUserDTO;
import com.walab.coding.model.RecomCountDTO;
import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecomTagDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.GroupGoalService;
import com.walab.coding.service.GroupInfoService;
import com.walab.coding.service.GroupProblemService;
import com.walab.coding.service.GroupService;
import com.walab.coding.service.ProblemService;
import com.walab.coding.service.GroupUserService;


/**
 * Handles requests for the application Mypage-groups page.
 */

@Controller
@RequestMapping(value = "")
public class MyGroupsController {

	@Autowired
	CodingSiteService codingSiteService;
  
	@Autowired
	GroupService groupService;
	@Autowired
	GroupInfoService groupInfoService;
	@Autowired
	GroupGoalService groupGoalService;
	@Autowired
	GroupProblemService groupProblemService;
	@Autowired
	GroupUserService groupUserService;
	
	@Autowired
	ProblemService problemService;

	/**
	 * Read user goal, solvedProblem, codingSite, solvedProblem List
	 */
	@RequestMapping(value = "/mypage/groups", method = RequestMethod.GET)
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

	

	@RequestMapping(value = "/mypage/groups/createGroup", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView createGroup(HttpServletRequest httpServletRequest, ModelAndView mv,
			@RequestParam(value="groupTitle") String groupTitle,
			@RequestParam(value="groupDesc") String groupDesc,
			@RequestParam(value="groupGoal") String groupGoal,
			@RequestParam(value="startDate") String startDate,
			@RequestParam(value="endDate") String endDate,
			@RequestParam(value="users[]") List<String> users
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
		//groupGoalService.createGoal(probStartDate, probEndDate, groupID);
		
		//int goalID = groupGoalService.readGoalID();
		//groupGoalService.createGoalProblems(goalID, problem, siteId, link);
		
		
		List<GroupDTO> adminGroups = groupService.readAdminGroups(userID);
		mv.addObject("adminGroups", adminGroups);
		mv.setViewName("ajaxContent/adminGroupContent");
		
		return mv;
	}
	
	@RequestMapping(value = "/mypage/groups/createProblem", method = RequestMethod.POST)
	@ResponseBody
	public String createProblem(HttpServletRequest httpServletRequest, ModelAndView mv,
			@RequestParam(value="probStartDate") String probStartDate,
			@RequestParam(value="groupID") int groupID,
			@RequestParam(value="probEndDate") String probEndDate,
			@RequestParam(value="siteId[]") List<String> siteId, 
			@RequestParam(value="problem[]") List<String> problem, 
			@RequestParam(value="link[]") List<String> link
			) throws UnsupportedEncodingException, ParseException {
		
		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();
		
		// groupID 보내줘야 함
		groupGoalService.createGoal(probStartDate, probEndDate, groupID);
		
		int goalID = groupGoalService.readGoalID();
		groupGoalService.createGoalProblems(goalID, problem, siteId, link);
		
		return "success";
	}
	
	
	
	@RequestMapping(value = "/mypage/eachGroup", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView eachGroup(HttpServletRequest httpServletRequest, ModelAndView mv) {
		
		int groupID = Integer.parseInt(httpServletRequest.getParameter("groupID"));
		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();
		int adminID = groupService.readAdminofGroup(groupID);
		
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<GroupGoalDTO> groupGoal = groupGoalService.readGoalListByGroupId(groupID);
		List<GroupInfoDTO> groupInfo = groupInfoService.readGroupInfoById(groupID);
		List<Map<String,Object>> progressByUser = groupGoalService.progressByUser(groupID);
		
		
		for(int i=0;i<groupGoal.size();i++) {
			List<GroupProblemDTO> groupProb = groupProblemService.readProblemsByGoalId(groupGoal.get(i).getId());
			groupGoal.get(i).setProbCount(groupProb.size());
		}
		
		System.out.println(groupInfo);
		
		mv.addObject("progressByUser", progressByUser);
		mv.addObject("CodingSite", codingSite);
		mv.addObject("userID", userID);
		mv.addObject("adminID", adminID);
		mv.addObject("groupID", groupID);
		mv.addObject("groupGoal", groupGoal);
		mv.addObject("groupInfo", groupInfo);
		mv.setViewName("/mypage/oneGroup");
		
		return mv;
	}
	
	@RequestMapping(value = "/mypage/update", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView eachGroupEdit(HttpServletRequest httpServletRequest, ModelAndView mv) {
		
		int groupID = Integer.parseInt(httpServletRequest.getParameter("id"));
		
		//editInfo
		GroupInfoDTO gid = new GroupInfoDTO();
		gid.setGroupDesc(httpServletRequest.getParameter("desc"));
		gid.setStartDate(httpServletRequest.getParameter("startDate"));
		gid.setEndDate(httpServletRequest.getParameter("endDate"));
		gid.setId(groupID);		
		
		if (groupInfoService.update(gid) > 0) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}
		////
		
		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();
		int adminID = groupService.readAdminofGroup(groupID);
		
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<GroupGoalDTO> groupGoal = groupGoalService.readGoalListByGroupId(groupID);
		List<GroupInfoDTO> groupInfo = groupInfoService.readGroupInfoById(groupID);
		List<Map<String,Object>> progressByUser = groupGoalService.progressByUser(groupID);
		
		
		for(int i=0;i<groupGoal.size();i++) {
			List<GroupProblemDTO> groupProb = groupProblemService.readProblemsByGoalId(groupGoal.get(i).getId());
			groupGoal.get(i).setProbCount(groupProb.size());
		}
		
		System.out.println(groupInfo);
		
		mv.addObject("progressByUser", progressByUser);
		mv.addObject("CodingSite", codingSite);
		mv.addObject("userID", userID);
		mv.addObject("adminID", adminID);
		mv.addObject("groupID", groupID);
		mv.addObject("groupGoal", groupGoal);
		mv.addObject("groupInfo", groupInfo);
		mv.setViewName("/ajaxContent/groupInfoContent");
		
		return mv;
	}
	
	@RequestMapping(value = "/mypage/readModalInfo", method = RequestMethod.POST)
	public ModelAndView readModalInfo(HttpServletRequest request, ModelAndView mv) {

		int goalID = Integer.parseInt(request.getParameter("goalID"));
		int groupID = Integer.parseInt(request.getParameter("groupID"));
		int userID = ((UserDTO) request.getSession().getAttribute("user")).getId();
		
		GroupGoalDTO groupGoal = groupGoalService.readGoalByGroupIdAndGoalId(groupID, goalID);
		//System.out.println("groupGoal: " + groupGoal.getStartDate() + " | " + groupGoal.getEndDate());

		List<GroupProblemDTO> groupProbDetail = groupProblemService.readProblemsByGoalId(goalID);
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		
		for(int i=0;i<groupProbDetail.size();i++) {
			ProblemDTO prob = problemService.readProblembyProblemIDAndUserID(groupProbDetail.get(i).getProblemID(), userID);
			
			groupProbDetail.get(i).setName(prob.getName());
			groupProbDetail.get(i).setLink(prob.getLink());
			groupProbDetail.get(i).setUserDate(prob.getUserDate());
			
			for(int j=0;j<codingSite.size();j++) {
				if(prob.getSiteID() == codingSite.get(j).getId()) {
					groupProbDetail.get(i).setSiteName(codingSite.get(j).getSiteName());
				}
			}
			
			String str = groupProbDetail.get(i).getLink();
			if(str.length() < 5 || !(str.substring(0, 5).equals("https"))) groupProbDetail.get(i).setLink(null);
		}
 
		mv.addObject("groupGoalDetail", groupGoal);
		mv.addObject("groupProbDetail", groupProbDetail);
		mv.setViewName("/ajaxContent/groupDetailModal");

		return mv;
	}
	
	@RequestMapping(value = "/mypage/groups/dropGroup", method = RequestMethod.POST)
	public ModelAndView dropGroup(ModelAndView mv, HttpServletRequest httpServletRequest,
			@RequestParam(value="groupID") int groupID) {
		
		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();
		
		groupService.deleteUser(userID, groupID);
		
//		mv.setViewName("redirect:/");
//
//		return mv; 
		mv.setView(new RedirectView("/", true));
		return mv;

	}
	
	
}

package com.walab.coding.controller;

import java.util.ArrayList;
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
		List<GroupDTO> groupList = new ArrayList<GroupDTO>();
		int n=0, currGroup=myGroups.get(0).getId(), groupUserCnt=0;
		
		/*for(int i=0;i<myGroups.size();i++) {
			if(myGroups.get(i).getIsAdmin()==1) {
				groupList.get(n).setId(myGroups.get(i).getId());
				groupList.get(n).setGroupName(myGroups.get(i).getGroupName());
				groupList.get(n).setGroupGoal(myGroups.get(i).getGroupGoal());
				groupList.get(n).setNickName(myGroups.get(i).getNickName());
			}
			
			if(currGroup != myGroups.get(i).getId()) {
				currGroup = myGroups.get(i).getId();
				groupList.get(n).setUserCnt(groupUserCnt);
				n++;
				groupUserCnt = 0;
			}
			else groupUserCnt++;
		}*/
		
		System.out.println(myGroups.toString());

		mv.addObject("codingSite", codingSite);
		mv.addObject("grouplist", myGroups);
		
		mv.setViewName("groupList");
		
		return mv;
	}
	
	
}
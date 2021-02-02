package com.walab.coding.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.service.GoalService;
import com.walab.coding.service.GoalServiceImpl;
import com.walab.coding.service.UserProblemService;
import com.walab.coding.service.UserService;

/**
 * Handles requests for the application Mypage-activities page.
 */

@Controller
@RequestMapping(value = "/mypage/activities")
public class MyactivitiesController {

	@Autowired
	GoalService goalService;
	
	@Autowired
	UserProblemService userProblemService;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewProblems(HttpServletRequest request, ModelAndView mv, Model model) {
		
		HttpSession session = request.getSession();
		UserDTO ud = (UserDTO) session.getAttribute("user");
		int userID = 0;
		userID = userService.readUserIDByEmail(ud.getEmail());
		session.setAttribute("user", ud);
		System.out.println(userID);
		if(userID > 0) {
			ud.setId(userID);
			session.setAttribute("user", ud);
			mv.setView(new RedirectView("mypage/activities",true));
		}
		
		List<GoalDTO> goalList = goalService.readGoalAll(userID);
	
		mv.addObject("goalList", goalList);
		
		mv.setViewName("mypage/activities");
		
		return mv;
	}
	
	@RequestMapping(value = "/readProblemActivities", method = RequestMethod.POST)
	public ModelAndView readProblemActivities(ModelAndView mv, HttpServletRequest request, HttpServletRequest httpServletRequest) {
		
		HttpSession session = request.getSession();
		UserDTO ud = (UserDTO) session.getAttribute("user");
		int userID = 0;
		userID = userService.readUserIDByEmail(ud.getEmail());
		session.setAttribute("user", ud);
		System.out.println(userID);
		if(userID > 0) {
			ud.setId(userID);
			session.setAttribute("user", ud);
			mv.setView(new RedirectView("mypage/activities",true));
		}
		
		int goalID = Integer.parseInt(httpServletRequest.getParameter("id"));
		
		List<UserProblemDTO> readProblemActivities = userProblemService.readProblemActivities(userID, goalID);
		
		ModelAndView mvNew = new ModelAndView();
		mvNew.addObject("readProblemActivities", readProblemActivities);
		mvNew.setViewName("ajaxContent/activitiesContent");
		
		return mvNew;
	}
}

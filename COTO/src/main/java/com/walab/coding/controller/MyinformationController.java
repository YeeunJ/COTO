package com.walab.coding.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.service.GoalService;
import com.walab.coding.service.GoalServiceImpl;
import com.walab.coding.service.UserService;
import com.walab.coding.service.UserServiceImpl;

/**
 * Handles requests for the application Mypage-informations page.
 */

@Controller
@RequestMapping(value = "/mypage/information")
public class MyinformationController {
	@Autowired
	UserService userService;
	
	@Autowired	
	GoalService goalService;	
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewUsers(HttpServletRequest request, ModelAndView mv) {
		
		int userID = ((UserDTO)request.getSession().getAttribute("user")).getId();
		
		List<UserDTO> users = userService.readUser(userID);
		System.out.println(users.toString());
		mv.addObject("users", users);
		mv.setViewName("mypage/information");
		
		List<GoalDTO> goals = goalService.readGoal(userID);
		System.out.println(goals.toString());
		mv.addObject("goals", goals);
		mv.setViewName("mypage/information");
		
		return mv;
	}

	@RequestMapping(value = "/updateGoal", method = RequestMethod.POST)
	public ModelAndView updateGoal(ModelAndView mv, HttpServletRequest request, HttpServletRequest httpServletRequest) throws ParseException {
		
		int userID = ((UserDTO)request.getSession().getAttribute("user")).getId();
	
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String goal = httpServletRequest.getParameter("goal");
		Date startDate = transFormat.parse(httpServletRequest.getParameter("startDate"));
		Date endDate = transFormat.parse(httpServletRequest.getParameter("endDate"));
		int goalNum = Integer.parseInt(httpServletRequest.getParameter("goalNum"));
				
		java.sql.Date sdate = new java.sql.Date(startDate.getTime());
		java.sql.Date edate = new java.sql.Date(endDate.getTime());

		GoalDTO updateGoal = new GoalDTO();
		updateGoal.setGoal(goal);
		updateGoal.setStartDate(sdate);
		updateGoal.setEndDate(edate);
		updateGoal.setGoalNum(goalNum);
		updateGoal.setId(Integer.parseInt(httpServletRequest.getParameter("id")));

		List<GoalDTO> goals = goalService.readGoal(userID);
		mv.addObject("goals", goals);

		if(goalService.updateGoal(updateGoal) > 0) {
		System.out.println("success");
		System.out.println(updateGoal.getGoal() + updateGoal.getStartDate() + updateGoal.getEndDate() + updateGoal.getGoalNum());

	}else {
		System.out.println("fail");
		System.out.println(updateGoal.getGoal() + updateGoal.getStartDate() + updateGoal.getEndDate());
	}
		
		mv.setViewName("redirect:/mypage/information");
		return mv;

	}
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateInfo(ModelAndView mv, HttpServletRequest request, HttpServletRequest httpServletRequest) {
		
		int userID = ((UserDTO)request.getSession().getAttribute("user")).getId();
		
		UserDTO updateUser = new UserDTO();
		updateUser.setName(httpServletRequest.getParameter("name"));
		updateUser.setNickName(httpServletRequest.getParameter("nickName"));
		updateUser.setIntro(httpServletRequest.getParameter("intro"));
		updateUser.setId(Integer.parseInt(httpServletRequest.getParameter("id")));

		if(userService.updateUser(updateUser) > 0) {
			System.out.println("success");
		}else {
			System.out.println("fail");
			System.out.println(updateUser.getName() + updateUser.getNickName());
		}
		
		List<UserDTO> users = userService.readUser(userID);
		ModelAndView mvNew = new ModelAndView();
		mvNew.addObject("users", users);
		mvNew.setViewName("redirect:/mypage/information");
		
		return mvNew;

	}
}

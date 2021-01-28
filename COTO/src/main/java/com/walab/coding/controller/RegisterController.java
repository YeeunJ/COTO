package com.walab.coding.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.service.UserService;

/**
 * Handles requests for the application RecommendProblems page.
 */

@Controller
public class RegisterController {
	
	@Autowired
	UserService UserService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping(value = "/registerUserinfo", method = RequestMethod.POST)
	public ModelAndView registerUserinfo(ModelAndView mv,HttpServletRequest httpServeletRequest) {
		
		String name = httpServeletRequest.getParameter("name");
		String email = httpServeletRequest.getParameter("email");
		String nickName = httpServeletRequest.getParameter("nickName");
		String intro = httpServeletRequest.getParameter("intro");
		
		UserDTO u = new UserDTO();
		u.setName(name);
		u.setEmail(email);
		u.setNickName(nickName);
		u.setIntro(intro);
		
		int result = UserService.createUserinfo(u);
		
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping(value = "/registerUsergoal", method = RequestMethod.POST)
	public ModelAndView registerUsergoal(ModelAndView mv, HttpServletRequest httpServeletRequest) throws ParseException {
		int userID = 1;
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		String goal = httpServeletRequest.getParameter("goal");
		Date startDate = transFormat.parse(httpServeletRequest.getParameter("startDate"));
		Date endDate = transFormat.parse(httpServeletRequest.getParameter("endDate"));
		int goalNum = Integer.parseInt(httpServeletRequest.getParameter("goalNum"));
		
		System.out.println(startDate + "/" + endDate); 
		
		GoalDTO g = new GoalDTO();
		g.setUserID(userID);
		g.setGoalNum(goalNum);
		g.setGoal(goal);
		g.setStartDate(startDate);
		g.setEndDate(endDate);

		int result = UserService.createUsergoal(g);
		
		mv.setViewName("home");
		return mv;
		
	}
}
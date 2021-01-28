package com.walab.coding.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView viewUsers(ModelAndView mv) {
		
		int userID = 1; //지금 session 처리와 로그인을 안해서 넣어놓은 예시 데이터!! 나중에 session 처리 할께요!!
		
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
//	@RequestMapping(value = "/editok", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView editGoal(ModelAndView mv) {
//		
//		int userID = 1;
//		GoalDTO goal; //지금 session 처리와 로그인을 안해서 넣어놓은 예시 데이터!! 나중에 session 처리 할께요!!
//		
//		goal = goalService.updateGoal(userID);
//		System.out.println(goal.toString());
//		mv.addObject("updateGoal", goal);
//		mv.setViewName("mypage/information");
////		if(goalService.updateGoal(GoalDTO goalDTO)==0)
////		System.out.println("목표 수정 실패");
////		else
////		System.out.println("목표 수정 성공!!");
//		
//		return mv;
//	}	
	
//	@RequestMapping(value="/editok", method = {RequestMethod.GET, RequestMethod.POST})
//	public String editGoal(GoalDTO goalDTO, ModelAndView mv) {
//		mv.addObject("goals", goalService.updateGoal(goalDTO));
////		mv.addObject("startDate", startDate);
////		mv.addObject("endDate", endDate);
//
//		if(goalService.updateGoal(goalDTO)==0)
//				System.out.println("목표 수정 실패");
//		else
//			System.out.println("목표 수정 성공!!");
//		return "redirect:/mypage/information";	
//	}
//	@RequestMapping(value="/editok", method = RequestMethod.POST)
//	public String editGoal(GoalDTO goalDTO) {
//		if(goalService.updateGoal(goalDTO)==0)
//				System.out.println("데이터 수정 실패");
//		else
//			System.out.println("데이터 수정 성공!!");
//		return "redirect:/";
//	}	
	@RequestMapping(value = "/updateGoal", method = RequestMethod.POST)
	public ModelAndView updateGoal(HttpServletRequest httpServletRequest) {
		
		int userID = 1;
		
//		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		

		GoalDTO updateGoal = new GoalDTO();
		updateGoal.setGoal(httpServletRequest.getParameter("goal"));
		updateGoal.setId(Integer.parseInt(httpServletRequest.getParameter("id")));
		try {
			updateGoal.setStartDate(transFormat.parse(httpServletRequest.getParameter("startDate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			updateGoal.setEndDate(transFormat.parse(httpServletRequest.getParameter("endDate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		if(goalService.updateGoal(updateGoal) > 0) {
			System.out.println("success");
		}else {
			System.out.println("fail");
			System.out.println(updateGoal.getGoal() + updateGoal.getStartDate() + updateGoal.getEndDate());
		}
		
		List<GoalDTO> goals = goalService.readGoal(userID);
		ModelAndView mv = new ModelAndView();
		mv.addObject("goals", goals);
		mv.setViewName("redirect:/mypage/information");
		
		return mv;

	}
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateInfo(HttpServletRequest httpServletRequest) {
		
		int userID = 1;
		
		UserDTO updateUser = new UserDTO();
		updateUser.setName(httpServletRequest.getParameter("name"));
		updateUser.setNickName(httpServletRequest.getParameter("nickName"));
		updateUser.setUserNumber(httpServletRequest.getParameter("userNumber"));
		updateUser.setIntro(httpServletRequest.getParameter("intro"));
		updateUser.setId(Integer.parseInt(httpServletRequest.getParameter("id")));

		if(userService.updateUser(updateUser) > 0) {
			System.out.println("success");
		}else {
			System.out.println("fail");
			System.out.println(updateUser.getName() + updateUser.getNickName());
		}
		
		List<UserDTO> users = userService.readUser(userID);
		ModelAndView mv = new ModelAndView();
		mv.addObject("users", users);
		mv.setViewName("redirect:/mypage/information");
		
		return mv;

	}
}

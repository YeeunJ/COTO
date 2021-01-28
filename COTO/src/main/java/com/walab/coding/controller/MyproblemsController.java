package com.walab.coding.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.service.GoalService;
import com.walab.coding.service.GoalServiceImpl;
import com.walab.coding.service.UserProblemService;
import com.walab.coding.service.UserProblemServiceImpl;

/**
 * Handles requests for the application Mypage-problems page.
 */

@Controller
@RequestMapping(value = "/mypage/problems")
public class MyproblemsController {
	
	@Autowired
	UserProblemService userProblemService;
	
	@Autowired
	GoalService goalService;	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewProblems(ModelAndView mv, Model model) {
		
		int userID = 1;
		
		List<UserProblemDTO> problems = userProblemService.read(userID);
		List<GoalDTO> goal = goalService.readGoal(userID);
		int userSolvedP = userProblemService.readSolvedP(userID);
		
		GoalDTO g = goal.get(0);
		int goalNum = g.getGoalNum();
		
		mv.addObject("goal", goal);
		mv.addObject("problems", problems);
		model.addAttribute("userSolvedP", userSolvedP);
		model.addAttribute("goalNum", goalNum);

		mv.setViewName("mypage/problems");

		
		return mv;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateProblem(HttpServletRequest httpServletRequest) {
		
		int userID = 1;
		
		UserProblemDTO upd = new UserProblemDTO();
		upd.setDifficulty(httpServletRequest.getParameter("difficulty"));
		upd.setMemo(httpServletRequest.getParameter("memo"));
		upd.setId(Integer.parseInt(httpServletRequest.getParameter("id")));
		
		if(userProblemService.update(upd) > 0) {
			System.out.println("success");
		}else {
			System.out.println("fail");
		}
		
		List<UserProblemDTO> problems = userProblemService.read(userID);
		ModelAndView mv = new ModelAndView();
		mv.addObject("problems", problems);
		mv.setViewName("ajaxContent/problemsContent");
		
		return mv;

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteProblem(HttpServletRequest httpServletRequest) {		
		
		int userID = 1;
		int userProblemID = Integer.parseInt(httpServletRequest.getParameter("id"));
		
		System.out.println(userProblemID);
		if(userProblemService.delete(userProblemID) > 0) {
			System.out.println("success");
		}else {
			System.out.println("fail");
		}
		
		List<UserProblemDTO> problems = userProblemService.read(userID);
		ModelAndView mv = new ModelAndView();
		mv.addObject("problems", problems);
		mv.setViewName("ajaxContent/problemsContent");
		return mv;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProblem(HttpServletRequest httpServletRequest) {		
		
		String searchValue= httpServletRequest.getParameter("searchValue");
		
		ModelAndView mv = new ModelAndView();
		return mv;
	}
}

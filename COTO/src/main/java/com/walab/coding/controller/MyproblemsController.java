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
import com.walab.coding.service.UserProblemServiceImpl;
import com.walab.coding.service.UserService;

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
			mv.setView(new RedirectView("mypage/problems",true));
		}	
		
		List<UserProblemDTO> problems = userProblemService.read(userID);
		List<GoalDTO> goal = goalService.readGoal(userID);
		int userSolvedP = userProblemService.readSolvedP(userID);
		List<UserProblemDTO> countSolvedProblemEachDay = userProblemService.countSolvedProblemEachDay(userID);
		
		GoalDTO g = goal.get(0);
		int goalNum = g.getGoalNum();
		
		ModelAndView mvNew = new ModelAndView();
		mvNew.addObject("goal", goal);
		mvNew.addObject("problems", problems);
		model.addAttribute("userSolvedP", userSolvedP);
		model.addAttribute("goalNum", goalNum);
		mvNew.addObject("countSolvedProblemEachDay", countSolvedProblemEachDay);

		mvNew.setViewName("mypage/problems");

		return mvNew;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateProblem(HttpServletRequest request, ModelAndView mv, HttpServletRequest httpServletRequest) {
		
		HttpSession session = request.getSession();
		UserDTO ud = (UserDTO) session.getAttribute("user");
		int userID = 0;
		userID = userService.readUserIDByEmail(ud.getEmail());
		session.setAttribute("user", ud);
		System.out.println(userID);
		if(userID > 0) {
			ud.setId(userID);
			session.setAttribute("user", ud);
			mv.setView(new RedirectView("ajaxContent/problemsContent",true));
		}	
		
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
		ModelAndView mvNew = new ModelAndView();
		mvNew.addObject("problems", problems);
		mvNew.setViewName("ajaxContent/problemsContent");
		
		return mvNew;

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteProblem(HttpServletRequest request, ModelAndView mv, HttpServletRequest httpServletRequest) {		
		
		HttpSession session = request.getSession();
		UserDTO ud = (UserDTO) session.getAttribute("user");
		int userID = userService.readUserIDByEmail(ud.getEmail());
		session.setAttribute("user", ud);
		System.out.println(userID);
		
		if(userID > 0) {
			ud.setId(userID);
			session.setAttribute("user", ud);
			mv.setView(new RedirectView("ajaxContent/problemsContent",true));
		}
		
		int userProblemID = Integer.parseInt(httpServletRequest.getParameter("id"));
		System.out.println(userProblemID);
		
		if(userProblemService.delete(userProblemID) > 0) {
			System.out.println("success");
		}else {
			System.out.println("fail");
		}
		
		List<UserProblemDTO> problems = userProblemService.read(userID);
		mv.addObject("problems", problems);
		mv.setViewName("ajaxContent/problemsContent");
		
		return mv;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProblem(HttpServletRequest request, ModelAndView mv, HttpServletRequest httpServletRequest) {		
		
		HttpSession session = request.getSession();
		UserDTO ud = (UserDTO) session.getAttribute("user");
		int userID = 0;
		userID = userService.readUserIDByEmail(ud.getEmail());
		session.setAttribute("user", ud);
		System.out.println(userID);
		if(userID > 0) {
			ud.setId(userID);
			session.setAttribute("user", ud);
			mv.setView(new RedirectView("ajaxContent/problemsContent",true));
		}
		
		String searchValue= httpServletRequest.getParameter("searchValue");
		
		List<UserProblemDTO> problems = userProblemService.search(userID, searchValue);
		System.out.println(searchValue);
		for(UserProblemDTO upd: problems) {
			System.out.println(upd.toString());
		}
		
		ModelAndView mvNew = new ModelAndView();
		mv.addObject("problems", problems);
		mv.setViewName("ajaxContent/problemsContent");
		return mvNew;
	}
}

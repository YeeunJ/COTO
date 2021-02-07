package com.walab.coding.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView viewProblems(HttpServletRequest httpServletRequest, ModelAndView mv, Model model,
			@RequestParam(value="page", defaultValue="1") int page) {
		
		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();

		// pagination
		int listCnt = goalService.readGoalCnt(userID); // 총 문제의 개수
		int list = 5; // 페이지 당 데이터 수
		int block = 5; // 블록 당 페이지 수
		
		int pageNum = (int) Math.ceil((float)listCnt/list); // 총 페이지
		int blockNum = (int)Math.ceil((float)pageNum/block); // 총 블록
		int nowBlock = (int)Math.ceil((float)page/block); // 현재 페이지가 위치한 블록 번호
		int s_point = (page-1)*list;
		
		int s_page = nowBlock*block - (block-1);
		if (s_page <= 1) {
			s_page = 1;
		}
		int e_page = nowBlock*block;
			if (pageNum <= e_page) {
				e_page = pageNum;
		}
			
		List<GoalDTO> goalList = goalService.readGoalAll(userID, s_point, list);
		
		System.out.println("listCnt: "+listCnt);
		System.out.println("s_page: "+s_page);
		System.out.println("e_page: "+e_page);
				
		mv.addObject("pagename", "problems");
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		
		
		GoalDTO g = goalList.get(0);
		System.out.println("goat rate = "+g.getRate());
	
		mv.addObject("goalList", goalList);
		
		mv.setViewName("mypage/activities");
		
		return mv;
	}
	
	@RequestMapping(value = "/readProblemActivities", method = RequestMethod.POST)
	public ModelAndView readProblemActivities(ModelAndView mv, HttpServletRequest httpServletRequest) {
		
		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
		
		int goalID = Integer.parseInt(httpServletRequest.getParameter("id"));
		
		List<UserProblemDTO> readProblemActivities = userProblemService.readProblemActivities(userID, goalID);
		
		mv.addObject("readProblemActivities", readProblemActivities);
		mv.setViewName("ajaxContent/activitiesContent");
		
		return mv;
	}
}

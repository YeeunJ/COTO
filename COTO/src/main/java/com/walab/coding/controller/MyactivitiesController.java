package com.walab.coding.controller;

import java.util.List;

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

/**
 * Handles requests for the application Mypage-activities page.
 */

@Controller
@RequestMapping(value = "/mypage/activities")
public class MyactivitiesController {

	@Autowired
	GoalService goalService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewProblems(ModelAndView mv, Model model) {
		
		int userID = 1; //지금 session 처리와 로그인을 안해서 넣어놓은 예시 데이터!! 나중에 session 처리 할께요!!
		
		List<GoalDTO> goalList = goalService.readGoalAll(userID);
			
		mv.addObject("goalList", goalList);
		
		mv.setViewName("mypage/activities");
		
		return mv;
	}

}

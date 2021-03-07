package com.walab.coding.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.model.RecomCartDTO;
import com.walab.coding.model.RecomCommentDTO;
import com.walab.coding.model.RecomCountDTO;
import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecomTagDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.GoalService;
import com.walab.coding.service.GoalServiceImpl;
import com.walab.coding.service.ProblemService;
import com.walab.coding.service.RecomCartService;
import com.walab.coding.service.RecomCommentService;
import com.walab.coding.service.RecomCountService;
import com.walab.coding.service.RecomProblemService;
import com.walab.coding.service.RecomTagService;
import com.walab.coding.service.RecommendService;
import com.walab.coding.service.UserProblemService;
import com.walab.coding.service.UserProblemServiceImpl;
import com.walab.coding.service.UserService;

/**
 * Handles requests for the application Mypage-groups page.
 */

@Controller
@RequestMapping(value = "/mypage/groups")
public class MyGroupsController {

	@Autowired
	UserProblemService userProblemService;

	@Autowired
	GoalService goalService;

	@Autowired
	CodingSiteService codingSiteService;
	
	@Autowired
	ProblemService problemService;
  
  @Autowired
	RecommendService recommendService;
	
	@Autowired
	RecomProblemService recomProblemsService;
	
	@Autowired
	RecomTagService recomTagService;
	
	@Autowired
	RecomCountService recomCountService;
	
	@Autowired
	RecomCommentService recomCommentService;
	
	@Autowired
	RecomCartService recomCartService;

	/**
	 * Read user goal, solvedProblem, codingSite, solvedProblem List
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewProblems(HttpServletRequest httpServletRequest, ModelAndView mv, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page) {

		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();

		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();

		mv.addObject("CodingSite", codingSite);

		/* pagination */
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
		/* pagination end */

		mv.setViewName("mypage/groups");

		return mv;
	}

}

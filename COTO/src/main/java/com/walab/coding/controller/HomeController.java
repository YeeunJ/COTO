package com.walab.coding.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.UserProblemsDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.CodingSiteServiceImpl;
import com.walab.coding.service.UserProblemService;
import com.walab.coding.service.UserProblemServiceImpl;
import com.walab.coding.service.UserService;
import com.walab.coding.service.UserServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	CodingSiteService codingSiteService;
	
	@Autowired
	UserService UserService;

	@Autowired
	UserProblemService userProblemService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView viewHome(ModelAndView mv) {
			
		int userID = 1;
		int probs = 0;
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		mv.addObject("CodingSite", codingSite);
		//mv.addObject("userID", userID);
		
		//List<String> ranks = userProblemService.readRank(probs);
		//System.out.println(ranks.toString());
		//mv.addObject("ranks", ranks);
		mv.setViewName("home");
		
		return mv;
	}
	
	// 문제 등록 모달로부터 UserProblemsDTO LiST를 반환받아야 함. 
	@RequestMapping(value = "/createProblem", method=RequestMethod.POST)

	public @ResponseBody String createProblem(@RequestParam(value="siteId[]") List<String> siteId, @RequestParam(value="problem[]") List<String> problem, @RequestParam(value="link[]") List<String> link) throws UnsupportedEncodingException {
		List<UserProblemsDTO> probs = new ArrayList<UserProblemsDTO>();
		
//		request.setCharacterEncoding("UTF-8");
//		String test = request.getParameter("test");
//		String testarr[] = request.getParameterValues("testarr");
//		System.out.println(test);
//		String[] siteID_arr = request.getParameterValues("siteId");
//		String[] prob_arr = request.getParameterValues("problem");
//		String[] link_arr = request.getParameterValues("link");

		int userID = 1;
		
		for(int i=0 ; i<siteId.size() ; i++) {
			System.out.println(siteId.get(i));
			UserProblemsDTO p = new UserProblemsDTO();
			
			p.setUserID(userID);
			if(Integer.parseInt(siteId.get(i)) != 0)
				p.setSiteID(Integer.parseInt(siteId.get(i)));
			
			p.setProblem(problem.get(i));
			if(link.get(i) == null)
				p.setLink(null);
			else	p.setLink(link.get(i));
			p.setDifficulty(null);
			p.setMemo(null);
			
			probs.add(p);
		}
		
		UserService.createUserProblem(probs);
		
		return "success";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewRank(ModelAndView mv) {
				
		List<RankDTO> ranks = userProblemService.readRankList();
		System.out.println(ranks.toString());
		mv.addObject("ranks", ranks);
		mv.setViewName("home");
		
		return mv;
	}
	
}

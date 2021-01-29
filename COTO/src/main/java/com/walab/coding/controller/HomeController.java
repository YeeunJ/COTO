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
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.UserProblemService;
import com.walab.coding.service.UserService;

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
					
		List<RankDTO> ranks = userProblemService.readRankList();
		//System.out.println(ranks.toString());
	
		mv.addObject("ranks", ranks);
		mv.setViewName("home");
	
		
		return mv;
	}
	
	// 문제 등록 모달로부터 UserProblemsDTO LiST를 반환받아야 함. 
	@RequestMapping(value = "/createProblem", method=RequestMethod.POST)

	public @ResponseBody String createProblem(@RequestParam(value="siteId[]") List<String> siteId, 
											  @RequestParam(value="problem[]") List<String> problem, 
											  @RequestParam(value="link[]") List<String> link) 
													throws UnsupportedEncodingException {
		List<UserProblemDTO> probs = new ArrayList<UserProblemDTO>();
	
		int userID = 1;
		System.out.println("size: "+link.size());
		System.out.println("link[0]"+link.get(0));
		
		for(int i=0 ; i<siteId.size() ; i++) {
			System.out.println(siteId.get(i));
			UserProblemDTO p = new UserProblemDTO();
			
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
		
		userProblemService.createUserProblem(probs);
		
		return "success";
	}


	
}

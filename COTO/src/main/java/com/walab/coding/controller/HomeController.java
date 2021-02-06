package com.walab.coding.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.RecomTagDTO;

import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.ProblemService;
import com.walab.coding.service.RecomTagService;
import com.walab.coding.service.RecommendService;
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
	UserService userService;

	@Autowired
	UserProblemService userProblemService;
	
	@Autowired
	RecomTagService recomTagService;
	
	@Autowired
	RecommendService recommendService;
	
	@Autowired
	ProblemService problemService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView viewHome(HttpServletRequest httpServletRequest, ModelAndView mv) {
		int probs = 0;
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		mv.addObject("CodingSite", codingSite);
					
		List<RankDTO> totalRankList = userProblemService.readTotalRankList();
		List<RankDTO> todayRankList = userProblemService.readTodayRankList();
		List<UserProblemDTO> upd = userProblemService.readProblemList();
		List<RecomTagDTO> rtd = recomTagService.readTagList();
		List<RecommendDTO> recentRecomList = recommendService.readRecentRecommendList();
		List<ProblemDTO> recentProblemList = problemService.readRecentProblem();

	
		mv.addObject("ranks", totalRankList);
		mv.addObject("ranksToday", todayRankList);
		mv.addObject("problems", upd);
		mv.addObject("tags", rtd);
		mv.addObject("recoms", recentRecomList);
		mv.addObject("recentProblems", recentProblemList);
		mv.setViewName("home");
	
		
		return mv;
	}
	
	// 문제 등록 모달로부터 UserProblemsDTO LiST를 반환받아야 함. 
	@RequestMapping(value = "/createProblem", method=RequestMethod.POST)
	@ResponseBody
	public String createProblem(HttpServletRequest httpServletRequest, ModelAndView mv, @RequestParam(value="siteId[]") List<String> siteId, 
											  @RequestParam(value="problem[]") List<String> problem, 
											  @RequestParam(value="link[]") List<String> link) {
	
		
		List<UserProblemDTO> probs = new ArrayList<UserProblemDTO>();

		
		for(int i=0 ; i<siteId.size() ; i++) {
			UserProblemDTO p = new UserProblemDTO();
			
			p.setUserID((int)httpServletRequest.getAttribute("userID"));
			if(Integer.parseInt(siteId.get(i)) != 0)
				p.setSiteID(Integer.parseInt(siteId.get(i)));
			
			p.setProblem(problem.get(i));
			
			if(link.get(i) == null)
				p.setLink(null);
			else
				p.setLink(link.get(i));
			p.setDifficulty(null);
			p.setMemo(null);
			
			probs.add(p);
		}
		
		userProblemService.createUserProblem(probs);
		
		return "success";
	}
}

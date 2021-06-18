package com.walab.coding.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.RecomTagDTO;

import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserDTO;
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
	UserService userService;

	@Autowired
	UserProblemService userProblemService;
	
	@Autowired
	ProblemService problemService;
	
	@Autowired
	RecomTagService recomTagService;
	
	@Autowired
	RecommendService recommendService;
	
	@Autowired
	CodingSiteService codingSiteService;
	
	/**
	 * reads rank, problem, tag, recommend list, coding sites
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView viewHome(HttpServletRequest httpServletRequest, ModelAndView mv) {
		
		if(httpServletRequest.getSession().getAttribute("user") != null) {
			int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
			
			mv.addObject("userID", userID);
		}
		List<RankDTO> totalRankList = userProblemService.readTotalRankList();
		List<RankDTO> todayRankList = userProblemService.readTodayRankList();
		List<UserProblemDTO> totalProblemList = userProblemService.readProblemList();
		List<ProblemDTO> recentProblemList = problemService.readRecentProblem();
		List<RecomTagDTO> tagList = recomTagService.readTagList();
		List<RecommendDTO> recentRecomList = recommendService.readRecentRecommendList();
		List<CodingSiteDTO> codingSiteList = codingSiteService.readCodingSitebyYN();

		mv.addObject("ranks", totalRankList);
		mv.addObject("ranksToday", todayRankList);
		mv.addObject("problems", totalProblemList);
		mv.addObject("recentProblems", recentProblemList);
		mv.addObject("tags", tagList);
		mv.addObject("recoms", recentRecomList);
		mv.addObject("CodingSite", codingSiteList);
		
		mv.setViewName("home");
	
		return mv;
	}
	
	/**
	 * creates the problem user entered
	 */	
	@RequestMapping(value = "/createProblem", method=RequestMethod.POST)
	@ResponseBody
	public String createProblem(HttpServletRequest httpServletRequest, ModelAndView mv, 
									@RequestParam(value="siteId[]") List<String> siteId, 
									@RequestParam(value="problem[]") List<String> problem, 
									@RequestParam(value="link[]") List<String> link) {
	
		
		List<UserProblemDTO> probs = new ArrayList<UserProblemDTO>();

		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();

		for (int i = 0; i < siteId.size(); i++) {
			UserProblemDTO p = new UserProblemDTO();
			p.setUserID(userID);

			if (Integer.parseInt(siteId.get(i)) != 0)
				p.setSiteID(Integer.parseInt(siteId.get(i)));

			p.setProblem(problem.get(i));
			System.out.println(link.get(i));
			if(link.get(i).contains("없는 문제")) {
				continue;
			}else if (link.size() > 0) {
				p.setLink(link.get(i));
			}
			p.setDifficulty(null);
			p.setMemo(null);

			probs.add(p);
		}

		userProblemService.createUserProblem(probs);

		return "success";
	}
	
}

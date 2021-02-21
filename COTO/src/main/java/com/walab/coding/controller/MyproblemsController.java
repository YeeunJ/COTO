package com.walab.coding.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.GoalService;
import com.walab.coding.service.GoalServiceImpl;
import com.walab.coding.service.RecommendService;
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
	CodingSiteService codingSiteService;
	
	@Autowired
	RecommendService recommendService;
	/**
	 * Create problem zip 
	 */
	@RequestMapping(value = "/createProblem", method = RequestMethod.POST)
	@ResponseBody
	public String createProblem(HttpServletRequest httpServletRequest, ModelAndView mv,
			@RequestParam(value = "siteId[]") List<String> siteId,
			@RequestParam(value = "problem[]") List<String> problem,
			@RequestParam(value = "link[]") List<String> link) {

		List<UserProblemDTO> probs = new ArrayList<UserProblemDTO>();

		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();

		for (int i = 0; i < siteId.size(); i++) {
			UserProblemDTO p = new UserProblemDTO();
			p.setUserID(userID);

			if (Integer.parseInt(siteId.get(i)) != 0)
				p.setSiteID(Integer.parseInt(siteId.get(i)));

			p.setProblem(problem.get(i));

			if (link.size() > 0) {
				p.setLink(link.get(i));
			}
			p.setDifficulty(null);
			p.setMemo(null);

			probs.add(p);
		}

		userProblemService.createUserProblem(probs);

		return "success";
	}

	/**
	 * Read user goal, solvedProblem, codingSite, solvedProblem List
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewProblems(HttpServletRequest httpServletRequest, ModelAndView mv, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page) {

		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();

		List<GoalDTO> goal = goalService.readGoal(userID);
		int userSolvedP = userProblemService.readSolvedP(userID);
		List<UserProblemDTO> countSolvedProblemEachDay = userProblemService.countSolvedProblemEachDay(userID);
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<RecommendDTO> recomCart = recommendService.readCartRecommendList();

		GoalDTO g = goal.get(0);
		int goalNum = g.getGoalNum();

		mv.addObject("goal", goal);
		model.addAttribute("userSolvedP", userSolvedP);
		model.addAttribute("goalNum", goalNum);
		mv.addObject("countSolvedProblemEachDay", countSolvedProblemEachDay);
		mv.addObject("CodingSite", codingSite);
		mv.addObject("recomCarts", recomCart);
		
		System.out.println(recomCart);

		/* pagination */
		int listCnt = userProblemService.readProblemCnt(userID); 
		int list = 10; 
		int block = 10;

		int pageNum = (int) Math.ceil((float) listCnt / list); 
		int blockNum = (int) Math.ceil((float) pageNum / block);
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

		List<UserProblemDTO> problems = userProblemService.readProblemByPage(userID, s_point, list);

		mv.addObject("pagename", "problems");
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		mv.addObject("problems", problems);
		/* pagination end */

		mv.setViewName("mypage/problems");

		return mv;
	}
	
	/**
	 * Update user solvedProblem List
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateProblem(ModelAndView mv, HttpServletRequest httpServletRequest) {

		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();

		UserProblemDTO upd = new UserProblemDTO();
		upd.setDifficulty(httpServletRequest.getParameter("difficulty"));
		upd.setMemo(httpServletRequest.getParameter("memo"));
		upd.setId(Integer.parseInt(httpServletRequest.getParameter("id")));

		if (userProblemService.update(upd) > 0) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}

		List<UserProblemDTO> problems = userProblemService.read(userID);
		ModelAndView mvNew = new ModelAndView();
		mvNew.addObject("problems", problems);
		mvNew.setViewName("ajaxContent/problemsContent");

		return mvNew;
	}

	/**
	 * Delete user solvedProblem List
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteProblem(ModelAndView mv, HttpServletRequest httpServletRequest) {

		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();
		int userProblemID = Integer.parseInt(httpServletRequest.getParameter("id"));

		if (userProblemService.delete(userProblemID) > 0) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}

		List<UserProblemDTO> problems = userProblemService.read(userID);
		mv.addObject("problems", problems);
		mv.setViewName("ajaxContent/problemsContent");

		return mv;
	}

	/**
	 * Search user solvedProblem List
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProblem(ModelAndView mv, HttpServletRequest httpServletRequest) {

		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();
		String searchValue = httpServletRequest.getParameter("searchValue");

		List<UserProblemDTO> problems = userProblemService.search(userID, searchValue);

		mv.addObject("problems", problems);
		mv.setViewName("ajaxContent/problemsContent");

		return mv;
	}
}

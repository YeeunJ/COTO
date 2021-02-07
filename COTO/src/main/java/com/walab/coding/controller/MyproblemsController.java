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
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.service.CodingSiteService;
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
	
	@Autowired
	CodingSiteService codingSiteService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView viewProblems(HttpServletRequest httpServletRequest, ModelAndView mv, Model model,
			@RequestParam(value="page", defaultValue="1") int page) {

		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();

		// List<UserProblemDTO> problems = userProblemService.read(userID);
		List<GoalDTO> goal = goalService.readGoal(userID);
		int userSolvedP = userProblemService.readSolvedP(userID);
		List<UserProblemDTO> countSolvedProblemEachDay = userProblemService.countSolvedProblemEachDay(userID);
		List<CodingSiteDTO> codingSite = codingSiteService.read();

		GoalDTO g = goal.get(0);
		int goalNum = g.getGoalNum();
		
		
		// pagination
		int listCnt = userProblemService.readProblemCnt(userID); // 총 문제의 개수
		int list = 10; // 페이지 당 데이터 수
		int block = 10; // 블록 당 페이지 수
		
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
		
		System.out.println("listCnt "+listCnt);
		System.out.println("blockNum: "+blockNum);
		System.out.println("nowBlock: "+nowBlock);
		System.out.println("page: "+page);
		System.out.println("s_page: "+s_page);
		System.out.println("e_page: "+e_page);
		
		List<UserProblemDTO> problems = userProblemService.readProblemByPage(userID, s_point, list);
		
		mv.addObject("pagename", "problems");
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		

		mv.addObject("goal", goal);
		mv.addObject("problems", problems);
		model.addAttribute("userSolvedP", userSolvedP);
		model.addAttribute("goalNum", goalNum);
		mv.addObject("countSolvedProblemEachDay", countSolvedProblemEachDay);
		mv.addObject("CodingSite", codingSite);
		
		mv.setViewName("mypage/problems");

		return mv;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateProblem(ModelAndView mv,
			HttpServletRequest httpServletRequest) {

		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();


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

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteProblem(ModelAndView mv, HttpServletRequest httpServletRequest) {

		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
		int userProblemID = Integer.parseInt(httpServletRequest.getParameter("id"));
		System.out.println(userProblemID);

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

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProblem(ModelAndView mv, HttpServletRequest httpServletRequest) {

		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
		String searchValue = httpServletRequest.getParameter("searchValue");

		List<UserProblemDTO> problems = userProblemService.search(userID, searchValue);
		System.out.println(searchValue);
		for (UserProblemDTO upd : problems) {
			System.out.println(upd.toString());
		}

		ModelAndView mvNew = new ModelAndView();
		mv.addObject("problems", problems);
		mv.setViewName("ajaxContent/problemsContent");
		return mvNew;
	}

	// 문제 등록 모달로부터 UserProblemsDTO LiST를 반환받아야 함.
	@RequestMapping(value = "/createProblem", method = RequestMethod.POST)
	@ResponseBody
	public String createProblem(HttpServletRequest httpServletRequest, ModelAndView mv,
			@RequestParam(value = "siteId[]") List<String> siteId,
			@RequestParam(value = "problem[]") List<String> problem,
			@RequestParam(value = "link[]") List<String> link) {
		System.out.println("siteId: " + siteId);
		System.out.println(siteId.size());
		System.out.println("problem: " + problem);
		System.out.println("link: " + link);
		List<UserProblemDTO> probs = new ArrayList<UserProblemDTO>();

		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();

		for (int i = 0; i < siteId.size(); i++) {
			System.out.println(siteId.get(i));
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
}

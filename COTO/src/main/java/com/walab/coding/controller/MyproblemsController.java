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
	
	@RequestMapping(value = "/addRecomCart", method = RequestMethod.POST)
	public ModelAndView createRecomCart(HttpServletRequest httpServletRequest) {
		RecomCartDTO cart = new RecomCartDTO();
		
		int userID = -1;
		int recomID= Integer.parseInt(httpServletRequest.getParameter("recomID"));
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
			
			cart.setRecomID(recomID);
			cart.setUserID(userID);
			recomCartService.createRecomCart(cart);
			
		}
		
		RecomCountDTO rcd = recomCountService.readRecomCount(recomID, userID);
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		int commentCount = recomComment.size();
		int cartYN = recomCartService.readCartByID(recomID, userID);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("cartYN", cartYN);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		mv.setViewName("ajaxContent/recomCommentCountContent");

		return mv;
	}
	
	@RequestMapping(value = "/addRecomCheck", method = RequestMethod.POST)
	public ModelAndView createRecomCheck(HttpServletRequest httpServletRequest) {
		int userID = -1;
		int rpID= Integer.parseInt(httpServletRequest.getParameter("rpID"));
		UserProblemDTO upd = new UserProblemDTO();
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
			upd.setProblemID(rpID);
			upd.setUserID(userID);
			userProblemService.createUserProblembyID(upd);
		}

		RecomProblemDTO rp = recomProblemsService.readEachProblem(rpID, userID);

		ModelAndView mv = new ModelAndView();
		mv.addObject("rp", rp);
		mv.setViewName("ajaxContent/recomCheckContent");

		return mv;
	}
	/**
	 * Create recommend ???
	 */
	@RequestMapping(value = "/addRecomCount", method = RequestMethod.POST)
	public ModelAndView createRecomCount(HttpServletRequest httpServletRequest) {
		RecomCountDTO rcd;
		int userID = -1;
		int recomID= Integer.parseInt(httpServletRequest.getParameter("recomID"));
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();

			rcd = new RecomCountDTO();
			rcd.setRecomID(recomID);
			rcd.setUserID(userID);
			recomCountService.createRecomCount(rcd);
		}
		rcd = recomCountService.readRecomCount(recomID, userID);
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		int commentCount = recomComment.size();
		int cartYN = recomCartService.readCartByID(recomID, userID);

		ModelAndView mv = new ModelAndView();
		mv.addObject("cartYN", cartYN);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		mv.setViewName("ajaxContent/recomCommentCountContent");

		return mv;
	}	
	@RequestMapping(value = "/deleteRecomProblem", method = RequestMethod.POST)
	public ModelAndView deleteRecomProblem(HttpServletRequest httpServletRequest) {
		int recomID = Integer.parseInt(httpServletRequest.getParameter("id"));

		recommendService.deleteRecom(recomID);

		List<RecommendDTO> recoms = recommendService.readRecommendList();
		List<Map<Integer,Integer>> commentCount = recomCommentService.readCount();
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<RecomProblemDTO> recomProblem = recomProblemsService.readProblemList();
		List<RecomTagDTO> recomProblemTag = recomTagService.readProblemTag();

		for(int i=0;i<recomProblem.size();i++) {
			for(int j=0;j<codingSite.size();j++) {
				if(recomProblem.get(i).getSiteID() == codingSite.get(j).getId())
					recomProblem.get(i).setSiteName(codingSite.get(j).getSiteName());
			}
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("recoms", recoms);
		mv.addObject("commentCount", commentCount);
		mv.addObject("recomProblem", recomProblem);
		mv.addObject("recomProblemTag", recomProblemTag);
		mv.setViewName("ajaxContent/recommendContent");

		return mv;
	}


	/**
	 * (미완성) Delete recommend
	 */
	@RequestMapping(value = "/deleteRecomCount", method = RequestMethod.POST)
	public ModelAndView deleteRecomCount(HttpServletRequest httpServletRequest) {
		RecomCountDTO rcd;
		int userID = -1;
		int recomID= Integer.parseInt(httpServletRequest.getParameter("recomID"));
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();

			rcd = new RecomCountDTO();
			rcd.setRecomID(recomID);
			rcd.setUserID(userID);
			recomCountService.deleteRecomCount(recomID);
		}
		rcd = recomCountService.readRecomCount(recomID, userID);
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		int commentCount = recomComment.size();

		ModelAndView mv = new ModelAndView();
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		mv.setViewName("ajaxContent/recomCommentCountContent");

		return mv;
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
		List<ProblemDTO> readOtherUserProblemName = problemService.readOtherUserProblemName(userID);
		List<RecommendDTO> recomCart = recomCartService.readCartRecommendList(userID);
		
		GoalDTO g = null;
		int goalNum = -1;
		if(goal.size() != 0) {
			g = goal.get(0);
			goalNum = g.getGoalNum();
		}

		mv.addObject("goal", goal);
		model.addAttribute("userSolvedP", userSolvedP);
		model.addAttribute("goalNum", goalNum);
		mv.addObject("countSolvedProblemEachDay", countSolvedProblemEachDay);
		mv.addObject("CodingSite", codingSite);
		mv.addObject("readOtherUserProblemName", readOtherUserProblemName);
		mv.addObject("recomCarts", recomCart);

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
	
	@RequestMapping(value = "/readRecomCartContent", method = RequestMethod.POST)
	public ModelAndView readRecomCartContent(HttpServletRequest httpServletRequest, ModelAndView mv) {
		
		int userID = -1;
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
		}
		
		List<RecommendDTO> recomCart = recomCartService.readCartRecommendList(userID);
		
		mv.addObject("recomCarts", recomCart);

		mv.setViewName("ajaxContent/recomCartContent");

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
//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	public ModelAndView deleteProblem(ModelAndView mv, HttpServletRequest httpServletRequest) {
//
//		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();
//		int userProblemID = Integer.parseInt(httpServletRequest.getParameter("id"));
//
//		if (userProblemService.delete(userProblemID) > 0) {
//			System.out.println("success");
//		} else {
//			System.out.println("fail");
//		}
//
//		List<UserProblemDTO> problems = userProblemService.read(userID);
//		mv.addObject("problems", problems);
//		mv.setViewName("ajaxContent/problemsContent");
//
//		return mv;
//	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletePostOK(@PathVariable("id") int id, HttpServletRequest request) {

		userProblemService.delete(id);

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	/**
	 * Search user solvedProblem List
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProblem(ModelAndView mv, HttpServletRequest httpServletRequest) {

		int userID = ((UserDTO) httpServletRequest.getSession().getAttribute("user")).getId();
		int page = Integer.parseInt(httpServletRequest.getParameter("page"));
		String searchValue = httpServletRequest.getParameter("searchValue");
		
		int listCnt = recommendService.readRecomListCnt();
		int list = 10;
		int block = 10;

		int pageNum = (int) Math.ceil((float)listCnt/list);
		int nowBlock = (int)Math.ceil((float)page/block);

		int s_point = (page-1)*list;

		int s_page = nowBlock*block - (block-1);
		if (s_page <= 1) {
			s_page = 1;
		}
		int e_page = nowBlock*block;
			if (pageNum <= e_page) {
				e_page = pageNum;
		}

			
		List<UserProblemDTO> problems = userProblemService.search(userID, searchValue, s_point, list);
		mv.addObject("list", list);
		mv.addObject("page", page);
		mv.addObject("e_page", e_page);
		mv.addObject("s_page", s_page);
		mv.addObject("problems", problems);
		mv.setViewName("ajaxContent/problemsContent");

		return mv;
	}
	
	/**
	 * Read recommend list(problem & tag & comment & recommend), coding site
	 */
	@RequestMapping(value = "/readModalInfo", method = RequestMethod.POST)
	public ModelAndView readModalInfo(HttpServletRequest request, ModelAndView mv) {

		int recomID = Integer.parseInt(request.getParameter("recomID"));

		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSitebyYN();
		RecommendDTO recom = recommendService.readRecommend(recomID);
		List<RecomProblemDTO> recomProblem = recomProblemsService.readProblemByID(recomID);
		List<RecomTagDTO> recomProblemTag = recomTagService.readTagByID(recomID);
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		int commentCount = recomComment.size();
		RecomCountDTO rcd;
		int userID = -1;
		if((UserDTO)request.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)request.getSession().getAttribute("user")).getId();
		}
		
		int admin = ((UserDTO)request.getSession().getAttribute("user")).getIsAdmin();
		int cartYN = recomCartService.readCartByID(recomID, userID);

		//if(((UserDTO)request.getSession().getAttribute("user")).getIsAdmin() > 0) {
		rcd = recomCountService.readRecomCount(recomID, userID);
		rcd.setRecomID(recomID);

		for(int i=0;i<recomProblem.size();i++) {
			for(int j=0;j<codingSite.size();j++) {
				if(recomProblem.get(i).getSiteID() == codingSite.get(j).getId())
					recomProblem.get(i).setSiteName(codingSite.get(j).getSiteName());
			}
		}
		
		mv.addObject("cartYN", cartYN);
		mv.addObject("recomID", recomID);
		mv.addObject("loginID", userID);
		mv.addObject("adminID", admin);
		mv.addObject("recom", recom);
		mv.addObject("codingSite", codingSite);
		mv.addObject("recomProblem", recomProblem);
		mv.addObject("recomProblemTag", recomProblemTag);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);

		mv.setViewName("ajaxContent/recomCartDetailModal");

		return mv;
	}
	
	/**
	 * Create comment
	 */
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView addComment(HttpServletRequest httpServletRequest, ModelAndView mv) {
		RecomCountDTO rcd;
		int userID = -1;
		int recomID= Integer.parseInt(httpServletRequest.getParameter("recomID"));
		String content = httpServletRequest.getParameter("content");
		
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
			
			RecomCommentDTO dto = new RecomCommentDTO();

			dto.setUserId(userID);
			dto.setRecomID(recomID);
			dto.setContent(content);

			recomCommentService.createComment(dto);
			
		}
		
		rcd = recomCountService.readRecomCount(recomID, userID);
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		int commentCount = recomComment.size();
		int cartYN = recomCartService.readCartByID(recomID, userID);

		mv.addObject("cartYN", cartYN);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		mv.setViewName("ajaxContent/recomCommentCountContent");

		return mv;
	}
	
	@RequestMapping(value = "/deleteRecomCart", method = RequestMethod.POST)
	public ModelAndView deleteRecomCart(HttpServletRequest httpServletRequest) {
		RecomCartDTO cart = new RecomCartDTO();
		
		int userID = -1;
		int recomID= Integer.parseInt(httpServletRequest.getParameter("recomID"));
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
			
			cart.setRecomID(recomID);
			cart.setUserID(userID);
			recomCartService.deleteRecomCart(cart);
			
		}
		
		RecomCountDTO rcd = recomCountService.readRecomCount(recomID, userID);
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		int commentCount = recomComment.size();
		int cartYN = recomCartService.readCartByID(recomID, userID);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("cartYN", cartYN);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		mv.setViewName("ajaxContent/recomCommentCountContent");

		return mv;
	}

}

package com.walab.coding.controller;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecomCommentDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.model.RecomTagDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.CodingSiteServiceImpl;
import com.walab.coding.service.RecomCommentService;
import com.walab.coding.service.RecomProblemServiceImpl;
import com.walab.coding.service.RecommendService;
import com.walab.coding.service.RecomCommentServiceImpl;
import com.walab.coding.service.RecomProblemService;
import com.walab.coding.service.RecommendServiceImpl;
import com.walab.coding.service.UserService;
import com.walab.coding.service.RecomTagService;
import com.walab.coding.service.RecomTagServiceImpl;
/**
 * Handles requests for the application RecommendProblems page.
 */

@Controller
@RequestMapping(value = "/recommendProblem")
public class RecommendController {
	
	@Autowired
	RecommendService recommendService;
	@Autowired
	RecomCommentService recomCommentService;
	@Autowired
	CodingSiteService codingSiteService;
	@Autowired
	RecomProblemService recomProblemsService;
	@Autowired
	RecomTagService recomTagService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView recommendProblem(ModelAndView mv) {
		//임의 값
		//int userID = 3;
				
//		List<RecomCommentDTO> recomComment = recomCommentService.read();
		List<RecommendDTO> recoms = recommendService.readRecom();
//		List<Map<Integer,Integer>> commentCount = recomCommentService.readCount();		
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		List<RecomProblemDTO> recomProblem = recomProblemsService.readProblem();
		List<RecomTagDTO> recomProblemTag = recomTagService.readProblemTag();
		
		for(int i=0;i<recomProblem.size();i++) {
			for(int j=0;j<codingSite.size();j++) {
				if(recomProblem.get(i).getSiteID() == codingSite.get(j).getId())
					recomProblem.get(i).setSiteName(codingSite.get(j).getSiteName());
			}
		}
		
		
		mv.addObject("recoms", recoms);
		mv.addObject("codingSite", codingSite);
//		mv.addObject("recomComment", recomComment);
//		mv.addObject("commentCount", commentCount);
		mv.addObject("recomProblem", recomProblem);
		mv.addObject("recomProblemTag", recomProblemTag);
				
		mv.setViewName("recommendProblem");

		return mv;
	}
	
	
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView addComment(HttpServletRequest request, ModelAndView mv) {
		int recomID = Integer.parseInt(request.getParameter("recomID"));
		HttpSession session = request.getSession();
		UserDTO ud = (UserDTO) session.getAttribute("user");
		int userID = 0;
		userID = userService.readUserIDByEmail(ud.getEmail());
		session.setAttribute("user", ud);
		System.out.println(userID);
		if(userID > 0) {
			ud.setId(userID);
			session.setAttribute("user", ud);
			mv.setView(new RedirectView("ajaxContent/recomCommentContent",true));
		}
		String content = request.getParameter("content");
		
		RecomCommentDTO dto = new RecomCommentDTO();
		
		dto.setUserId(userID);
		dto.setRecomID(recomID);
		dto.setContent(content);
		
		recomCommentService.createComment(dto);
		
		
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		
		
		mv.addObject("userid", userID);
		mv.addObject("recomID", recomID);
		mv.addObject("recomComment", recomComment);
		mv.setViewName("ajaxContent/recomCommentContent");
		
		return mv;
		
//		String html="<input type=\"text\" name=\"writer\" value=\""+userid+"\" hidden>\n"
//				+ "  <input type=\"text\" name=\"recomID\" value=\""+recomID+"\" hidden>";
//		
//		
//		for(int i=0 ; i<recomComment.size();i++) {
//			System.out.println( recomComment.get(i).get("regDate"));
//
//			html+="<div class=\'comment-wrapper\'>\n"
//				+ "		<span class=\'username\'>"+recomComment.get(i).get("name")+"</span><span class=\"commentdate\">"+recomComment.get(i).get("regDate")+"</span>\n"
//				+ "		<p class=\"comment\">"+recomComment.get(i).get("content")+"</p>\n"
//				+ "	</div>";
//		}
//		
//		return html;
	}
	
	@RequestMapping(value = "/readComment", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView readComment(HttpServletRequest request, ModelAndView mv) {
		
		int recomID = Integer.parseInt(request.getParameter("recomID"));		
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		
		HttpSession session = request.getSession();
		UserDTO ud = (UserDTO) session.getAttribute("user");
		int userID = 0;
		userID = userService.readUserIDByEmail(ud.getEmail());
		session.setAttribute("user", ud);
		System.out.println(userID);
		if(userID > 0) {
			ud.setId(userID);
			session.setAttribute("user", ud);
			mv.setView(new RedirectView("ajaxContent/recomCommentContent",true));
		}		
		
		ModelAndView mvNew = new ModelAndView();
		

		mvNew.addObject("userid", userID);
		mvNew.addObject("recomID", recomID);
		mvNew.addObject("recomComment", recomComment);
		mvNew.setViewName("ajaxContent/recomCommentContent");
		
		return mvNew;
	}

	@RequestMapping(value = "/createRecomProblem", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView createProblem(HttpServletRequest request, ModelAndView mv, @RequestParam(value="siteId[]") List<String> siteId, @RequestParam(value="problem[]") List<String> problem, @RequestParam(value="link[]") List<String> link, @RequestParam(value="title") String title, @RequestParam(value="difficulty") String difficulty, @RequestParam(value="tag[]") List<String> tag, @RequestParam(value="content") String content) throws UnsupportedEncodingException {
		RecommendDTO recom = new RecommendDTO();
		List<RecomProblemDTO> recomProbs = new ArrayList<RecomProblemDTO>();
		List<RecomTagDTO> recomTags = new ArrayList<RecomTagDTO>();

		HttpSession session = request.getSession();
		UserDTO ud = (UserDTO) session.getAttribute("user");
		int userID = 0;
		userID = userService.readUserIDByEmail(ud.getEmail());
		session.setAttribute("user", ud);
		System.out.println(userID);
		if(userID > 0) {
			ud.setId(userID);
			session.setAttribute("user", ud);
			mv.setView(new RedirectView("ajaxContent/recommendContent",true));
		}			
		recom.setUserID(userID);
		recom.setTitle(title);
		recom.setDifficulty(Integer.parseInt(difficulty));
		recom.setContent(content);
		int recomID = recommendService.createRecomProblem(recom);
		
		for(int i=0 ; i<siteId.size() ; i++) {
			System.out.println(siteId.get(i));
			RecomProblemDTO p = new RecomProblemDTO();
			
			p.setRecomID(recomID);
			
			if(Integer.parseInt(siteId.get(i)) != 0)
				p.setSiteID(Integer.parseInt(siteId.get(i)));
			
			p.setName(problem.get(i));
//			int problemID = 0;
//			problemID = recomProblemsService.readProblemID(Integer.parseInt(siteId.get(i)), problem.get(i));
//			
//			if(problemID == 0) {
//				
//			}
//			else p.setProblemID(problemID);
			
			if(link.get(i) == null) p.setLink(null);
			else p.setLink(link.get(i));
			
			recomProbs.add(p);
		}
		
		recomProblemsService.createRecomProblem(recomProbs);
		
		for(int i=0;i<tag.size();i++) {
			RecomTagDTO t = new RecomTagDTO();
			
			t.setRecomID(recomID);
			t.setTag(tag.get(i));
			
			recomTags.add(t);
		}
		
		recomTagService.createTag(recomTags);
		
		List<RecommendDTO> recoms = recommendService.readRecom();
		List<Map<Integer,Integer>> commentCount = recomCommentService.readCount();
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		List<RecomProblemDTO> recomProblem = recomProblemsService.readProblem();
		List<RecomTagDTO> recomProblemTag = recomTagService.readProblemTag();
		
		for(int i=0;i<recomProblem.size();i++) {
			for(int j=0;j<codingSite.size();j++) {
				if(recomProblem.get(i).getSiteID() == codingSite.get(j).getId())
					recomProblem.get(i).setSiteName(codingSite.get(j).getSiteName());
			}
		}
		
		ModelAndView mvNew = new ModelAndView();
		mvNew.addObject("recoms", recoms);
		mvNew.addObject("commentCount", commentCount);
		mvNew.addObject("recomProblem", recomProblem);
		mvNew.addObject("recomProblemTag", recomProblemTag);
		mvNew.setViewName("ajaxContent/recommendContent");
		
		return mvNew;
	}
	
	@RequestMapping(value = "/updateRecomProblem", method = RequestMethod.POST)
	public ModelAndView updateRecomProblem(HttpServletRequest httpServletRequest) {
		
//		int userID = 3;
//		
//		UserProblemDTO upd = new UserProblemDTO();
//		upd.setDifficulty(httpServletRequest.getParameter("difficulty"));
//		upd.setMemo(httpServletRequest.getParameter("memo"));
//		upd.setId(Integer.parseInt(httpServletRequest.getParameter("id")));
//		
//		if(userProblemService.update(upd) > 0) {
//			System.out.println("success");
//		}else {
//			System.out.println("fail");
//		}
//		
		List<RecommendDTO> recoms = recommendService.readRecom();
		List<Map<Integer,Integer>> commentCount = recomCommentService.readCount();
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		List<RecomProblemDTO> recomProblem = recomProblemsService.readProblem();
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
	
	@RequestMapping(value = "/deleteRecomProblem", method = RequestMethod.POST)
	public ModelAndView deleteRecomProblem(HttpServletRequest httpServletRequest) {
		int userID = 3;
		int recomID = Integer.parseInt(httpServletRequest.getParameter("id"));
		
		recommendService.deleteRecom(recomID);
		
//		System.out.println(recomID);
//		if(recommendService.deleteRecom(recomID) > 0) {
//			System.out.println("recom success");
//		}else {
//			System.out.println("recom fail");
//		}
//		if(recomCommentService.deleteRecomComment(recomID) > 0) {
//			System.out.println("recomComment success");
//		}else {
//			System.out.println("recomComment fail");
//		}
//		if(recomProblemsService.deleteRecomProblem(recomID) > 0) {
//			System.out.println("recomProblem success");
//		}else {
//			System.out.println("recomProblem fail");
//		}
//		if(recomTagService.deleteRecomTag(recomID) > 0) {
//			System.out.println("recomTag success");
//		}else {
//			System.out.println("recomTag fail");
//		}
//		if(recomCommentService.deleteRecomCount(recomID) > 0) {
//			System.out.println("recomCount success");
//		}else {
//			System.out.println("recomCount fail");
//		}
		
		List<RecommendDTO> recoms = recommendService.readRecom();
		List<Map<Integer,Integer>> commentCount = recomCommentService.readCount();
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		List<RecomProblemDTO> recomProblem = recomProblemsService.readProblem();
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
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProblem(HttpServletRequest httpServletRequest) {		
		
		int userID = 1;
		String searchValue= httpServletRequest.getParameter("searchValue");
		String orderValue= httpServletRequest.getParameter("orderValue");
		
		List<RecommendDTO> recoms = recommendService.search(searchValue, orderValue);
		List<Map<Integer,Integer>> commentCount = recomCommentService.readCount();
		System.out.println(searchValue);
		System.out.println(orderValue);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("recoms", recoms);
		mv.addObject("commentCount", commentCount);
		System.out.println("hello");
		for(RecommendDTO recom: recoms) {
			System.out.println(recom.getTitle());
		}
		
		mv.setViewName("ajaxContent/recommendContent");
		
		return mv;
	}
}
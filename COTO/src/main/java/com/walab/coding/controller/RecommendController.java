package com.walab.coding.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecomCommentDTO;
import com.walab.coding.model.RecomCountDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.RecomTagDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.RecomCommentService;
import com.walab.coding.service.RecommendService;
import com.walab.coding.service.RecomCountService;
import com.walab.coding.service.RecomProblemService;
import com.walab.coding.service.UserService;
import com.walab.coding.service.RecomTagService;
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
	@Autowired
	RecomCountService recomCountService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView readRecommendProblemList(HttpServletRequest request, ModelAndView mv, 
			@RequestParam(value="page", defaultValue="1") int page) {
		
//		List<RecommendDTO> recoms_t = recommendService.readRecom();	
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		
		// pagination
		int listCnt = recommendService.readRecomListCnt(); // 총 문제의 개수
		int list = 10; // 페이지 당 데이터 수
		int block = 10; // 블록 당 페이지 수
		
		int pageNum = (int) Math.ceil((float)listCnt/list); // 총 페이지
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
		
		List<RecommendDTO> recoms = recommendService.readRecomByPage(s_point, list);
		
		mv.addObject("pagename", "recommendProblem");
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		
		mv.addObject("recoms", recoms);
		mv.addObject("codingSite", codingSite);
		
		mv.setViewName("recommendProblem");

		return mv;
	}
	
	@RequestMapping(value = "/readModalInfo", method = RequestMethod.POST)
	public ModelAndView readModalInfo(HttpServletRequest request, ModelAndView mv) {
		
		int recomID = Integer.parseInt(request.getParameter("recomID"));
		
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		RecommendDTO recom = recommendService.readRecommend(recomID);	
		List<RecomProblemDTO> recomProblem = recomProblemsService.readProblemByID(recomID);
		List<RecomTagDTO> recomProblemTag = recomTagService.readTagByID(recomID);
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		int commentCount = recomComment.size();
		RecomCountDTO rcd = new RecomCountDTO();
		
		
		rcd.setRecomID(recomID);
		
		for(int i=0;i<recomProblem.size();i++) {
			for(int j=0;j<codingSite.size();j++) {
				if(recomProblem.get(i).getSiteID() == codingSite.get(j).getId())
					recomProblem.get(i).setSiteName(codingSite.get(j).getSiteName());
			}
		}
		
		mv.addObject("recomID", recomID);
		mv.addObject("recom", recom);
		mv.addObject("codingSite", codingSite);
		mv.addObject("recomProblem", recomProblem);
		mv.addObject("recomProblemTag", recomProblemTag);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		
		mv.setViewName("ajaxContent/recomDetailModal");
		
		return mv;
	}
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView addComment(HttpServletRequest request, ModelAndView mv) {
		int recomID = Integer.parseInt(request.getParameter("recomID"));
		int userID = ((UserDTO)request.getSession().getAttribute("user")).getId();
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
	}
	
	@RequestMapping(value = "/addRecomCount", method = RequestMethod.POST)
	public ModelAndView createRecomCount(HttpServletRequest httpServletRequest) {		
		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
		int recomID= Integer.parseInt(httpServletRequest.getParameter("searchValue"));
		
		RecomCountDTO rcd = new RecomCountDTO();
		rcd.setRecomID(recomID);
		rcd.setUserID(userID);
		
		recomCountService.createRecomCount(rcd);
		rcd = recomCountService.readRecomCount(recomID, userID);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("countInfo", rcd);
		mv.setViewName("ajaxContent/recomCountContent");
		
		return mv;
	}
	
	@RequestMapping(value = "/readComment", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView readComment(HttpServletRequest request, ModelAndView mv) {
		
		int recomID = Integer.parseInt(request.getParameter("recomID"));		
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		
		int userID = ((UserDTO)request.getSession().getAttribute("user")).getId();
		System.out.println("in readCommend: "+userID);
		
		mv.addObject("userid", userID);
		mv.addObject("recomID", recomID);
		mv.addObject("recomComment", recomComment);
		mv.setViewName("ajaxContent/recomCommentContent");
		
		return mv;
	}

	@RequestMapping(value = "/createRecomProblem", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView createProblem(HttpServletRequest request, ModelAndView mv, @RequestParam(value="siteId[]") List<String> siteId, @RequestParam(value="problem[]") List<String> problem, @RequestParam(value="link[]") List<String> link, @RequestParam(value="title") String title, @RequestParam(value="difficulty") String difficulty, @RequestParam(value="tag[]") List<String> tag, @RequestParam(value="content") String content) throws UnsupportedEncodingException {
		RecommendDTO recom = new RecommendDTO();
		List<RecomProblemDTO> recomProbs = new ArrayList<RecomProblemDTO>();
		List<RecomTagDTO> recomTags = new ArrayList<RecomTagDTO>();

		int userID = ((UserDTO)request.getSession().getAttribute("user")).getId();
	
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
	public ModelAndView updateRecomProblem(HttpServletRequest httpServletRequest, @RequestParam(value="siteId[]") List<String> siteId, @RequestParam(value="problem[]") List<String> problem, @RequestParam(value="link[]") List<String> link, @RequestParam(value="tag[]", required=false) List<String> tag) {
		
		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
		List<RecomProblemDTO> recomProbs = new ArrayList<RecomProblemDTO>();
		RecommendDTO recom = new RecommendDTO();
		List<RecomTagDTO> recomTags = new ArrayList<RecomTagDTO>();
		
		recom.setId(Integer.parseInt(httpServletRequest.getParameter("recomID")));
		recom.setTitle(httpServletRequest.getParameter("title"));
		recom.setDifficulty(Integer.parseInt(httpServletRequest.getParameter("difficulty")));
		recom.setContent(httpServletRequest.getParameter("content"));
		
		if(recommendService.updateRecommend(recom) > 0) {
			System.out.println("success");
		}else {
			System.out.println("fail");
		}
		
		recomTagService.deleteRecomTag(Integer.parseInt(httpServletRequest.getParameter("recomID")));
		
		if(tag != null) {
			for(int i=0;i<tag.size();i++) {
				RecomTagDTO t = new RecomTagDTO();
				
				t.setRecomID(Integer.parseInt(httpServletRequest.getParameter("recomID")));
				t.setTag(tag.get(i));
				
				recomTags.add(t);
			}
			
			recomTagService.createTag(recomTags);
		}
		//recomTagService.updateTag(recomTags);
		
//		if(recomTagService.updateTag(recomTags) > 0) {
//			System.out.println("success");
//		}else {
//			System.out.println("fail");
//		}
		
		recomProblemsService.deleteRecomProblem(Integer.parseInt(httpServletRequest.getParameter("recomID")));
		
		for(int i=0 ; i<siteId.size() ; i++) {
			System.out.println(siteId.get(i));
			RecomProblemDTO p = new RecomProblemDTO();
			
			p.setRecomID(Integer.parseInt(httpServletRequest.getParameter("recomID")));
			
			if(Integer.parseInt(siteId.get(i)) != 0)
				p.setSiteID(Integer.parseInt(siteId.get(i)));
			
			p.setName(problem.get(i));
			
			if(link.get(i) == null) p.setLink(null);
			else p.setLink(link.get(i));
			
			recomProbs.add(p);
		}
		
		recomProblemsService.createRecomProblem(recomProbs);

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
		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
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
//		if(recomCountService.deleteRecomCount(recomID) > 0) {
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
		
		int userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
		String searchValue= httpServletRequest.getParameter("searchValue");
		String orderValue= httpServletRequest.getParameter("orderValue");
		
		List<RecommendDTO> recoms = recommendService.search(searchValue, orderValue);
		System.out.println(searchValue);
		System.out.println(orderValue);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("recoms", recoms);
		mv.setViewName("ajaxContent/recommendContent");
		
		return mv;
	}
}
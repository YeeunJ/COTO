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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.RecomProblemsDTO;
import com.walab.coding.model.RecomCommentDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.TagDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.CodingSiteServiceImpl;
import com.walab.coding.service.RecomCommentService;
import com.walab.coding.service.RecomProblemsServiceImpl;
import com.walab.coding.service.RecommendService;
import com.walab.coding.service.RecomCommentServiceImpl;
import com.walab.coding.service.RecomProblemsService;
import com.walab.coding.service.RecommendServiceImpl;
import com.walab.coding.service.TagService;
import com.walab.coding.service.TagServiceImpl;
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
	RecomProblemsService recomProblemsService;
	@Autowired
	TagService tagService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView recommendProblem(ModelAndView mv) {
		//임의 값
		//int userID = 3;
				
//		List<RecomCommentDTO> recomComment = recomCommentService.read();
		List<RecommendDTO> recoms = recommendService.readRecom();
		List<Map<Integer,Integer>> commentCount = recomCommentService.readCount();
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		
		
		mv.addObject("recoms", recoms);
		mv.addObject("codingSite", codingSite);
//		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
				
		mv.setViewName("recommendProblem");

		return mv;
	}
	
	
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(HttpServletRequest request) {
		int recomID = Integer.parseInt(request.getParameter("recomID"));
		int userId = 2;
		String content = request.getParameter("content");
		
		RecomCommentDTO dto = new RecomCommentDTO();
		
		dto.setUserId(userId);
		dto.setRecomID(recomID);
		dto.setContent(content);
		
		recomCommentService.createComment(dto);
		
		
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		
		int userid=2;
		String html="<input type=\"text\" name=\"writer\" value=\""+userid+"\" hidden>\n"
				+ "  <input type=\"text\" name=\"recomID\" value=\""+recomID+"\" hidden>";
		
		
		for(int i=0 ; i<recomComment.size();i++) {
			System.out.println( recomComment.get(i).get("regDate"));
//			Date from = recomComment.get(i).get("regDate");
//			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String to = transFormat.format(from);

			html+="<div class=\'comment-wrapper\'>\n"
				+ "		<span class=\'username\'>"+recomComment.get(i).get("name")+"</span><span class=\"commentdate\">"+recomComment.get(i).get("regDate")+"</span>\n"
				+ "		<p class=\"comment\">"+recomComment.get(i).get("content")+"</p>\n"
				+ "	</div>";
		}
		
		return html;
	}
	
	@RequestMapping(value = "/readComment", method = RequestMethod.POST)
	@ResponseBody
	public String readComment(HttpServletRequest request) {
		
		int recomID = Integer.parseInt(request.getParameter("recomID"));		
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);

		System.out.println(recomComment.isEmpty());
		System.out.println(recomComment.size());
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date startDate = transFormat.parse(httpServeletRequest.getParameter("startDate"));
		
		
		int userid=2;
		String html="<input type=\"text\" name=\"writer\" value=\""+userid+"\" hidden>\n"
				+ "  <input type=\"text\" name=\"recomID\" value=\""+recomID+"\" hidden>";
		
		for(int i=0 ; i<recomComment.size();i++) {
			String regDate = transFormat.format(recomComment.get(i).get("regDate"));
			
			html+="<div class=\'comment-wrapper\'>\n"
				+ "		<span class=\'username\'>"+recomComment.get(i).get("name")+"</span><span class=\"commentdate\">"+regDate+"</span>\n"
				+ "		<p class=\"comment\">"+recomComment.get(i).get("content")+"</p>\n"
				+ "	</div>";
		}
		System.out.println(html);
		
		return html;
	}

	@RequestMapping(value = "/createRecomProblem", method = RequestMethod.POST)
	public @ResponseBody String createProblem(@RequestParam(value="siteId[]") List<String> siteId, @RequestParam(value="problem[]") List<String> problem, @RequestParam(value="link[]") List<String> link, @RequestParam(value="title") String title, @RequestParam(value="difficulty") int difficulty, @RequestParam(value="tag[]") List<String> tag, @RequestParam(value="content") String content) throws UnsupportedEncodingException {
		RecommendDTO recom = new RecommendDTO();
		List<RecomProblemsDTO> recomProbs = new ArrayList<RecomProblemsDTO>();
		List<TagDTO> recomTags = new ArrayList<TagDTO>();

		int userID = 3;
		
		recom.setUserID(userID);
		recom.setTitle(title);
		recom.setDifficulty(difficulty);
		recom.setContent(content);
		int recomID = recommendService.createRecomProblem(recom);
		
		for(int i=0 ; i<siteId.size() ; i++) {
			System.out.println(siteId.get(i));
			RecomProblemsDTO p = new RecomProblemsDTO();
			
			p.setRecomID(recomID);
			
			if(Integer.parseInt(siteId.get(i)) != 0)
				p.setSite(Integer.parseInt(siteId.get(i)));
			
			p.setProblem(problem.get(i));
			
			recomProbs.add(p);
		}
		
		recomProblemsService.createRecomProblem(recomProbs);
		
		for(int i=0;i<tag.size();i++) {
			TagDTO t = new TagDTO();
			
			t.setRecomID(recomID);
			t.setTag(tag.get(i));
			
			recomTags.add(t);
		}
		
		tagService.createTag(recomTags);
		
		return "success";
	}
}
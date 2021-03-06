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

import com.mysql.cj.xdevapi.JsonArray;
import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.RecomCartDTO;
import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecomCommentDTO;
import com.walab.coding.model.RecomCountDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.model.RecomTagDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.RecomCartService;
import com.walab.coding.service.RecomCommentService;
import com.walab.coding.service.RecommendService;
import com.walab.coding.service.UserProblemService;
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
	@Autowired
	UserProblemService userProblemService;
	@Autowired
	RecomCartService recomCartService;



	/**
	 * Create recommend zip
	 */
	@RequestMapping(value = "/createRecomProblem", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView createProblem(HttpServletRequest request, ModelAndView mv, 
			@RequestParam(value="siteId[]") List<String> siteId, 
			@RequestParam(value="problem[]") List<String> problem, 
			@RequestParam(value="link[]") List<String> link, 
			@RequestParam(value="title") String title, 
			@RequestParam(value="difficulty") String difficulty, 
			@RequestParam(value="tag[]", required=false) List<String> tag, 
			@RequestParam(value="content") String content) throws UnsupportedEncodingException {
		
		RecommendDTO recom = new RecommendDTO();
		List<RecomProblemDTO> recomProbs = new ArrayList<RecomProblemDTO>();
		List<RecomTagDTO> recomTags = new ArrayList<RecomTagDTO>();

		int userID = ((UserDTO)request.getSession().getAttribute("user")).getId();
		
		recom.setUserID(userID);
		recom.setTitle(title);
		recom.setDifficulty(Integer.parseInt(difficulty));
		recom.setContent(content);
		int recomID = recommendService.createRecomProblem(recom);

		for(int i = 0 ; i < siteId.size() ; i++) {
			RecomProblemDTO p = new RecomProblemDTO();

			p.setRecomID(recomID);

			if(Integer.parseInt(siteId.get(i)) != 0)
				p.setSiteID(Integer.parseInt(siteId.get(i)));

			p.setName(problem.get(i));

			if(link.size() == 0) p.setLink(null);
			else p.setLink(link.get(i));

			recomProbs.add(p);
		}
		
		recomProblemsService.createRecomProblem(recomProbs);

		if(tag != null) {
			for(int i = 0; i < tag.size(); i++) {
				RecomTagDTO t = new RecomTagDTO();

				t.setRecomID(recomID);
				t.setTag(tag.get(i));

				recomTags.add(t);
			}

			recomTagService.createTag(recomTags);
		}

		ModelAndView mvNew = new ModelAndView();
		mvNew.setViewName("ajaxContent/recommendContent");

		return mvNew;
	}

	/**
	 * Create comment
	 */
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView addComment(HttpServletRequest httpServletRequest, ModelAndView mv) {
		RecomCountDTO rcd;
		int userID = -1;
		int recomID = Integer.parseInt(httpServletRequest.getParameter("recomID"));
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
		RecommendDTO recom = recommendService.readRecommend(recomID);
		int isAdmin = userService.readIsAdminByUserID(userID);
		
		
		mv.addObject("recom", recom);
		mv.addObject("isAdmin", isAdmin);
		mv.addObject("loginID", userID);
		mv.addObject("cartYN", cartYN);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		mv.setViewName("ajaxContent/recomCommentCountContent");

		return mv;
	}

	/**
	 * Delete comment
	 */
	@RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView deleteComment(HttpServletRequest httpServletRequest, ModelAndView mv) {
		RecomCountDTO rcd;
		int userID = -1;
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
		}
		int recomID = Integer.parseInt(httpServletRequest.getParameter("recomID"));
		int commentID = Integer.parseInt(httpServletRequest.getParameter("commentID"));
		recomCommentService.deleteRecomComment(commentID);
	
		rcd = recomCountService.readRecomCount(recomID, userID);
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		RecommendDTO recom = recommendService.readRecommend(recomID);
		
		int commentCount = recomComment.size();
		int cartYN = recomCartService.readCartByID(recomID, userID);
		int isAdmin = userService.readIsAdminByUserID(userID);

		mv.addObject("recom", recom);
		mv.addObject("isAdmin", isAdmin);
		mv.addObject("loginID", userID);
		mv.addObject("cartYN", cartYN);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		mv.setViewName("ajaxContent/recomCommentCountContent");

		return mv;
	}
	
	/**
	 * Create recommend ???
	 */
	@RequestMapping(value = "/addRecomCheck", method = RequestMethod.POST)
	public ModelAndView createRecomCheck(HttpServletRequest httpServletRequest) {
		int userID = -1;
		int rpID = Integer.parseInt(httpServletRequest.getParameter("rpID"));
		String problemName= httpServletRequest.getParameter("problemName");
		String link  = httpServletRequest.getParameter("link");
		UserProblemDTO upd = new UserProblemDTO();
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
			upd.setProblemID(rpID);
			upd.setUserID(userID);
			userProblemService.createUserProblembyID(upd);
		}

		RecomProblemDTO rp = recomProblemsService.readEachProblem(rpID, userID);
		if(rp == null) {
			rp = new RecomProblemDTO();
			rp.setProblemID(rpID);
			rp.setName(problemName);
			rp.setLink(link);
		}
		rp.setLink(link);
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
		int recomID = Integer.parseInt(httpServletRequest.getParameter("recomID"));
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
		RecommendDTO recom = recommendService.readRecommend(recomID);
		int isAdmin = userService.readIsAdminByUserID(userID);

		ModelAndView mv = new ModelAndView();
		mv.addObject("recom", recom);
		mv.addObject("isAdmin", isAdmin);
		mv.addObject("loginID", userID);
		mv.addObject("userID", userID);
		mv.addObject("cartYN", cartYN);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		mv.setViewName("ajaxContent/recomCommentCountContent");

		return mv;
	}
	
	@RequestMapping(value = "/addRecomCart", method = RequestMethod.POST)
	public ModelAndView createRecomCart(HttpServletRequest httpServletRequest,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "searchValue", defaultValue = "") String searchValue,
			@RequestParam(value = "orderValue", defaultValue = "") String orderValue,
			@RequestParam(value = "tagValue", defaultValue = "") List<String> tagValue) {
		RecomCartDTO cart = new RecomCartDTO();
		
		int userID = -1;
		int recomID = Integer.parseInt(httpServletRequest.getParameter("recomID"));
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
			
			cart.setRecomID(recomID);
			cart.setUserID(userID);
			recomCartService.createRecomCart(cart);
			
		}
		
		ModelAndView mv = new ModelAndView();
		
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<String> tagData = new ArrayList<String>();
		
		if(!tagValue.get(0).equalsIgnoreCase("[]")) {
			tagValue.stream().forEach(tag -> {
				String [] tagSplit = tag.split("\"");
				tagData.add(tagSplit[1]);
			});
		}
		
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

		List<RecommendDTO> recoms = recommendService.readRecomByPage(searchValue, orderValue, tagData, s_point, list);
		
		mv.addObject("userID", userID);
		List<RecommendDTO> recomCart = recomCartService.readCartByRecommend(searchValue, orderValue, tagData, s_point, list, userID);
		
		mv.addObject("recoms", recomCart);
				
		mv.addObject("pagename", "recommendProblem");
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		
		
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") == null) {
			mv.addObject("recoms", recoms);
		}
		mv.addObject("codingSite", codingSite);

		mv.setViewName("ajaxContent/recommendContent");

		return mv;
	}
	
	@RequestMapping(value = "/addRecomCartinModal", method = RequestMethod.POST)
	public ModelAndView createRecomCartinModal(HttpServletRequest httpServletRequest) {
		RecomCartDTO cart = new RecomCartDTO();
		
		int userID = -1;
		int recomID = Integer.parseInt(httpServletRequest.getParameter("recomID"));
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
		
		RecommendDTO recom = recommendService.readRecommend(recomID);
		int isAdmin = userService.readIsAdminByUserID(userID);

		mv.addObject("recom", recom);
		mv.addObject("isAdmin", isAdmin);
		mv.addObject("loginID", userID);
		mv.addObject("cartYN", cartYN);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		mv.setViewName("ajaxContent/recomCommentCountContent");

		return mv;
	}



	/**
	 * Read page
	 */
	@RequestMapping(value = "", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView readRecommendProblemList(HttpServletRequest request, ModelAndView mv) {
		
		List<RecomTagDTO> tags = recomTagService.readProblemTag();
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSitebyYN();

		mv.addObject("codingSite", codingSite);
		mv.addObject("tags", tags);
		mv.setViewName("recommendProblem");

		return mv;
	}

	/**
	 * Read recommend list(problem & tag & comment & recommend), coding site
	 */
	@RequestMapping(value = "/readModalInfo", method = RequestMethod.POST)
	public ModelAndView readModalInfo(HttpServletRequest request, ModelAndView mv) {

		int recomID = Integer.parseInt(request.getParameter("recomID"));

		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		RecommendDTO recom = recommendService.readRecommend(recomID);
		List<RecomProblemDTO> recomProblem = recomProblemsService.readProblemByID(recomID);
		List<RecomTagDTO> recomProblemTag = recomTagService.readTagByID(recomID);
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		
		
		int commentCount = recomComment.size();
		RecomCountDTO rcd;
		int userID = -1;
		if((UserDTO)request.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)request.getSession().getAttribute("user")).getId();
			
			int admin = ((UserDTO)request.getSession().getAttribute("user")).getIsAdmin();
			
			mv.addObject("adminID", admin);
		}
		
		int cartYN = recomCartService.readCartByID(recomID, userID);

		rcd = recomCountService.readRecomCount(recomID, userID);
		rcd.setRecomID(recomID);

		for(int i=0;i<recomProblem.size();i++) {
			for(int j=0;j<codingSite.size();j++) {
				if(recomProblem.get(i).getSiteID() == codingSite.get(j).getId())
					recomProblem.get(i).setSiteName(codingSite.get(j).getSiteName());
			}
			
			String str = recomProblem.get(i).getLink();
			if(str.length() < 5 || !(str.substring(0, 5).equals("https"))) recomProblem.get(i).setLink(null);
		}
		
		int isAdmin = userService.readIsAdminByUserID(userID);
		
		mv.addObject("recomUser", recom.getUserID());
		mv.addObject("isAdmin", isAdmin);
		mv.addObject("cartYN", cartYN);
		mv.addObject("recomID", recomID);
		mv.addObject("loginID", userID);
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

	/**
	 * Read comment
	 */
	@RequestMapping(value = "/readComment", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView readComment(HttpServletRequest request, ModelAndView mv) {

		int recomID = Integer.parseInt(request.getParameter("recomID"));
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);

		int userID = ((UserDTO)request.getSession().getAttribute("user")).getId();
		
		mv.addObject("userid", userID);
		mv.addObject("recomID", recomID);
		mv.addObject("recomComment", recomComment);
		mv.setViewName("ajaxContent/recomCommentContent");

		return mv;
	}

	/**
	 * Update recommend zip
	 */
	@RequestMapping(value = "/updateRecomProblem", method = RequestMethod.POST)
	public ModelAndView updateRecomProblem(HttpServletRequest httpServletRequest, @RequestParam(value="siteId[]") List<String> siteId, @RequestParam(value="problem[]") List<String> problem, @RequestParam(value="link[]") List<String> link, @RequestParam(value="tag[]", required=false) List<String> tag) {

		List<RecomProblemDTO> recomProbs = new ArrayList<RecomProblemDTO>();
		RecommendDTO recom = new RecommendDTO();
		List<RecomTagDTO> recomTags = new ArrayList<RecomTagDTO>();

		recom.setId(Integer.parseInt(httpServletRequest.getParameter("recomID")));
		recom.setTitle(httpServletRequest.getParameter("title"));
		recom.setDifficulty(Integer.parseInt(httpServletRequest.getParameter("difficulty")));
		recom.setContent(httpServletRequest.getParameter("content"));

		recommendService.updateRecommend(recom);

		recomTagService.deleteRecomTag(Integer.parseInt(httpServletRequest.getParameter("recomID")));

		if(tag != null) {
			for(int i = 0; i < tag.size(); i++) {
				RecomTagDTO t = new RecomTagDTO();

				t.setRecomID(Integer.parseInt(httpServletRequest.getParameter("recomID")));
				t.setTag(tag.get(i));

				recomTags.add(t);
			}

			recomTagService.createTag(recomTags);
		}

		recomProblemsService.deleteRecomProblem(Integer.parseInt(httpServletRequest.getParameter("recomID")));

		for(int i = 0 ; i < siteId.size() ; i++) {
			RecomProblemDTO p = new RecomProblemDTO();

			p.setRecomID(Integer.parseInt(httpServletRequest.getParameter("recomID")));

			if(Integer.parseInt(siteId.get(i)) != 0)
				p.setSiteID(Integer.parseInt(siteId.get(i)));

			p.setName(problem.get(i));

			if(link.size() == 0) p.setLink(null);
			else p.setLink(link.get(i));

			recomProbs.add(p);
		}

		recomProblemsService.createRecomProblem(recomProbs);

		List<RecommendDTO> recoms = recommendService.readRecommendList();
		List<Map<Integer,Integer>> commentCount = recomCommentService.readCount();
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<RecomProblemDTO> recomProblem = recomProblemsService.readProblemList();
		List<RecomTagDTO> recomProblemTag = recomTagService.readProblemTag();

		for(int i = 0; i < recomProblem.size(); i++) {
			for(int j = 0; j < codingSite.size(); j++) {
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
	 * Delete recommend zip
	 */
	@RequestMapping(value = "/deleteRecomProblem", method = RequestMethod.POST)
	public ModelAndView deleteRecomProblem(HttpServletRequest httpServletRequest) {
		int recomID = Integer.parseInt(httpServletRequest.getParameter("id"));

		recommendService.deleteRecom(recomID);

		List<RecommendDTO> recoms = recommendService.readRecommendList();
		List<Map<Integer,Integer>> commentCount = recomCommentService.readCount();
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<RecomProblemDTO> recomProblem = recomProblemsService.readProblemList();
		List<RecomTagDTO> recomProblemTag = recomTagService.readProblemTag();

		for(int i = 0; i < recomProblem.size(); i++) {
			for(int j = 0; j < codingSite.size(); j++) {
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
	 * (미완성) Delete recommend ???
	 */
	@RequestMapping(value = "/deleteRecomCheck", method = RequestMethod.POST)
	public ModelAndView deleteRecomCheck(HttpServletRequest httpServletRequest) {
		int userID = -1;
		int rpID = Integer.parseInt(httpServletRequest.getParameter("rpID"));
		String problemName = httpServletRequest.getParameter("problemName");
		String link = httpServletRequest.getParameter("link");
		UserProblemDTO upd = new UserProblemDTO();
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
			upd.setProblemID(rpID);
			upd.setUserID(userID);
			userProblemService.deleteUserProblemByProblemID(rpID);
		}

		RecomProblemDTO rp = recomProblemsService.readEachProblem(rpID, userID);
		if(rp == null) {
			rp = new RecomProblemDTO();
			rp.setProblemID(rpID);
			rp.setName(problemName);
			rp.setLink(link);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("rp", rp);
		mv.setViewName("ajaxContent/recomCheckContent");

		return mv;
	}

	/**
	 * (미완성) Delete recommend
	 */
	@RequestMapping(value = "/deleteRecomCount", method = RequestMethod.POST)
	public ModelAndView deleteRecomCount(HttpServletRequest httpServletRequest) {
		RecomCountDTO rcd;
		int userID = -1;
		int recomID = Integer.parseInt(httpServletRequest.getParameter("recomID"));
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();

			rcd = new RecomCountDTO();
			rcd.setRecomID(recomID);
			rcd.setUserID(userID);
			recomCountService.deleteRecomCount(rcd);
		}
		rcd = recomCountService.readRecomCount(recomID, userID);
		List<Map<String,Object>> recomComment = recomCommentService.read(recomID);
		int commentCount = recomComment.size();

		ModelAndView mv = new ModelAndView();
		RecommendDTO recom = recommendService.readRecommend(recomID);
		int isAdmin = userService.readIsAdminByUserID(userID);

		mv.addObject("recom", recom);
		mv.addObject("isAdmin", isAdmin);
		mv.addObject("loginID", userID);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		mv.setViewName("ajaxContent/recomCommentCountContent");

		return mv;
	} 
	
	@RequestMapping(value = "/deleteRecomCart", method = RequestMethod.POST)
	public ModelAndView deleteRecomCart(HttpServletRequest httpServletRequest,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "searchValue", defaultValue = "") String searchValue,
			@RequestParam(value = "orderValue", defaultValue = "") String orderValue,
			@RequestParam(value = "tagValue", defaultValue = "") List<String> tagValue) {
		RecomCartDTO cart = new RecomCartDTO();
		
		int userID = -1;
		int recomID = Integer.parseInt(httpServletRequest.getParameter("recomID"));
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
			
			cart.setRecomID(recomID);
			cart.setUserID(userID);
			recomCartService.deleteRecomCart(cart);
			
		}
		
		ModelAndView mv = new ModelAndView();
		
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<String> tagData = new ArrayList<String>();
		
		if(!tagValue.get(0).equalsIgnoreCase("[]")) {
			tagValue.stream().forEach(tag -> {
				String [] tagSplit = tag.split("\"");
				tagData.add(tagSplit[1]);
			});
		}
		
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

		List<RecommendDTO> recoms = recommendService.readRecomByPage(searchValue, orderValue, tagData, s_point, list);
		
		mv.addObject("userID", userID);
		List<RecommendDTO> recomCart = recomCartService.readCartByRecommend(searchValue, orderValue, tagData, s_point, list, userID);
		
		mv.addObject("recoms", recomCart);
				
		mv.addObject("pagename", "recommendProblem");
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") == null) {
			mv.addObject("recoms", recoms);
		}
		mv.addObject("codingSite", codingSite);

		mv.setViewName("ajaxContent/recommendContent");

		return mv;
	}
	
	@RequestMapping(value = "/deleteRecomCartinModal", method = RequestMethod.POST)
	public ModelAndView deleteRecomCartinModal(HttpServletRequest httpServletRequest) {
		RecomCartDTO cart = new RecomCartDTO();
		
		int userID = -1;
		int recomID = Integer.parseInt(httpServletRequest.getParameter("recomID"));
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
		RecommendDTO recom = recommendService.readRecommend(recomID);
		int isAdmin = userService.readIsAdminByUserID(userID);
		
		ModelAndView mv = new ModelAndView();

		mv.addObject("recom", recom);
		mv.addObject("isAdmin", isAdmin);
		mv.addObject("loginID", userID);
		mv.addObject("cartYN", cartYN);
		mv.addObject("countInfo", rcd);
		mv.addObject("recomComment", recomComment);
		mv.addObject("commentCount", commentCount);
		mv.setViewName("ajaxContent/recomCommentCountContent");

		return mv;
	}



	/**
	 * Search
	 */
	@RequestMapping(value = "/search", method = {RequestMethod.POST})
	public ModelAndView searchProblem(HttpServletRequest httpServletRequest,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "searchValue", defaultValue = "") String searchValue,
			@RequestParam(value = "orderValue", defaultValue = "") String orderValue,
			@RequestParam(value = "tagValue", defaultValue = "") List<String> tagValue) {

		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<String> tagData = new ArrayList<String>();
		
		if(!tagValue.get(0).equalsIgnoreCase("[]")) {
			tagValue.stream().forEach(tag -> {
				String [] tagSplit = tag.split("\"");
				tagData.add(tagSplit[1]);
			});
		}
		
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

		List<RecommendDTO> recoms = recommendService.readRecomByPage(searchValue, orderValue, tagData, s_point, list);
		
		ModelAndView mv = new ModelAndView();
		
		int userID = -1;
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
			
			mv.addObject("userID", userID);
			List<RecommendDTO> recomCart = recomCartService.readCartByRecommend(searchValue, orderValue, tagData, s_point, list, userID);

			mv.addObject("recoms", recomCart);

		}
				
		mv.addObject("pagename", "recommendProblem");
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") == null) {
			mv.addObject("recoms", recoms);
		}
		mv.addObject("codingSite", codingSite);

		mv.setViewName("ajaxContent/recommendContent");

		return mv;
	}
	
	@RequestMapping(value = "/tagSearch", method = {RequestMethod.POST})
	public ModelAndView tagSearch(HttpServletRequest httpServletRequest,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "searchValue", defaultValue = "") String searchValue,
			@RequestParam(value = "orderValue", defaultValue = "") String orderValue,
			@RequestParam(value = "tagValue", defaultValue = "") List<String> tagValue,
			@RequestParam(value = "tagName") String tagName) {

		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		List<String> tagData = new ArrayList<String>();
		
		if(!tagValue.get(0).equalsIgnoreCase("[]")) {
			tagValue.stream().forEach(tag -> {
				String [] tagSplit = tag.split("\"");
				tagData.add(tagSplit[1]);
			});
		}
		
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

		List<RecommendDTO> recoms = recommendService.readRecomByPageTag(searchValue, orderValue, tagData, s_point, list, tagName);
		
		ModelAndView mv = new ModelAndView();
		
		int userID = -1;
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") != null) {
			userID = ((UserDTO)httpServletRequest.getSession().getAttribute("user")).getId();
			
			mv.addObject("userID", userID);
			List<RecommendDTO> recomCart = recomCartService.readCartByPageTag(searchValue, orderValue, tagData, s_point, list, userID, tagName);

			mv.addObject("recoms", recomCart);

		}
				
		mv.addObject("pagename", "recommendProblem");
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		
		if((UserDTO)httpServletRequest.getSession().getAttribute("user") == null) {
			mv.addObject("recoms", recoms);
		}
		mv.addObject("codingSite", codingSite);

		mv.setViewName("ajaxContent/recommendContent");

		return mv;
	}
}

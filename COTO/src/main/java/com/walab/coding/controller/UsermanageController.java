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

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.service.UserService;

/**
 * Handles requests for the application user manage page.
 */

@Controller
@RequestMapping(value= "/usermanage")
public class UsermanageController {
	
	@Autowired
	UserService userService;
	
	/**
	 * Read all users' information.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView usermanage(ModelAndView mv) {
		List<UserDTO> users = userService.read();
		
		mv.addObject("users", users);
		mv.setViewName("userManage");
		return mv;
	}
	
	/**
	 * Update user's admin permission.
	 */
	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
	@ResponseBody
	public void updateUserAdmin(HttpServletRequest httpServletRequest) {
		int isAdmin = Integer.parseInt(httpServletRequest.getParameter("isAdmin"));
		int userID = Integer.parseInt(httpServletRequest.getParameter("userID"));
		userService.updateUserAdmin(isAdmin, userID);
	}
	
	@RequestMapping(value = "/search", method = {RequestMethod.POST})
	public ModelAndView searchProblem(HttpServletRequest httpServletRequest,
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="searchValue", defaultValue="") String searchValue,
			@RequestParam(value="orderValue", defaultValue="") String orderValue) {
		
		// pagination
		int listCnt = userService.readUserListCnt();
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

		List<UserDTO> users = userService.readUserByPage(searchValue, orderValue, s_point, list);
		
		ModelAndView mv = new ModelAndView();
				
		mv.addObject("pagename", "userManage");
		mv.addObject("page", page);
		mv.addObject("s_page", s_page);
		mv.addObject("e_page", e_page);
		
		mv.addObject("users", users);

		mv.setViewName("ajaxContent/userManageContent");

		return mv;
	}
	
}

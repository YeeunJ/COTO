package com.walab.coding.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
}

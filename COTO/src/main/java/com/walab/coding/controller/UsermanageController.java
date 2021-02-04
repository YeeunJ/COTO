package com.walab.coding.controller;

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


@Controller
@RequestMapping(value= "/usermanage")
public class UsermanageController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView usermanage(ModelAndView mv) {
		List<UserDTO> users = userService.read();
		
		for(int i=0 ; i<users.size() ; i++) {
			System.out.println(users.get(i).toString());
		}
		
		mv.addObject("users", users);
		mv.setViewName("userManage");
		return mv;
	}
	
	
	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
	@ResponseBody
	public void updateUserAdmin(HttpServletRequest httpServletRequest, ModelAndView mv) {
		int isAdmin = Integer.parseInt(httpServletRequest.getParameter("isAdmin"));
		int userID = Integer.parseInt(httpServletRequest.getParameter("userID"));
		System.out.println(isAdmin+"//"+userID);
		userService.updateUserAdmin(isAdmin, userID);
	}
	
}

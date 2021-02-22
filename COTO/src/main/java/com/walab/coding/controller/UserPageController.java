package com.walab.coding.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.service.GoalService;
import com.walab.coding.service.UserProblemService;
import com.walab.coding.service.UserService;

@Controller
@RequestMapping(value = "/{nickName}")
public class UserPageController {

	@Autowired
	GoalService goalService;

	@Autowired
	UserProblemService userProblemService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView moveUserPAge(HttpServletRequest httpServletRequest, ModelAndView mv, @PathVariable("nickName") String nickName) throws UnsupportedEncodingException {

		int userID = userService.selectedUserTotalProblem(URLDecoder.decode(nickName,"UTF-8"));
		List<UserProblemDTO> readOtherUserPage = userProblemService.readOtherUserPage(userID);

		mv.addObject("readOtherUserPage", readOtherUserPage);
		mv.setViewName("userPage");
	
		return mv;
	}
}

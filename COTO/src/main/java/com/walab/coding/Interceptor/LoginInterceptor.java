package com.walab.coding.Interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import com.walab.coding.model.UserDTO;
import com.walab.coding.service.UserServiceImpl;


public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	UserServiceImpl UserService;
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//System.out.println("===================        login interceptor test        ===================");
		HttpSession session = request.getSession();
		UserDTO ud = (UserDTO) session.getAttribute("user");
		int id = 0;
		id = UserService.readUserIDByEmail(ud.getEmail());
		session.setAttribute("user", ud);
		//System.out.println(id);
		if(id > 0) {
			ud.setId(id);
			session.setAttribute("user", ud);
			modelAndView.setView(new RedirectView("/",true));
		}
		super.postHandle(request, response, handler, modelAndView);
	}

}

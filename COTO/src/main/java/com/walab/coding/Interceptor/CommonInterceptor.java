package com.walab.coding.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.walab.coding.model.UserDTO;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("===================        common interceptor test start       ===================");
		System.out.println(request.getRequestURI());
		if(request.getSession().getAttribute("user") == null) {
			System.out.println("notLogin");
			request.getSession().setAttribute("header", "logoutHeader");
			//request.setAttribute("header", "logoutHeader");
			//modelAndView.addObject("header", "logoutHeader");
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("user") == null) {
			System.out.println("notLogin");
			//request.getSession().setAttribute("header", "logoutHeader");
			//request.setAttribute("header", "logoutHeader");
			//modelAndView.addObject("header", "logoutHeader");
		}else if(((UserDTO)request.getSession().getAttribute("user")).getIsAdmin() > 0) {
			System.out.println("admin");
			modelAndView.addObject("header", "adminHeader");
		}else {
			System.out.println("user");
			modelAndView.addObject("header", "loginHeader");
		}
		System.out.println("===================        common interceptor test end        ===================");
		super.postHandle(request, response, handler, modelAndView);
	}
	
}

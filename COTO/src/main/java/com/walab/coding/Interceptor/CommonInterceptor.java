package com.walab.coding.Interceptor;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.service.CodingSiteService;

@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	CodingSiteService codingSiteService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getSession().getAttribute("user") == null) {
			if(request.getRequestURI().contains("mypage") || request.getRequestURI().contains("manageCodingsite") || request.getRequestURI().contains("usermanage")) {
				response.sendRedirect(request.getContextPath() + "/");
		        return false;
			}

		}else {
			if(((UserDTO)request.getSession().getAttribute("user")).getIsAdmin() <= 0) {
				if(request.getRequestURI().contains("manageCodingsite") || request.getRequestURI().contains("usermanage")) {
					response.sendRedirect(request.getContextPath() + "/");
			        return false;
				}
			}
			request.setAttribute("userID", ((UserDTO)request.getSession().getAttribute("user")).getId());
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(request.getSession().getAttribute("user") == null) {
			request.setAttribute("header", "logoutHeader.jsp");
		}else if(((UserDTO)request.getSession().getAttribute("user")).getIsAdmin() > 0) {
			request.setAttribute("header", "adminHeader.jsp");
		}else {
			request.setAttribute("header", "loginHeader.jsp");
		}
		
		List<CodingSiteDTO> codingSite = codingSiteService.readCodingSite();
		request.setAttribute("codingSiteFooter", codingSite);
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
}

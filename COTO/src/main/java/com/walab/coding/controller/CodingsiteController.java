package com.walab.coding.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.service.CodingSiteService;
import com.walab.coding.service.CodingSiteServiceImpl;

/**
 * Handles requests for the application manageCodingSite page.
 */


@Controller
@RequestMapping(value = "/manageCodingsite")
public class CodingsiteController {
	
	@Autowired
	CodingSiteService codingSiteService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView CodingSiteList(ModelAndView mv) {
		mv.addObject("CodingSite", codingSiteService.read());
		mv.setViewName("manageCodingsite");
		return mv;
	}
	
	@RequestMapping(value="/addok", method = RequestMethod.POST)
	public String addCodingSiteOK(CodingSiteDTO dto) {
		System.out.println("in addok");
		if(codingSiteService.insertCodingSite(dto)==0)
				System.out.println("데이터 추가 실패");
		else
			System.out.println("데이터 추가 성공!!");
		
		return "redirect:../manageCodingsite";
		
	}

	@RequestMapping(value="/editok", method = RequestMethod.POST)
	public String editPostOK(HttpServletRequest request) {
		
		String siteName = request.getParameter("siteName");
		String siteUrl = request.getParameter("siteUrl");
		int id = Integer.parseInt(request.getParameter("id"));
		
		CodingSiteDTO dto = new CodingSiteDTO();
		dto.setId(id);
		dto.setSiteName(siteName);
		dto.setSiteUrl(siteUrl);
		
		if(codingSiteService.updateCodingSite(dto)==0)
				System.out.println("데이터 수정 실패");
		else
			System.out.println("데이터 수정 성공!!");
		return "redirect:../manageCodingsite";
		
	}
	@RequestMapping(value="/deleteok/{id}", method = RequestMethod.GET)
	public String deletePostOK(@PathVariable("id") int id, HttpServletRequest request) {
		if(codingSiteService.deleteCodingSite(id)==0)
				System.out.println("데이터 삭제 실패");
		else
			System.out.println("데이터 삭제 성공!!");
		
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	
	@RequestMapping(value = "/sitelist", method = RequestMethod.GET,produces = "application/text; charset=utf8")
	@ResponseBody
	public String home2(Locale locale, Model model) {
		ArrayList<String> data = new ArrayList<String>();
		List<CodingSiteDTO> codingSite = codingSiteService.read();
		
		String html="<li>"+" | ";
		for(CodingSiteDTO i : codingSite) {
			html += "<a href='" + i.getSiteUrl() + "'>"+i.getSiteName()+"</a>"+"   |   ";
		}
		html+="</li>";
		System.out.println(html);
		return html;
	}
}

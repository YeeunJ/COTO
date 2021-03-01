package com.walab.coding.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.service.CodingSiteService;

@Controller
@RequestMapping(value = "/manageCodingsite")
public class CodingsiteController {

	@Autowired
	CodingSiteService codingSiteService;

	@RequestMapping(value = "/addok", method = RequestMethod.POST)
	public String addCodingSiteOK(CodingSiteDTO dto) {

		codingSiteService.insertCodingSite(dto);
		return "redirect:../manageCodingsite";

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView CodingSiteList(ModelAndView mv) {
		mv.addObject("CodingSite", codingSiteService.readCodingSite());
		mv.setViewName("manageCodingsite");
		return mv;
	}
	@RequestMapping(value = "/editok", method = RequestMethod.POST)
	public String editPostOK(HttpServletRequest request) {

		String siteName = request.getParameter("siteName");
		String siteUrl = request.getParameter("siteUrl");
		int id = Integer.parseInt(request.getParameter("id"));

		CodingSiteDTO dto = new CodingSiteDTO();
		dto.setId(id);
		dto.setSiteName(siteName);
		dto.setSiteUrl(siteUrl);
		codingSiteService.updateCodingSite(dto);

		return "redirect:../manageCodingsite";

	}

	@RequestMapping(value = "/deleteok/{id}", method = RequestMethod.GET)
	public String deletePostOK(@PathVariable("id") int id, HttpServletRequest request) {

		codingSiteService.deleteCodingSite(id);

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

}

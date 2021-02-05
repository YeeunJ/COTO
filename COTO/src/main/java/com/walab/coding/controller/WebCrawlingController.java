package com.walab.coding.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.walab.coding.model.ProblemDTO;
import com.walab.coding.service.WebCrawlingService;

@Controller
@RequestMapping("/crawling")
public class WebCrawlingController {
	
	@Autowired
	WebCrawlingService webCrawler;
	
	@RequestMapping(value = "/baekjoon", method = RequestMethod.POST)
	public ModelAndView crawlingBaekjoon(HttpServletRequest httpServletRequest, ModelAndView mv, @RequestParam(value="problem[]") List<String> problem, @RequestParam(value="siteID") String siteID) {
		
		for(String prob: problem) {
			System.out.println(prob);
			
		}
		List<ProblemDTO> problemInfo = webCrawler.crawlingBaekjoonByName(problem, Integer.parseInt(siteID));
		mv.addObject("problemInfo", problemInfo);
		mv.setViewName("webCrawling/baekjoonCrawlingProblem");
		return mv;
	}
}

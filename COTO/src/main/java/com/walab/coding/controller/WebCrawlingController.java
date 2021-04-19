package com.walab.coding.controller;

import java.util.ArrayList;
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
	public ModelAndView crawlingBaekjoon(HttpServletRequest httpServletRequest, ModelAndView mv, @RequestParam(value="problem[]") List<String> problem, @RequestParam(value="siteID") String siteID, @RequestParam(value="count") String count) {
		
		List<ProblemDTO> problemInfo = webCrawler.crawlingBaekjoonByName(problem, Integer.parseInt(siteID));
		/*
		List<String> dataSet = new ArrayList<String>();
		for(ProblemDTO prob: problemInfo) {
			System.out.println(prob.getName());
			dataSet.add("<i class=\"small smaller material-icons\" style=\"color:green;\">done</i><input disabled name=\""+prob.getSiteID()+"\" value=\""+prob.getName()+" ("+prob.getSiteName()+") - "+prob.getLink()+"\" id=\"last_name disabled\" type=\"text\" class=\"problem validate\" style=\"width: 95%;\"/>");
		}
		*/
		mv.addObject("problemInfo", problemInfo);
		mv.addObject("count", count);
		mv.setViewName("webCrawling/baekjoonCrawlingProblem");
		return mv;
		//return dataSet;
	}
	
	@RequestMapping(value = "/leetcode", method = RequestMethod.POST)
	public ModelAndView crawlingLeetcode(HttpServletRequest httpServletRequest, ModelAndView mv, @RequestParam(value="problem[]") List<String> problem, @RequestParam(value="siteID") String siteID, @RequestParam(value="count") String count) {
		
		List<ProblemDTO> problemInfo = webCrawler.crawlingLeetcodeByName(problem, Integer.parseInt(siteID));
		/*
		List<String> dataSet = new ArrayList<String>();
		for(ProblemDTO prob: problemInfo) {
			System.out.println(prob.getName());
			dataSet.add("<i class=\"small smaller material-icons\" style=\"color:green;\">done</i><input disabled name=\""+prob.getSiteID()+"\" value=\""+prob.getName()+" ("+prob.getSiteName()+") - "+prob.getLink()+"\" id=\"last_name disabled\" type=\"text\" class=\"problem validate\" style=\"width: 95%;\"/>");
		}
		*/
		mv.addObject("problemInfo", problemInfo);
		mv.addObject("count", count);
		mv.setViewName("webCrawling/leetcodeCrawlingProblem");
		
		return mv;
		//return dataSet;
	}
	@RequestMapping(value = "/leetcode_database", method = RequestMethod.POST)
	public ModelAndView crawlingLeecodeDatabase(HttpServletRequest httpServletRequest, ModelAndView mv, @RequestParam(value="problem[]") List<String> problem, @RequestParam(value="siteID") String siteID, @RequestParam(value="count") String count) {
		
		List<ProblemDTO> problemInfo = webCrawler.crawlingLeetcodeByName(problem, Integer.parseInt(siteID));
		/*
		List<String> dataSet = new ArrayList<String>();
		for(ProblemDTO prob: problemInfo) {
			System.out.println(prob.getName());
			dataSet.add("<i class=\"small smaller material-icons\" style=\"color:green;\">done</i><input disabled name=\""+prob.getSiteID()+"\" value=\""+prob.getName()+" ("+prob.getSiteName()+") - "+prob.getLink()+"\" id=\"last_name disabled\" type=\"text\" class=\"problem validate\" style=\"width: 95%;\"/>");
		}
		*/
		mv.addObject("problemInfo", problemInfo);
		mv.addObject("count", count);
		mv.setViewName("webCrawling/baekjoonCrawlingProblem");
		
		return mv;
		//return dataSet;
	}
}
